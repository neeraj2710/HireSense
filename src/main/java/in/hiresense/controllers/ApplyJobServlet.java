package in.hiresense.controllers;
import in.hiresense.dao.ApplicationDao;
import in.hiresense.dao.JobDao;
import in.hiresense.dao.ResumeAnalysisLogDao;
import in.hiresense.dao.UserDao;
import in.hiresense.pojo.ApplicationPojo;
import in.hiresense.pojo.JobPojo;
import in.hiresense.pojo.ResumeAnalysisLogsPojo;
import in.hiresense.pojo.UserPojo;
import in.hiresense.utils.MailUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ApplyJobServlet", value = "/ApplyJobServlet")
public class ApplyJobServlet extends HttpServlet {
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
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        System.out.println("In Apply Job servlet");

        int userId = (Integer)session.getAttribute("userId");
        int jobId = Integer.parseInt(request.getParameter("jobId"));
        int score = Integer.parseInt(request.getParameter("score"));
        try{
            String resumePath = "N/A";
            List<ResumeAnalysisLogsPojo> logs = ResumeAnalysisLogDao.getLogsByUser(userId);
            System.out.println(logs);
            if(!logs.isEmpty()){
                String resultJson = logs.get(0).getJsonResult();
                JSONObject obj = new JSONObject(resultJson);
                JSONObject data = obj.getJSONObject("data");
                resumePath = data.optString("resumePath","N/A");
            }
            ApplicationPojo app = new ApplicationPojo(0,userId,jobId,resumePath,score,"applied",null);
            ApplicationDao.apply(app);
            UserPojo u1 = UserDao.getUserById(userId);
            JobPojo job = JobDao.getJobById(jobId);
            MailUtil.sendApplicationConfirmation(u1.getName(), u1.getEmail(), job.getTitle(),job.getCompany());
            UserPojo u2 = UserDao.getUserById(job.getEmployerId());
            MailUtil.sendNewApplicationNotificationToEmployer(u2.getName(), u2.getEmail(), u1.getName(), job.getTitle());
            response.sendRedirect("UserDashboardServlet?success=applied");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }

    }
    
}
 