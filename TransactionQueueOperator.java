package com.servlet.bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransactionQueueOperator {//this is just a wrapper class for the transaction queue which is acting as a buffer between the producer and consumer class
	//And thus it is a shared resource acting a bridge between the producer and consumer class and it is a singleton class
	private final LinkedBlockingQueue<TransactionTaskPOJO> transactionqueue = new LinkedBlockingQueue<>();
	private final File f = new File("C:\\Users\\PRANEETH\\eclipse-workspace\\TSRBank.zip_expanded\\TSRBank\\src\\Resources\\livejournal.txt");
	private final FileReader fr = new FileReader(f);
	private final BufferedReader br = new BufferedReader(fr);
	private final BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
	private final Pattern tid = Pattern.compile("\\d+\\s\\-");
	private final Pattern transactiondetailspattern = Pattern.compile("\\- *");
	private final HashMap<String, String[]> transactionlogmap = new HashMap<String, String[]>();//to store the transaction logs from the journal file in memory for quick access and to avoid reading the journal file multiple times for the same transaction log
	
	
	
	public void addTransactionTask(TransactionTaskPOJO transactiontask) {
		transactionqueue.offer(transactiontask);
		System.out.println("Transaction added");
	}
	
	public TransactionTaskPOJO takeTransactionTask() throws Exception{
		return transactionqueue.take(); 
	}
	
	
	private static final TransactionQueueOperator transactionqueuesingletoninstance = createInstance();//we should use an explicit method to declare the instance, because we need to handle the I/O exception in try block as we are using a LinkedBlockingQueue in this class 

	private static TransactionQueueOperator createInstance() {
			TransactionQueueOperator t = null;
			try {
				t = new TransactionQueueOperator();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return t;
	}
	
	private TransactionQueueOperator() throws IOException {
		System.out.println("TransactionQueueOperator constructor called-successfully created a transaction queue operator object");
	}
	
	public void addJournalLog(Long transactionid, String[] transactiondetails, String status) throws IOException {
		//to add a log of the transaction task in the journal file
		bw.newLine();
		StringBuilder sb = new StringBuilder();
		sb.append(transactionid).append(" - ");
		if(transactiondetails != null && status.equalsIgnoreCase("pending")) {
			for(String detail : transactiondetails) {
				sb.append(detail).append(",");
			}
			sb.append(status);
		}else if(transactiondetails == null && !status.equalsIgnoreCase("pending")){
			sb.append("null");
			sb.append(status);
		}
		bw.write(sb.toString());
		bw.flush();
		System.out.println("Transaction log added to journal");
	}
	
	@SuppressWarnings("finally")
	public TransactionTaskPOJO[] checkForPendingTransactionsInJournal() {
		
		
		try {
			String nextline = null;
			StringBuilder transaction = new StringBuilder(br.readLine());
			Matcher tidmatch = null;
			String transactionid = null;
			Matcher transactiondetailmatch = null;
			String transactiondetails[] = null;
			
			System.out.println("Transaction id : " + transactionid);
			System.out.println("Transaction details : " + transactiondetails);
			System.out.println("Transaction : "+transaction);
			System.out.println("Transaction empty : "+transaction.isEmpty());
			System.out.println("Transaction null : " + (transaction!=null));
			while(transaction != null && !transaction.isEmpty()) {//if we find a transaction twice from the journal, then we can remove it from the HashSet immediately 
				System.out.println("while");
				tidmatch = tid.matcher(transaction);
				transactionid = tidmatch.find() ? tidmatch.group().substring(0, 16) : null;//we limited the transaction id to 16 digits
				System.out.println("tid"+transactionid);
				transactiondetailmatch = transactiondetailspattern.matcher(transaction);
				transactiondetails = transactiondetailmatch.find() ? transaction.substring(19).split(",") : null;
				
				System.out.println("Transaction ID : "+transactionid);
				System.out.println("Transaction details : "+transactiondetails[0]);
				
				if(transactionid!=null && !transactionid.isEmpty()) {
					if(transactionlogmap.containsKey(transactionid)) {//if we find a transaction log with the same transaction id, then we can remove it from the HashMap immediately as it is already processed and added to the transaction queue by the producer class
						transactionlogmap.remove(transactionid);
					}else {
						transactionlogmap.put(transactionid, transactiondetails);
					}
				}
				System.out.println(transaction.substring(19));
				nextline = br.readLine();
				transaction.replace(0, transaction.length(), nextline==null?"":nextline);//to clear the string builder and read the next line from the journal
			}
			
		}catch(NullPointerException npe) {
			System.out.println(npe.getMessage());
			System.out.println("There are no pending transactions in the journal");
		}finally {
			System.out.println("finally block");
			if(transactionlogmap.size()>0) {
				TransactionTaskPOJO[] pendingtransactiontasks = new TransactionTaskPOJO[transactionlogmap.size()];
				int i = 0;
				System.out.println("Transaction hashmap keyset : " + transactionlogmap.keySet());
				for(String transactionid : transactionlogmap.keySet()) {
					String[] transactiondetails = transactionlogmap.get(transactionid);
					pendingtransactiontasks[i++] = new TransactionTaskPOJO(Long.parseLong(transactiondetails[0]), Long.parseLong(transactiondetails[1]), transactiondetails[2], Long.parseLong(transactiondetails[3]), Long.parseLong(transactionid));
				}
				return pendingtransactiontasks;
			}else {
				return null;
			}
	   }
	}
	
	
	public static TransactionQueueOperator getInstance() {
		return transactionqueuesingletoninstance;
	}

}
