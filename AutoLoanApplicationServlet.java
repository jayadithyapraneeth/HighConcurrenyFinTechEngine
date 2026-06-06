package com.servlet.bank;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AutoLoanApplicationServlet
 */
@WebServlet("/autoloanapplicationpage")
public class AutoLoanApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AutoLoanApplicationServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		Long accountno = (Long) request.getSession().getAttribute("accountno");
		request.getSession().setAttribute("accountno",accountno);
		request.getSession().setAttribute("typeofloan","autoloan");
		System.out.println(accountno);
		pw.println("<head>"
				+ "    <meta charset='UTF-8'>"
				+ "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
				+ "    <title>Auto Loan Application</title>"
				+ "    <link rel='stylesheet' href='loanapplicationssharedcss.css'>"
				+ "</head>"
				+ "");
		pw.println("<body>"
				+ "    <div class='container'>"
				+ "        <h1>Auto Loan Application</h1>"
				+ "        <form action='loanapplicationsubmissionpage'>"
				+ "            <div class='form-group'>"
				+ "                <label for='fullname'>Full Name</label>"
				+ "                <input type='text' name='fullname' required>"
				+ "            </div>"
				+ "            <div class='form-group'>"
			    + "                <label for='phoneno'>Phone No:</label>"
				+ "                <input type='number' name='phone' required>"
				+ "            </div>"
				+ "            <div class='form-group'>"
				+ "                <label for='car-model'>Car Model</label>"
				+ "                <input type='text' name='carmodel' required>"
				+ "            </div>"
				+ "            <div class='form-group'>"
				+ "                <label for='car-price'>Car Price ($)</label>"
				+ "                <input type='number' name='carprice' min='5000' required>"
				+ "            </div>"
				+ "            <div class='form-group'>"
				+ "                <label for='loan-term'>Loan Term (Months)</label>"
				+ "                <select name='loanterm' required>"
				+ "                    <option value='12'>12</option>"
				+ "                    <option value='24'>24</option>"
				+ "                    <option value='36'>36</option>"
				+ "                    <option value='48'>48</option>"
				+ "                    <option value='60'>60</option>"
				+ "                </select>"
				+ "            </div>"
				+ "            <div class='form-group'>"
				+ "                <label for='employment'>Employment Status</label>"
				+ "                <select name='employmentstatus' required>"
				+ "                    <option value='employed'>Employed</option>"
				+ "                    <option value='self-employed'>Self-Employed</option>"
				+ "                    <option value='unemployed'>Unemployed</option>"
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
