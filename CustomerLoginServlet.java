package com.servlet.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class adminloginServlet
 */
@WebServlet("/customerloginpage")
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
//    public adminloginServlet() {
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
		
		try {
			//PreparedStatement preparedstatement = conn.prepareStatement("select AccountNo,Password from customerlogin");

			request.getSession().setAttribute("category", "customer");

			PrintWriter pw = response.getWriter();
			response.setContentType("text/html");
			pw.println("<head>" + "<title>tsrbank</title>"
					+ "<meta name='viewport' content='width=device-width, initial-scale=1'><link rel='stylesheet' href='Login.css' />"
					+ "<link rel='icon' href='Picsart_24-11-05_08-57-42-856.png'/>" + "</head>");
			pw.println("<body>" + "<div id='admin_log'>" + "<div id='logo_div'>"
					+ "<img id='image' src='Picsart_24-10-02_22-19-03-595.png' />" + "<div id='logo_text'>"
					+ "<p style='font-size:1.5vw;'>Ongole</p>"
					+ "<p><span style='font-size:2vw; color:dodgerblue;'>T</span>REASURE <span style='font-size:2vw;'>S</span>AVINGS <span style='font-size:2vw; color:dodgerblue;'>R</span>GUKT</p>'"
					+ "</div>" + "</div>");
			pw.println("<form name='customer_login' action='credentialverification'>" + "<fieldset id='admin_details'>"
					+ "<legend>Customer_Login</legend>");
			pw.println("<table cellpadding='4vw' cellspacing='3vw'>" + "<tr>"
					+ "<td><label class='cat' for='Account_No'><b>Acc. No</b></label></td>"
					+ "<td><b class='cat'>:</b><input type='tel' pattern='[0-9]{16}' minlength='16' maxlength='16' id='Account_No' name='accountno' required /></td>"
					+ "</tr>");
			pw.println("<tr>" + "<td><label class='cat' for='Customer_pin'><b>Password</b></label></td>"
					+ "<td><b class='cat'>:</b><input type='password' id='Customer_pin' name='password' required /></td>"
					+ "</tr>" + "</table>");
			pw.println("<input class='form_sub' type='submit' value='Login'/>" + "</fieldset>" + "</form>" + "</body>");

//	pw.println("<br><a href='mainloginpage'>LoginPage</a>");
			pw.close();
			
			System.out.println("Customer Login page");

		} catch (Exception e) {
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
