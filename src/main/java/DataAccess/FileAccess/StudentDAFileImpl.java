package DataAccess.FileAccess;

import DataAccess.IStudentDA;
import Model.Student;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class StudentDAFileImpl implements IStudentDA {
    private final String fileName = "StudentStorage.json";
    private ArrayList<Student> students = new ArrayList<>();
    private File file;

    public StudentDAFileImpl() throws IOException {
        file = new File("/" + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
    }


    @Override
    public ArrayList<Student> getAllStudents() {
        try {
            FileReader reader = new FileReader(file);
            Gson gson = new Gson();
            Type collectionType = new TypeToken<ArrayList<Student>>(){}.getType();
            students = gson.fromJson(reader, collectionType);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void Create(Student student) {
        students.add(student);
        writeStudentsToFile();
    }

    @Override
    public boolean Delete(String studentId) {
        for (Student aStudent : students) {
            if (aStudent.getId().equals(studentId)) {
                students.remove(aStudent);
                writeStudentsToFile();
                return true;
            }
        }
        return false;

    }

    private void writeStudentsToFile() {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file, false);
            Gson gson = new Gson();
            gson.toJson(students, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Update(Student updatedStudent) {
        for (Student aStudent : students) {
            if (aStudent.getId().equals(updatedStudent.getId())) {
                int indexOfStudent = students.indexOf(aStudent);
                students.set(indexOfStudent, updatedStudent);
                writeStudentsToFile();
            }
        }
    }

    @Override
    public ArrayList<Student> searchByName(String studentName) {
        ArrayList<Student> listStudents = new ArrayList<>();
        for (Student aStudent : students) {
            if (aStudent.getfName().contains(studentName))
                listStudents.add(aStudent);
        }
        return listStudents;
    }

    @Override
    public ArrayList<Student> searchById(String studentId) {
        ArrayList<Student> listStudents = new ArrayList<>();
        for (Student aStudent : students) {
            if (aStudent.getId().equals(studentId))
                listStudents.add(aStudent);
        }
        return listStudents;
    }

    @Override
    public ArrayList<Student> searchStudentBetweenGPA(float belowGPA, float aboveGPA) {
        ArrayList<Student> listStudents = new ArrayList<>();
        for (Student aStudent : students) {
            if (aStudent.getGPA() >= belowGPA && aStudent.getGPA() <= aboveGPA)
                listStudents.add(aStudent);
        }
        return listStudents;
    }

    @Override
    public ArrayList<Student> searchStudentLargerGPA(float GPA) {
        ArrayList<Student> listStudents = new ArrayList<>();
        for (Student aStudent : students) {
            if (aStudent.getGPA() > GPA)
                listStudents.add(aStudent);
        }
        return listStudents;
    }
}