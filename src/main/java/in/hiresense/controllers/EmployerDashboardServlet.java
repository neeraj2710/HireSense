package in.hiresense.controllers;
import in.hiresense.dao.JobDao;
import in.hiresense.pojo.JobPojo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployerDashboardServlet", value = "/EmployerDashboardServlet")
public class EmployerDashboardServlet extends HttpServlet {
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

        int userId = (Integer)session.getAttribute("userId");
        String search = request.getParameter("search");
        String status = request.getParameter("status");
        String sort = request.getParameter("sort");

        try{
            List<JobPojo> jobList = JobDao.getJobsByEmployer(userId, search, status, sort);
            request.setAttribute("jobList", jobList);
            request.setAttribute("search", search);
            request.setAttribute("status", status);
            request.setAttribute("sort", sort);
            request.getRequestDispatcher("employerDashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }

    }
    
}
 