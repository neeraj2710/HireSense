package in.hiresense.dao;

import in.hiresense.dbutils.DBConnection;
import in.hiresense.pojo.ApplicationPojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDao {

    public static boolean apply(ApplicationPojo application)throws Exception{
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = DBConnection.getConnection();
            String qry = "insert into applications(user_id,job_id,resume_path,score) values(?,?,?,?)";
            ps = conn.prepareStatement(qry);
            ps.setInt(1,application.getUserId());
            ps.setInt(2,application.getJobId());
            ps.setString(3, application.getResume_path());
            ps.setDouble(4,application.getScore());
            return ps.executeUpdate() > 0;
        }finally {
            if(ps != null) ps.close();
        }
    }

    public static List<ApplicationPojo> getApplicationsByUser(int userid)throws Exception{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ApplicationPojo> appList = new ArrayList<>();
        try{
            String qry = "SELECT * FROM applications WHERE user_id = ?";
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(qry);
            ps.setInt(1,userid);
            rs = ps.executeQuery();
            while(rs.next()){
                ApplicationPojo application = new ApplicationPojo();
                application.setId(rs.getInt("id"));
                application.setUserId(rs.getInt("user_id"));
                application.setJobId(rs.getInt("job_id"));
                application.setResume_path(rs.getString("resume_path"));
                application.setScore(rs.getDouble("score"));
                application.setStatus(rs.getString("status"));
                application.setAppliedAt(rs.getString("applied_at"));
                appList.add(application);
            }
            return appList;
        }finally {
            if(ps != null) ps.close();
            if(rs != null) rs.close();
        }
    }

    public static List<ApplicationPojo> getApplicationsByJobAndStatus(int jobId, String status)throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ApplicationPojo> appList = new ArrayList<>();
        try {
            String qry = "SELECT * FROM applications WHERE job_id = ? AND status = ? ORDER BY score";
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(qry);
            ps.setInt(1, jobId);
            ps.setString(2, status);
            rs = ps.executeQuery();
            while (rs.next()) {
                ApplicationPojo application = new ApplicationPojo();
                application.setId(rs.getInt("id"));
                application.setUserId(rs.getInt("user_id"));
                application.setJobId(rs.getInt("job_id"));
                application.setResume_path(rs.getString("resume_path"));
                application.setScore(rs.getDouble("score"));
                application.setStatus(rs.getString("status"));
                application.setAppliedAt(rs.getString("applied_at"));
                appList.add(application);
            }
            return appList;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
    }

    public static boolean updateApplicationStatus(int id, String status)throws Exception{
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = DBConnection.getConnection();
            String qry = "UPDATE applications SET status = ? WHERE id = ?";
            ps = conn.prepareStatement(qry);
            return ps.executeUpdate() > 0;
        }finally {
            if(ps != null) ps.close();
        }
    }
}
