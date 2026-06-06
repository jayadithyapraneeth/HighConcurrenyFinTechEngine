package com.servlet.bank;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ThankYouServlet
 */
@WebServlet("/thankyoupage")
public class ThankYouServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
//    public ThankYouServlet() {
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
		pw.println("<head>" + "<title>tsrbank</title>"
				+ "<meta name='viewport' content='width=device-width, initial-scale=1' />"
				+ "<link rel='stylesheet' href='tq.css' />"
				+ "<link rel='icon' href='Picsart_24-11-05_08-57-42-856.png'/>" + "</head>");
		pw.println("<body>" + "<div id='logo_div'>" + "<img id='image' src='Picsart_24-10-02_22-19-03-595.png' />"
				+ "<div id='logo_text'>" + "<span style=font-size:2.6vw;'>ONGOLE</span>"
				+ "<p><span style='font-size:2.5vw; color:dodgerblue;'>T</span>REASURE <span style='font-size:2vw;'>S</span>AVINGS <span style='font-size:2vw; color:dodgerblue;'>R</span>GUKT</p>"
				+ "</div>\n" + "</div>\n" + "\n" + "<div id='tq_pop' style='height:21.5vw;'>"
				+ "<p id='tq_text' style='font-size:1.7vw; font-family:Roboto;'>");
		String page = (String) request.getSession().getAttribute("pagename");

		switch (page) {
		// new application registration
		case "newapplication":// new application submission
			pw.println(
					"<span style='font-size:2.5vw; text-decoration:underline;'>Thank you</span> for choosing TSR Bank !<br /><br />"
							+ "Your application has been recieved !<br />"
							+ "Your application status will be sent to you through the E-mail or SMS.<br />"
							+ "__We are happy to have your trust in us__</p>"
							+ "<a href='mainloginpage'><button>Done</button></a>");
			break;
		case "adminpasswordchange":// admin password changed successfully
			// String loc = "\'adminpasswordchange\'";
			pw.println("<span style='font-size:2.5vw; text-decoration:underline;'> Done </span><br/><br/>"
					+ "Your Profile Password has been Changed Successfully !<br />"
					+ "<a href='adminpasswordchangepage'><button>Done</button></a>");
			break;
		case "customerpasswordchange": // customer password changed successfully
			pw.println("<span style='font-size:2.5vw; text-decoration:underline;'> Done </span><br /><br />"
					+ "Your Account Password has been Changed Successfully !<br />"
					+ "<a href='customerpasswordchangepage'><button>Done</button></a>");
			break;
		case "checkbalance": // showing current account balance of the customer
			pw.println(
					"<span style='font-size:2.5vw; text-decoration:underline;'>Thank you</span> for choosing TSR Bank !<br /><br />"
							+ "Your tsrbank Account with Ac/No " + request.getSession().getAttribute("accountno")
							+ " is having a Current Balance of &#8377;"
							+ request.getSession().getAttribute("currentbalance") + "/-<br />"
							+ "<a href='checkbalancepage'><button>Thanks</button></a>");
			break;
		case "transfermoney": // customer transferred the money successfully
			pw.println(
					"<span style='font-size:2.5vw; text-decoration:underline;'>Thank you</span> for choosing TSR Bank !<br /><br />"
							+ "Transaction Successfull !<br />"
							+ "<a href='transfermoneypage'><button>Thanks</button></a>");
			break;
		case "nonewapplications": // no new applications submitted
			pw.println("<span style='font-size:2vw; text-decoration:underline;'> Empty </span><br/><br/>"
					+ "There are No New Applications present !<br />"
					+ "<a href='applicationapprovalpage'><button>Okay</button></a>");
			break;
		case "insufficientbalance":
			String reason = (String) request.getSession().getAttribute("category");
			pw.println(
					"<span style='font-size:2vw; text-decoration:underline;'> Insufficient Balance </span><br/><br/>");
			switch (reason) {
			case "customer":
				pw.println("There is insufficient balance in your account with Ac/No "
						+ (Long) request.getSession().getAttribute("accountno")
						+ " to withdraw the required money! <br>And your current account balance is "
						+ (Long) request.getSession().getAttribute("currentbalance") + " <br />"
						+ "<a href='depositandwithdrawpage'><button>Okay</button></a>");
				break;
			default:
				pw.println("There is insufficient balance in the account with Ac/No "
						+ (Long) request.getSession().getAttribute("fromaccountno")
						+ " to make the transaction !<br>And your current account balance is "
						+ (Long) request.getSession().getAttribute("currentbalance") + " <br />"
						+ "<a href='transfermoneypage'><button>Okay</button></a>");
				break;
			}
			break;
		case "transactionbyadmin": // customer transferred the money successfully
			pw.println("<span style='font-size:2.5vw; text-decoration:underline;'> Done </span><br /><br />"
					+ "Transaction Successfull !<br />" + "<a href='transactionbyadminpage'><button>Done</button></a>");
			break;
		case "nocustransactionsdone":// no transactions done in a particular date
			pw.println(
					"<span style='font-size:2.5vw; text-decoration:underline;'>Thank you</span> for choosing TSR Bank !<br /><br />"
							+ (String) request.getSession().getAttribute("message") + "<br />"
							+ "<a href='customertransactionhistorypage'><button>Thanks</button></a>");
			break;
		case "nobanktransactionsdone":// no transactions done in a particular date
			pw.println("<span style='font-size:2.5vw; text-decoration:underline;'>Empty !</span><br /><br />"
					+ "No transactions have been made on that particular date !<br />"
					+ "<a href='banktransactionhistorypage'><button>Okay</button></a>");
			break;
		case "deposit":
			pw.println("<span style='font-size:2.5vw; text-decoration:underline;'>Done !</span><br /><br />"
					+ "Deposited Successfully. <br />" + "<a href='depositandwithdrawpage'><button>Okay</button></a>");
			break;
		case "withdraw":
			pw.println("<span style='font-size:2.5vw; text-decoration:underline;'>Done !</span><br /><br />"
					+ "Withdrawal Successful.<br />" + "<a href='depositandwithdrawpage'><button>Okay</button></a>");
			break;
		case "studentloan": 
			pw.println("<span style='font-size:2.5vw; text-decoration:underline;'>Thank you</span> for choosing TSR Bank !<br /><br />"
					+"Application submitted successfully and the response will be mailed after background check.<br>"
					+ "<a href='loanapplicationshubpage'><button>Thanks</button></a>");
			break;
		case "autoloan": 
			pw.println("<span style='font-size:2.5vw; text-decoration:underline;'>Thank you</span> for choosing TSR Bank !<br /><br />"
					+"Application submitted successfully and the response will be mailed after background check.<br>"
					+ "<a href='loanapplicationshubpage'><button>Thanks</button></a>");
			break;
		case "mortgageloan": 
			pw.println("<span style='font-size:2.5vw; text-decoration:underline;'>Thank you</span> for choosing TSR Bank !<br /><br />"
					+"Application submitted successfully and the response will be mailed after background check.<br>"
					+ "<a href='loanapplicationshubpage'><button>Thanks</button></a>");
			break;
		case "personalloan": 
			pw.println("<span style='font-size:2.5vw; text-decoration:underline;'>Thank you</span> for choosing TSR Bank !<br /><br />"
					+"Application submitted successfully and the response will be mailed after background check.<br>"
					+ "<a href='loanapplicationshubpage'><button>Thanks</button></a>");
			break;

		}
		pw.println("</div>" + "</body>");
		
		pw.close();

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
