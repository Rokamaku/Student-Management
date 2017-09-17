package StudentManagement.DataAccess.DatabaseAccess;

import StudentManagement.Model.Student;
import StudentManagement.Util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {
    public static ObservableList<Student> getAllStudents() throws SQLException, ClassNotFoundException {
        String queryStmt = "select * from student";
        ObservableList<Student> students = FXCollections.observableArrayList();
        ResultSet rs = DBUtil.dbExcuteQuery(queryStmt);
        while (rs.next()) {
            Student aStudent = new Student();
            aStudent.setId(rs.getString("id"));
            aStudent.setfName(rs.getString(2));
            aStudent.setlName(rs.getString(3));
            aStudent.setDOB(rs.getDate("DOB").toLocalDate());
            aStudent.setGender(rs.getString("gender"));
            aStudent.setAddress(rs.getString("address"));
            aStudent.setPhone(rs.getString("phone"));
            aStudent.setEmail(rs.getString("email"));
            aStudent.setClassCode(rs.getString("classCode"));
            aStudent.setGPA(rs.getFloat("GPA"));
            students.add(aStudent);
        }
        return students;
    }

    public static void addStudent(Student student) throws SQLException, ClassNotFoundException {
        String updateStmt = String.format("insert into student values ('%s','%s','%s','%tF','%s','%s','%s','%s','%s','%f')",
                student.getId(),
                student.getfName(),
                student.getlName(),
                student.getDOB(),
                student.getGender(),
                student.getPhone(),
                student.getEmail(),
                student.getAddress(),
                student.getClassCode(),
                student.getGPA());
        try {
            DBUtil.dbExcuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.println("Error occurred while Create Operation: " + e);
            throw e;
        }
    }

    public static void deleteStudent(String studentId) throws SQLException, ClassNotFoundException {
        String updateStmt = String.format("delete from student where id = '%s'", studentId);
        try {
            DBUtil.dbExcuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.println("Error occurred while DELETE Operation:" + e);
            throw e;
        }
    }

    public static void updateStudent(Student student) throws SQLException, ClassNotFoundException {

        String updateStmt = String.format("update student set fName='%s', lName='%s', gender='%s', DOB='%tF'," +
                        "address='%s', phone='%s', email='%s', classCode='%s', GPA='%f' where id='%s'",
                student.getfName(), student.getlName(), student.getGender(), student.getDOB(), student.getAddress(),
                student.getPhone(), student.getEmail(), student.getClassCode(), student.getGPA(), student.getId());
        try {
            DBUtil.dbExcuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.println("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }
}
