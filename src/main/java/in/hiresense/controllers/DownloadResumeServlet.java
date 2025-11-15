package in.hiresense.controllers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "DownloadResumeServlet", value = "/DownloadResumeServlet")
public class DownloadResumeServlet extends HttpServlet {
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
        String resumePath = request.getParameter("path");
        if(resumePath == null || !new File(resumePath).exists()){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        File file = new File(resumePath);
        response.setContentType("application/pdf");
        response.setHeader("Content-Description", "attachment:filename\""+file.getName()+"\"");
        try(FileInputStream in = new FileInputStream(file); OutputStream out = response.getOutputStream()){
            byte[] buffer = new byte[1024];
            int bytesRead;
            while((bytesRead = in.read(buffer))!=-1){
                out.write(buffer,0,bytesRead);
            }
        }
    }
    
}
 