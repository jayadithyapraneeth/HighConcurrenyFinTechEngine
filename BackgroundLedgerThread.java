package com.servlet.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

@jakarta.servlet.annotation.WebListener
public class BackgroundLedgerThread implements ServletContextListener {// it is a consumer class

	// keep a reference to the worker so we can stop it on shutdown
	private volatile WorkerThread workerThread;

	private class WorkerThread extends Thread {

		private final RingBufferOperator ringbufferoperator = RingBufferOperator.getInstance();

		@Override
		public void run() {
			try (Connection conn = HikariCP.getConnection();
					PreparedStatement pstmt1 = conn.prepareStatement(
					"SELECT c1.Password AS SenderPassword, c1.AccountBalance AS SenderBalance, "
							+ "c2.AccountNo AS ReceiverAccountNo FROM customerdetails c1, customerdetails c2 "
							+ "WHERE c1.AccountNo = ? AND c2.AccountNo = ?");
					PreparedStatement batchStmt1 = conn.prepareStatement("UPDATE customerdetails SET AccountBalance = AccountBalance - ? WHERE AccountNo = ?;");
					PreparedStatement batchStmt2 = conn.prepareStatement("INSERT INTO transactionhistory(TransactionType, FromAccountNo, ToAccountNo, Amount, Date) VALUES('TransferMoney', ?, ?, ?, NOW())")) {
				conn.setAutoCommit(false);
				long lastFlushTime = System.currentTimeMillis();
//				int batchSize = 100; // Adjust batch size as needed
				int batchSize = 500; // Adjust batch size as needed
				int timeoutMs = 10000; // Flush batch every 10 milli seconds if not full
				int batchCount = 0;
				long currentTime = 0;
				long transactioncount = 0;
				
				System.out.println("Background Ledger WorkerThread started and waiting for transaction tasks...");

				while (!Thread.currentThread().isInterrupted()) {
					currentTime = System.currentTimeMillis();
					TransactionTaskPOJO1 transactionTask = ringbufferoperator.takeTransactionTask(10000); // Wait for up to 10 milli seconds

					if (transactionTask != null) {
						pstmt1.setLong(1, transactionTask.getFromAccountNumber());
						pstmt1.setLong(2, transactionTask.getToAccountNumber());

						try (ResultSet rs1 = pstmt1.executeQuery()) {
							if (rs1.next()) {
								String senderPassword = rs1.getString("SenderPassword");
								long senderBalance = rs1.getLong("SenderBalance");
								boolean receiverExists = rs1.getLong("ReceiverAccountNo") > 0;

								if (transactionTask.getPassword().equals(senderPassword)) {
									if (receiverExists) {
										if (senderBalance >= transactionTask.getAmount()) {
											batchStmt1.clearParameters();
											batchStmt2.clearParameters();

											batchStmt1.setLong(1, transactionTask.getAmount());
											batchStmt1.setLong(2, transactionTask.getFromAccountNumber());
											batchStmt1.addBatch();

											batchStmt1.setLong(1, transactionTask.getAmount());
											batchStmt1.setLong(2, transactionTask.getToAccountNumber());
											batchStmt1.addBatch();

											batchStmt2.setLong(1, transactionTask.getFromAccountNumber());
											batchStmt2.setLong(2, transactionTask.getToAccountNumber());
											batchStmt2.setLong(3, transactionTask.getAmount());
											batchStmt2.addBatch();

											transactioncount++;
											batchCount++;
										} else {
											System.out.println("Insufficient balance for account: " + transactionTask.getFromAccountNumber());
										}
									} else {
										System.out.println("Invalid receiver account: " + transactionTask.getToAccountNumber());
									}
								} else {
									System.out.println("Invalid password for account: " + transactionTask.getFromAccountNumber());
								}
							} else {
								System.out.println("Sender or receiver account not found.");
							}
						}
					}

					// Check timeout or batch size condition
					if (batchCount > 0 && (batchCount >= batchSize || (currentTime - lastFlushTime) > timeoutMs)) {
						batchStmt1.executeBatch();
						batchStmt2.executeBatch();
						conn.commit();
						System.out.println("Flushed batch of transactions to database. Batch size: " + batchCount+ "transaction count: "+transactioncount);// + "elapsed time: " + (currentTime - lastFlushTime) + "ms");
						batchCount = 0;
						lastFlushTime = currentTime;
					}

					// Ensure timeout is enforced even when no tasks are retrieved
					if (transactionTask == null && (currentTime - lastFlushTime) > timeoutMs) {//task will be null only after timeout
						if (batchCount > 0) {
							batchStmt1.executeBatch();
							batchStmt2.executeBatch();
							conn.commit();
							System.out.println("Flushed batch of transactions to database (timeout reached). Batch size: " + batchCount + "transaction count: "+transactioncount);//+"elapsed time: " + (currentTime - lastFlushTime) + "ms");
							batchCount = 0;
							lastFlushTime = currentTime;
						}
					}
				}
			} catch (SQLException e) {
				System.out.println("SQLException in WorkerThread: " + e.getMessage());
				e.printStackTrace();
			} finally {
				System.out.println("WorkerThread shutting down.");
			}
		}
	}

