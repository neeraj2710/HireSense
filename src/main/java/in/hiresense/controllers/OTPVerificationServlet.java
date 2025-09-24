package in.hiresense.controllers;

import in.hiresense.utils.AppConfig;
import in.hiresense.utils.MailUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        String recipient = request.getParameter("email");
        int otp = new Random().nextInt(900000) + 100000;
        String emailBody = "Hello," +
                "\n" +
                "Your HireSense verification code is : " + otp +
                "\n" +
                "If you did not request this code, please ignore this email or contact us at "+
                AppConfig.getProperty("mail.username") +
                "\n" +
                "Cheers," +
                "\n" +
                "The HireSense Team";
        try {
            MailUtil.sendTextEmail(recipient, "Email Verification", emailBody);
        } catch (MessagingException e) {
            System.out.println("Error in sending mail : "+e.getMessage());
        }
    }

}
 