package com.servlet.bank;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoanApplicationServlet
 */
@WebServlet("/personalloanapplicationpage")
public class PersonalLoanApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public PersonalLoanApplicationServlet() {
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
		request.getSession().setAttribute("typeofloan","personalloan");
		System.out.println(accountno);
		
		try {
			pw.println("<head>"
					+ "    <meta charset='UTF-8'>"
					+ "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
					+ "    <title>Personal Loan Application</title>"
					+ "    <link rel='stylesheet' href='loanapplicationssharedcss.css'>"
					+ "</head>"
					+ "");
			pw.println("<body>"
					+ "    <div class='container'>"
					+ "        <h1>Personal Loan Application</h1>"
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
					+ "                <label for='amount'>Loan Amount ($)</label>"
					+ "                <input type='number' name='loanamount' min='5000' max='1000000' required>"
					+ "            </div>"
					+ "            <div class='form-group'>"
					+ "                <label for='purpose'>Loan Purpose</label>"
					+ "                <select name='loanpurpose' required>"
					+ "                    <option value=''>Select</option>"
					+ "                    <option value='debt'>Debt Consolidation</option>"
					+ "                    <option value='medical'>Medical Expenses</option>"
					+ "                    <option value='home'>Home Improvement</option>"
					+ "                    <option value='other'>Other</option>"
					+ "                </select>"
					+ "            </div>"
					+ "            <button type='submit'>Submit Application</button>"
					+ "        </form>"
					+ "    </div>"
					+ "</body>");
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
