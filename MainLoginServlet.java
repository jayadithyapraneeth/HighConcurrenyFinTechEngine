package com.servlet.bank;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainLoginServlet
 */
@WebServlet("/mainloginpage")
public class MainLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
//    public MainLoginServlet() {
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
		pw.println("<head>\n" + "<title>tsrbank</title>\n"
				+ "<meta name='viewport' content='width=device-width, initial-scale=1' charset='ISO-8859-1' />\n"
				+ "<link rel='stylesheet' href='tsr.css' />"
				+ "<link rel='icon' href='Picsart_24-11-05_08-57-42-856.png'/>" + "</head>");
		String onclick = "document.location='adminloginpage'";
		pw.println("<body>" 
		        + "<div id='logo_div'>" 
				+ "<img id='image' src='Picsart_24-10-02_22-19-03-595.png' />"
				+ "<div id='logo_text'>"
				+ "<span style='font-size:2.6vw;'>ONGOLE</span>"
				+ "<p><span style='font-size:2.5vw; color:dodgerblue;'>T</span>REASURE <span style='font-size:2vw;'>S</span>AVINGS <span style='font-size:2vw; color:dodgerblue;'>R</span>GUKT</p>"
				+ "</div>" 
				+ "</div>" 
				+ "" 
				+ "<div id='admin_div'>" 
				+ "<button class='admin_but' onclick=" + onclick
				+ ">Admin</button><br /><!-- Admin_Login.html -->" 
				+ ""
				+ "<div id='customer_but' onclick='customer_options()'>"
				+ "<button class='admin_but'>Customer</button>"
				+ "<div id='customer_opt'>"
				+ "<a href='customerregisterpage' class='reg_login_but' >Register</a><br /><!-- Registration_form.html -->"
				+ "<a href='customerloginpage' class='reg_login_but'>Login</a><!-- Customer_Login.html -->" 
				+ "</div>"
				+ "</div>" + "" + "</div>"
				+ "<div id='instructions' style='padding-left: 5vw; float : left;margin : 8vw 2vw;font-size : 2vw; height : 30vw; width : 40vw; color : white;'>"
				+ "<p style='font-family:Roboto;'>\n"
				+ "A small deposit can make big difference in future. So, save money and be safe, open an account in tsrbank instantly. \n"
				+ "</p>\n" + "<p line-indent:1.5vw;> \n"
				+ "We're here to protect your money. tsrbank assures you a wealthy tomorrow. \n" + "</p>\n" + "\n"
				+ "</div>" + "</body>" + "" + "<script>" + "var flag=0;" + "function customer_options()" + "{"
				+ "		if(flag==0)" + "		{" + "			customer_opt.style.display='block';"
				+ "			flag=1;" + "		}" + "		else" + "		{"
				+ "			customer_opt.style.display='none';" + "			flag=0;" + "		}" + "}" + "</script>");
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
