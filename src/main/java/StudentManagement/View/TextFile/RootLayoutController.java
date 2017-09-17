package StudentManagement.View.TextFile;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;


public class RootLayoutController {

    // Reference to the main application
    private TextSourceManagement mainScene;

    public void setMainScene(TextSourceManagement mainScene) {
        this.mainScene = mainScene;
    }

    @FXML
    private void handleNew() {
        mainScene.getStudents().clear();
        mainScene.setStudentFilePath(null);
    }

    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainScene.getPrimaryStage());

        if (file != null) {
            mainScene.loadStudentsFromFile(file);
        }
    }

    @FXML
    private void handleSave() {
        File studentFile = mainScene.getStudentFilePath();
        if (studentFile != null) {
            mainScene.saveStudentsToFile(studentFile);
        } else {
            handleSaveAs();
        }
    }

    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainScene.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainScene.saveStudentsToFile(file);
        }
    }

    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Student Management");
        alert.setHeaderText("About");
        alert.setContentText("Author: Bui Hoang Anh");

        alert.showAndWait();
    }

    @FXML
    private void handleClose() {
        System.exit(0);
    }
}
