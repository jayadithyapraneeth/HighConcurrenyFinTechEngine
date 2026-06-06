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

/**
 * Servlet implementation class
 * DepositAndWithdrawalCredentialVerificationServlet
 */
@WebServlet("/depositandwithdrawalcredentialverificationpage")
public class DepositAndWithdrawalCredentialVerificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet() //
	 */
//    public DepositAndWithdrawalCredentialVerificationServlet() {
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

		pw.println("Verification Servlet");
		String category = request.getParameter("category");// category will be a Parameter
		Long accountno = Long.parseLong(request.getParameter("accountno"));
		request.getSession().setAttribute("accountno", accountno);
		String operator = (String) request.getSession().getAttribute("operator");
		String password = request.getParameter("password");
		Long amount = Long.parseLong(request.getParameter("amount"));
		try (Connection conn = new DBCP().getConnection()) {
			PreparedStatement currentbalance = conn
					.prepareStatement("select AccountBalance from customerdetails where AccountNo=?");
			currentbalance.setLong(1, accountno);
			ResultSet accountbalance = currentbalance.executeQuery();
			accountbalance.next();
			Long accbalance = accountbalance.getLong("AccountBalance");
			switch (operator) {
			case "admin":
				pw.println("admin");
				String adminid = (String) request.getSession().getAttribute("adminid");
				PreparedStatement admin = conn.prepareStatement("select Password from adminlogin where EmpId=?");
				admin.setString(1, adminid);
				ResultSet adminpassword = admin.executeQuery();
				if (adminpassword.next()) {
					if (adminpassword.getString("Password").equalsIgnoreCase(password)) {
						if (category.equalsIgnoreCase("deposit")) {
							PreparedStatement deposit = conn
									.prepareStatement("update customerdetails set AccountBalance=? where AccountNo=?");
							PreparedStatement deposithistory = conn.prepareStatement(
									"insert into transactionhistory(TransactionType,FromAccountNo,ToAccountNo,Amount,Date) values(?,?,?,?,now())");
							deposithistory.setString(1, "Deposit");
							deposithistory.setLong(2, 0000000000000000);
							deposithistory.setLong(3, accountno);
							deposithistory.setLong(4, amount);
							deposit.setLong(1, accbalance + amount);
							deposit.setLong(2, accountno);
							deposit.executeUpdate();
							deposithistory.executeUpdate();
							conn.close();
							request.getSession().setAttribute("pagename", "deposit");
							response.sendRedirect("thankyoupage");
						} else if (category.equalsIgnoreCase("withdraw")) {
							if (accbalance < amount) {
								conn.close();
								pw.println("Insufficient balance to withdraw money");
								request.getSession().setAttribute("pagename", "insufficientbalance");
								request.getSession().setAttribute("category", "customer");
								request.getSession().setAttribute("currentbalance", accbalance);
								response.sendRedirect("thankyoupage");
							} else if (accbalance >= amount) {
								PreparedStatement withdraw = conn.prepareStatement(
										"update customerdetails set AccountBalance=? where AccountNo=?");
								PreparedStatement withdrawalhistory = conn.prepareStatement(
										"insert into transactionhistory(TransactionType,FromAccountNo,ToAccountNo,Amount,Date) values(?,?,?,?,now())");
								withdrawalhistory.setString(1, "Withdraw");
								withdrawalhistory.setLong(2, accountno);
								withdrawalhistory.setLong(3, 0000000000000000);
								withdrawalhistory.setLong(4, amount);
								withdraw.setLong(1, accbalance - amount);
								withdraw.setLong(2, accountno);
								withdraw.executeUpdate();
								withdrawalhistory.executeUpdate();
								conn.close();
								request.getSession().setAttribute("pagename", "withdraw");
								response.sendRedirect("thankyoupage");
							}else {
								conn.close();
							}
						}

					} else {
						pw.println("Admin Password mismatch");
					}
				} else {
					pw.println("AdminId not found.Admin is not supposed to do the diposit or withdrawal");
				}
				break;
			case "customer":
				//pw.println("customer");
				PreparedStatement customer = conn
						.prepareStatement("select Password from customerlogin where AccountNo=?");
				customer.setLong(1, accountno);
				ResultSet customerpassword = customer.executeQuery();
				if (customerpassword.next()) {
					//pw.println("customerpassword.next()");
					if (customerpassword.getString("Password").equalsIgnoreCase(password)) {
						//pw.println("customerpassword.getString('Password').equalsIgnoreCase(password)");
						if (category.equalsIgnoreCase("deposit")) {
							//pw.println("category=deposit");
							PreparedStatement deposit = conn
									.prepareStatement("update customerdetails set AccountBalance=? where AccountNo=?");
							PreparedStatement deposithistory = conn.prepareStatement(
									"insert into transactionhistory(TransactionType,FromAccountNo,ToAccountNo,Amount,Date) values(?,?,?,?,now())");
							deposithistory.setString(1, "Deposit");
							deposithistory.setLong(2, 0000000000000000);
							deposithistory.setLong(3, accountno);
							deposithistory.setLong(4, amount);
							deposit.setLong(1, accbalance + amount);
							deposit.setLong(2, accountno);
							deposit.executeUpdate();
							deposithistory.executeUpdate();
							conn.close();
							request.getSession().setAttribute("pagename", "deposit");
							response.sendRedirect("thankyoupage");
						} else if (category.equalsIgnoreCase("withdraw")) {
							//pw.println("category=withdraw");
							if (accbalance < amount) {
								conn.close();
								pw.println("Insufficient balance to withdraw money");
								request.getSession().setAttribute("pagename", "insufficientbalance");
								request.getSession().setAttribute("category", "customer");
								request.getSession().setAttribute("currentbalance", accbalance);
								response.sendRedirect("thankyoupage");
							} else if (accbalance >= amount) {
								PreparedStatement withdraw = conn.prepareStatement(
										"update customerdetails set AccountBalance=? where AccountNo=?");
								PreparedStatement withdrawalhistory = conn.prepareStatement(
										"insert into transactionhistory(TransactionType,FromAccountNo,ToAccountNo,Amount,Date) values(?,?,?,?,now())");
								withdrawalhistory.setString(1, "Withdraw");
								withdrawalhistory.setLong(2, accountno);
								withdrawalhistory.setLong(3, 0000000000000000);
								withdrawalhistory.setLong(4, amount);
								withdraw.setLong(1, accbalance - amount);
								withdraw.setLong(2, accountno);
								withdraw.executeUpdate();
								withdrawalhistory.executeUpdate();
								conn.close();
								request.getSession().setAttribute("pagename", "withdraw");
								response.sendRedirect("thankyoupage");
							}else {
								conn.close();
							}
						}else if(category.equals(null)) {
							pw.println("No transaction category is seleted");
						}

					} else {
						pw.println("Incorrect Password");
					}
				} else {
					pw.println("AccountNo not found!");
				}
				break;
			}
		} catch (SQLException sqle) {
			pw.println(sqle.getMessage());
			sqle.printStackTrace();
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
