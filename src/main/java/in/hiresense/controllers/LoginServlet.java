package in.hiresense.controllers;
import in.hiresense.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println(email+" "+password);

        try{
            String name = UserDao.verifyUser(email, password);
            System.out.println(name);
            if(name != null){
                session.setAttribute("currUser", email);
                session.setAttribute("currUserName", name);
                response.sendRedirect("userDashboard.jsp");
            }else {
                response.sendRedirect("login.jsp?invalidCredentials=true");
            }

        } catch (Exception e) {
            throw new ServletException("Error in LoginServlet");
        }

    }
    
}
 