package com.servlet.bank;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loanapplicationshubpage")
public class LoanApplicationsHubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        Long accountno = (Long) request.getSession().getAttribute("accountno");
        request.getSession().setAttribute("accountno",accountno);
        System.out.println("hub:"+accountno);
        PrintWriter pw = response.getWriter();
        
        pw.println("<!DOCTYPE html>");
        pw.println("<html lang='en'>");
        pw.println("<head>");
        pw.println("    <meta charset='UTF-8'>");
        pw.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        pw.println("    <title>Loan Applications | TSR Bank</title>");
        pw.println("    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css'>");
        pw.println("    <style>");
        pw.println("        * {");
        pw.println("            margin: 0;");
        pw.println("            padding: 0;");
        pw.println("            box-sizing: border-box;");
        pw.println("            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;");
        pw.println("        }");
        pw.println("        body {");
        pw.println("            background: linear-gradient(135deg, #f8f9fa, #e9ecef);");
        pw.println("            min-height: 100vh;");
        pw.println("            padding: 20px;");
        pw.println("        }");
        pw.println("        .loan-header {");
        pw.println("            text-align: center;");
        pw.println("            margin-bottom: 30px;");
        pw.println("            padding-bottom: 15px;");
        pw.println("            border-bottom: 2px solid #3498db;");
        pw.println("        }");
        pw.println("        .loan-header h1 {");
        pw.println("            color: #2c3e50;");
        pw.println("            font-size: 2.2rem;");
        pw.println("            margin-bottom: 10px;");
        pw.println("        }");
        pw.println("        .loan-header p {");
        pw.println("            color: #7f8c8d;");
        pw.println("            font-size: 1.1rem;");
        pw.println("            max-width: 700px;");
        pw.println("            margin: 0 auto;");
        pw.println("        }");
        pw.println("        .loan-grid {");
        pw.println("            display: grid;");
        pw.println("            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));");
        pw.println("            gap: 25px;");
        pw.println("            max-width: 1300px;");
        pw.println("            margin: 0 auto;");
        pw.println("        }");
        pw.println("        .loan-card {");
        pw.println("            background: white;");
        pw.println("            border-radius: 15px;");
        pw.println("            overflow: hidden;");
        pw.println("            box-shadow: 0 8px 20px rgba(0,0,0,0.08);");
        pw.println("            transition: all 0.3s ease;");
        pw.println("            display: flex;");
        pw.println("            flex-direction: column;");
        pw.println("            height: 100%;");
        pw.println("        }");
        pw.println("        .loan-card:hover {");
        pw.println("            transform: translateY(-10px);");
        pw.println("            box-shadow: 0 12px 25px rgba(0,0,0,0.15);");
        pw.println("        }");
        pw.println("        .card-header {");
        pw.println("            padding: 25px;");
        pw.println("            display: flex;");
        pw.println("            align-items: center;");
        pw.println("            color: white;");
        pw.println("        }");
        pw.println("        .card-header i {");
        pw.println("            font-size: 2.5rem;");
        pw.println("            margin-right: 15px;");
        pw.println("        }");
        pw.println("        .card-header h2 {");
        pw.println("            font-size: 1.5rem;");
        pw.println("            font-weight: 600;");
        pw.println("        }");
        pw.println("        .card-content {");
        pw.println("            padding: 25px;");
        pw.println("            flex-grow: 1;");
        pw.println("        }");
        pw.println("        .card-content p {");
        pw.println("            color: #7f8c8d;");
        pw.println("            line-height: 1.6;");
        pw.println("            margin-bottom: 20px;");
        pw.println("        }");
        pw.println("        .features {");
        pw.println("            margin: 15px 0;");
        pw.println("        }");
        pw.println("        .features li {");
        pw.println("            display: flex;");
        pw.println("            align-items: center;");
        pw.println("            margin-bottom: 10px;");
        pw.println("            color: #34495e;");
        pw.println("        }");
        pw.println("        .features li i {");
        pw.println("            color: #27ae60;");
        pw.println("            margin-right: 10px;");
        pw.println("        }");
        pw.println("        .card-footer {");
        pw.println("            padding: 0 25px 25px;");
        pw.println("            text-align: center;");
        pw.println("        }");
        pw.println("        .apply-btn {");
        pw.println("            display: inline-block;");
        pw.println("            padding: 12px 30px;");
        pw.println("            background: #3498db;");
        pw.println("            color: white;");
        pw.println("            border-radius: 30px;");
        pw.println("            text-decoration: none;");
        pw.println("            font-weight: 600;");
        pw.println("            transition: all 0.3s ease;");
        pw.println("            border: 2px solid #3498db;");
        pw.println("        }");
        pw.println("        .apply-btn:hover {");
        pw.println("            background: transparent;");
        pw.println("            color: #3498db;");
        pw.println("        }");
        pw.println("        .home-loan .card-header { background: linear-gradient(135deg, #3498db, #2c3e50); }");
        pw.println("        .auto-loan .card-header { background: linear-gradient(135deg, #e74c3c, #c0392b); }");
        pw.println("        .personal-loan .card-header { background: linear-gradient(135deg, #9b59b6, #8e44ad); }");
        pw.println("        .student-loan .card-header { background: linear-gradient(135deg, #2ecc71, #27ae60); }");
        pw.println("        .interest-rate {");
        pw.println("            background: #f8f9fa;");
        pw.println("            border-radius: 20px;");
        pw.println("            padding: 5px 15px;");
        pw.println("            display: inline-block;");
        pw.println("            font-weight: 600;");
        pw.println("            margin-top: 10px;");
        pw.println("            color: #2c3e50;");
        pw.println("        }");
        pw.println("        .max-amount {");
        pw.println("            color: #3498db;");
        pw.println("            font-weight: 600;");
        pw.println("            font-size: 1.2rem;");
        pw.println("            margin-top: 10px;");
        pw.println("        }");
        pw.println("        @media (max-width: 768px) {");
        pw.println("            .loan-grid {");
        pw.println("                grid-template-columns: 1fr;");
        pw.println("            }");
        pw.println("        }");
        pw.println("    </style>");
        pw.println("</head>");
        
        pw.println("<body>");
        pw.println("    <div class='loan-header'>");
        pw.println("        <h1><i class='fas fa-hand-holding-usd'></i> Loan Products</h1>");
        pw.println("        <p>Explore our competitive loan options designed to meet your financial needs. Select a loan type to begin your application.</p>");
        pw.println("    </div>");
        
        pw.println("    <div class='loan-grid'>");
        
        // Mortgage Loan Card
        pw.println("        <div class='loan-card home-loan'>");
        pw.println("            <div class='card-header'>");
        pw.println("                <i class='fas fa-home'></i>");
        pw.println("                <h2>Mortgage Loan</h2>");
        pw.println("            </div>");
        pw.println("            <div class='card-content'>");
        pw.println("                <p>Realize your dream of owning a home with our flexible mortgage loan options and competitive interest rates.</p>");
        pw.println("                <ul class='features'>");
        pw.println("                    <li><i class='fas fa-check-circle'></i> Up to 30 years repayment period</li>");
        pw.println("                    <li><i class='fas fa-check-circle'></i> Loan amount up to ₹5 Crores</li>");
        pw.println("                    <li><i class='fas fa-check-circle'></i> Preferential rates for women</li>");
        pw.println("                    <li><i class='fas fa-check-circle'></i> Online application processing</li>");
        pw.println("                </ul>");
        pw.println("                <div class='interest-rate'>Interest Rate: 8.25% - 9.15% p.a.</div>");
        pw.println("                <div class='max-amount'>Max Amount: ₹5,00,00,000</div>");
        pw.println("            </div>");
        pw.println("            <div class='card-footer'>");
        pw.println("                <a href='mortgageloanapplicationpage' class='apply-btn'>Apply Now</a>");
        pw.println("            </div>");
        pw.println("        </div>");
        
        // Auto Loan Card
        pw.println("        <div class='loan-card auto-loan'>");
        pw.println("            <div class='card-header'>");
        pw.println("                <i class='fas fa-car'></i>");
        pw.println("                <h2>Auto Loan</h2>");
        pw.println("            </div>");
        pw.println("            <div class='card-content'>");
        pw.println("                <p>Drive home your dream car with our quick and easy auto loans with minimal documentation.</p>");
        pw.println("                <ul class='features'>");
        pw.println("                    <li><i class='fas fa-check-circle'></i> 100% on-road funding</li>");
        pw.println("                    <li><i class='fas fa-check-circle'></i> Repayment up to 7 years</li>");
        pw.println("                    <li><i class='fas fa-check-circle'></i> Special discounts on insurance</li>");
        pw.println("                    <li><i class='fas fa-check-circle'></i> Instant approval for select models</li>");
        pw.println("                </ul>");
        pw.println("                <div class='interest-rate'>Interest Rate: 7.99% - 10.25% p.a.</div>");
        pw.println("                <div class='max-amount'>Max Amount: ₹50,00,000</div>");
        pw.println("            </div>");
        pw.println("            <div class='card-footer'>");
        pw.println("                <a href='autoloanapplicationpage' class='apply-btn'>Apply Now</a>");
        pw.println("            </div>");
        pw.println("        </div>");
        
        // Personal Loan Card
        pw.println("        <div class='loan-card personal-loan'>");
        pw.println("            <div class='card-header'>");
        pw.println("                <i class='fas fa-user'></i>");
        pw.println("                <h2>Personal Loan</h2>");
        pw.println("            </div>");
        pw.println("            <div class='card-content'>");
        pw.println("                <p>Meet your personal financial needs with our instant personal loans with minimal documentation.</p>");
        pw.println("                <ul class='features'>");
        pw.println("                    <li><i class='fas fa-check-circle'></i> Disbursal in 24 hours</li>");
        pw.println("                    <li><i class='fas fa-check-circle'></i> No collateral required</li>");
        pw.println("                    <li><i class='fas fa-check-circle'></i> Flexible repayment up to 5 years</li>");
        pw.println("                    <li><i class='fas fa-check-circle'></i> Special offers for existing customers</li>");
        pw.println("                </ul>");
        pw.println("                <div class='interest-rate'>Interest Rate: 10.5% - 15.75% p.a.</div>");
        pw.println("                <div class='max-amount'>Max Amount: ₹25,00,000</div>");
        pw.println("            </div>");
        pw.println("            <div class='card-footer'>");
        pw.println("                <a href='personalloanapplicationpage' class='apply-btn'>Apply Now</a>");
        pw.println("            </div>");
        pw.println("        </div>");
        
        // Student Loan Card
        pw.println("        <div class='loan-card student-loan'>");
        pw.println("            <div class='card-header'>");
        pw.println("                <i class='fas fa-graduation-cap'></i>");
        pw.println("                <h2>Student Loan</h2>");
        pw.println("            </div>");
        pw.println("            <div class='card-content'>");
        pw.println("                <p>Invest in your future with our education loans that cover tuition and living expenses globally.</p>");
        pw.println("                <ul class='features'>");
        pw.println("                    <li><i class='fas fa-check-circle'></i> Covers tuition and living expenses</li>");
        pw.println("                    <li><i class='fas fa-check-circle'></i> Moratorium period up to 12 months</li>");
        pw.println("                    <li><i class='fas fa-check-circle'></i> Special rates for top universities</li>");
        pw.println("                    <li><i class='fas fa-check-circle'></i> Loan repayment up to 15 years</li>");
        pw.println("                </ul>");
        pw.println("                <div class='interest-rate'>Interest Rate: 8.75% - 11.25% p.a.</div>");
        pw.println("                <div class='max-amount'>Max Amount: ₹1,50,00,000</div>");
        pw.println("            </div>");
        pw.println("            <div class='card-footer'>");
        pw.println("                <a href='studentloanapplicationpage' class='apply-btn'>Apply Now</a>");
        pw.println("            </div>");
        pw.println("        </div>");
        
        pw.println("    </div>"); // Close loan-grid
        
//        pw.println("    <script>");
//        pw.println("        // Add smooth scrolling for anchor links");
//        pw.println("        document.querySelectorAll('.apply-btn').forEach(button => {");
//        pw.println("            button.addEventListener('click', function(e) {");
//        pw.println("                e.preventDefault();");
//        pw.println("                alert('Loan application process would start here.\\nSelected: ' + this.closest('.loan-card').querySelector('h2').textContent);");
//        pw.println("            });");
//        pw.println("        });");
//        pw.println("    </script>");
        pw.println("</body>");
        pw.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}