	@SuppressWarnings("unused")
	@Override
	public void contextInitialized(ServletContextEvent sce) {

		RingBufferOperator tqo = RingBufferOperator.getInstance();//for money transfer taks only
		TransactionTaskPOJO1[] pendingtransactiontask = null;
		// pendingtransactiontask = tqo.checkForPendingTransactionsInJournal();

		if (pendingtransactiontask != null) {
			for (TransactionTaskPOJO1 transactiontask : pendingtransactiontask) {
				try {

					tqo.addTransactionTask(transactiontask.getToAccountNumber(), transactiontask.getFromAccountNumber(),
							transactiontask.getPassword(), transactiontask.getAmount(), transactiontask.getTransactionID());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else if (pendingtransactiontask == null) {
			System.out.println("There are no pending tasks");
		}

		System.out.println("Background Ledger Class");
		this.workerThread = new WorkerThread();
		this.workerThread.setDaemon(true);
		this.workerThread.start();
		// optionally store reference in servlet context for safety
		sce.getServletContext().setAttribute("backgroundWorker", this.workerThread);

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Stopping Background Ledger Thread");
		WorkerThread t = this.workerThread;
		if (t != null && t.isAlive()) {
			// interrupt and wait for a short period for graceful shutdown
			t.interrupt();
			try {
				t.join(5000); // wait up to 5 seconds
			} catch (InterruptedException e) {
				// restore interrupt status
				Thread.currentThread().interrupt();
			}
		}
		// remove context attribute if present
		sce.getServletContext().removeAttribute("backgroundWorker");
	}

	// private static void processTransactionTask(long fromaccountno, long toaccountno,
	// long amount) throws IOException {
	// Connection conn = null;
	// RingBufferOperator ringbufferoperator = RingBufferOperator.getInstance();
	//
	// try {
	// conn = new DBCP().getConnection();
	// conn.setAutoCommit(false);
	//
	// // Combine password check and receiver account validation into one query
	// PreparedStatement pstmt1 = conn.prepareStatement(
	// "select c1.Password as SenderPassword, c1.AccountBalance as SenderBalance, c2.AccountNo as ReceiverAccountNo from customerdetails c1, customerdetails c2 where c1.AccountNo = ? and c2.AccountNo = ?;"
	// );
	//
	// pstmt1.setLong(1, transactiontask.getFromAccountNumber()); // Sender account
	// pstmt1.setLong(2, transactiontask.getToAccountNumber()); // Receiver account
	// ResultSet rs1 = pstmt1.executeQuery();
	//
	// if (rs1.next()) {
	// String senderpassword = rs1.getString("SenderPassword");
	// long senderbalance = rs1.getLong("SenderBalance");
	// boolean receiverexists = rs1.getLong("ReceiverAccount") > 0;
	//
	// if (transactiontask.getPassword().equals(senderpassword)) { // Password is correct
	// if (receiverexists) { // Receiver account exists
	// if (senderbalance >= transactiontask.getAmount()) { // Sufficient balance
	//
	// PreparedStatement pstmt = null;
	//
	// pstmt.addBatch("UPDATE customerdetails SET AccountBalance = AccountBalance - "+transactiontask.getAmount()+" WHERE AccountNo = "+transactiontask.getFromAccountNumber());
	//
	// // Add debit, credit, and transaction history to batch
	//// PreparedStatement pstmt2 = conn.prepareStatement("UPDATE customerdetails SET AccountBalance = AccountBalance - ? WHERE AccountNo = ?");
	//// pstmt2.setLong(1, transactiontask.getAmount());
	//// pstmt2.setLong(2, transactiontask.getFromAccountNumber());
	//// pstmt2.addBatch();
	//
	//
	// pstmt.addBatch("UPDATE customerdetails SET AccountBalance = AccountBalance + "+transactiontask.getAmount()+" WHERE AccountNo = "+transactiontask.getToAccountNumber());
	// //PreparedStatement pstmt3 = conn.prepareStatement("UPDATE customerdetails SET AccountBalance = AccountBalance + ? WHERE AccountNo = ?");
	//// pstmt2.setLong(1, transactiontask.getAmount());
	//// pstmt2.setLong(2, transactiontask.getToAccountNumber());
	//// pstmt2.addBatch();
	//
	//
	// pstmt.addBatch("insert INTO transactionhistory(TransactionType, FromAccountNo, ToAccountNo, Amount, Date) VALUES(TransferMoney, "+transactiontask.getFromAccountNumber()+", "+transactiontask.getToAccountNumber()+", "+transactiontask.getAmount()+", NOW())");
	//
	//// PreparedStatement pstmt3 = conn.prepareStatement("INSERT INTO transactionhistory(TransactionType, FromAccountNo, ToAccountNo, Amount, Date) VALUES(?, ?, ?, ?, NOW())");
	//// pstmt3.setString(1, "TransferMoney");
	//// pstmt3.setLong(2, transactiontask.getFromAccountNumber());
	//// pstmt3.setLong(3, transactiontask.getToAccountNumber());
	//// pstmt3.setLong(4, transactiontask.getAmount());
	//// pstmt3.addBatch();
	//
	// // Execute batch
	//// pstmt2.executeBatch();
	//// pstmt3.executeBatch();
	//// pstmt3.executeBatch();
	//
	//
	// pstmt.addBatch("");
	//
	// pstmt.executeBatch();
	//
	// conn.commit();
	// System.out.println("Transaction processed successfully" + count);
	// count++;
	// } else {
	// System.out.println("Insufficient balance in sender account with account number: " + transactiontask.getFromAccountNumber() + " in background ledger thread");
	// }
	// } else {
	// System.out.println("Invalid receiver account number: " + transactiontask.getToAccountNumber() + " in background ledger thread");
	// }
	// } else {
	// System.out.println("Invalid password for sender with account number: " + transactiontask.getFromAccountNumber() + " in background ledger thread");
	// }
	// } else {
	// System.out.println("Sender account not found or invalid receiver account in background ledger thread");
	// }
	//
	// } catch (SQLException sqle) {
	// System.out.println("SQLException in background ledger thread: " + sqle.getMessage());
	// try {
	// if (conn != null) {
	// conn.rollback();
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// } finally {
	// if (conn != null) {
	// try {
	// conn.close();
	// } catch (SQLException sqle) {
	// System.out.println("SQLException while closing connection in background ledger thread: " + sqle.getMessage());
	// }
	// }
	// }

}