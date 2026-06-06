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
 * Servlet implementation class TransferMoneyServlet
 */
@WebServlet("/transactionbyadminpage")
public class TransactionByAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
//    public TransferMoneyServlet() {
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
		String category = "transactionbyadmin";
		request.getSession().setAttribute("category", category);
		String adminid = (String) request.getSession().getAttribute("adminid");
		request.getSession().setAttribute("adminid", adminid);
		// Long acno = (Long)request.getSession().getAttribute("accountno");
		// request.getSession().setAttribute("accountno",acno);
		PrintWriter pw = response.getWriter();
//	pw.println("<b>transfermoneypage</b>");
		try {
			pw.println("<head>" + "<title>tsrbank</title>"
					+ "<meta name='viewport' content='width=device-width.initial-scale=1' />"
					+ "<link rel='stylesheet' href='change_pass.css' />"
					+ "<link rel='icon' href='Picsart_24-11-05_08-57-42-856.png'/>" + "</head>");
			pw.println("<body>" + "<div id='logo_div'>" + "<img id='image' src='Picsart_24-10-02_22-19-03-595.png' />"
					+ "<div id='logo_text'>" + "<span style='font-size:2.6vw;'>ONGOLE</span>"
					+ "<p><span style='font-size:2.5vw; color:dodgerblue;'>T</span>REASURE <span style='font-size:2vw;'>S</span>AVINGS <span style='font-size:2vw; color:dodgerblue;'>R</span>GUKT</p>"
					+ "</div>" + "</div>"
					+ "<form id='transfer_money' action='pincredentialverificationpage' method='post'>"
					+ "<fieldset id='field' style='height:28.5vw'>" + "<table cellpadding='0' cellspacing='20'>"
					+ "<tr><td class='cat'>From Acc.no</td><td style='width:19vw; font-size:2vw;'><span>:</span><input type='tel' maxlength='16' minlength='16' name='fromaccountno' required /></td></tr>"
					+ "<tr><td class='cat'>To Acc.no</td><td style='width:19vw; font-size:2vw;'>:<input type='tel' maxlength='16' minlength='16' name='toaccountno' required /></td></tr>"
					+ "<tr><td class='cat'>Amount</td><td style='width:19vw; font-size:2vw;'>:<input type='tel' step='100' name='amount' required /></td></tr>"
					+ "<tr><td class='cat'>Admin authentication PIN</td><td style='width:19vw; font-size:2vw;'1>:<input type='password' name='password' required /></td></tr>"
					+ "</table>"
					+ "<input style='height:3vw; width:13vw;' type='submit' id='form_sub' value='Transfer'>"
					+ "</fieldset>" + "</form>" + "</body>");
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
