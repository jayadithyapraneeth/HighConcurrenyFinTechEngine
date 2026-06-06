package com.servlet.bank;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeLoanApplicationServlet
 */
@WebServlet("/mortgageloanapplicationpage")
public class MortgageLoanApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MortgageLoanApplicationServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		
	//	request.getSession().setAttribute("category","loan");
		Long accountno = (Long) request.getSession().getAttribute("accountno");
		request.getSession().setAttribute("accountno",accountno);
		request.getSession().setAttribute("typeofloan","mortgageloan");
		System.out.println(accountno);
		
		pw.println("<head>"
				+ "    <meta charset='UTF-8'>"
				+ "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
				+ "    <title>Mortgage Loan Application</title>"
				+ "    <link rel='stylesheet' href='loanapplicationssharedcss.css'>"
				+ "</head>");
		pw.println("<body>"
				+ "    <div class='container'>"
				+ "        <h1>Mortgage Loan Application</h1>"
				+ "        <form action='loanapplicationsubmissionpage'>"
				+ "            <div class='form-group'>"
				+ "                <label for='fullname'>Full Name</label>"
				+ "                <input type='text' name='fullname' required>"
				+ "            </div>"
				+ "            <div class='form-group'>"
				+ "                <label for='email'>Email</label>"
				+ "                <input type='email' name='email' required>"
				+ "            </div>"
				+ "            <div class='form-group'>"
				+ "                <label for='phone'>Phone Number</label>"
				+ "                <input type='tel' name='phone' required>"
				+ "            </div>"
				+ "            <div class='form-group'>"
				+ "                <label for='property-value'>Property Value ($)</label>"
				+ "                <input type='number' name='propertyvalue' min='50000' required>"
				+ "            </div>"
				+ "            <div class='form-group'>"
				+ "                <label for='down-payment'>Down Payment ($)</label>"
				+ "                <input type='number' name='downpayment' min='0' required>"
				+ "            </div>"
				+ "            <div class='form-group'>"
				+ "                <label for='loan-term'>Loan Term (Years)</label>"
				+ "                <select name='loanterm' required>"
				+ "                    <option value='15'>15</option>"
				+ "                    <option value='30'>30</option>"
				+ "                </select>"
				+ "            </div>"
				+ "            <button type='submit'>Submit Application</button>"
				+ "        </form>"
				+ "    </div>"
				+ "</body>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
