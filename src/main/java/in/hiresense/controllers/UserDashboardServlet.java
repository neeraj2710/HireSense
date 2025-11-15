package in.hiresense.controllers;
import in.hiresense.dao.ApplicationDao;
import in.hiresense.dao.JobDao;
import in.hiresense.dao.ResumeAnalysisLogDao;
import in.hiresense.pojo.ApplicationPojo;
import in.hiresense.pojo.JobPojo;
import in.hiresense.pojo.ResumeAnalysisLogsPojo;
import in.hiresense.utils.AffindaAPI;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            List<ResumeAnalysisLogsPojo> logs = ResumeAnalysisLogDao.getLogsByUser(userId);
            boolean resumeUploaded = !logs.isEmpty();
            List<String> userSkills = null;
            if(resumeUploaded){
                JSONObject obj = new JSONObject(logs.get(0).getJsonResult());
                userSkills = AffindaAPI.extractSkills(obj.toString());
                System.out.println(userSkills);
            }
            List<JobPojo> jobs = JobDao.getAllJobsForUserDashboard(
                    search,
                    sort,
                    location,
                    experience,
                    packageLpa);
            if(resumeUploaded && userSkills!=null){
                for(JobPojo job : jobs){
                    int score = AffindaAPI.calculateMatchScore(job.getSkills(), userSkills);
                    job.setScore(score);
                }
            }
            List<ApplicationPojo> applicationList = ApplicationDao.getApplicationsByUser(userId);
            Set<Integer> appliedJobIds = new HashSet<>();
            for(ApplicationPojo app : applicationList){
                appliedJobIds.add(app.getJobId());
            }
            request.setAttribute("jobs", jobs);
            request.setAttribute("appliedJobIds", appliedJobIds);
            request.setAttribute("search", search);
            request.setAttribute("location", location);
            request.setAttribute("sort", sort);
            request.setAttribute("experience", experience);
            request.setAttribute("packageLpa", packageLpa);
            request.setAttribute("resumeUploaded", resumeUploaded);
            request.getRequestDispatcher("userDashboard.jsp").forward(request, response);

        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        }

    }
    
}
 