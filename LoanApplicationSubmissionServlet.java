package com.servlet.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
//import java.util.Scanner;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoanApplicationSubmissionServlet
 */
@WebServlet("/loanapplicationsubmissionpage")
public class LoanApplicationSubmissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoanApplicationSubmissionServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter();
		String acno = request.getSession().getAttribute("accountno").toString();//to convert a String to a primitive datatype we can use "parse" methods but to convert a primitive datatype into a String we must use the method ".toString()"
		System.out.println("submissionpage:"+acno);
	
		
		try(Connection conn = new DBCP().getConnection()){
			
			switch((String)request.getSession().getAttribute("typeofloan")) {
			
		    default : //Mortgage Loan //there is no default case, i should just give single case in the default case
		    	PreparedStatement pstmt = conn.prepareStatement("insert into mortgageloanapplication(LoanId,FullName,AccountNo,PropertyValue,DownPayment,Term,PhoneNo,EmailId,Date) values(?,?,?,?,?,?,?,?,now())");
		    	
		    	
				String email = request.getParameter("email");
				Long phno = Long.parseLong(request.getParameter("phone"));
				String fullname = request.getParameter("fullname");
				String propertyvalue1 =(String) request.getParameter("propertyvalue").toString();
				int propertyvalue2 = Integer.parseInt(propertyvalue1);
				
				int downpayment = Integer.parseInt(request.getParameter("downpayment"));
				int mortgageloanterm = Integer.parseInt(request.getParameter("loanterm"));
				
				String mortgageloanid = ""; //loanid is dynamic so we have to name it accordingly
				if(propertyvalue1.length() >= 4) {
					mortgageloanid =  new String("1111"+acno.substring(0,6)+propertyvalue1.substring(0,3));//all are digits
				}
				pstmt.setLong(1,Long.parseLong(mortgageloanid));//all are digits
				pstmt.setString(2,fullname);
				pstmt.setLong(3,Long.parseLong(acno));
				pstmt.setInt(4,propertyvalue2);
				pstmt.setInt(5,downpayment);
				pstmt.setInt(6,mortgageloanterm);
				pstmt.setLong(7, phno);
				pstmt.setString(8,email);
				//pstmt.setLong(0,acno);
	            if(pstmt.executeUpdate() == 1) {
	            
	            	conn.close();
	            	
	            	PrintWriter pw = response.getWriter();
	            	request.getSession().setAttribute("pagename","studentloan");
		           	response.sendRedirect("thankyoupage");
		           	System.out.println("Application Submitted Successfully");
		        }else {
		        	System.out.println("Application not submitted");
		        }
	            break;
	            
		    case "autoloan" : 
		    	String carmodel = request.getParameter("carmodel");
		    	String employmentstatus = request.getParameter("employmentstatus");
		    	int carprice = Integer.parseInt(request.getParameter("carprice"));
		    	int autoloanterm = Integer.parseInt(request.getParameter("loanterm"));
		    	long phno1 = Long.parseLong(request.getParameter("phone"));
		    	String fullname1 = request.getParameter("fullname");
		    	String autoloanid = ""; //loanid is dynamic so we have to name it accordingly
				if(carmodel.length() >= 4) {
					autoloanid =  new String("2222"+acno.substring(0,6)+carmodel.substring(0,3));
				}
				PreparedStatement pstmt1 = conn.prepareStatement("insert into autoloanapplication(LoanId,FullName,AccountNo,CarModel,CarPrice,Term,EmploymentStatus,Date) values(?,?,?,?,?,?,?,?,now())");
				pstmt1.setString(1,autoloanid);
				pstmt1.setString(2,fullname1);
				pstmt1.setLong(3,Long.parseLong(acno));
				pstmt1.setString(4,carmodel);
				pstmt1.setInt(5,carprice);
				pstmt1.setInt(6,autoloanterm);
				pstmt1.setLong(7, phno1);
				pstmt1.setString(8,employmentstatus);
				
				if(pstmt1.executeUpdate() == 1) {
					conn.close();
		           	PrintWriter pw = response.getWriter();
		           	request.getSession().setAttribute("pagename","studentloan");
		           	response.sendRedirect("thankyoupage");
		           	System.out.println("Application Submitted Successfully");
		        }else {
		        	System.out.println("Application not submitted");
		        }
				
				break;
				
		    case "personalloan" :
		    	
		    	LocalDate today = LocalDate.now();
		        DateTimeFormatter datepattern = DateTimeFormatter.ofPattern("ddMMyyyy");
		        String todaysdate = today.format(datepattern);
		        
		        String personalloanid = acno.substring(0,6)+todaysdate;
		        System.out.println(personalloanid);
		        String fullname2 = request.getParameter("fullname");
		        String loanpurpose = request.getParameter("loanpurpose");
		    	String email1 = request.getParameter("email");
		    	int loanamount = Integer.parseInt(request.getParameter("loanamount"));
		    	long phno2 = Long.parseLong(request.getParameter("phone"));
		    	
		    	
				PreparedStatement pstmt2 = conn.prepareStatement("insert into personalloanapplication(LoanId,FullName,AccountNo,LoanPurpose,LoanAmount,PhoneNo,EmailId,Date) values(?,?,?,?,?,?,?,now())");
				pstmt2.setLong(1,Long.parseLong(personalloanid));
				pstmt2.setString(2,fullname2);
				pstmt2.setLong(3,Long.parseLong(acno));
				pstmt2.setString(4,loanpurpose);
				pstmt2.setInt(5,loanamount);
				pstmt2.setLong(6,phno2);
				pstmt2.setString(7,email1);
				
				if(pstmt2.executeUpdate() == 1) {
					conn.close();
		           	PrintWriter pw = response.getWriter();
		           	request.getSession().setAttribute("pagename","studentloan");
		           	response.sendRedirect("thankyoupage");
		           	System.out.println("Application Submitted Successfully");
		        }else {
		        	System.out.println("Application not submitted");
		        }
				break;

		    case "studentloan" : 
		    	String studentloanid = "";
		    	String loanamount1 =(String) request.getParameter("loanamount").toString();
				int loanamount2 = Integer.parseInt(loanamount1);
		    	if(loanamount1.length() >= 4) {
					studentloanid =  new String("3333"+acno.substring(0,6)+loanamount1.substring(0,3));
				}
		    	
		    	String fullname3 = request.getParameter("fullname");
		    	String universityname = request.getParameter("universityname");
		    	String graduationprogramname = request.getParameter("graduationprogramname");
		    	String email2 = request.getParameter("email");
		    	int expectedgraduationyear = Integer.parseInt(request.getParameter("expectedgraduationyear"));
		    	long phno3 = Long.parseLong(request.getParameter("phone"));
		    	
		    	PreparedStatement pstmt3 = conn.prepareStatement("insert into studentloanapplication(LoanId,FullName,AccountNo,UniversityName,GraduationProgramName,LoanAmount,ExpectedGraduationYear,PhoneNo,EmailId,Date) values(?,?,?,?,?,?,?,?,?,now())");
		    	pstmt3.setLong(1,Long.parseLong(studentloanid));
		    	pstmt3.setString(2,fullname3);
		    	pstmt3.setLong(3,Long.parseLong(acno));
		    	pstmt3.setString(4,universityname);
		    	pstmt3.setString(5,graduationprogramname);
		    	pstmt3.setInt(6,loanamount2);
		    	pstmt3.setInt(7,expectedgraduationyear);
		    	pstmt3.setLong(8,phno3);
		    	pstmt3.setString(9,email2);
		    	
		    	if(pstmt3.executeUpdate() == 1) {
		    		conn.close();
		           	PrintWriter pw = response.getWriter();
		           	request.getSession().setAttribute("pagename","studentloan");
		           	response.sendRedirect("thankyoupage");
		           	System.out.println("Application Submitted Successfully");
		        }else {
		        	System.out.println("Application not submitted");
		        }
		    	break;
		    	
		}
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
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
