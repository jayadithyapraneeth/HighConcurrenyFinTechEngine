package com.servlet.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsuranceApplicationSubmissionServlet
 */
@WebServlet("/insuranceapplicationsubmissionpage")
public class InsuranceApplicationSubmissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public InsuranceApplicationSubmissionServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String acno = request.getSession().getAttribute("accountno").toString();//to convert a String to a primitive datatype we can use "parse" methods but to convert a primitive datatype into a String we must use the method ".toString()"
		System.out.println("submissionpage:"+acno);
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	
		
try(Connection conn = new DBCP().getConnection()){
			
		switch((String)request.getSession().getAttribute("typeofinsurance")) {
			
		    default : //Life Insuarance
		    	PreparedStatement pstmt = conn.prepareStatement("insert into lifeinsurancedetails(Accountno,FullName,Weight,Smoker,CoverageAmount,Term,PolicyType,EastimatedPremium,BeneficiaryFullName,ReletionshipWithBeneficiary,DOB,Gender,Occupation,AnnualIncome,Address,Height,Date) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())");
		    	
		    	
				String email = request.getParameter("email");
				Long phno = Long.parseLong(request.getParameter("phone"));
				String fullname = request.getParameter("fullname");
				String dob = request.getParameter("dob");
				String gender = (String) request.getParameter("gender");
				String occupation = (String) request.getParameter("occupation");
				int annualincome = Integer.parseInt(request.getParameter("annualincome"));
				String address = (String) request.getParameter("address");
				int height = Integer.parseInt(request.getParameter("height"));
				int weight = Integer.parseInt(request.getParameter("weight"));
				String smoker = (String) request.getParameter("smoker");
				int totalcoverage = Integer.parseInt(request.getParameter("totalcoverage"));
				int policyterm = Integer.parseInt(request.getParameter("policyterm"));
				String policytype = (String) request.getParameter("policytype");
				int premium = Integer.parseInt(request.getParameter("premium"));
				//int coveragedisplay = (int) request.getParameter("coverage-display");
				//int policytermdisplay = (int) request.getParameter("policyterm-display");
				//String policytypedisplay = (String) request.getParameter("policytype-display");
				String beneficiaryfullname = (String) request.getParameter("beneficiaryname");
				String relationshipwithbeneficiary = (String) request.getParameter("relationshipwithbeneficiary");
				
				pstmt.setLong(1,Long.parseLong(acno));//all are digits
				pstmt.setString(2,fullname);
				pstmt.setInt(3,weight);
				pstmt.setString(4,smoker);
				pstmt.setInt(5,totalcoverage);
				pstmt.setInt(6,policyterm);
				pstmt.setString(7, policytype);
				pstmt.setInt(8,premium);
				pstmt.setString(9, beneficiaryfullname);
				pstmt.setString(10, relationshipwithbeneficiary);
				pstmt.setString(11, dob);
				pstmt.setString(12, gender);
				pstmt.setString(13, occupation);
				pstmt.setInt(14, annualincome);
				pstmt.setString(15, address);
				pstmt.setInt(16, height);
	            if(pstmt.executeUpdate() == 1) {
	            	//request.getSession().setAttribute("pagename","insuarnceapplication");
	            	conn.close();
		           	response.sendRedirect("thankyoupage");
		           	System.out.println("Application Submitted Successfully");
		        }else {
		        	System.out.println("Application not submitted");
		        }
	            break;//automatically connection closes as try completes here but we can also close it manually as shown above
	            
		    case "vehicleinsurance" : 
				// Create variables for vehicle insurance using attribute names
				String vehicletype = request.getParameter("vehicletype");
				String vehiclemake = request.getParameter("vehiclemake");
				String vehiclemodel = request.getParameter("vehiclemodel");
				int manufacturingyear = Integer.parseInt(request.getParameter("manufacturingyear"));
				String vehicleregnumber = request.getParameter("vehicleregnumber");
				int currentmarketvalue = Integer.parseInt(request.getParameter("currentmarketvalue"));
				String vehicleownername = request.getParameter("vehicleownername");
				String drivinglicense = request.getParameter("drivinglicense");
				String vehicleaddress = request.getParameter("vehicleaddress");
				String coveragetype = request.getParameter("coveragetype");
				String policyaddons = request.getParameter("policyaddons");
				int prevclaims = Integer.parseInt(request.getParameter("prevclaims"));
				int ncbonusclaims = Integer.parseInt(request.getParameter("ncbonusclaims"));
				String policystart = request.getParameter("policystart");
				int vehiclepremium = Integer.parseInt(request.getParameter("vehiclepremium"));
				
		    	String fullname1 = request.getParameter("fullname");
		    	String autoloanid = ""; //loanid is dynamic so we have to name it accordingly
				if(vehiclemodel.length() >= 4) {
					autoloanid =  new String("2222"+acno.substring(0,6)+vehiclemodel.substring(0,3));
				}
				PreparedStatement pstmtVehicle = conn.prepareStatement(
			        "INSERT INTO vehicleinsurance(AccountNo,VehicleType,VehicleMake,VehicleModel,ManufacturingYear,VehicleRegNumber,CurrentMarketValue,VehicleOwnerName,DrivingLicense,VehicleAddress,CoverageType,PolicyAddons,PrevClaims,NCBonusClaims,PolicyStart,VehiclePremium,Date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())"
			    );
			    pstmtVehicle.setLong(1, Long.parseLong(acno));
			    pstmtVehicle.setString(2, vehicletype);
			    pstmtVehicle.setString(3, vehiclemake);
			    pstmtVehicle.setString(4, vehiclemodel);
			    pstmtVehicle.setInt(5, manufacturingyear);
			    pstmtVehicle.setString(6, vehicleregnumber);
			    pstmtVehicle.setInt(7, currentmarketvalue);
			    pstmtVehicle.setString(8, vehicleownername);
			    pstmtVehicle.setString(9, drivinglicense);
			    pstmtVehicle.setString(10, vehicleaddress);
			    pstmtVehicle.setString(11, coveragetype);
			    pstmtVehicle.setString(12, policyaddons);
			    pstmtVehicle.setInt(13, prevclaims);
			    pstmtVehicle.setInt(14, ncbonusclaims);
			    pstmtVehicle.setString(15, policystart);
			    pstmtVehicle.setInt(16, vehiclepremium);
			    
			    if(pstmtVehicle.executeUpdate() == 1) {
			        //PrintWriter pw = response.getWriter();
			        //request.getSession().setAttribute("pagename","vehicleinsurance");
			    	conn.close();
			        response.sendRedirect("thankyoupage");
			        System.out.println("Vehicle Insurance Application Submitted Successfully");
			    } else {
			        System.out.println("Vehicle Insurance Application not submitted");
			    }
				break;
				
		    case "homeinsurance" :
		    	// Create variables for home insurance using attribute names
		        String propertytype = request.getParameter("propertytype");
		        int builtyear = Integer.parseInt(request.getParameter("builtyear"));
		        int area = Integer.parseInt(request.getParameter("area"));
		        String propertyaddress = request.getParameter("propertyaddress");
		        int currentmarketvalue1 = Integer.parseInt(request.getParameter("propertyvalue"));
		        int contentsvalue = Integer.parseInt(request.getParameter("contentsvalue"));
		        String homeownername = request.getParameter("homeowner");
		        String phone = request.getParameter("phone");
		        String email1 = request.getParameter("email");
		        String coveragetypehome = request.getParameter("coveragetypehome");
		        String additionalprotections = request.getParameter("homeaddons");
		        String securitysystem = request.getParameter("securitysystem");
		        String fireextin = request.getParameter("fireextin");
		        String policystart1 = request.getParameter("policystart-home");
		        // Premium display variables (if needed)
		        int estimatedannualpremium = Integer.parseInt(request.getParameter("estimatedannualpremium"));
		        String propertyvalue_display = request.getParameter("propertyvalue-display");
		        String contentsvalue_display = request.getParameter("contentsvalue-display");
		        String homeaddons_display = request.getParameter("homeaddons-display");

		        PreparedStatement pstmtHome = conn.prepareStatement(
		            "INSERT INTO homeinsuranceapplication(AccountNo,PropertyType,BuiltYear,Area,PropertyAddress,PropertyValue,ContentsValue,HomeOwner,Phone,Email,CoverageTypeHome,HomeAddons,SecuritySystem,FireExtin,PolicyStartHome,PropertyValueDisplay,ContentsValueDisplay,HomeAddonsDisplay,Date) VALUES (?,?,?,?,?,?,now(),?,?,?,?,?,?,?,?,?,?,now(),now())"
		        );
		        pstmtHome.setLong(1, Long.parseLong(acno));
		        pstmtHome.setString(2, propertytype);
		        pstmtHome.setInt(3, builtyear);
		        pstmtHome.setInt(4, area);
		        pstmtHome.setString(5, propertyaddress);
		        pstmtHome.setInt(6, currentmarketvalue1);
		        pstmtHome.setInt(7, contentsvalue);
		        pstmtHome.setString(8, homeownername);
		        pstmtHome.setString(9, phone);
		        pstmtHome.setString(10, email1);
		        pstmtHome.setString(11, coveragetypehome);
		        pstmtHome.setString(12, additionalprotections);
		        pstmtHome.setInt(13, estimatedannualpremium);
		        pstmtHome.setString(14, securitysystem);
		        pstmtHome.setString(15, fireextin);
		        pstmtHome.setString(16, policystart1);
		        //pstmtHome.setString(16, propertyvalue_display);
		        //pstmtHome.setString(17, contentsvalue_display);
		        //pstmtHome.setString(18, homeaddons_display);
		        if(pstmtHome.executeUpdate() == 1) {
		        	conn.close();
		            response.sendRedirect("thankyoupage");
		            System.out.println("Home Insurance Application Submitted Successfully");
		        } else {
		            System.out.println("Home Insurance Application not submitted");
		        }
		        break;   
		        
	}
}catch (Exception e) {
	e.printStackTrace();
	pw.println("<h2>Error: " + e.getMessage() + "</h2>");
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