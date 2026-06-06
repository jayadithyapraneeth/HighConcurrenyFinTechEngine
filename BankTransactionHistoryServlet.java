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
 * Servlet implementation class SearchCustomerServlet
 */
@WebServlet("/banktransactionhistorypage")
public class BankTransactionHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		try (Connection conn = new DBCP().getConnection()) {
			// String existingcustomerdetails = "select * from customerdetails";
			// PreparedStatement c = conn.prepareStatement("select count('EmpId') from
			// admindetails");// = conn.createStatement();

			PreparedStatement pstmt = conn.prepareStatement("select * from transactionhistory where Date=now()");
			ResultSet rs = pstmt.executeQuery();
			// pw.println(rs.next());
			// rs.next();

			pw.println("<head>" + "<title>tsrbank</title>"
					+ "<meta name='viewport' content='width=device-width, initial-scale=1' />"
					+ "<link rel='stylesheet' href='existingcustomerspage.css' />"
					+ "<link rel='icon' href='Picsart_24-11-05_08-57-42-856.png'/>" + "</head>" + "<body>");
			request.getSession().setAttribute("category", "adminsearchdate");
			// request.getSession().setAttribute("accountno",
			// (String)request.getSession().getAttribute("accountno"));
			pw.println(
					"<form action='searchpage' method='post'><input name='date' type='tel' minlength='10' maxlength='10' required placeholder='enter_date...' />"
							+ "<button type='submit'>Search</button></form><br>");
			if (rs.next()) {// even though a bank all the time have ongoing transactions, the correct way of
							// showing the transaction history is this only
				pw.println(
						"<table border='2' cellspacing='0' cellpadding='2' align='center' style='text-align : center; margin-top:4vw; margin-bottom:2vw;' ><tr><th>TransactionId</th><th>TransactionType</th><th>FromAccountNo</th><th>ToAccountNo</th><th>Amount</th><th>Date</th></tr>");
				pw.println("<tr><td>" + rs.getString("TransactionId") + "</td><td>" + rs.getString("TransactionType")
						+ "</td><td>" + rs.getString("FromAccountNo") + "</td><td>" + rs.getString("ToAccountNo")
						+ "</td><td>" + rs.getString("Amount") + "</td><td>" + rs.getString("Date") + "</td></tr>");
			} else {
				request.getSession().setAttribute("message",
						"No transactions have been made on that particular date !");
				request.getSession().setAttribute("pagename", "nobanktransactionsdone");
				response.sendRedirect("thankyoupage");
			}
			while (rs.next()) {
				pw.println("<tr><td>" + rs.getString("TransactionId") + "</td><td>" + rs.getString("TransactionType")
						+ "</td><td>" + rs.getString("FromAccountNo") + "</td><td>" + rs.getString("ToAccountNo")
						+ "</td><td>" + rs.getString("Amount") + "</td><td>" + rs.getString("Date") + "</td></tr>");
			}
			pw.println("</table></body>");
			rs.close();
			conn.close();
			// int noofrecords = pstmt.getMaxRows();
			// pw.println("<b>noofrecord : "+(noofrecords+1)+"</b>");
//		pw.println("<head>"
//				+ "<title>search_customer</title>"
//				+ "<meta name='viewport' content='width=device-width, initial-scale=1' />"
//				+ "<link rel='stylesheet' href='existingcustomerspage.css' />"
//				+ "</head>");
//		pw.println("<body>"
//				+ "<p id='paragraph'></p>");
			// pw.println("<input type='tel' minlength='16' maxlength='16'
			// placeholder='enter_accouno...' />"
			// + "<button onClick='allcustomers()'>Search</button><br>");
//        pw.println("<script>");
//        pw.println("function allcustomers(){"
//        		+ "<b>onclick</b>"
//        		+ "<input type='tel' minlength='16' maxlength='16' placeholder='enter_accouno...' />"
//        		+ "<button>Search</button><br>"
//        		+ "<table>"
//        		+ "<tr><td>Empid</td><td>Password</td></tr>");
//        		+ "<tr><td>AccountNo</td><td>FirstName</td><td>MiddleName</td><td>Lastname</td><td>Gender</td><td>DOB</td><td>PhoneNo</td><td>Nationality</td><td>State</td><td>District</td><td>Zipcode</td><td>AadharNo</td><td>EmailAddress</td><td>YearlyIncome</td><td>AccountBalance</td>"
//        		+ "while("+rs.next()+"){"
//        		+ "<tr><td>"+rs.getLong("AccountNo")+"</td><td>"+rs.getString("FirstName")+"</td><td>"+rs.getString("MiddleName")+"</td><td>"+rs.getString("LastName")+"</td><td>"+rs.getString("Gender")+"</td><td>"+rs.getString("DOB")+"</td><td>"+rs.getLong("PhoneNo")+"</td><td>"+rs.getString("Nationality")+"</td><td>"+rs.getString("State")+"</td><td>"+rs.getString("District")+"</td><td>"+rs.getString("Zipcode")+"</td><td>"+rs.getLong("AadharNo")+"</td><td>"+rs.getString("EmailAddress")+"</td><td>"+rs.getLong("YearlyIncome")+"</td><td>"+rs.getLong("Accountbalance")+"</td></tr>"
//       for(int i=1;i<3;i++) {
//    	   pw.println("<tr><td>"+rs.getString("EmpId")+"</td><td>"+rs.getString("Password")+"</td></tr>");
//    	   rs.next();
//       }   
			// pw.println("</table></body>");
			// + " <b>println</b>}");
			// + "document.getElementById('paragraph').innerHTML='text';}");
//       pw.println("</script>");	
//       pw.println("document.getElementById('paragraph').innerHTML='text'");
		} catch (SQLException sqle) {
			pw.println("<b>" + sqle.getMessage() + "</b>");
			sqle.printStackTrace();
		}
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
