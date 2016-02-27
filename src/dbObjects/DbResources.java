package dbObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
    
    private String dbSetupMsg ;
    
    public DbResources()
    {     
        try{
          Class.forName("com.mysql.jdbc.Driver").newInstance(); 
            
            Properties connectionProps = new Properties();
            connectionProps.put("user", this.userName);
            connectionProps.put("password", this.password);
            
            conn = DriverManager.getConnection(
                   "jdbc:mysql://awsdatabase.cmrhopvooloy.us-east-1.rds.amazonaws.com:3306/LSDatabase",
                   connectionProps);
            
            System.out.println("Connected to database");
            dbSetupMsg = "Connected to SQL database";
        }
        catch(Exception e){
            System.out.println("Sql error: " + e);
            dbSetupMsg = "SQL error"+e;
        }
    }
    
    public String getSQLSetupStatus() { return dbSetupMsg ;  }
    
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
    
    public UserData createUser(String userName, String userPass, Boolean isTeacher, int teacherId) throws SQLException {
        
        UserData user = new UserData();
         String query = "insert into LSDatabase.userData (userName, userPassword,isTeacher)"
                       + " value(?, ?, ?)";
        
        if(teacherId > 0){
            query = "insert into LSDatabase.userData (userName, userPassword,isTeacher, teachersId)"
                       + " value(?, ?, ?, ?)";
        }
       
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
            
            if(teacherId > 0){
                ps.setInt(4, teacherId);
                user.setTeacherId(teacherId);
            }
            ps.executeUpdate();
            
            user.setUserName(userName);
            user.setPassword(userPass);
            user.setIsTeacher(isTeacher);
            
            return user;
        }
        catch(SQLException e){
            System.out.println("SQL createUser error: " + e);
            
            dbSetupMsg = "SQL create user error :"+e ;
            
            return null;
        }
        finally{
            if(ps != null)
              ps.close();
        }
    }
    
    public List<ClassData> getClasses() throws SQLException {
    
        String query = "select idclassData, className"
                       + " from LSDatabase.classData";
        
        Statement st = null;
        try{
            
            st = conn.createStatement();
            
            //st = conn.createStatement();
            //ps.setString(1, userName);
            
            ResultSet rs = st.executeQuery(query);
            List<ClassData> classes = new ArrayList<ClassData>();
            
                while(rs.next()){
                    ClassData newClass = new ClassData();
                    newClass.setClassId(rs.getInt("idclassData"));
                    newClass.setClassName(rs.getString("className"));
                
                    classes.add(newClass);
                }
                
                return classes;
        }
        catch(SQLException e){
            
            System.out.println("SQL getClass error: " + e);
        }
        finally{
            if(st != null)
              st.close();
        }
        return null;
    }
    
    public List<UserData> getTeachers() throws SQLException {
    
        String query = "select iduserData, userName"
                       + " from LSDatabase.userData"
                        + " where isTeacher = 1";
        
        Statement st = null;
        try{
            
            st = conn.createStatement();
            
            //st = conn.createStatement();
            //ps.setString(1, userName);
            
            ResultSet rs = st.executeQuery(query);
            List<UserData> teachers = new ArrayList<UserData>();
            
                while(rs.next()){
                    UserData newUser = new UserData();
                    newUser.setUserName(rs.getString("userName"));
                    newUser.setUserId(rs.getInt("iduserData"));
                    teachers.add(newUser);
                }
                
                return teachers;
        }
        catch(SQLException e){
            
            System.out.println("SQL getClass error: " + e);
        }
        finally{
            if(st != null)
              st.close();
        }
        return null;
    }
}
