package com.servlet.bank;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerRegisterServlet
 */
@WebServlet("/customerregisterpage")
public class CustomerRegisterServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		try {
			pw.println("<head>" + "<title>tsrbank</title>"
					+ "<meta name='viewport' content='width=device-width, initial-scale=1' />"
					+ "<link rel='stylesheet' href='Registration_from.css' />"
					+ "<link rel='icon' href='Picsart_24-11-05_08-57-42-856.png'/>" + "</head>");
			pw.println("<body>" + "<div id='logo_div'>" + "<img id='image' src='Picsart_24-10-02_22-19-03-595.png' />"
					+ "<div id='logo_text'>" + "<span style='font-size:2.6vw;'>ONGOLE</span>"
					+ "<p><span style='font-size:2.5vw; color:dodgerblue;'>T</span>REASURE <span style='font-size:2vw;'>S</span>AVINGS <span style='font-size:2vw; color:dodgerblue;'>R</span>GUKT</p>"
					+ "</div>" + "</div>");
			pw.println("<form name=temp_cust_details action='newapplicationsubmissionpage'>"
					+ "<fieldset id='new_cust_details'>" + "<legend>Registration Form</legend>");
			pw.println("<table cellspacing='4vw' cellpadding='3vw'>" + "<tr>"
					+ "<td><label class='cat' for ='fname'>First name <b>*</b></td>"
					+ "<td><b class='cat'>:</b><input type='text' id='fname' name='fname' required></td>\n" + "</tr>");
			pw.println("<tr>\n" + "<td><label class='cat' for ='mname'>Middle name</td>\n"
					+ "<td><b class='cat'>:</b><input type='text' id='mname' name='mname'/></td>\n" + "</tr>");
			pw.println("<tr>\n" + "<td><label class='cat' for ='lname'>Last name <b>*</b></td>\n"
					+ "<td><b class='cat'>:</b><input type='text' id='lname' name='lname'required></td>\n" + "</tr>");
			pw.println("<tr>\n" + "<td><label class='cat' for='gend'>Gender</td>\n" + "<td><b class='cat'>:</b>\n"
					+ "<input type='radio' id='male' name='gender' value='male' /><label for='male'><label class='cat' for='male'>Male</label>\n"
					+ "<input type='radio' id='female' name='gender' value='female' /><label for='female'><label class='cat' for='female'>Female</label>\n"
					+ "<input type='radio' id='rns' name='gender' value=rathernotsay' /><label for='rns'><label class='cat' for='rns'>rather not say</label>\n"
					+ "</td>\n" + "</tr>");
			pw.println("<tr>\n" + "<td><label class='cat' for='DOB'>Date of birth <b>*</b></label></td>\n"
					+ "<td><b class='cat'>:</b><input type='date' id='DOB' name='DOB' required /></td>\n" + "</tr>");
			pw.println("<tr>\n" + "<td><label class='cat' for='nation'>Nationality <b>*</b></label></td>\n"
					+ "<td><b class='cat'>:</b><input type='text' value='Indian' name='nation' required /></td>\n"
					+ "</tr>");
			pw.println("<tr>\n" + "<td><label class='cat' for='phno'>Phone no <b>*</b></label>\n"
					+ "<td><b class='cat'>:</b><input type='tel' pattern='[6789]{1}[0-9]{9}' maxlength='10' name='phno' required /></td>\n"
					+ "</tr>");
			pw.println("<tr>\n" + "<td><label class='cat' for='mail'>E-mail</label></td>\n"
					+ "<td><b class='cat'>:</b><input type='email' id='mail'  name='email'/></td>\n" + "</tr>");
			pw.println("<tr>\n" + "<td><label class='cat' for='state_Select'>State <b>*</b></label></td>\n"
					+ "<td><b class='cat'>:</b>\n" + "<select name='state'>\n" + "<option>Andhra Pradesh</option>\n"
					+ "<option>Telangana</option>\n" + "<option>Tamilnadu</option>\n" + "<option>Karnataka</option>\n"
					+ "<option>Kerala</option>\n" + "<option>Odissa</option>\n" + "<option>Uttar Pradesh</option>"
					+ "<option>Madhya Pradesh</option>" + "<option>Goa</option>"
					// + "<option></option>"
					// + "<option></option>"
					// + "<option></option>"
					// + "<option></option>"
					// + "<option></option>"
					// + "<option></option>"
					// + "<option></option>"
					+ "</select>\n" + "</td>\n" + "</tr>");
			pw.println("<tr>\n" + "<td><label class='cat' for='Dist'>District <b>*</b></label></td>\n"
					+ "<td><b class='cat'>:</b>\n" + "<select name='district'>\n" + "<option>Prakasam</option>\n"
					+ "<option>Baptla</option>\n" + "<option>Palnadu</option>\n" + "<option>Krishna</option>\n"
					+ "<option>Guntur</option>\n" + "<option>Kadapa</option>\n" + "<option>West Godavari</option>\n"
					+ "<option>East Godavari</option>\n" + "<option>Srikakulam</option>\n"
					+ "<option>Anakapalli</option>" + "<option>Ananthapuramu</option>" + "<option>Annamayya</option>"
					+ "<option>Chittoor</option>" + "<option>Konaseema</option>" + "<option>Eluru</option>"
					+ "<option>Kakinada</option>" + "<option>Kurnool</option>" + "<option>Nandyal</option>"
					+ "<option>NTR</option>" + "<option>Parvathipuram Manyam</option>"
					+ "<option>Sri Potti Sriramlu</option>" + "<option>Nellore</option>"
					+ "<option>Sri Sathya Sai</option>" + "<option>Tirupati</option>" + "<option>Vishakapatnam</option>"
					+ "<option>Vijayanagaram</option>" + "<option>Alluri Sitharama Raju</option>" + "</select>\n"
					+ "</td>\n" + "</tr>");
			pw.println("<tr>\n" + "<td><label class='cat' for='village'>Village <b>*</b></label></td>\n"
					+ "<td><b class='cat'>:</b><input type='text' name='village' required /></td>\n" + "</tr>");
			pw.println("<tr>\n" + "<td><label class='cat' for='pincode'>Pincode <b>*</b></td>\n"
					+ "<td><b class='cat'>:</b><input type='tel' id='pincode' name='pincode' minlength='6' maxlength='6' required /></td>\n"
					+ "</tr>");
			pw.println("<tr>\n" + "<td><label class='cat' for='adhar'>Adhar no <b>*</b></label></td>\n"
					+ "<td><b class='cat'>:</b><input type='tel' name='aadharid' pattern='[0-9]{4}-[0-9{4}]-[0-9]{4}-[0-9]}4{' maxlength='16' /></td>\n"
					+ "</tr>");
			pw.println("<tr>\n" + "<td><label class='cat' for='income'>Annual income <b>*</b></label></td>\n"
					+ "<td><b class='cat'>:</b><input type='tel' name='annualincome' placeholder='as per the income certificate' required /></td>\n"
					+ "</tr>\n" + "</table>");
			pw.println("<input type='submit' id='form_sub' value='Register' />\n" + "</fieldset>\n" + "\n" + "</form>\n"
					+ "</body>");
		} catch (Exception e) {
			pw.println(e.getMessage());
			e.printStackTrace();
		}
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
