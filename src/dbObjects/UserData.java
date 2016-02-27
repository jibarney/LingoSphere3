/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbObjects;

/**
 *
 * @author RJ
 */
public class UserData {
    
    private int userId;
    private String userName;
    private String password;
    private boolean isTeacher;
    private int teacherId;
    
    public UserData()
    {
        
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(boolean isTeacher) {
        this.isTeacher = isTeacher;
    }
    
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
  
    public String welcomeUser(){
        
        String usertype = "STUDENT"; 
        if (this.isTeacher) usertype = "INSTRUCTOR";
        return  "   Welcome user "+userName +"!\n"+
                "   You have "+usertype+" access. " ;
}
    @Override
    public String toString() {
        return this.getUserName(); //To change body of generated methods, choose Tools | Templates.
    }
}