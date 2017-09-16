package StudentManagement.DataAccess;

import StudentManagement.Model.Student;

import java.util.ArrayList;

public interface IStudentDA {
    public ArrayList<Student> getAllStudents();
    public void Create(Student student);
    public boolean Delete(String studentId);
    public void Update(Student student);
    public ArrayList<Student> searchByName(String studentName);
    public ArrayList<Student> searchById(String studentId);
    public ArrayList<Student> searchStudentBetweenGPA(float belowGPA, float aboveGPA);
    public ArrayList<Student> searchStudentLargerGPA(float GPA);
}