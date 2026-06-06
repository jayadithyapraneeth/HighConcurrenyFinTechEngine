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
@WebServlet("/adminloginpage")
public class AdminLoginServlet extends HttpServlet {
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
			request.getSession().setAttribute("category", "admin");

			PrintWriter pw = response.getWriter();
			// response.setContentType("text/html");
			pw.println(
					"<head><title>tsrbank</title><meta name='viewport' content='width=device-width, initial-scale=1'><link rel='stylesheet' href='Login.css' />"
							+ "<link rel='icon' href='Picsart_24-11-05_08-57-42-856.png'/>" + "</head>");
			pw.println(
					"<body><div id='logo_div'><img id='image' src='Picsart_24-10-02_22-19-03-595.png' /><div id='logo_text'>");
			pw.println("<span style='font-size:2.6vw;'>ONGOLE</span>"
					+ "<p><span style='font-size:2.5vw; color:dodgerblue;'>T</span>REASURE <span style='font-size:2vw;'>S</span>AVINGS <span style='font-size:2vw; color:dodgerblue;'>R</span>GUKT</p>"
					+ "</div></div>");
			pw.println("<form name='admin_login' action='credentialverification'>" + "<fieldset id='admin_details'>"
					+ "<legend>Admin_Login</legend>");
			pw.println("<table cellpadding='4vw' cellspacing='3vw'>"
					+ "<tr><td><label class='cat' for='admin_id'><b>Admin Id</b></label></td>"
					+ "<td><b class='cat'>:</b><input type='text' name='adminid' id='admin_id' required /></td></tr>");
			pw.println("<tr><td><label class='cat' for='admin_pin'><b>Password</b></label></td>"
					+ "<td><b class='cat'>:</b><input type='password' name='password' id='admin_pin' required /></td>"
					+ "</tr></table>");
			pw.println("<input class='form_sub' type='submit' value='Login'/>" + "</fieldset>" + "</form>" + "</body>");

			// pw.println("<br><a href='MainLoginPage.html'>LoginPage</a>");
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
