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
 * Servlet implementation class ChangePasswordCredentialVerificationServlet
 */
@WebServlet("/changepasswordcredentialverificationpage")
public class ChangePasswordCredentialVerificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
//    public ChangePasswordCredentialVerificationServlet() {
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

		String category = (String) request.getSession().getAttribute("category");
		PrintWriter pw = response.getWriter();
//	    pw.println("<b>PrintWriter<b>");
		// pw.println("category = "+category+"");

		String currentpassword = request.getParameter("currentpassword");
		String newpassword = request.getParameter("newpassword");
		String confirmnewpassword = request.getParameter("confirmnewpassword");
		//jdbc:mysql:///tsrbank", "jayadithyapraneeth",
		try (Connection conn = new DBCP().getConnection()) {

			PreparedStatement pstmt, setnewpassword;
			// String adminid="";
			// (long) 0;

			if (category == "admin") {
				pstmt = conn.prepareStatement("select EmpId, Password from adminlogin where EmpId = ?");
				// adminid = (String)request.getSession().getAttribute("adminid");
				pstmt.setString(1, (String) request.getSession().getAttribute("adminid"));
				// pw.println("id = "+adminid);

			} else {
				pstmt = conn.prepareStatement("select AccountNo, Password from customerlogin where AccountNo = ?");
				// accountno = (Long)request.getSession().getAttribute("accountno");
				pstmt.setLong(1, (Long) request.getSession().getAttribute("accountno"));// id is a string up to here but
																						// the account no we needed in
																						// the mysql query is a number
				// pw.println("id = "+accountno);
			}

			ResultSet realcurrentpassword = pstmt.executeQuery();// already existed password in the database
			realcurrentpassword.next();

			if (currentpassword.equalsIgnoreCase(realcurrentpassword.getString("Password"))) {
				if (newpassword.equalsIgnoreCase(confirmnewpassword)) {
					if (category == "customer") {
						Long accountno = (Long) request.getSession().getAttribute("accountno");
						// Long accountno = Long.parseLong("id");
						// pw.println("id = "+accountno);
						setnewpassword = conn.prepareStatement(
								"update customerlogin,customerdetails set customerlogin.Password = ?,customerdetails.Password = ? where customerlogin.AccountNo = ? and customerdetails.AccountNo = ?");
						setnewpassword.setString(1, newpassword);
						setnewpassword.setString(2, newpassword);
						setnewpassword.setLong(3, accountno);
						setnewpassword.setLong(4, accountno);
						setnewpassword.executeUpdate();
						// pw.println("Password updated successfully ");
						request.getSession().setAttribute("pagename", "customerpasswordchange");
						conn.close();
						response.sendRedirect("thankyoupage");
						// response.sendRedirect("customerpasswordchangepage");//not working --
						// http://localhost:8080/tsrbank/customerhomepage/customerpasswordchangepage");
					} else if (category == "admin")// (NumberFormatException nfe)
					{
						String adminid = (String) request.getSession().getAttribute("adminid");
						// pw.println("NumberFormatException/n id = "+adminid);
						setnewpassword = conn.prepareStatement(
								"update adminlogin,admindetails set adminlogin.Password = ?,admindetails.Password = ? where adminlogin.Empid = ? and admindetails.EmpId = ?");
						setnewpassword.setString(1, newpassword);
						setnewpassword.setString(2, newpassword);
						setnewpassword.setString(3, adminid);
						setnewpassword.setString(4, adminid);
						setnewpassword.executeUpdate();
						// pw.println("Password updated successfully ");
						request.getSession().setAttribute("pagename", "adminpasswordchange");
						conn.close();
						response.sendRedirect("thankyoupage");
						// response.sendRedirect("adminpasswordchangepage");//not working --
						// http://localhost:8080/tsrbank/adminhomepage/adminpasswordchangepage");
					}
				} else {
					conn.close();
					System.out.println("Incorrect confirm new password");
					pw.println("<b>Enter \"Confirm new password\" same as in the new password</b>");
				}
			} else {
				conn.close();
				System.out.println("Incorrect password entered");
				pw.println("<b>Entered current password : " + currentpassword + " is incorrect</b>");
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
