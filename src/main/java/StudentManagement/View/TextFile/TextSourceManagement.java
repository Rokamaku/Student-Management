package StudentManagement.View.TextFile;

import StudentManagement.DataAccess.FileAccess.StudentDAFile;
import StudentManagement.MainApp;
import StudentManagement.Model.Student;
import StudentManagement.Model.StudentListWrapper;
import StudentManagement.View.SourceManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class TextSourceManagement extends SourceManagement {

    private StudentDAFile DAO;

    public ObservableList<Student> getStudents() {
        return students;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public TextSourceManagement() {
        this.primaryStage = MainApp.primaryStage;
        students = FXCollections.observableArrayList();
        DAO = new StudentDAFile();
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass()
                    .getResource("/StudentManagement/View/TextFile/RootLayout.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout, 800,400);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainScene(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try to load last opened person file.
        File file = getStudentFilePath();
        if (file != null) {
            loadStudentsFromFile(file);
        }
    }

    public void loadStudentsFromFile(File file) {
        try {
            StudentListWrapper wrapper = DAO.loadStudentDataFromFile(file);
            students.clear();
            students.addAll(wrapper.getStudents());

            // Save the file path to the registry.
            setStudentFilePath(file);
        }  catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    public void saveStudentsToFile(File file) {
        try {
            DAO.saveStudentDataToFile(file, students);
            setStudentFilePath(file);
        }  catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    public File getStudentFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public void setStudentFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("Student list - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("Student list");
        }
    }

    public void addStudent(Student student) {
    }

    @Override
    public void delStudent(Student student) {
    }

    @Override
    public void updateStudent(Student student) {
    }

}
