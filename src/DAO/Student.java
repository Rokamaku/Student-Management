package DAO;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {

    private String id;
    private String name;
    private Date DOB;
    private String gender;
    private String classCode;
    private float GPA;

    public Student(String id, String name, Date DOB, String gender, String classCode, float GPA) {
        this.id = id;
        this.name = name;
        this.DOB = DOB;
        this.gender = gender;
        this.classCode = classCode;
        this.GPA = GPA;
    }

    public Student(String name, Date DOB, String gender, String classCode, float GPA) {
        this.name = name;
        this.DOB = DOB;
        this.gender = gender;
        this.classCode = classCode;
        this.GPA = GPA;
    }

    public float getGPA() {
        return GPA;
    }

    public void setGPA(float GPA) {
        this.GPA = GPA;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    @Override
    public String toString() {
        return "id:" + id
                + "name:" + name
                + "DOB:" + DOB.toString()
                + "classCode" + classCode
                + "GPA" + Float.toString(GPA);
    }
}