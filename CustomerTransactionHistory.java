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
 * Servlet implementation class Customertransactionhistory
 */
@WebServlet("/customertransactionhistorypage")
public class CustomerTransactionHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
//    public Customertransactionhistory() {
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
			// String existingcustomerdetails = "select * from customerdetails";
			// PreparedStatement c = conn.prepareStatement("select count('EmpId') from
			// admindetails");// = conn.createStatement();

			PreparedStatement pstmt = conn
					.prepareStatement("select * from transactionhistory where FromAccountNo =? or ToAccountNo=?");
			pstmt.setLong(1, (Long) request.getSession().getAttribute("accountno"));
			pstmt.setLong(2, (Long) request.getSession().getAttribute("accountno"));
			ResultSet rs = pstmt.executeQuery();
			// pw.println(rs.next());
			// rs.next();
			request.getSession().setAttribute("category", "customersearchdate");
			request.getSession().setAttribute("accountno", (Long) request.getSession().getAttribute("accountno"));
			pw.println("<head>" + "<title>tsrbank</title>"
					+ "<meta name='viewport' content='width=device-width, initial-scale=1' />"
					+ "<link rel='stylesheet' href='existingcustomerspage.css' />"
					+ "<link rel='icon' href='Picsart_24-11-05_08-57-42-856.png'/>" + "</head>" + "<body>"
					+ "<form action='searchpage' method='post'><input name='date' type='tel' minlength='10' maxlength='10' required placeholder='enter_date...' />"
					+ "<button type='submit'>Search</button></form><br>");
			if (rs.next()) {
				pw.println(
						"<table border='2' cellspacing='0' cellpadding='2' align='center' style='text-align : center; margin-top:4vw; margin-bottom:4vw;' ><tr><th>TransactionType</th><th>FromAccountNo</th><th>ToAccountNo</th><th>Amount</th><th>Date</th></tr>");
				pw.println("<tr><td>" + rs.getString("TransactionType") + "</td><td>" + rs.getString("FromAccountNo")
						+ "</td><td>" + rs.getString("ToAccountNo") + "</td><td>" + rs.getString("Amount") + "</td><td>"
						+ rs.getString("Date") + "</td></tr>");
			} else {
				request.getSession().setAttribute("message", "No transactions have been made with your account!");
				request.getSession().setAttribute("pagename", "nocustransactionsdone");
				response.sendRedirect("thankyoupage");
			}
			while (rs.next()) {
				pw.println("<tr><td>" + rs.getString("TransactionType") + "</td><td>" + rs.getString("FromAccountNo")
						+ "</td><td>" + rs.getString("ToAccountNo") + "</td><td>" + rs.getString("Amount") + "<td>"
						+ rs.getString("Date") + "</td></tr>");
			}
			pw.println("</table></body>");
			rs.close();
			conn.close();
		} catch (SQLException sqle) {
			pw.println("<b>" + sqle.getMessage() + "</b>");
			sqle.printStackTrace();
		}

		pw.close();
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
