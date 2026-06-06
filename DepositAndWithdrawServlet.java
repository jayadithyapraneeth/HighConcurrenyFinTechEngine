package com.servlet.bank;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DepositAndWithdrawByAdminServlet
 */
@WebServlet("/depositandwithdrawpage")
public class DepositAndWithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet() //
	 */
//    public DepositAndWithdrawByAdminServlet() {
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
		String operator = (String) request.getSession().getAttribute("operator");
		switch (operator) {
		case "admin":
			request.getSession().setAttribute("operator", "admin");
			request.getSession().setAttribute("adminid", (String) request.getSession().getAttribute("adminid"));
			break;
		case "customer":
			request.getSession().setAttribute("operator", "customer");
			break;
		}

		pw.println("<head>" + "<title>tsrbank</title>"
				+ "<meta name='viewport' content='width=device-width.initial-scale=1' />"
				+ "<link rel='stylesheet' href='change_pass.css' />"
				+ "<link rel='icon' href='Picsart_24-11-05_08-57-42-856.png'/>" + "</head>" + "<body>"
				+ "<div id='dis'>" + "<div id='logo_div'>"
				+ "<img id='image' src='Picsart_24-10-02_22-19-03-595.png' />" + "<div id='logo_text'>"
				+ "<span style='font-size:2.6vw;'>ONGOLE</span>" + "<p><span\n"
				+ "style='font-size:2.5vw; color:dodgerblue;'>T</span>REASURE <span style='font-size:2vw;'>S</span>AVINGS <span style='font-size:2vw; color:dodgerblue;'>R</span>GUKT</p>"
				+ "</div></div>" + "<form action='depositandwithdrawalcredentialverificationpage' method='post'>"
				+ "<fieldset style='padding-left:11vw; height:auto;' id='field'>"
				+ "<table cellpadding='0' cellspacing='0'>" + "<tr>"
				+ "<td colspan='2' style='text-align:center; font-size:1.5vw; font-family:Roboto;'><span id='span' style='text-decoration:underline; margin-left:4vw;color : dodgerblue;'>select your option!</span></td>"
				+ "</tr>" + "<tr>" + "<td>"
				+ "<button onclick='withdraw_fun()' style='border:none; margin-left:1.5vw; background-color:transparent; color:white; font-size:1.7vw; font-weight:bold; font-family:Roboto;' id='withdraw'>Withdraw</button>"
				+ "</td>" + "<td>"
				+ "<button onclick='deposit_fun()' style='border:none; margin-left:8vw;background-color:transparent; color:white; font-size:1.7vw; font-weight:bold; font-family:Roboto;' id='deposit'>Deposit</button>"
				+ "</td>" + "</tr>" + "</table>" + "<table cellpadding='0' cellspacing='20'>" + "<tr>" + "</tr>"
				+ "<tr>" + "<td class='cat'>Acc. no</td>"
				+ "<td><span style='font-weight:bold; font-size:1.5vw;'>: </span><input name='accountno' type='tel' maxlength='16' minlength='16' required /></td>"
				+ "</tr>" + "<tr>" + "<td class='cat'>Amount</td>"
				+ "<td><span style='font-weight:bold; font-size:1.5vw;'>: </span><input name='amount' type='tel' step='100' required /></td>"
				+ "</tr>" + "<tr>" + "<td class='cat'>Enter PIN</td>"
				+ "<td><span style='font-weight:bold; font-size:1.5vw;'>: </span><input name='password' type='password' required /></td>"
				+ "</tr>" + "</table>" + "<table style='width:30vw;' cellpadding='0' cellspacing='0' >"
				+ "<tr><td colspan='2' style='text-align:center;'>"
				+ "<input name='category' type='submit' id='form_sub' style='display:none' required /></td></tr>"
				+ "</table>" + "</fieldset>" + "</form>" + "</div></div>" + "</body>" + "<script>"
				+ "function deposit_fun()" + "{ span.style.color='white';" + "  deposit.style.fontSize='2.4vw';"
				+ "	deposit.style.color='dodgerblue';" + "	withdraw.style.fontSize='1.7vw';"
				+ "	withdraw.style.color='white'; form_sub.style.display='block'; form_sub.style.marginLeft='10vw'; "
				+ "	form_sub.value='deposit';}" + "function withdraw_fun()" + "{ span.style.color='white';"
				+ "  withdraw.style.fontSize='2.4vw';" + "	withdraw.style.color='dodgerblue';"
				+ "	deposit.style.fontSize='1.7vw';"
				+ "	deposit.style.color='white'; form_sub.style.display='block'; form_sub.style.marginLeft='10vw';"
				+ "	form_sub.value='withdraw';}" + "</script>");

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
