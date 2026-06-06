package com.servlet.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ApplicationCredentialVerificationServlet
 */
@WebServlet("/applicationcredentialverificationpage")
public class ApplicationCredentialVerificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
//    public ApplicationCredentialVerificationServlet() {
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
		// pw.println("credential verification");
		// pw.println(request.getParameter("status"));
		String status = request.getParameter("status");
		// String reject = request.getParameter("reject");

		int del;//this variable is initialized later

		try (Connection conn = new DBCP().getConnection()) {
			PreparedStatement temppstmt = conn.prepareStatement("select * from temporarycustomerdetails where AadharNo = ?");
			PreparedStatement deletefromtemp = conn.prepareStatement("delete from temporarycustomerdetails where AadharNo = ?");
			String aadharno = request.getParameter("identity");
			if (status.equalsIgnoreCase("accept")) {
				// pw.println("<body><b>accepted successfully</b></body>");
//String aadharno = (String)request.getSession().getAttribute("identity");

				// pw.println("aadharno : "+aadharno);
				String acno = aadharno;
				Random r = new Random();
				for (int i = 1; i <= 2; i++) {
					acno += r.nextInt(99 - 10 + 1) + 10;
				}
//pw.println("new account no : "+acno);
				String password = acno.substring(0, 4);
//pw.println("password : "+password);
				// pw.println("--try--");
				// Long newaccountno = Long.parseLong(aadharno);
				temppstmt.setLong(1, Long.parseLong(aadharno));
				deletefromtemp.setLong(1, Long.parseLong(aadharno));
				ResultSet rs = temppstmt.executeQuery();
				rs.next();
				Long newaccountno = Long.parseLong(acno);

				// pw.println("ResultSet -- newaccountno : "+newaccountno);

				PreparedStatement ppstmt = conn.prepareStatement(
						"insert into customerdetails(AccountNo,Password,FirstName,MiddleName,LastName,Gender,DOB,PhoneNo,Nationality,State,District,Village,Zipcode,AadharNo,EmailAddress,YearlyIncome,AccountBalance,DateCreated)value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())");
				PreparedStatement cuslogintable = conn
						.prepareStatement("insert into customerlogin(AccountNo,Password) values(?,?)");
				pw.println("firstname" + rs.getString("FirstName"));
				ppstmt.setLong(1, newaccountno);
				cuslogintable.setLong(1, newaccountno);
				ppstmt.setString(2, password);
				cuslogintable.setString(2, password);
				ppstmt.setString(3, rs.getString("FirstName"));
				ppstmt.setString(4, rs.getString("MiddleName"));
				ppstmt.setString(5, rs.getString("LastName"));
				ppstmt.setString(6, rs.getString("Gender"));
				ppstmt.setString(7, rs.getString("DOB"));
				ppstmt.setLong(8, rs.getLong("PhoneNo"));
				ppstmt.setString(9, rs.getString("Nationality"));
				ppstmt.setString(10, rs.getString("State"));
				ppstmt.setString(11, rs.getString("District"));
				ppstmt.setString(12, rs.getString("Village"));
				ppstmt.setString(13, rs.getString("Zipcode"));
				ppstmt.setLong(14, rs.getLong("AadharNo"));
				ppstmt.setString(15, rs.getString("EmailAddress"));
				ppstmt.setLong(16, rs.getLong("YearlyIncome"));
				ppstmt.setInt(17, 0);
				ppstmt.executeUpdate();
				cuslogintable.executeUpdate();
				del = deletefromtemp.executeUpdate();
				conn.close();
				response.sendRedirect("applicationapprovalpage");
				// account created successfully
			} else if (status.equalsIgnoreCase("reject")) {
				deletefromtemp.setLong(1, Long.parseLong(aadharno));
				// pw.println("<b> rejected successfully</b>");
				del = deletefromtemp.executeUpdate();
				conn.close();
				response.sendRedirect("applicationapprovalpage");
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
