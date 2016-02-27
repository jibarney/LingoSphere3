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
public class ClassData {
    
    private int classId;
    private String className;
    private int teacherId;

    public ClassData(){
        
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return this.getClassName(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
