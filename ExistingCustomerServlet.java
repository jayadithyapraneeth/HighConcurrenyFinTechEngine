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
@WebServlet("/existingcustomerspage")
public class ExistingCustomerServlet extends HttpServlet {
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
			// admindetails");// = conn.createStatement()
			// pw.println(rs.next());
			// int noofrecords = pstmt.getMaxRows();
			// pw.println("<b>noofrecord : "+(noofrecords+1)+"</b>");
			//String s = "'output'";
			PreparedStatement pstmt = conn.prepareStatement("select * from customerdetails");
			ResultSet rs = pstmt.executeQuery();
			// pw.println(rs.next());
			// rs.next();
			pw.println("<head>" + "<title>tsrbank</title>"
					+ "<meta name='viewport' content='width=device-width, initial-scale=1' />"
					+ "<link rel='stylesheet' href='existingcustomerspage.css' />"
					+ "<link rel='icon' href='Picsart_24-11-05_08-57-42-856.png'/>" + "</head>" + "<body>");
			request.getSession().setAttribute("category", "searchaccount");
			pw.println(
					"<form action='searchpage' method='post'><input type='tel' minlength='16' maxlength='16' name='accountno' required placeholder='enter_accouno...' required/>"
							+ "<button type='submit'>Search</button></form><br>");
			pw.println(
					"<table border='2' cellspacing='0' cellpadding='2' align='center' style='text-align : center; margin-top:4vw; width:100%' >"
							+ "<tr><th>AccountNo</th><th>Name</th></th><th>Gender</th><th style='width:100%'>DateOfBirth</th><th>PhoneNo</th><th style='width:100%'>Address</th><th>AadharNo</th><th>EmailAddress</th><th>AccountOpeningDate</th><th>YearlyIncome</th><th>CurrentBalance</th>");
			while (rs.next()) {

				pw.println("<tr><td>" + rs.getLong("AccountNo") + "</td><td style='width:100%'><text`>"
						+ rs.getString("FirstName") + " " + rs.getString("MiddleName") + " " + rs.getString("Lastname")
						+ "</textarea></td><td>" + rs.getString("Gender") + "</td><td style='width:100px'>"
						+ rs.getString("DOB") + "</td><td>" + rs.getLong("PhoneNo") + "</td><td><text>"
						+ rs.getString("Nationality") + "," + rs.getString("State") + "," + rs.getString("District")
						+ "," + rs.getString("Village") + "," + rs.getString("Zipcode") + "</text></td><td>"
						+ rs.getLong("AadharNo") + "</td><td>" + rs.getString("EmailAddress") + "</td><td>"
						+ rs.getString("DateCreated") + "</td><td>" + rs.getLong("YearlyIncome") + "</td><td>"
						+ rs.getLong("AccountBalance") + "</td>");
			}
			pw.println("</table></body>");
			rs.close();
			conn.close();
			// + "<script>"
			// for(int i=1;i<=2;i++){
			// pw.println("<b>brave</b>");//<b>para</b>"
			// }
			// + "document.getElementById("+s+").innerHTML += '<tr><td>break</td></tr>';"
			// + "}"
			// + "</script>"
			// pw.println( "</script></body>");
			// pw.println("<input type='tel' minlength='16' maxlength='16'
			// placeholder='enter_accouno...' />"
			// + "<button onClick='allcustomers()'>Search</button><br>");
			// pw.println("<script>");
			// pw.println("function allcustomers(){"
			// + "var empid="+rs.getString("EmpId")+";"
			// + "<table>"
			// + "<tr><td>Empid</td><td>Password</td></tr>");
//        		+ "pw.println("<tr><th>AccountNo</th><th>FirstName</th><th>MiddleName</th><th>Lastname</th><th>Gender</th><th>DOB</th><th>PhoneNo</th><th>Nationality</th><th>State</th><th>District</th><th>Zipcode</th><th>AadharNo</th><th>EmailAddress</th><th>YearlyIncome</th><th>AccountBalance</th>");
//        		+ "while("+rs.next()+"){"
//        		+ "<tr><td>"+rs.getLong("AccountNo")+"</td><td>"+rs.getString("FirstName")+"</td><td>"+rs.getString("MiddleName")+"</td><td>"+rs.getString("LastName")+"</td><td>"+rs.getString("Gender")+"</td><td>"+rs.getString("DOB")+"</td><td>"+rs.getLong("PhoneNo")+"</td><td>"+rs.getString("Nationality")+"</td><td>"+rs.getString("State")+"</td><td>"+rs.getString("District")+"</td><td>"+rs.getString("Zipcode")+"</td><td>"+rs.getLong("AadharNo")+"</td><td>"+rs.getString("EmailAddress")+"</td><td>"+rs.getLong("YearlyIncome")+"</td><td>"+rs.getLong("Accountbalance")+"</td></tr>"
			// for(int i=1;rs.next();i++) {
			// pw.println("<tr><td>'EmpId='empid</td><td>"+rs.getString("Password")+"</td></tr>"
			// + "</table>}");
			// rs.next();
			// }
			// pw.println("</table></body>");
			// + " <b>println</b>}");
			// + "document.getElementById('paragraph').innerHTML='text';}");
			// pw.println("</script>");
			// pw.println("document.getElementById('paragraph').innerHTML='text'");
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
