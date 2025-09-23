package in.hiresense.dao;

import in.hiresense.dbutils.DBConnection;
import in.hiresense.pojo.JobPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobDao {

    public static boolean postJob(JobPojo job) throws Exception {
        Connection conn;
        PreparedStatement ps = null;
        boolean res = false;
        try{
            conn = DBConnection.getConnection();
            String qry = "insert into jobs(title,description,skills,company,location,experience,package_lpa,vacancies,employer_id) values(?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(qry);
            ps.setString(1, job.getTitle());
            ps.setString(2, job.getDescription());
            ps.setString(3, job.getSkills());
            ps.setString(4, job.getCompany());
            ps.setString(5, job.getLocation());
            ps.setString(6, job.getExperience());
            ps.setString(7, job.getPackageLpa());
            ps.setInt(8, job.getVacancies());
            ps.setInt(9, job.getEmployerId());
            res = ps.executeUpdate() > 1;
        }finally{
            if(ps != null) ps.close();
            return res;
        }
    }

    public static JobPojo getJobById(int jobId)throws Exception{
        Connection conn;
        PreparedStatement ps = null;
        ResultSet rs = null;
        JobPojo job = null;
        try{
            conn = DBConnection.getConnection();
            String qry = "SELECT * FROM jobs where id = ?";
            ps = conn.prepareStatement(qry);
            ps.setInt(1, jobId);
            rs = ps.executeQuery();
            if(rs.next()){
                job = new JobPojo();
                job.setId(rs.getInt("id"));
                job.setTitle(rs.getString("title"));
                job.setDescription(rs.getString("description"));
                job.setSkills(rs.getString("skills"));
                job.setCompany(rs.getString("company"));
                job.setLocation(rs.getString("location"));
                job.setExperience(rs.getString("experience"));
                job.setPackageLpa(rs.getString("package_lpa"));
                job.setVacancies(rs.getInt("vacancies"));
                job.setEmployerId(rs.getInt("employer_id"));
                job.setCreatedAt(rs.getTimestamp("created_at"));
                job.setStatus(rs.getString("status"));
            }
        }finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            return job;
        }
    }

    public static List<JobPojo> getJobsByEmployer(int employerId, String search, String status, String sort)
            throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        // Create a list to hold the resulting JobPojo objects
        List<JobPojo> list = new ArrayList<>();

        try {



            // Get a connection to the database
            conn = DBConnection.getConnection();

            // Base SQL query to fetch job details along with the count of applicants
            StringBuilder sql = new StringBuilder(
                    "SELECT j.*, (SELECT COUNT(*) FROM applications a WHERE a.job_id = j.id) AS applicant_count "
                            + "FROM jobs j WHERE j.employer_id = ?");

            // Prepare list of parameters for the query
            List<Object> params = new ArrayList<>();
            params.add(employerId); // First parameter is always employerId

            // Add search condition if the search term is provided
            if (search != null && !search.trim().isEmpty()) {
                sql.append(" AND j.title LIKE ?");
                params.add("%" + search + "%");
            }

            // Add job status filter if provided
            if (status != null && !status.trim().isEmpty()) {
                sql.append(" AND j.status = ?");
                params.add(status);
            }

            // Handle sorting based on applicant count or created_at
            if ("asc".equalsIgnoreCase(sort)) {
                sql.append(" ORDER BY applicant_count ASC");
            } else if ("desc".equalsIgnoreCase(sort)) {
                sql.append(" ORDER BY applicant_count DESC");
            } else {
                sql.append(" ORDER BY j.created_at DESC");
            }

            // Prepare and bind parameters to the SQL statement
            ps = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            // Execute the query and process the result set
            rs = ps.executeQuery();
            while (rs.next()) {
                // Populate the JobPojo object with data from a result set
                JobPojo job = new JobPojo();
                job.setId(rs.getInt("id"));
                job.setTitle(rs.getString("title"));
                job.setDescription(rs.getString("description"));
                job.setSkills(rs.getString("skills"));
                job.setCompany(rs.getString("company"));
                job.setLocation(rs.getString("location"));
                job.setExperience(rs.getString("experience"));
                job.setPackageLpa(rs.getString("package_lpa"));
                job.setVacancies(rs.getInt("vacancies"));
                job.setEmployerId(rs.getInt("employer_id"));
                job.setCreatedAt(rs.getTimestamp("created_at"));
                job.setStatus(rs.getString("status"));
                job.setApplicantCount(rs.getInt("applicant_count"));
                list.add(job); // Add job to the list
            }

            // Return the final list of jobs

        } finally {
            // Clean up resources to avoid memory leaks
            if(rs!=null)
                rs.close();
            if(ps!=null)
                ps.close();
            return list;
        }
    }

    public static void toggleJobStatus(int jobId)throws Exception{
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String qry = "update jobs set status = case when status = 'active' then 'inactive' else 'active' end where id=?";
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(qry);
            ps.executeUpdate();
        }finally {
            if (ps != null) ps.close();
        }
    }

    public static boolean deleteJob(int jobId)throws Exception{
        int rowsEffected = 0;
        Connection conn = null;
        PreparedStatement ps1 = null, ps2 = null;
        try{
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            ps1 = conn.prepareStatement("delete from applications where job_id=?");
            ps1.setInt(1, jobId);
            ps1.executeUpdate();
            ps2 = conn.prepareStatement("delete from jobs where id=?");
            ps2.setInt(1, jobId);
            rowsEffected = ps2.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            if(conn != null) conn.rollback();
            throw e;
        } finally {
            if(ps1 != null) ps1.close();
            if(ps2 != null) ps2.close();
            return rowsEffected > 0;
        }
    }


    public static List<JobPojo> getAllJobsForUserDashboard(String search, String sort,String location, String experience, String packageLpa)
            throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        // Create a list to hold the resulting JobPojo objects
        List<JobPojo> list = new ArrayList<>();

        try {
            // Get a connection to the database
            conn = DBConnection.getConnection();

            // Base SQL query to fetch job details along with the count of applicants
            StringBuilder sql = new StringBuilder(
                    "SELECT j.* FROM jobs j  WHERE j.status = 'active'");

            // Prepare a list of parameters for the query
            List<Object> params = new ArrayList<>();

            // Add search condition if the search term is provided
            if (search != null && !search.trim().isEmpty()) {
                sql.append(" AND (j.title LIKE ? OR j.company LIKE?)");
                String keyword = "%" + search.trim() + "%";
                params.add(keyword);
                params.add(keyword);
            }

            // Handle sorting based on applicant count or created_at
            if ("asc".equalsIgnoreCase(sort)) {
                sql.append(" ORDER BY j.vacancies ASC");
            } else if ("desc".equalsIgnoreCase(sort)) {
                sql.append(" ORDER BY j.vacancies DESC");
            } else {
                sql.append(" ORDER BY j.created_at DESC");
            }

            //add search on the basis of location
            if(location != null && !location.trim().isEmpty()){
                sql.append("AND j.location = ?");
                params.add("%" + location.trim() + "%");
            }
            // add search on the basis of experience
            if(experience != null && !experience.trim().isEmpty()){
                sql.append("AND j.experience = ?");
                params.add(experience.trim());
            }

            //add search on the basis of packageLpa
            if(packageLpa != null && !packageLpa.trim().isEmpty()){
                sql.append("AND j.package_lpa = ?");
                params.add(packageLpa.trim());
            }

            // Prepare and bind parameters to the SQL statement
            ps = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            // Execute the query and process the result set
            rs = ps.executeQuery();
            while (rs.next()) {
                // Populate the JobPojo object with data from a result set
                JobPojo job = new JobPojo();
                job.setId(rs.getInt("id"));
                job.setTitle(rs.getString("title"));
                job.setDescription(rs.getString("description"));
                job.setSkills(rs.getString("skills"));
                job.setCompany(rs.getString("company"));
                job.setLocation(rs.getString("location"));
                job.setExperience(rs.getString("experience"));
                job.setPackageLpa(rs.getString("package_lpa"));
                job.setVacancies(rs.getInt("vacancies"));
                job.setEmployerId(rs.getInt("employer_id"));
                job.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(job); // Add job to the list
            }

            // Return the final list of jobs

        } finally {
            // Clean up resources to avoid memory leaks
            if(rs!=null)
                rs.close();
            if(ps!=null)
                ps.close();
            return list;
        }
    }

    public static List<JobPojo> getAllJobsWithEmployerAndApplicationCount()throws Exception{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JobPojo> jobList = new ArrayList<>();
        try{
            conn = DBConnection.getConnection();
            String sql = "SELECT j.*, (SELECT COUNT(*) FROM applications a WHERE a.job_id = j.id) AS applicant_count "
                    + "FROM jobs j";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                JobPojo job = new JobPojo();
                job.setId(rs.getInt("id"));
                job.setTitle(rs.getString("title"));
                job.setDescription(rs.getString("description"));
                job.setSkills(rs.getString("skills"));
                job.setCompany(rs.getString("company"));
                job.setLocation(rs.getString("location"));
                job.setExperience(rs.getString("experience"));
                job.setPackageLpa(rs.getString("package_lpa"));
                job.setVacancies(rs.getInt("vacancies"));
                job.setEmployerId(rs.getInt("employer_id"));
                job.setCreatedAt(rs.getTimestamp("created_at"));
                job.setStatus(rs.getString("status"));
                job.setApplicantCount(rs.getInt("applicant_count"));
                jobList.add(job);
            }
            return jobList;
        }finally {
            if(rs!=null)
                rs.close();
            if(ps!=null)
                ps.close();
        }
    }

}
