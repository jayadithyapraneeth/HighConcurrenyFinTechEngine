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
 * Servlet implementation class SearchServlet
 */
@WebServlet("/searchpage")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
//    public SearchServlet() {
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

		try (Connection conn = new DBCP().getConnection()) {
			pw.println("<body style='background-color:white;'>");
			String category = (String) request.getSession().getAttribute("category");
			switch (category) {
			case "searchaccount":// we should give details of this particular account
				PreparedStatement searchaccount = conn
						.prepareStatement("select * from customerdetails where AccountNo=?");
				searchaccount.setLong(1, Long.parseLong(request.getParameter("accountno")));
				ResultSet accountdetails = searchaccount.executeQuery();
				accountdetails.next();
				pw.println(
						"<table  border='2' cellspacing='0' cellpadding='2' align='center' style='text-align : center; margin-top:4vw; width:100%; background-color:white' >"
								+ "<tr><th>AccountNo</th><th>Name</th></th><th>Gender</th><th style='width:100%'>DateOfBirth</th><th>PhoneNo</th><th style='width:100%'>Address</th><th>AadharNo</th><th>EmailAddress</th><th>AccountOpeningDate</th><th>YearlyIncome</th><th>CurrentBalance</th>");
				pw.println("<tr><td>" + accountdetails.getLong("AccountNo") + "</td><td style='width:100%'><text`>"
						+ accountdetails.getString("FirstName") + " " + accountdetails.getString("MiddleName") + " "
						+ accountdetails.getString("Lastname") + "</textarea></td><td>"
						+ accountdetails.getString("Gender") + "</td><td style='width:100px'>"
						+ accountdetails.getString("DOB") + "</td><td>" + accountdetails.getLong("PhoneNo")
						+ "</td><td><text>" + accountdetails.getString("Nationality") + ","
						+ accountdetails.getString("State") + "," + accountdetails.getString("District") + ","
						+ accountdetails.getString("Village") + "," + accountdetails.getString("Zipcode")
						+ "</text></td><td>" + accountdetails.getLong("AadharNo") + "</td><td>"
						+ accountdetails.getString("EmailAddress") + "</td><td>"
						+ accountdetails.getString("DateCreated") + "</td><td>" + accountdetails.getLong("YearlyIncome")
						+ "</td><td>" + accountdetails.getLong("AccountBalance") + "</td>" + "</table><br>");
				pw.println("<br><b><<<a href='existingcustomerspage'>all_customers</a></b></body>");
				break;
			case "customersearchdate": // we should submit the transaction history of particular date for a particular
										// account number
				// pw.println("switch case : customersearchdate");
				PreparedStatement cussearchdate = conn.prepareStatement(
						"select * from transactionhistory where (FromAccountNo=? or ToAccountNo=?) and Date(Date)=?");

				// pw.println("search date = "+request.getParameter("date"));
				cussearchdate.setLong(1, (Long) request.getSession().getAttribute("accountno"));
				cussearchdate.setLong(2, (Long) request.getSession().getAttribute("accountno"));
				cussearchdate.setString(3, request.getParameter("date"));
				ResultSet customertransactionhistory = cussearchdate.executeQuery();
				if (customertransactionhistory.next()) {
					pw.println(
							"<table border='2' cellspacing='0' cellpadding='2' align='center' style='text-align : center; margin-top:4vw' ><tr><th>TransactionType</th><th>FromAccountNo</th><th>ToAccountNo</th><th>Amount</th><th>Date</th></tr>");
					pw.println("<tr><td>" + customertransactionhistory.getString("TransactionType") + "</td><td>"
							+ customertransactionhistory.getString("FromAccountNo") + "</td><td>"
							+ customertransactionhistory.getString("ToAccountNo") + "</td><td>"
							+ customertransactionhistory.getString("Amount") + "<td>"
							+ customertransactionhistory.getString("Date") + "</td></tr>");
				} else {
					request.getSession().setAttribute("message",
							"No transactions have been made on that particular date!");
					request.getSession().setAttribute("pagename", "nocustransactionsdone");
					response.sendRedirect("thankyoupage");
				}
				while (customertransactionhistory.next()) {
					pw.println("<tr><td>" + customertransactionhistory.getString("TransactionType") + "</td><td>"
							+ customertransactionhistory.getString("FromAccountNo") + "</td><td>"
							+ customertransactionhistory.getString("ToAccountNo") + "</td><td>"
							+ customertransactionhistory.getString("Amount") + "<td>"
							+ customertransactionhistory.getString("Date") + "</td></tr>");
				}
				pw.println("</table><br><br><b><<<a href='customertransactionhistorypage'>back</a></b></body>");
				break;
			case "adminsearchdate": // we should submit the transaction history of particular date
				// pw.println("switch case : customersearchdate");
				PreparedStatement adminsearchdate = conn
						.prepareStatement("select * from transactionhistory where Date(Date)=?");

				// pw.println("search date = "+request.getParameter("date"));
				// adminsearchdate.setLong(1,
				// (Long)request.getSession().getAttribute("accountno"));
				// adminsearchdate.setLong(2,
				// (Long)request.getSession().getAttribute("accountno"));
				adminsearchdate.setString(1, request.getParameter("date"));
				ResultSet banktransactionhistory = adminsearchdate.executeQuery();
				if (banktransactionhistory.next()) {
					pw.println(
							"<table border='2' cellspacing='0' cellpadding='2' align='center' style='text-align : center; margin-top:4vw' ><tr><th>TransactionId</th><th>TransactionType</th><th>FromAccountNo</th><th>ToAccountNo</th><th>Amount</th><th>Date</th></tr>");
					pw.println("<tr><td>" + banktransactionhistory.getString("TransactionId") + "</td><td>"
							+ banktransactionhistory.getString("TransactionType") + "</td><td>"
							+ banktransactionhistory.getString("FromAccountNo") + "</td><td>"
							+ banktransactionhistory.getString("ToAccountNo") + "</td><td>"
							+ banktransactionhistory.getString("Amount") + "<td>"
							+ banktransactionhistory.getString("Date") + "</td></tr>");
				} else {
					request.getSession().setAttribute("pagename", "nobanktransactionsdone");
					response.sendRedirect("thankyoupage");
				}
				while (banktransactionhistory.next()) {
					pw.println("<tr><td>" + banktransactionhistory.getString("TransactionId") + "</td><td>"
							+ banktransactionhistory.getString("TransactionType") + "</td><td>"
							+ banktransactionhistory.getString("FromAccountNo") + "</td><td>"
							+ banktransactionhistory.getString("ToAccountNo") + "</td><td>"
							+ banktransactionhistory.getString("Amount") + "<td>"
							+ banktransactionhistory.getString("Date") + "</td></tr>");
				}
				pw.println("</table><br><br><b><<<a href='banktransactionhistorypage'>back</a></b></body>");
				break;
			}

		} catch (SQLException sqle) {
			pw.println(sqle.getMessage());
			sqle.printStackTrace();
		} catch (Exception e) {
			pw.println(e.getMessage());
			e.printStackTrace();
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
