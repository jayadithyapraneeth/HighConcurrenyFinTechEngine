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
 * Servlet implementation class LoginCredentialVerification
 */
@WebServlet("/credentialverification")
public class LoginCredentialVerification extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = new DBCP().getConnection();//there is no need of try catch around this connection because the getConnection() method is already handling the SQLException and returning null if there is any exception
		
		PreparedStatement pstmt;
		String pin = request.getParameter("password");

		PrintWriter pw = response.getWriter();
		// pw.println("<b>Login pin : "+pin+" </b>");
		if (request.getSession().getAttribute("category") == "customer") {// try{
			long accountno = Long.parseLong(request.getParameter("accountno"));// It is an account number
			// pw.println("<b>Login identity long : "+identity+" </b>");
			try {
				pstmt = conn.prepareStatement("select AccountNo,Password from customerlogin where AccountNo = ?");
				pstmt.setLong(1, accountno);
				// pstmt.setString(2, pin);
				ResultSet customer = pstmt.executeQuery();
				if (customer.next()) {
					if (customer.getString("Password").equalsIgnoreCase(pin)) {// we cannot use == operator to compare
																				// these two strings
						// can be logged in
//					pw.println("You can login");
						request.getSession().setAttribute("password", pin);
						request.getSession().setAttribute("accountno", accountno);
						response.sendRedirect("customerhomepage");
					} else {
						// password mismatch
						pw.println("Password mismatch");
					}
				} else {
					pw.println("AccountNo not found");
				}
				customer.close();
				conn.close();
				// changing the position to the first row if record exists or throwing exception
				// pw.println("rs : "+rs);
				// pw.println("<b>Login identity long : "+rs.getLong("AccountNo")+" </b>");
				// pw.println("<b>Login pin : "+rs.getString("Password")+" </b>");
				// boolean idexists=false,pinexists=false;

			} catch (SQLException e) {
				e.printStackTrace();
				pw.println(e.getMessage());

			}
		} else if (request.getSession().getAttribute("category") == "admin")
		// catch(NumberFormatException nfe)
		{
			// pw.println(nfe.getMessage());
			// nfe.printStackTrace();

			try {
				pstmt = conn.prepareStatement("select EmpId,Password from adminlogin where EmpId = ?");
				String adminid = request.getParameter("adminid");// it is an alpha numerical admin id

				// pw.println("<b>Login identity String : "+identity+" </b>");
				pstmt.setString(1, adminid);
				// pstmt.setString(2, pin);
				ResultSet admin = pstmt.executeQuery();
				if (admin.next()) {// changing the position to the first row if record exists or throwing exception
					if (admin.getString("Password").equalsIgnoreCase(pin)) {// we cannot use == operator to compare
																			// these two strings
						// can be logged in
						// pw.println("You can login");
						request.getSession().setAttribute("adminid", adminid);
						request.getSession().setAttribute("password", pin);
						response.sendRedirect("adminhomepage");
					} else {
						// password mismatch
						pw.println("Password mismatch");
					}
				} else {
					pw.println("AdminId not found");
				}
				
				admin.close();
				conn.close();
				// pw.println("rs : :"+request.getParameter("password"));
				// pw.println("<b>Login identity String : "+rs.getString("EmpId")+" </b>");
				// pw.println("<b>Login pin : "+rs.getString("Password")+" </b>");

				// id not found
				// pw.println("Id not found");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				pw.println(e.getMessage());

			}

		}
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);

	}

}
