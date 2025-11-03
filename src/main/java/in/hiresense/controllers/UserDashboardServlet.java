package in.hiresense.controllers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserDashboardServlet", value = "/UserDashboardServlet")
public class UserDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userId") == null){
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (Integer)session.getAttribute("userId");
        String search = request.getParameter("search");
        String sort = request.getParameter("sort");
        String location = request.getParameter("location");
        String experience = request.getParameter("experience");
        String packageLpa = request.getParameter("packageLpa");

        try{

        }

    }
    
}
 