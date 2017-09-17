package StudentManagement.View.Database;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class RootLayoutController {

    private DatabaseSourceManagement mainScene;

    public void setMainScene(DatabaseSourceManagement mainScene) {
        this.mainScene = mainScene;
    }

    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
