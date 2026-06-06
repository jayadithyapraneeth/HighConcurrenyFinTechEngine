package com.servlet.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewApplicationServlet
 */
@WebServlet("/newapplicationsubmissionpage")
public class NewApplicationSubmissionServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		// pw.println("<b>Application Submission Page</b> ");
		String fname = request.getParameter("fname");
		String mname = request.getParameter("mname");
		String lname = request.getParameter("lname");
		String gender = request.getParameter("gender");// radio button should be replaced with options
		String DOB = request.getParameter("DOB");
		String nation = request.getParameter("nation");
		String email = request.getParameter("email");
		String state = request.getParameter("state");
		String district = request.getParameter("district");
		String village = request.getParameter("village");
		String pincode = request.getParameter("pincode");
		long phno = Long.parseLong(request.getParameter("phno"));
		long aadharid = Long.parseLong(request.getParameter("aadharid"));
		int annualincome = Integer.parseInt(request.getParameter("annualincome"));
		try (Connection conn = new DBCP().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(
					"insert into temporarycustomerdetails(FirstName,MiddleName,LastName,Gender,DOB,Nationality,PhoneNo,EmailAddress,State,District,Village,Zipcode,AadharNo,YearlyIncome,DateSubmitted)value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())");
			pstmt.setString(1, fname);
			pstmt.setString(2, mname);
			pstmt.setString(3, lname);
			pstmt.setString(4, gender);
			pstmt.setString(5, DOB);
			pstmt.setString(6, nation);
			pstmt.setLong(7, phno);
			pstmt.setString(8, email);
			pstmt.setString(9, state);
			pstmt.setString(10, district);
			pstmt.setString(11, village);
			pstmt.setString(12, pincode);
			pstmt.setLong(13, aadharid);
			pstmt.setInt(14, annualincome);
			int recordCount = pstmt.executeUpdate();// gives 1 only if the insert statement is executed
			conn.close();
			if (recordCount == 1) {// statement executed
				// pw.println("<b>Application Submitted Successfully</b>");
				request.getSession().setAttribute("pagename", "newapplication");
				response.sendRedirect("thankyoupage");
			}
		} catch (SQLException sqle) {
			if (sqle.getMessage().contains("Duplicate") && sqle.getMessage().contains("PRIMARY")) {// statement executed
				pw.println(
						"<b>Application Submission failed, duplicate Aadhar number, check the Aaadhar Number again and try to resubmit the form</b>");
			} else if (sqle.getMessage().contains("Duplicate") && sqle.getMessage().contains("PhoneNo")) {
				pw.println(
						"<b>Application Submission failed, duplicate Phone number, check the Phone Number again and try to resubmit the form</b>");
			}
			pw.println(sqle.getMessage());
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
