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

@WebServlet(name = "PostJobServlet", value = "/PostJobServlet")
public class PostJobServlet extends HttpServlet {
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
        if(session == null || session.getAttribute("userId") == null || !session.getAttribute("role").equals("employer")){
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String skills = request.getParameter("skills");
        String company = request.getParameter("company");
        String location = request.getParameter("location");
        String experience = request.getParameter("experience");
        String packageLpa = request.getParameter("packageLpa");
        int vacancies = Integer.parseInt(request.getParameter("vacancies"));

        JobPojo job = new JobPojo();
        job.setTitle(title);
        job.setDescription(description);
        job.setSkills(skills);
        job.setCompany(company);
        job.setLocation(location);
        job.setExperience(experience);
        job.setPackageLpa(packageLpa);
        job.setVacancies(vacancies);
        job.setEmployerId(userId);

        try{
            boolean result = JobDao.postJob(job);
            if(result) response.sendRedirect("EmployerDashboardServlet?success=1");
            else response.sendRedirect("EmployerDashboardServlet?error=1");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("EmployerDashboardServlet?error=1");
        }

    }
    
}
 