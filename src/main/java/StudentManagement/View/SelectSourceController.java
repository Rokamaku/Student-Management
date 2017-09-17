package StudentManagement.View;

import StudentManagement.View.Database.DatabaseSourceManagement;
import StudentManagement.View.TextFile.TextSourceManagement;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

public class SelectSourceController {

    @FXML
    private RadioButton textFileRadioBtn;
    @FXML
    private RadioButton databaseRadioBtn;

    @FXML
    private void handleSceneProcess() {
        if (textFileRadioBtn.isSelected()) {
            createTextFileSourceScene();
        } else if (databaseRadioBtn.isSelected()) {
            createDatabaseSourceScene();
        }

    }

    private void createTextFileSourceScene() {
        TextSourceManagement textSourceManagement = new TextSourceManagement();
        textSourceManagement.start();
    }

    private void createDatabaseSourceScene() {
        DatabaseSourceManagement databaseSourceManagement = new DatabaseSourceManagement();
        databaseSourceManagement.start();
    }

}
