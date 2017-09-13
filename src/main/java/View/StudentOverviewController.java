package View;

import Model.Student;
import Util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class StudentOverviewController {

    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> fNameCol;
    @FXML
    private TableColumn<Student, String> lNameCol;

    @FXML
    private Label fNameLabel;
    @FXML
    private Label lNameLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label DOBLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label classLabel;
    @FXML
    private Label GPALabel;


    private View.MainApp mainApp;

    public StudentOverviewController() {
    }

    @FXML
    private void initialize() {
        fNameCol.setCellValueFactory(cellData -> cellData.getValue().fNameProperty());
        lNameCol.setCellValueFactory(cellData -> cellData.getValue().lNameProperty());

        showStudentDetails(null);
    }

    private void showStudentDetails(Student student) {
        if (student != null) {
            fNameLabel.setText(student.getfName());
            lNameLabel.setText(student.getlName());
            genderLabel.setText(student.getGender());
            DOBLabel.setText(DateUtil.format(student.getDOB()));
            addressLabel.setText(student.getAddress());
            phoneLabel.setText(student.getPhone());
            emailLabel.setText(student.getEmail());
            classLabel.setText(student.getClassCode());
            GPALabel.setText(Float.toString(student.getGPA()));
        }
        else {
            fNameLabel.setText("");
            lNameLabel.setText("");
            genderLabel.setText("");
            DOBLabel.setText("");
            addressLabel.setText("");
            phoneLabel.setText("");
            emailLabel.setText("");
            classLabel.setText("");
            GPALabel.setText("");
        }
    }

    private void handleNewStudent() {
        Person tempPerson = new Student;
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = studentTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            studentTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}
