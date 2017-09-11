//package DALayer.FileAccess;
//
//import DALayer.IStudentDA;
//import DAO.Student;
//
//import java.io.*;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class FileAccessImpl implements IStudentDA {
//    private final String fileName = "StudentStorage.txt";
//    private ArrayList<Student> students = new ArrayList<>();
//    private File file;
//
//    public FileAccessImpl() throws IOException {
//        file = new File("/" + fileName);
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//    }
//
//
//    @Override
//    public ArrayList<Student> getAllStudents() {
//        FileInputStream outputStream = null;
//        try {
//            outputStream = new FileInputStream(file);
//            ObjectInputStream objInput = new ObjectInputStream(outputStream);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    private void parseStudentInfoFromFile(Student aStudent) {
//    }
//
//
//    @Override
//    public boolean Create(Student student) {
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            DataOutputStream dataOutputStream = new DataOutputStream((fileOutputStream));
//            writeStudentToFile(dataOutputStream, student);
//            dataOutputStream.close();
//            fileOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void writeStudentToFile(DataOutputStream dataOutputStream, Student newStudent) {
//        try {
//            dataOutputStream.writeChars(newStudent.getId());
//            dataOutputStream.writeChars(newStudent.getName());
//            dataOutputStream.writeChars(newStudent.getDOB().toString());
//            dataOutputStream.writeChars(newStudent.getGender());
//            dataOutputStream.writeChars(newStudent.getClassCode());
//            dataOutputStream.writeChars(Float.toString(newStudent.getGPA()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Override
//    public boolean Delete(String studentId) {
//        return false;
//    }
//
//    @Override
//    public boolean Update(Student student) {
//        return false;
//    }
//
//    @Override
//    public ArrayList<Student> searchByName(String studentName) {
//        return null;
//    }
//
//    @Override
//    public ArrayList<Student> searchById(String studentId) {
//        return null;
//    }
//
//    @Override
//    public ArrayList<Student> searchStudentBetweenGPA(float belowGPA, float aboveGPA) {
//        return null;
//    }
//
//    @Override
//    public ArrayList<Student> searchStudentLargerGPA(float GPA) {
//        return null;
//    }
//}