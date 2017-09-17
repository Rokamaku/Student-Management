package StudentManagement.View.Database;

import StudentManagement.DataAccess.DatabaseAccess.StudentDAO;
import StudentManagement.MainApp;
import StudentManagement.Model.Student;
import StudentManagement.View.SourceManagement;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class DatabaseSourceManagement extends SourceManagement {

    public DatabaseSourceManagement() {
        this.primaryStage = MainApp.primaryStage;
    }
    @Override
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public ObservableList<Student> getStudents() {
        ObservableList<Student> students = null;
        try {
            students = StudentDAO.getAllStudents();
        } catch (SQLException e) {
            showErrorSQLException(e);
        } catch (ClassNotFoundException e) {
            showErrorClassNotFoundException();
        }
        return students;
    }

    @Override
    public void addStudent(Student student) {
        try {
            StudentDAO.addStudent(student);
        } catch (SQLException e) {
            showErrorSQLException(e);
        } catch (ClassNotFoundException e) {
            showErrorClassNotFoundException();
        }
    }

    @Override
    public void delStudent(Student student)  {
        try {
            StudentDAO.deleteStudent(student.getId());
        } catch (SQLException e) {
            showErrorSQLException(e);
        } catch (ClassNotFoundException e) {
            showErrorClassNotFoundException();
        }
    }

    @Override
    public void updateStudent(Student student) {
        try {
            StudentDAO.updateStudent(student);
        } catch (SQLException e) {
            showErrorSQLException(e);
        } catch (ClassNotFoundException e) {
            showErrorClassNotFoundException();
        }
    }

    @Override
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass()
                    .getResource("/StudentManagement/View/Database/RootLayout.fxml"));
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

    }

    private void showErrorClassNotFoundException() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(primaryStage);
        alert.setTitle("Error!");
        alert.setHeaderText("Class Not Found Exception");
        alert.setContentText("JDBC Driver Class Not Found!");
        alert.showAndWait();
    }

    private void showErrorSQLException(SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(primaryStage);
        alert.setTitle("Error!");
        alert.setHeaderText("SQL Exception");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}
