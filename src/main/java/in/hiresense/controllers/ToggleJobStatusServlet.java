package in.hiresense.controllers;
import in.hiresense.dao.JobDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ToggleJobStatusServlet", value = "/ToggleJobStatusServlet")
public class ToggleJobStatusServlet extends HttpServlet {
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
        if(session == null || session.getAttribute("userId") == null || !session.getAttribute("userRole").equals("employer")){
            response.sendRedirect("login.jsp");
            return;
        }

        int jobId = Integer.parseInt(request.getParameter("jobId"));

        try{
            JobDao.toggleJobStatus(jobId);
            response.sendRedirect("EmployerDashboardServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("EmployerDashboardServlet?error=1");
        }
    }
    
}
 