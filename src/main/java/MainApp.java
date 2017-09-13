package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("SelectSource.fxml"));
//        primaryStage.setTitle("Student Management");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();

        Parent root = FXMLLoader.load(getClass().getResource("../StudentOverview.fxml"));
        primaryStage.setTitle("Student Management");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
