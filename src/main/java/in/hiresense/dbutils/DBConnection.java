package in.hiresense.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
     private static Connection conn;
     public static void openConnection(String url, String username, String password){
         try{
             Class.forName("com.mysql.cj.jdbc.Driver");

             conn = DriverManager.getConnection(url,username,password);
             System.out.println("connected successfully to db");
         }catch (Exception ex){
             System.out.println("Cannot open connection in DBConnection");
             ex.printStackTrace();
         }
     }

     public static Connection getConnection() throws SQLException {
         if(conn == null)
             throw new SQLException("Connection not opened!");
         return conn;
     }

     public static void closeConnection(){
         if(conn != null){
             try{
                 conn.close();
                 System.out.println("Connection closed successfully");
             } catch (SQLException e) {
                 System.out.println("Cannot close the connection in DBConnection");
                 e.printStackTrace();
             }
         }
     }
}
