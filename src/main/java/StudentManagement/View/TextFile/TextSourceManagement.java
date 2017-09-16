package StudentManagement.View.TextFile;

import StudentManagement.DataAccess.FileAccess.StudentDAFile;
import StudentManagement.MainApp;
import StudentManagement.Model.Student;
import StudentManagement.Model.StudentListWrapper;
import StudentManagement.View.StudentEditDialogController;
import StudentManagement.View.StudentOverviewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class TextSourceManagement {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private StudentDAFile DAO;

    public ObservableList<Student> studentsData;

    public ObservableList<Student> getStudentsData() {
        return studentsData;
    }

    public void setStudentsData(ObservableList<Student> studentsData) {
        this.studentsData = studentsData;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public TextSourceManagement() {
        this.primaryStage = MainApp.primaryStage;
        studentsData = FXCollections.observableArrayList();
        DAO = new StudentDAFile();
    }

    public void start() {
        this.primaryStage.setTitle("Student Management");

        initRootLayout();
        showStudentOverview();
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

    public void showStudentOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/StudentManagement/View/StudentOverview.fxml"));
            AnchorPane personOverview = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            StudentOverviewController controller = loader.getController();
            controller.setMainApp(this);

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
            dialogStage.setTitle("Edit Person");
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

    public void loadStudentsFromFile(File file) {
        try {
            StudentListWrapper wrapper = DAO.loadStudentDataFromFile(file);
            studentsData.clear();
            studentsData.addAll(wrapper.getStudents());

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
            DAO.saveStudentDataToFile(file, studentsData);
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

}
