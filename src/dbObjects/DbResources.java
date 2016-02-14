package dbObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RJ
 */
public class DbResources {
    
    private Connection conn = null;
    private final String userName = "admin1";
    private final String password = "dbAdmin1";
    private final String selectQuery = "select * from LSDatabase";
    
    public DbResources()
    {
        try{
            Properties connectionProps = new Properties();
            connectionProps.put("user", this.userName);
            connectionProps.put("password", this.password);
            
            conn = DriverManager.getConnection(
                   "jdbc:mysql://awsdatabase.cmrhopvooloy.us-east-1.rds.amazonaws.com:3306/LSDatabase",
                   connectionProps);
            
            System.out.println("Connected to database");
        }
        catch(Exception e){
            System.out.println("Sql error: " + e);
        }
    }
    
    public UserData getUser(String userName, String userPass) throws SQLException {
        
        UserData user = new UserData();
        String query = "select iduserData, userName, isTeacher"
                       + " from LSDatabase.userData where userName = ?"
                       + " and userPassword = ?";
        //Statement st = null;
        PreparedStatement ps = null;
        try{
            
            ps = conn.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, userPass);
            //st = conn.createStatement();
            //ps.setString(1, userName);
            
            ResultSet rs = ps.executeQuery();//st.executeQuery(query);
            
            //if(rs.getInt("iduserData") > 0){
                while(rs.next()){
                user.setUserId(rs.getInt("iduserData"));
                user.setUserName(rs.getString("userName"));
                
                boolean isTeacher = false;
                if(rs.getInt("isTeacher") >= 1) isTeacher = true;
                user.setIsTeacher(isTeacher);
                }
                
                if(user.getUserName() != null && !user.getUserName().equals("")){
                    return user;
                }
                else{
                    return null;
                }
        }
        catch(SQLException e){
            System.out.println("SQL getUser error: " + e);
        }
        finally{
            if(ps != null)
              ps.close();
        }
        return null;
    }
    
    public UserData createUser(String userName, String userPass, Boolean isTeacher) throws SQLException {
        
        UserData user = new UserData();
        String query = "insert into LSDatabase.userData (userName, userPassword,isTeacher)"
                       + " value(?, ?, ?)";
        //Statement st = null;
        PreparedStatement ps = null;
        try{
            
            ps = conn.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, userPass);
            
            if(isTeacher){
                ps.setInt(3, 1);
            }else{
                ps.setInt(3, 0);
            }
            
            ps.executeUpdate();
            
            user.setUserName(userName);
            user.setPassword(userPass);
            user.setIsTeacher(isTeacher);
            
            return user;
        }
        catch(SQLException e){
            System.out.println("SQL createUser error: " + e);
            return null;
        }
        finally{
            if(ps != null)
              ps.close();
        }
    }
    
    
}
