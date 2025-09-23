package in.hiresense.dao;

import in.hiresense.dbutils.DBConnection;
import in.hiresense.pojo.UserPojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static int registerUser(UserPojo user)throws Exception{
        Connection conn;
        PreparedStatement ps = null;
        int count = 0;
        try{
            conn = DBConnection.getConnection();
            String qry = "insert into users(name,email,password,role) values(?,?,?,?)";
            ps = conn.prepareStatement(qry);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            count = ps.executeUpdate();
        }finally{
            if(ps != null) ps.close();
            return count;
        }
    }

    private static UserPojo getUserByEmail(String email)throws Exception{
        Connection conn;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserPojo user = null;
        try{
            conn = DBConnection.getConnection();
            String qry = "SELECT * FROM users where email = ?";
            ps = conn.prepareStatement(qry);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()){
                user = new UserPojo();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));
                user.setCreatedAt(rs.getDate("created_at"));
            }
        }finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            return user;
        }
    }

    public static UserPojo getUserById(int id)throws Exception{
        Connection conn;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserPojo user = null;
        try{
            conn = DBConnection.getConnection();
            String qry = "SELECT * FROM users where id = ?";
            ps = conn.prepareStatement(qry);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                user = new UserPojo();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));
                user.setCreatedAt(rs.getDate("created_at"));
            }
        }finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            return user;
        }
    }
    public static List<UserPojo> getAllUsers()throws Exception{
        Connection conn;
        Statement st = null;
        ResultSet rs = null;
        List<UserPojo> userList = new ArrayList<>();
        try{
            conn = DBConnection.getConnection();
            st = conn.createStatement();
            String qry = "Select * from users order by id desc";
            rs = st.executeQuery(qry);
            while(rs.next()){
                UserPojo user = new UserPojo();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));
                user.setCreatedAt(rs.getDate("created_at"));
                userList.add(user);
            }
        }finally{
            if(rs != null) rs.close();
            if(st != null) st.close();
            return userList;
        }
    }

    public static int updateStatus(int userId,String status)throws Exception{
        Connection conn;
        PreparedStatement ps = null;
        int res = 0;
        try{
            conn = DBConnection.getConnection();
            String qry = "Update users set status = ? where id = ?";
            ps = conn.prepareStatement(qry);
            ps.setString(1, status);
            ps.setInt(2, userId);
            res = ps.executeUpdate();
        }finally {
            if(ps != null) ps.close();
            return res;
        }
    }

}
