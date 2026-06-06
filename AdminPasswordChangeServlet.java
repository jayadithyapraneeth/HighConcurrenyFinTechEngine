package com.servlet.bank;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/adminpasswordchangepage")
public class AdminPasswordChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
//    public ChangePasswordServlet() {
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
		String admin = "admin";
		request.getSession().setAttribute("category", admin);
		String adminid = (String) request.getSession().getAttribute("adminid");
		request.getSession().setAttribute("adminid", adminid);
		pw.println("<head>" + "<title>tsrbank</title>"
				+ "<meta name='viewport' content='width=device-width.initial-scale=1' />"
				+ "<link rel='stylesheet' href='change_pass.css' />"
				+ "<link rel='icon' href='Picsart_24-11-05_08-57-42-856.png'/>" + "</head>");
		pw.println("<body>" + "<div id='logo_div'>" + "<img id='image' src='Picsart_24-10-02_22-19-03-595.png' />"
				+ "<div id='logo_text'>" + "<span style='font-size:2.6vw;'>ONGOLE</span>"
				+ "<p><span style='font-size:2.5vw; color:dodgerblue;'>T</span>REASURE <span style='font-size:2vw;'>S</span>AVINGS <span style='font-size:2vw; color:dodgerblue;'>R</span>GUKT</p>"
				+ "</div></div>"
				+ "<form name='adminpasswordchange' action='changepasswordcredentialverificationpage' method='post'>"
				+ "<fieldset id='field' style='height:25vw; width:37vw'>" + "<table cellpadding='0' cellspacing='20'>"
				+ "<tr>"
				+ "<td class='cat'>Current Admin Password</td><td>:<input type='text' name='currentpassword' required /></td>"
				+ "</tr>" + "<tr>"
				+ "<td class='cat'>New Admin Password</td><td>:<input type='text' name='newpassword' required /></td>"
				+ "</tr>" + "<tr>"
				+ "<td class='cat'>Confirm Admin Password</td><td>:<input type='text' name='confirmnewpassword' required /></td>"
				+ "</tr>" + "</table>" + "<input type='submit' id='form_sub' value='Done'>" + "</fieldset>" + "</form>"
				+ "</body>");
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
