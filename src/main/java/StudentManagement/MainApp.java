package StudentManagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainApp.primaryStage = primaryStage;
        MainApp.primaryStage.getIcons().add(new Image("images/if_07_Note_Book_2064482.png"));
        Parent root = FXMLLoader.load(getClass().getResource("/StudentManagement/View/SelectSource.fxml"));
        primaryStage.setTitle("Select Source");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
