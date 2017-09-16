package StudentManagement.View;

import StudentManagement.Model.Student;
import StudentManagement.Util.DateUtil;
import StudentManagement.View.TextFile.TextSourceManagement;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class StudentOverviewController {

    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> idCol;
    @FXML
    private TableColumn<Student, String> fNameCol;
    @FXML
    private TableColumn<Student, String> lNameCol;

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


    private TextSourceManagement mainScene;

    public StudentOverviewController() {
    }

    @FXML
    private void initialize() {
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        fNameCol.setCellValueFactory(cellData -> cellData.getValue().fNameProperty());
        lNameCol.setCellValueFactory(cellData -> cellData.getValue().lNameProperty());

        showStudentDetails(null);

        studentTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showStudentDetails(newValue));
    }

    public void setMainApp(TextSourceManagement mainScene) {
        this.mainScene = mainScene;

        // Add observable list data to the table
        studentTable.setItems(mainScene.getStudentsData());
    }

    private void showStudentDetails(Student student) {
        if (student != null) {
            genderLabel.setText(student.getGender());
            DOBLabel.setText(DateUtil.format(student.getDOB()));
            addressLabel.setText(student.getAddress());
            phoneLabel.setText(student.getPhone());
            emailLabel.setText(student.getEmail());
            classLabel.setText(student.getClassCode());
            GPALabel.setText(Float.toString(student.getGPA()));
        }
        else {
            genderLabel.setText("");
            DOBLabel.setText("");
            addressLabel.setText("");
            phoneLabel.setText("");
            emailLabel.setText("");
            classLabel.setText("");
            GPALabel.setText("");
        }
    }

    @FXML
    private void handleNewStudent() {
        Student tempStudent = new Student();
        tempStudent.setId(Student.createNewStudentId(mainScene.getStudentsData()));
        boolean okClicked = mainScene.showStudentEditDialog(tempStudent);
        if (okClicked) {
            mainScene.getStudentsData().add(tempStudent);
        }
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = studentTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            studentTable.getItems().remove(selectedIndex);
        } else {
            handleNoSelection();
        }
    }

    @FXML
    private void handleEditStudent() {
        Student selectedPerson = studentTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainScene.showStudentEditDialog(selectedPerson);
            if (okClicked) {
                showStudentDetails(selectedPerson);
            }

        } else {
            handleNoSelection();
        }
    }

    private void handleNoSelection() {
        // Nothing selected.
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainScene.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");

        alert.showAndWait();
    }
}
