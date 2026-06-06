package com.servlet.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PinCredentialVerificationServlet
 */
@WebServlet("/pincredentialverificationpage")
public class PinCredentialVerificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
//    public PinCredentialVerificationServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter pw = response.getWriter();
		pw.println("<b>pincredentialverificationpage</b>");

		String category = "transfermoney";
//		String category = (String) request.getSession().getAttribute("category");
		// String acno = request.getParameter("accountno");//
		Long accountno1 = (long) 0;
		Long accountno2 = (long) 0;

		switch (category) {

		case "checkbalance": // pw.println("switch block");
			accountno2 = (Long)request.getSession().getAttribute("accountno");// sender
			break;
		case "transfermoney"://attribute will be an object but parameter will be a String
			accountno1 = Long.parseLong(request.getParameter("accountno"));// receiver
			accountno2 = Long.parseLong(request.getParameter("accountno1"));//sender from url
//			accountno2 = (Long)request.getSession().getAttribute("accountno");// sender from session
			break;
		case "transactionbyadmin":
			pw.println("transaction by admin");
			accountno1 = Long.parseLong(request.getParameter("toaccountno"));// to account
			accountno2 = Long.parseLong(request.getParameter("fromaccountno"));// from account
			request.getSession().setAttribute("fromaccountno", accountno2);
			break;
		}
		// pw.println("<b>"+accountno1+"</b>,br>");

		// pw.println("<b>"+accountno1+"</b>,br>");
		// Long accountno = Long.parseLong(acno);
		String password = request.getParameter("password");// entered password
		// pw.println("entered password : "+password);
		
		RingBufferOperator ringbufferoperator = RingBufferOperator.getInstance();
		String transactionid = accountno2.toString().substring(12) + String.valueOf(System.currentTimeMillis()).substring(0,8) + accountno1.toString().substring(12);
		//TransactionTaskPOJO transactiontask = new TransactionTaskPOJO(accountno2, accountno1, password, Long.parseLong(request.getParameter("amount")), Long.parseLong(transactionid));
		if(ringbufferoperator != null) {
			//System.out.println("Transaction task object created successfully");
//			System.out.println("Created a transaction task object and adding it to the transaction queue");
			//from + to + password + amount + status
			//ringbufferoperator.addJournalLog(Long.parseLong(transactionid), new String[] {accountno2.toString(), accountno1.toString(), password, request.getParameter("amount")}, "pending");
			
			try {
				ringbufferoperator.addTransactionTask(accountno2, accountno1, password, Long.parseLong(request.getParameter("amount")), Long.parseLong(transactionid));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		try (Connection conn = new DBCP().getConnection()) {
//			/* sender */ PreparedStatement pstmt = conn.prepareStatement(
//					"select AccountNo,Password,AccountBalance from customerdetails where AccountNo = ?");
//			pstmt.setLong(1, accountno2);// from account
//			ResultSet rs = pstmt.executeQuery();
//			pw.println("from account no : " + accountno2);
//			if (rs.next()) {
//				//pw.println("rs.next()");
//
//				if (category == "checkbalance") {
//					// if(password.equalsIgnoreCase(rs.getString("Password"))) {
//					// pw.println("<b>Balance Checking Page</b><br> Current Balance =
//					// "+rs.getLong("AccountBalance"));
//					if (accountno2 == Long.parseLong(request.getParameter("accountno"))) {//only if he tries to check his acount balanace
//						if (rs.getString("Password").equalsIgnoreCase(password)) {
//							request.getSession().setAttribute("pagename", "checkbalance");
//							request.getSession().setAttribute("currentbalance", rs.getLong("AccountBalance"));
//							rs.close();
//							conn.close();
//							request.getSession().setAttribute("accountno", accountno2);
//							response.sendRedirect("thankyoupage");
//						} else {
//							pw.println("Incorrect password !");
//						}
//					} else {
//						pw.println("Incorrect account no entered !");
//					}
//
//				} else if (category == "transfermoney" || category == "transactionbyadmin") {
//					String p = "";
//					if (category == "transactionbyadmin") {
//						pw.println("category = transactionbyadmin");
//						PreparedStatement admin = conn
//								.prepareStatement("select Password from adminlogin where EmpId = ?");
//						pw.println((String) request.getSession().getAttribute("adminid"));
//						admin.setString(1, (String) request.getSession().getAttribute("adminid"));
//						ResultSet adminpassword = admin.executeQuery();
//						adminpassword.next();
//						request.getSession().setAttribute("pagename", "transactionbyadmin");
//						p = adminpassword.getString("Password");
//						pw.println("p=" + p);
//					} else {
//						if (rs.getString("Password").equalsIgnoreCase(password)) {
//						    p = rs.getString("Password");// original password
//						    request.getSession().setAttribute("pagename", "transfermoney");
//						}
//					}
//
//					//pw.println("real password : " + p);
//					//pw.println("password.equalsIgnoreCase(p) : " + password.equalsIgnoreCase(p));
//					if (password.equalsIgnoreCase(p)) {
//						/* receiver */ PreparedStatement preparedstatement = conn.prepareStatement(
//								"select AccountNo,AccountBalance from customerdetails where AccountNo = ?");// we dont
//																											// need
//																											// password
//																											// to
//																											// receive
//																											// money
//																											// from the
//																											// sender
//						// request.getSession().setAttribute("pagename", "transactionbyadmin");
//						preparedstatement.setLong(1, accountno1);// to account
//						// preparedstatement.setString(2,password);//from account
//						ResultSet receiver = preparedstatement.executeQuery();
//						if (receiver.next()) {
//
//							Long amount = Long.parseLong(request.getParameter("amount"));// amt);
//							Long smoney = rs.getLong("AccountBalance");
//
//							if (rs.getLong("AccountBalance") < amount) {
//								request.getSession().setAttribute("pagename", "insufficientbalance");
//								request.getSession().setAttribute("currentbalance", rs.getLong("AccountBalance"));
//								rs.close();
//								conn.close();
//								response.sendRedirect("thankyoupage");
//							} else {
//								PreparedStatement transfermoney = conn.prepareStatement(
//										"Update customerdetails set AccountBalance = ? where AccountNo = ?");
//								PreparedStatement receivemoney = conn.prepareStatement(
//										"Update customerdetails set AccountBalance = ? where AccountNo = ?");
//								PreparedStatement transactionhistory = conn.prepareStatement(
//										"insert into transactionhistory(TransactionType,FromAccountNo,ToAccountNo,Amount,Date) values(?,?,?,?,now())");// current_date()
//								// String amt = request.getParameter("amount");
//								pw.println("password == p");
//								smoney -= amount;// sender money
//								Long rmoney = receiver.getLong("AccountBalance") + amount;
//								// pw.println(rmoney);
//								// if(rs.getString("Password").equalsIgnoreCase(p))
//								transfermoney.setLong(1, smoney);
//								transfermoney.setLong(2, accountno2);
//								transfermoney.executeUpdate();
//								receivemoney.setLong(1, rmoney);
//								receivemoney.setLong(2, accountno1);
//								receivemoney.executeUpdate();
//								transactionhistory.setString(1, "Transfers");
//								transactionhistory.setLong(2, accountno2);
//								transactionhistory.setLong(3, accountno1);
//								transactionhistory.setLong(4, amount);
//								transactionhistory.executeUpdate();
//								conn.close();
//								response.sendRedirect("thankyoupage");
//							}
//
//						} else {
//							pw.println("Receiver account no not found");
//						}
//
//						// pw.println(smoney);
//						// pw.println("MONEY TRANSFERRED SUCCESSFULLY");
//						// response.sendRedirect("transfermoneypage");
//					} else {
//						pw.println("IncorrectPasswordEntered");
//					}
//
//				}
//			} else {
//				pw.println("Incorrect fromaccountno ");
//			}
//		} catch (SQLException sqle) {
//			pw.println(sqle.getMessage());
//			sqle.printStackTrace();
//		} catch (Exception e) {
//			pw.println(e.getMessage());
//			e.printStackTrace();
//		}
		
		pw.close();
		
		//System.out.println("pin credential verification page");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
