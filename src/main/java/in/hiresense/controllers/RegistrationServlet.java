package in.hiresense.controllers;
import in.hiresense.dao.UserDao;
import in.hiresense.pojo.UserPojo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", value = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession  session = request.getSession();

        String inputOtp = request.getParameter("otp");
        String actualOtp = (String) session.getAttribute("regOtp");

        if(inputOtp.equals(actualOtp)){
            UserPojo user = new UserPojo();

            user.setName((String)session.getAttribute("regName"));
            user.setEmail((String)session.getAttribute("regEmail"));
            user.setPassword((String)session.getAttribute("regPassword"));
            user.setRole((String)session.getAttribute("regRole"));

            try {
                UserDao.registerUser(user);
                session.removeAttribute("regOtp");
                response.sendRedirect("login.jsp?registered=true");
                System.out.println("user Registered");
            } catch (Exception e) {
                System.out.println();
                throw new ServletException("Error in RegistrationServlet");
            }
        }else{
            response.sendRedirect("register.jsp?showOtp=true&error=invalid");
        }
    }
    
}
 