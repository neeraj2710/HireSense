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

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println(email+" "+password);

        try{
            UserPojo user  = UserDao.getUserByEmail(email);
            if(user != null && user.getPassword().equals(password) && user.getStatus().equals("active")){
                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getId());
                session.setAttribute("userName", user.getName());
                session.setAttribute("userRole", user.getRole());
                switch (user.getRole()){
                    case "admin":
                        response.sendRedirect("AdminPanelServlet");
                        break;
                    case "employer":
                        response.sendRedirect("EmployerDashboardServlet");
                        break;
                    default:
                        response.sendRedirect("UserDashboardServlet");
                }
            }else {
                request.setAttribute("error", "Invalid credentials or account is blocked");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            throw new ServletException("Error in LoginServlet : "+e.getMessage());
        }

    }
    
}
 