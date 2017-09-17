package StudentManagement.View;

import StudentManagement.MainApp;
import StudentManagement.Model.Student;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public abstract class SourceManagement {

    protected ObservableList<Student> students;
    protected Stage primaryStage;
    protected BorderPane rootLayout;

    public abstract Stage getPrimaryStage();
    public abstract ObservableList<Student> getStudents();
    public abstract void addStudent(Student student);
    public abstract void delStudent(Student student);
    public abstract void updateStudent(Student student);
    public abstract void initRootLayout();

    public void start() {
        this.primaryStage.setTitle("Student Management");

        initRootLayout();
        showStudentOverview();
    }

    public void showStudentOverview() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/StudentManagement/View/StudentOverview.fxml"));
            AnchorPane studentOverview = loader.load();

            rootLayout.setCenter(studentOverview);

            StudentOverviewController controller = loader.getController();
            controller.setMainScene(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean showStudentEditDialog(Student student) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/StudentManagement/View/StudentEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Student");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the student into the controller.
            StudentEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStudent(student);

            // Set the dialog icon.
            dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
