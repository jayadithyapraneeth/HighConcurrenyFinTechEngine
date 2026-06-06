package com.servlet.bank;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentLoanApplicationServlet
 */
@WebServlet("/studentloanapplicationpage")
public class StudentLoanApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public StudentLoanApplicationServlet() {
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
		request.getSession().setAttribute("typeofloan","studentloan");
		System.out.println("studentloan:"+accountno);
		
		pw.println("<head>"
				+ "    <meta charset='UTF-8'>"
				+ "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
				+ "    <title>Student Loan Application</title>"
				+ "    <link rel='stylesheet' href='loanapplicationssharedcss.css'>"
				+ "</head>");
		pw.println("<body>"
				+ "    <div class='container'>"
				+ "        <h1>Student Loan Application</h1>"
				+ "        <form action='loanapplicationsubmissionpage'>"
				+ "            <div class='form-group'>"
				+ "                <label for='fullname'>Full Name</label>"
				+ "                <input type='text' name='fullname' required>"
				+ "            </div>"
				+ "            <div class='form-group'>"
				+ "                <label for='university'>University/Institution</label>"
				+ "                <input type='text' name='universityname' required>"
				+ "            </div>"
				+ "            <div class='form-group'>"
				+ "                <label for='degree'>Degree Program</label>"
				+ "                <input type='text' name='graduationprogramname' required>"
				+ "            </div>"
				+ "            <div class='form-group'>"
				+ "                <label for='amount'>Loan Amount ($)</label>"
				+ "                <input type='number' name='loanamount' min='1000' required>"
				+ "            </div>"
				+ "            <div class='form-group'>"
				+ "                <label for='graduation'>Expected Graduation Year</label>"
				+ "                <input type='number' name='expectedgraduationyear' min='2023' max='2030' required>"
				+ "            </div>"
				+ "            <div class='form-group'>"
				+ "                <label for='phoneno'>Phone No</label>"
				+ "                <input type='number' name='phone' required>"
				+ "            </div>"
				+ "            <div class='form-group'>"
				+ "                <label for='email'>Email Id</label>"
				+ "                <input type='text' name='email' >"
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
