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
 * Servlet implementation class AdminHomeServlet
 */
@WebServlet("/adminhomepage")
public class AdminHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter pw = response.getWriter();
		String admin_id = (String) request.getSession().getAttribute("adminid");// the getAttribute() method returns
																				// string object not a string
		//String password = (String) request.getSession().getAttribute("password");// the getAttribute() method returns
																					// string object not a string
		// pw.println("<b>admin id : "+admin_id+"password : "+password+"</b>");

		// pw.println("<p><b>Admin Home Page</b></p>");
		try (Connection conn = new DBCP().getConnection()) {
			PreparedStatement preparedstatement = conn.prepareStatement("select * from admindetails where EmpId=?");
			// pw.println("prepared statement");
			// pw.println("adminid="+admin_id+"before parseLong");
			// long adminid = Long.parseLong(admin_id);//adminid is not a number
			// pw.println("adminid="+adminid);
			preparedstatement.setString(1, admin_id);
			// pw.println("preparedstatement.setString(1, adminid)");
			ResultSet rs = preparedstatement.executeQuery();
			// pw.println("resultset");

			rs.next();
			// pw.println(rs.getString("FirstName"));

			request.getSession().setAttribute("adminid", admin_id);
			request.getSession().setAttribute("operator", "admin");

			pw.println("<head>" + "<title>tsrbank</title>"
					+ "<meta name='viewport' content='width=device-width, initial-scale=1' />"
					+ "<link rel='stylesheet' href='Admin_page.css' />"
					+ "<link rel='icon' href='Picsart_24-11-05_08-57-42-856.png'/>" + "</head>");
			pw.println("<body>" + "<iframe id='fr' name='my_data' src='tsr.html'></iframe>"
					+ "<div id='main' style='height:50.7vw;'>" + "<table cellpadding='0' cellspacing='5'>" + "<tr>"
					+ "<th id='pro' colspan='2'>Profile</th>" + "</tr>" + "<tr>" + "<td class='rat'>Admin ID</td>"
					+ "<td>:" + rs.getString("EmpId") + "</td>" + "</tr>" + "<tr>" + "<td class='rat'>Name</td>"
					+ "<td>:"
					+ rs.getString("FirstName").concat(" " + rs.getString("MiddleName"))
							.concat(" " + rs.getString("LastName"))
					+ "</td>" + "</tr>" + "<tr>" + "<td class='rat'>Ph.No</td>" + "<td>:" + rs.getLong("PhoneNo")
					+ "</td>" + "<tr>" + "<tr>" + "<td class='rat'>Branch</td>" + "<td>:Ongole</td>" + "</tr>"
					+ "</table>");
			pw.println("<ul style='text-align:center;padding-left:0.87vw;margin-bottom:5vw;'>"
					+ "<li class='adminpage' ><button style='height:3.6vw; font-size:1.5vw; border:1px solid black;' class='adminpage' id='but1' onclick='myfun1(),but_click(1)'>New Applications</button></li>"
					+ "<li class='adminpage' ><button style='height:3.6vw; font-size:1.5vw; border:1px solid black;' class='adminpage' id='but2' onclick='myfun2(),but_click(2)'>Customers</button></li>"
					+ "<li class='adminpage' ><button style='height:3.6vw; font-size:1.5vw; border:1px solid black;' class='adminpage' id='but3' onclick='myfun3(),but_click(3)'>Make Transfer</button></li>"
					+ "<li class='adminpage' ><button style='height:3.6vw; font-size:1.5vw; border:1px solid black;' class='adminpage' id='but4' onclick='myfun4(),but_click(4)'>Deposit and Withdraw</button></li>"
					+ "<li class='adminpage' ><button style='height:3.6vw; font-size:1.5vw; border:1px solid black;' class='adminpage' id='but5' onclick='myfun5(),but_click(5)'>Transaction history</button></li>"
					+ "<li class='adminpage' ><button style='height:3.6vw; font-size:1.5vw; border:1px solid black;' class='adminpage' id='but6' onclick='myfun6(),but_click(6)'>Change password</button></li>"
					+ "<li><a id='log_out' href='mainloginpage' >Log out</a></li>" + "</ul >" + "</div>" + "<p></p>"
					+ "</body>");
			pw.println("<script>" 
					+ "function myfun1() {fr.src='applicationapprovalpage';}" 
					+ "function myfun2() {fr.src='existingcustomerspage';}" 
					+ "function myfun3() {fr.src='transactionbyadminpage';}"
					+ "function myfun4() {fr.src='depositandwithdrawpage';} "
					+ "function myfun5() {fr.src='banktransactionhistorypage';}"
					+ " function myfun6() {fr.src='adminpasswordchangepage';}" + "function but_click(j){" + "for(var i=1;i<=6;i++){"
					+ " var x='but'+i;" + "var z=document.getElementById(x).style;" + "if(i==j){"
					+ " z.backgroundColor='lightgray';" + "z.color='dodgerblue';" + "z.borderColor='gray';"
					+ "if(j==4){" + "z.fontSize='1.7vw';" + "}else{" + "z.fontSize='1.8vw';" + "}" + "}else{"
					+ " z.backgroundColor='dodgerblue';" + " z.color='white';" + " z.fontSize='1.5vw';" + "}" + "}"
					+ "}" + "</script>");
			
			rs.close();
			conn.close();
		} catch (SQLException sqle) {
			pw.println(sqle.getMessage());
			sqle.printStackTrace();
		} catch (Exception e) {
			pw.println(e.getMessage());
			e.printStackTrace();
		}

		// pw.println("<br><a href='mainloginpage'>LoginPage</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
