package in.hiresense.controllers;
import in.hiresense.dao.ResumeAnalysisLogDao;
import in.hiresense.pojo.ResumeAnalysisLogsPojo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;


@MultipartConfig
@WebServlet(name = "UploadResumeServlet", value = "/UploadResumeServlet")
public class UploadResumeServlet extends HttpServlet {
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

        int userId = (Integer)session.getAttribute("userId");
        Part filePart = request.getPart("resume");

        //geting name of a resume file
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();


        //creating a new dir if dir doesn't exist
        String uploadDir = getServletContext().getRealPath("/resumes");
        File dir = new File(uploadDir);
        if(!dir.exists()){
            dir.mkdirs();
        }

        //deleting the old resume file if new is uploaded
        File resumeFile = new File(dir,fileName);
        try{
            List<ResumeAnalysisLogsPojo> logs = ResumeAnalysisLogDao.getLogsByUser(userId);
            if(!logs.isEmpty()){
                String previousJson = logs.get(0).getJsonResult();
                JSONObject obj = new JSONObject(previousJson);
                String previousPath = obj.getJSONObject("data").optString("resumePath",null);
                if(previousPath != null){
                    File oldFile = new File(previousPath);
                    if(oldFile.exists()){
                        oldFile.delete();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // reading from the uploaded file
        try(InputStream input = filePart.getInputStream(); FileOutputStream output = new FileOutputStream(resumeFile)){
            byte[] buffer = new byte[1024];
            int bytesRead;
            while((bytesRead = input.read(buffer))!=-1){
                output.write(buffer,0,bytesRead);
            }
        }

        //API call



    }
    
}
 