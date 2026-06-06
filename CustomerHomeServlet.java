package com.servlet.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/customerhomepage")
@MultipartConfig
public class CustomerHomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");
        Long accountno = (Long) request.getSession().getAttribute("accountno");
        String password = (String) request.getSession().getAttribute("password");
        System.out.println("accountno=" + accountno + "password=" + password);
        
        request.getSession().setAttribute("accountno", accountno);
        request.getSession().setAttribute("operator", "customer");

        try (Connection conn = new DBCP().getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("select * from customerdetails where AccountNo=?");
            
            pstmt.setLong(1, accountno);
            ResultSet rs = pstmt.executeQuery();
            rs.next();

            // Start HTML output
            pw.println("<!DOCTYPE html>");
            pw.println("<html lang='en'>");
            pw.println("<head>");
            pw.println("    <meta charset='UTF-8'>");
            pw.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            pw.println("    <title>Bank Customer Profile</title>");
            pw.println("    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css'>");
            pw.println("    <link rel='stylesheet' href='customerprofile.css'>");
            pw.println("    <style>");
            pw.println("        /* Add styles for the iframe and active buttons */");
            pw.println("        .main-content { position: relative; }");
            pw.println("        #contentFrame {");
            pw.println("            position: absolute;");
            pw.println("            top: 0;");
            pw.println("            left: 0;");
            pw.println("            width: 100%;");
            pw.println("            height: 100%;");
            pw.println("            border: none;");
            pw.println("            z-index: 10;");
            pw.println("            display: none; /* Hidden by default */");
            pw.println("        }");
            pw.println("        .nav-btn.active {");
            pw.println("            background-color: #3498db;");
            pw.println("            color: white;");
            pw.println("            font-weight: bold;");
            pw.println("        }");
            pw.println("        #homeBtn.active {");
            pw.println("            background-color: #27ae60; /* Different color for Home button */");
            pw.println("        }");
            pw.println("        .new-button {");
            pw.println("            background-color: #9b59b6; /* Purple for loan button */");
            pw.println("        }");
            pw.println("        .new-button2 {");
            pw.println("            background-color: #e67e22; /* Orange for insurance button */");
            pw.println("        }");
            pw.println("    </style>");
            pw.println("</head>");
            
            pw.println("<body>");
            pw.println("    <!-- Sidebar on LEFT -->");
            pw.println("    <div class='sidebar'>");
            pw.println("        <div class='profile-header'>");
            pw.println("            <h2><i class='fas fa-user-circle'></i> Customer Profile</h2>");
            pw.println("        </div>");
            pw.println("        ");
            pw.println("        <div class='profile-pic-container'>");
            pw.println("            <div class='profile-pic-wrapper' id='profileWrapper'>");
            pw.println("                <div class='profile-pic-placeholder' id='profilePlaceholder'>");
            pw.println("                    <i class='fas fa-user-plus'></i>");
            pw.println("                </div>");
            pw.println("                <input type='file' name='profileUpload' accept='image/*' style='display:none'>");
            pw.println("            </div>");
            pw.println("            <div class='upload-text'>Click above to upload profile photo</div>");
            pw.println("        </div>");
            pw.println("        ");
            pw.println("        <div class='customer-details'>");
            pw.println("            <div class='detail-row'>");
            pw.println("                <div class='detail-label'>Account No:</div>");
            pw.println("                <div class='detail-value'>"+rs.getLong("AccountNo")+"</div>");
            pw.println("            </div>");
            pw.println("            <div class='detail-row'>");
            pw.println("                <div class='detail-label'>Full Name:</div>");
            pw.println("                <div class='detail-value'>"+rs.getString("FirstName")+" "+rs.getString("MiddleName")+" "+rs.getString("LastName")+"</div>");
            pw.println("            </div>");
            pw.println("            <div class='detail-row'>");
            pw.println("                <div class='detail-label'>Phone:</div>");
            pw.println("                <div class='detail-value'>"+rs.getLong("PhoneNo")+"</div>");
            pw.println("            </div>");
            pw.println("            <div class='detail-row'>");
            pw.println("                <div class='detail-label'>Email:</div>");
            pw.println("                <div class='detail-value'>"+rs.getString(15)+"</div>");
            pw.println("            </div>");
            pw.println("            <div class='detail-row'>");
            pw.println("                <div class='detail-label'>Branch:</div>");
            pw.println("                <div class='detail-value'>Ongole</div>");
            pw.println("            </div>");
            pw.println("        </div>");
            pw.println("        ");
            pw.println("        <div class='nav-buttons'>");
            // New Home button
            pw.println("            <button id='homeBtn' class='nav-btn active' onclick='showHomePage(this)'>");
            pw.println("                <i class='fas fa-home'></i> Home");
            pw.println("            </button>");
            
            // Existing buttons
            pw.println("            <button id='balanceBtn' class='nav-btn' onclick='loadPage(\"checkbalancepage\", this)'>");
            pw.println("                <i class='fas fa-wallet'></i> Check Balance");
            pw.println("            </button>");
            pw.println("            <button id='transferBtn' class='nav-btn' onclick='loadPage(\"transfermoneypage\", this)'>");
            pw.println("                <i class='fas fa-exchange-alt'></i> Transfer Money");
            pw.println("            </button>");
            pw.println("            <button id='historyBtn' class='nav-btn' onclick='loadPage(\"customertransactionhistorypage\", this)'>");
            pw.println("                <i class='fas fa-history'></i> Transaction History");
            pw.println("            </button>");
            pw.println("            <button id='passwordBtn' class='nav-btn' onclick='loadPage(\"customerpasswordchangepage\", this)'>");
            pw.println("                <i class='fas fa-key'></i> Change Password");
            pw.println("            </button>");
            
            // New Loan and Insurance buttons
            pw.println("            <button id='loanBtn' class='nav-btn new-button' onclick='loadPage(\"loanapplicationshubpage\", this)'>");
            pw.println("                <i class='fas fa-file-invoice-dollar'></i> Loan Applications");
            pw.println("            </button>");
            pw.println("            <button id='insuranceBtn' class='nav-btn new-button2' onclick='loadPage(\"insuranceapplicationshubpage\", this)'>");
            pw.println("                <i class='fas fa-shield-alt'></i> Insurance Applications");
            pw.println("            </button>");
            pw.println("        </div>");
            pw.println("        ");
            pw.println("        <a href='mainloginpage' class='logout-btn'>");
            pw.println("            <i class='fas fa-sign-out-alt'></i> Log Out");
            pw.println("        </a>");
            pw.println("    </div>");
            pw.println("    ");
            pw.println("    <!-- Main content on RIGHT -->");
            pw.println("    <div class='main-content'>");
            pw.println("        <!-- Welcome section (visible by default) -->");
            pw.println("        <div id='welcomeSection' class='welcome-section'>");
            pw.println("            <h1>Welcome Back, "+rs.getString("FirstName")+"!</h1>");
            pw.println("            <p>Welcome to your banking dashboard. Here you can manage your accounts, view transactions, transfer funds, apply for loans and insurance, and more. Your financial journey is our priority, and we're here to help you every step of the way.</p>");
            pw.println("            ");
            pw.println("            <div class='detail-row'>");
            pw.println("                <div class='detail-label'>Account Status:</div>");
            pw.println("                <div class='detail-value' style='color: #27ae60; font-weight: 600;'>Active</div>");
            pw.println("            </div>");
            pw.println("            <div class='detail-row'>");
            pw.println("                <div class='detail-label'>Last Login:</div>");
            pw.println("                <div class='detail-value'>Today at " + new java.util.Date().toString().substring(11, 16) + "</div>");
            pw.println("            </div>");
            pw.println("            ");
            pw.println("            <!-- Quick Stats Section -->");
            pw.println("            <div class='quick-stats'>");
            pw.println("                <h2>Your Financial Snapshot</h2>");
            pw.println("                <div class='stats-row'>");
            pw.println("                    <div class='stat-card'>");
            pw.println("                        <i class='fas fa-rupee-sign'></i>");
            pw.println("                        <h3>Account Balance</h3>");
            pw.println("                        <p>"+rs.getInt("AccountBalance")+"</p>");
            pw.println("                    </div>");
            pw.println("                    <div class='stat-card'>");
            pw.println("                        <i class='fas fa-piggy-bank'></i>");
            pw.println("                        <h3>Savings Goal</h3>");
            pw.println("                        <p>75% Completed</p>");
            pw.println("                    </div>");
            pw.println("                    <div class='stat-card'>");
            pw.println("                        <i class='fas fa-chart-line'></i>");
            pw.println("                        <h3>Investment Growth</h3>");
            pw.println("                        <p>+12.5% YTD</p>");
            pw.println("                    </div>");
            pw.println("                </div>");
            pw.println("            </div>");
            pw.println("        </div>");
            pw.println("        ");
            pw.println("        <!-- Iframe for content -->");
            pw.println("        <iframe id='contentFrame' name='contentFrame' src='' title='Customer Content'></iframe>");
            pw.println("    </div>");
            pw.println("");
            pw.println("    <!-- Debug panel -->");
            pw.println("    <div class='debug-panel'>");
            pw.println("        <h3>Debug Console</h3>");
            pw.println("        <div class='debug-messages' id='debugMessages'>");
            pw.println("            <p class='success'>System initialized</p>");
            pw.println("            <p>Home page loaded</p>");
            pw.println("        </div>");
            pw.println("    </div>");
            pw.println("");
            pw.println("    <script>");
            pw.println("        // Function to show home page");
            pw.println("        function showHomePage(button) {");
            pw.println("            // Hide iframe");
            pw.println("            document.getElementById('contentFrame').style.display = 'none';");
            pw.println("            // Show welcome section");
            pw.println("            document.getElementById('welcomeSection').style.display = 'block';");
            pw.println("            ");
            pw.println("            // Set active button");
            pw.println("            setActiveButton(button);");
            pw.println("            ");
            pw.println("            // Debug log");
            pw.println("            debugLog('Showing home page');");
            pw.println("        }");
            pw.println("        ");
            pw.println("        // Function to load pages into iframe");
            pw.println("        function loadPage(pageUrl, button) {");
            pw.println("            // Hide welcome section");
            pw.println("            document.getElementById('welcomeSection').style.display = 'none';");
            pw.println("            ");
            pw.println("            // Show and load the iframe");
            pw.println("            const frame = document.getElementById('contentFrame');");
            pw.println("            frame.src = pageUrl;");
            pw.println("            frame.style.display = 'block';");
            pw.println("            ");
            pw.println("            // Set active button");
            pw.println("            setActiveButton(button);");
            pw.println("            ");
            pw.println("            // Debug log");
            pw.println("            debugLog(`Navigating to: ${pageUrl}`);");
            pw.println("        }");
            pw.println("        ");
            pw.println("        // Function to set active button");
            pw.println("        function setActiveButton(activeButton) {");
            pw.println("            // Remove active class from all buttons");
            pw.println("            const buttons = document.querySelectorAll('.nav-btn');");
            pw.println("            buttons.forEach(button => {");
            pw.println("                button.classList.remove('active');");
            pw.println("            });");
            pw.println("            ");
            pw.println("            // Add active class to clicked button");
            pw.println("            activeButton.classList.add('active');");
            pw.println("        }");
            pw.println("        ");
            pw.println("        // Debug logging function");
            pw.println("        function debugLog(message, type = 'info') {");
            pw.println("            const debugMessages = document.getElementById('debugMessages');");
            pw.println("            const msgElement = document.createElement('p');");
            pw.println("            msgElement.textContent = message;");
            pw.println("            msgElement.className = type;");
            pw.println("            debugMessages.appendChild(msgElement);");
            pw.println("            debugMessages.scrollTop = debugMessages.scrollHeight;");
            pw.println("        }");
            pw.println("        ");
            pw.println("        // Initialize debug logging");
            pw.println("        debugLog('Page loaded successfully');");
            pw.println("        debugLog('Navigation functionality ready', 'success');");
            pw.println("        ");
            pw.println("        // Profile picture upload functionality");
            pw.println("        document.addEventListener('DOMContentLoaded', function() {");
            pw.println("            const profileWrapper = document.getElementById('profileWrapper');");
            pw.println("            const profilePlaceholder = document.getElementById('profilePlaceholder');");
            pw.println("            const profileUpload = document.getElementById('profileUpload');");
            pw.println("");
            pw.println("            // Add event listener to the wrapper");
            pw.println("            profileWrapper.addEventListener('click', function() {");
            pw.println("                debugLog('Profile picture area clicked');");
            pw.println("                profileUpload.click();");
            pw.println("            });");
            pw.println("");
            pw.println("            // Handle file selection");
            pw.println("            profileUpload.addEventListener('change', function(event) {");
            pw.println("                debugLog('File input changed');");
            pw.println("                ");
            pw.println("                const file = event.target.files[0];");
            pw.println("                if (!file) {");
            pw.println("                    debugLog('No file selected', 'error');");
            pw.println("                    return;");
            pw.println("                }");
            pw.println("                ");
            pw.println("                debugLog(`Selected file: ${file.name} (${(file.size/1024).toFixed(2)} KB)`);");
            pw.println("                ");
            pw.println("                // Check if file is an image");
            pw.println("                if (!file.type.match('image.*')) {");
            pw.println("                    debugLog('Error: Selected file is not an image', 'error');");
            pw.println("                    alert('Please select an image file (JPEG, PNG, etc.)');");
            pw.println("                    return;");
            pw.println("                }");
            pw.println("                ");
            pw.println("                // Check file size (max 5MB)");
            pw.println("                if (file.size > 5 * 1024 * 1024) {");
            pw.println("                    debugLog('Error: File size exceeds 5MB limit', 'error');");
            pw.println("                    alert('File size exceeds 5MB limit. Please select a smaller image.');");
            pw.println("                    return;");
            pw.println("                }");
            pw.println("                ");
            pw.println("                // Create a FileReader to read the image");
            pw.println("                const reader = new FileReader();");
            pw.println("                ");
            pw.println("                reader.onload = function(e) {");
            pw.println("                    debugLog('File read successfully');");
            pw.println("                    ");
            pw.println("                    // Create a new image element");
            pw.println("                    const img = new Image();");
            pw.println("                    ");
            pw.println("                    img.onload = function() {");
            pw.println("                        debugLog(`Image dimensions: ${img.width}px × ${img.height}px`);");
            pw.println("                        ");
            pw.println("                        // Replace the placeholder with the image");
            pw.println("                        profilePlaceholder.innerHTML = '';");
            pw.println("                        profilePlaceholder.style.backgroundImage = `url(${e.target.result})`;");
            pw.println("                        profilePlaceholder.style.backgroundSize = 'cover';");
            pw.println("                        profilePlaceholder.style.backgroundPosition = 'center';");
            pw.println("                        ");
            pw.println("                        // Add success message");
            pw.println("                        const successMsg = document.createElement('div');");
            pw.println("                        successMsg.innerHTML = '<i class=\"fas fa-check-circle\"></i> Updated';");
            pw.println("                        successMsg.style.color = '#27ae60';");
            pw.println("                        successMsg.style.fontSize = '12px';");
            pw.println("                        successMsg.style.marginTop = '5px';");
            pw.println("                        successMsg.style.fontWeight = '600';");
            pw.println("                        profilePlaceholder.appendChild(successMsg);");
            pw.println("                        ");
            pw.println("                        debugLog('Profile picture updated successfully', 'success');");
            pw.println("                    };");
            pw.println("                    ");
            pw.println("                    img.onerror = function() {");
            pw.println("                        debugLog('Error loading image', 'error');");
            pw.println("                        alert('Error loading image. Please try another file.');");
            pw.println("                    };");
            pw.println("                    ");
            pw.println("                    img.src = e.target.result;");
            pw.println("                };");
            pw.println("                ");
            pw.println("                reader.onerror = function() {");
            pw.println("                    debugLog('Error reading file', 'error');");
            pw.println("                    alert('Error reading file. Please try another image.');");
            pw.println("                };");
            pw.println("                ");
            pw.println("                reader.readAsDataURL(file);");
            pw.println("            });");
            pw.println("        });");
            pw.println("    </script>");
            pw.println("</body>");
            pw.println("</html>");
            
            rs.close();
			conn.close();
            
           //uploading profilepic
            
            
            
        } catch (SQLException sqle) {
            pw.println(sqle.getMessage());
            sqle.printStackTrace();
        } catch (Exception e) {
            pw.println(e.getMessage());
            e.printStackTrace();
        }
        
        pw.close();
        
        System.out.println("Customer Home page");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}