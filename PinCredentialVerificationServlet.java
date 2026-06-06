package com.servlet.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pincredentialverificationpage")
public class PinCredentialVerificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		PrintWriter pw = response.getWriter();
		pw.println("<b>pincredentialverificationpage</b>");

		String category = "transfermoney";
		Long accountno1 = (long) 0;
		Long accountno2 = (long) 0;

		switch (category) {

		case "checkbalance": 
			accountno2 = (Long)request.getSession().getAttribute("accountno");// sender
			break;
		case "transfermoney"://attribute will be an object but parameter will be a String
			accountno1 = Long.parseLong(request.getParameter("accountno"));// receiver
			accountno2 = Long.parseLong(request.getParameter("accountno1"));//sender from benchmark testing url
//			accountno2 = (Long)request.getSession().getAttribute("accountno");// sender from session
			break;
		case "transactionbyadmin":
			pw.println("transaction by admin");
			accountno1 = Long.parseLong(request.getParameter("toaccountno"));// to account
			accountno2 = Long.parseLong(request.getParameter("fromaccountno"));// from account
			request.getSession().setAttribute("fromaccountno", accountno2);
			break;
		}
		String password = request.getParameter("password");// entered password
		
		RingBufferOperator ringbufferoperator = RingBufferOperator.getInstance();
		String transactionid = accountno2.toString().substring(12) + String.valueOf(System.currentTimeMillis()).substring(0,8) + accountno1.toString().substring(12);
		
		if(ringbufferoperator != null) {
			
			try {
				ringbufferoperator.addTransactionTask(accountno2, accountno1, password, Long.parseLong(request.getParameter("amount")), Long.parseLong(transactionid));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		pw.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
