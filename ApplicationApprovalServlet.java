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
@WebServlet("/applicationapprovalpage")
public class ApplicationApprovalServlet extends HttpServlet {
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
			PreparedStatement pstmt = conn.prepareStatement("select * from temporarycustomerdetails");
			ResultSet rs = pstmt.executeQuery();
			// pw.println(rs.next());
			// rs.next();
			pw.println("<head>" + "<title>tsrbank</title>"
					+ "<meta name='viewport' content='width=device-width, initial-scale=1' />"
					+ "<link rel='stylesheet' href='existingcustomerspage.css' />"
					+ "<link rel='icon' href='Picsart_24-11-05_08-57-42-856.png'/>" + "</head>" + "<body>"
					+ "<form  id='newapplication' action='applicationcredentialverificationpage' method='post'> ");

			// request.getSession().setAttribute("pagename","applicationapproval");
			// response.sendRedirect("thankyoupage");

			String a = "";
			if (rs.next()) {
				a = "" + rs.getLong("AadharNo");
				pw.println(
						"<table border='2' cellspacing='0' cellpadding='2' align='center' style='text-align : center; margin-top:4vw'><tr><th>FirstName</th><th>MiddleName</th><th>Lastname</th><th>Gender</th><th>DOB</th><th>PhoneNo</th><th>Nationality</th><th>State</th><th>District</th><th>Zipcode</th><th>AadharNo</th><th>EmailAddress</th><th>YearlyIncome</th><th>ApplicationSubmissionDate</th>");
				pw.println("<tr>" + "<td>" + rs.getString("FirstName") + "</td><td>" + rs.getString("MiddleName")
						+ "</td><td>" + rs.getString("Lastname") + "</td><td>" + rs.getString("Gender") + "</td><td>"
						+ rs.getString("DOB") + "</td><td>" + rs.getLong("PhoneNo") + "</td><td>"
						+ rs.getString("Nationality") + "</td><td>" + rs.getString("State") + "</td><td>"
						+ rs.getString("District") + "</td><td>" + rs.getString("Zipcode") + "</td><td>"
						+ rs.getLong("AadharNo") + "</td><td>" + rs.getString("EmailAddress") + "</td><td>"
						+ rs.getLong("YearlyIncome") + "</td><td>" + rs.getString("DateSubmitted") + "</td>" + "<td>");
				pw.println(
						"<input name='status' value='accept' style='display : none;'><input name='identity' id='identity' value='"
								+ a + "' style='display : none;'>");
				pw.println("<button type='submit'>accept" + "</button>" + "</td>" + "</form>"
						+ "<form action='applicationcredentialverificationpage' method='post'><td>"
						+ "<input name='identity' id='identity' value='" + a + "' style='display : none;'>" + "<button>"
						+ "<input name='status' id='reject' value='reject' style='display:none;'>" + "reject"
						+ "</button>" + "</td>");
			} else {
				request.getSession().setAttribute("pagename", "nonewapplications");
				response.sendRedirect("thankyoupage");
			}

			while(rs.next()) {

				a = "" + rs.getLong("AadharNo");
				//String var = "approval(" + a + ")";
				// request.getSession().setAttribute("identity",a);

				pw.println("<tr>" + "<td>" + rs.getString("FirstName") + "</td><td>" + rs.getString("MiddleName")
						+ "</td><td>" + rs.getString("Lastname") + "</td><td>" + rs.getString("Gender") + "</td><td>"
						+ rs.getString("DOB") + "</td><td>" + rs.getLong("PhoneNo") + "</td><td>"
						+ rs.getString("Nationality") + "</td><td>" + rs.getString("State") + "</td><td>"
						+ rs.getString("District") + "</td><td>" + rs.getString("Zipcode") + "</td><td>"
						+ rs.getLong("AadharNo") + "</td><td>" + rs.getString("EmailAddress") + "</td><td>"
						+ rs.getLong("YearlyIncome") + "</td><td>" + rs.getString("DateSubmitted") + "</td>" + "<td>"
						+ "<button type='submit'>");
				pw.println(
						"<input name='status' value='accept' style='display : none;'><input name='identity' id='identity' value='"
								+ a + "' style='display : none;'>");
				pw.println("accept" + "</button>" + "</td>" + "</form>"
						+ "<form action='applicationcredentialverificationpage' method='post'><td>"
						+ "<input name='identity' id='identity' value='" + a + "' style='display : none;'>" + "<button>"
						+ "<input name='status' id='reject' value='reject' style='display:none;'>" + "reject"
						+ "</button>" + "</td>");
			}
			pw.println("</table>" + "</form>" + "</body>");
			rs.close();
			conn.close();
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
