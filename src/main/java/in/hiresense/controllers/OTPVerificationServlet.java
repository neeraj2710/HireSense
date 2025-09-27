package in.hiresense.controllers;

import in.hiresense.utils.AppConfig;
import in.hiresense.utils.MailUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "OTPVerificationServlet", value = "/OTPVerificationServlet")
public class OTPVerificationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        String appName = (String) super.getServletContext().getAttribute("appName");

        session.setAttribute("regName", name);
        session.setAttribute("regOtp", "otp");
        session.setAttribute("regEmail", email);
        session.setAttribute("regPassword", password);
        session.setAttribute("regRole", role);

        String emailBody = "Hello," +
                "\n" +
                "Your "+appName+" verification code is : " + otp +
                "\n" +
                "If you did not request this code, please ignore this email or contact us at "+
                AppConfig.getProperty("mail.username") +
                "\n" +
                "Cheers," +
                "\n" +
                "The "+appName+" Team";

        try {
            MailUtil.sendTextEmail(email, "Email Verification", emailBody);
            response.sendRedirect("register.jsp?showOtp=true");
        } catch (MessagingException e) {
            throw new ServletException("Error in sending mail : "+e.getMessage());
        }
    }

}
 