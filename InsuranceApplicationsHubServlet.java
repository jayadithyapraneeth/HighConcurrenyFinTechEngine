package com.servlet.bank;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsuranceApplicationsHubServlet
 */
@WebServlet("/insuranceapplicationshubpage")
public class InsuranceApplicationsHubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public InsuranceApplicationsHubServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		
		response.setContentType("text/html");
		
		Object acno = request.getSession().getAttribute("accountno");//we will convert this object to long if we need to use it in this page
		//long accoutno = (Long) acno;
		request.getSession().setAttribute("accountno", acno);
		
		pw.println("<!DOCTYPE html>");
        pw.println("<html lang='en'>");
		pw.println("<head>"
				+ "    <meta charset='UTF-8'>"
				+ "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
				+ "    <title>Bank Insurance Services</title>"
				+ "    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css'>"
				+ "    <link rel='stylesheet' href='insuranceaplicationshubcss.css'>"
				+ "</head>"
				+ "<body>"
				+ "    <!-- Content remains unchanged -->"
				+ "<div class='container'>"
				+ "        <header>"
				+ "            <h1><i class='fas fa-shield-alt'></i> Bank Insurance Services</h1>"
				+ "            <p>Protect what matters most with our comprehensive insurance solutions</p>"
				+ "        </header>"
				+ "        "
				+ "        <div class='services'>"
				+ "            <div class='service-card'>"
				+ "                <div class='service-icon'>"
				+ "                    <i class='fas fa-heartbeat'></i>"
				+ "                </div>"
				+ "                <h2>Life Insurance</h2>"
				+ "                <p>Secure your family's financial future with our flexible life insurance plans tailored to your needs.</p>"
				+ "                <a href='lifeinsuranceapplicationpage' class='btn'>Apply Now</a>"
				+ "            </div>"
				+ "            "
				+ "            <div class='service-card'>"
				+ "                <div class='service-icon'>"
				+ "                    <i class='fas fa-car'></i>"
				+ "                </div>"
				+ "                <h2>Vehicle Insurance</h2>"
				+ "                <p>Comprehensive coverage for your car, bike, or commercial vehicle with attractive premiums.</p>"
				+ "                <a href='vehicleinsuranceapplicationpage' class='btn'>Apply Now</a>"
				+ "            </div>"
				+ "            "
				+ "            <div class='service-card'>"
				+ "                <div class='service-icon'>"
				+ "                    <i class='fas fa-home'></i>"
				+ "                </div>"
				+ "                <h2>Home Insurance</h2>"
				+ "                <p>Protect your home and belongings against natural disasters, theft, and other unforeseen events.</p>"
				+ "                <a href='homeinsuranceapplicationpage' class='btn'>Apply Now</a>"
				+ "            </div>"
				+ "        </div>"
				+ "        "
				+ "        <div class='features'>"
				+ "            <h2>Why Choose Our Insurance Services?</h2>"
				+ "            <div class='feature-grid'>"
				+ "                <div class='feature'>"
				+ "                    <i class='fas fa-shield-alt'></i>"
				+ "                    <h3>Comprehensive Coverage</h3>"
				+ "                    <p>Our policies offer extensive protection tailored to your specific needs.</p>"
				+ "                </div>"
				+ "                <div class='feature'>"
				+ "                    <i class='fas fa-hand-holding-usd'></i>"
				+ "                    <h3>Competitive Premiums</h3>"
				+ "                    <p>Get the best coverage at the most affordable rates in the market.</p>"
				+ "                </div>"
				+ "                <div class='feature'>"
				+ "                    <i class='fas fa-headset'></i>"
				+ "                    <h3>24/7 Support</h3>"
				+ "                    <p>Our customer service team is always ready to assist you anytime.</p>"
				+ "                </div>"
				+ "                <div class='feature'>"
				+ "                    <i class='fas fa-bolt'></i>"
				+ "                    <h3>Quick Claims</h3>"
				+ "                    <p>Fast and hassle-free claim settlement process for your convenience.</p>"
				+ "                </div>"
				+ "            </div>"
				+ "        </div>"
				+ "    </div>"
				+ "    <script src='insuranceapplicationshubcss.js'></script>"
				+ "</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
