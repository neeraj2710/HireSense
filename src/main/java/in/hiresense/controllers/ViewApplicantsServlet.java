package in.hiresense.controllers;
import in.hiresense.dao.ApplicationDao;
import in.hiresense.dao.JobDao;
import in.hiresense.pojo.ApplicationPojo;
import in.hiresense.pojo.JobPojo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ViewApplicantsServlet", value = "/ViewApplicantsServlet")
public class ViewApplicantsServlet extends HttpServlet {
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
        String status = request.getParameter("status")!=null ? request.getParameter("status") : "applied";
        List<ApplicationPojo> applicants = new ArrayList<>();

        try{
            JobPojo job = JobDao.getJobById(jobId);
            if(job == null){
                response.sendRedirect("EmployerDashboardServlet?error=InvalidJob");
                return;
            }
            applicants = ApplicationDao.getApplicationsByJobAndStatus(jobId, status);
            request.setAttribute("job", job);
            request.setAttribute("applicants", applicants);
            request.setAttribute("status", "status");
            request.getRequestDispatcher("viewApplicants.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Unable to fetch applicants or job details");
        }
    }
    
}
 