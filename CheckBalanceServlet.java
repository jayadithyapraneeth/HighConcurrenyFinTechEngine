package com.servlet.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckBalanceServlet
 */
@WebServlet("/checkbalancepage")
public class CheckBalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
//    public CheckBalanceServlet() {
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
//	pw.println("<b>Checkbalanceservlet</b>");

		
		try {

			String category = "checkbalance";
			request.getSession().setAttribute("category", category);
			request.getSession().setAttribute("accountno", (Long) request.getSession().getAttribute("accountno"));

			pw.println("<head>" + "<title>tsrbank</title>"
					+ "<meta name='viewport' content='width=device-width.initial-scale=1' />"
					+ "<link rel='stylesheet' href='change_pass.css' />"
					+ "<link rel='icon' href='Picsart_24-11-05_08-57-42-856.png'/>" + "</head>");
			pw.println("<body>" + "<div id='logo_div'>" + "<img id='image' src='Picsart_24-10-02_22-19-03-595.png' />"
					+ "<div id='logo_text'>" + "<span style='font-size:2.6vw;'>ONGOLE</span>"
					+ "<p><span style='font-size:2.5vw; color:dodgerblue;'>T</span>REASURE <span style='font-size:2vw;'>S</span>AVINGS <span style='font-size:2vw; color:dodgerblue;'>R</span>GUKT</p>"
					+ "</div>" + "</div>"
					+ "<form id='balance_check' action='pincredentialverificationpage' method='post'>"
					+ "<fieldset id='field' style='padding-top:1vw;'>"
					+ "<div style=''><img src='Picsart_24-10-02_22-19-03-595.png' style='height:4.5vw; width:6.5vw; float:left; margin-left:10vw;' /><span style='color:white; float:left; margin-left:1vw;font-weight:bold; font-size:3vw; margin-top:1.3vw;'>Bank</span></div>"
					+ "<table cellpadding='0' cellspacing='20' style='margin:6vw 0vw 0vw 4vw;'>"
					+ "<tr><td class='cat'>Acc.No</td><td>:<input type='tel' minlength='16' maxlength=16' name='accountno' required /></td></tr>"
					+ "<tr><td class='cat'>Enter PIN</td><td>:<input type='password' name='password' required /></td></tr>"
					+ "</table>" + "<input type='submit' id='form_sub' value='Done' >" + "</fieldset>" + "</form>"
					+ "<script></script>" + "</body>");
		} catch (Exception e) {
			pw.println(e.getMessage());
			e.printStackTrace();
		}
		
		pw.close();
		
		System.out.println("Check Balance page");

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
