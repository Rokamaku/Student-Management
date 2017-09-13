package View;

import Model.Student;
import com.sun.deploy.util.StringUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class StudentEditDialogController {

    @FXML
    private TextField fNameField;
    @FXML
    private TextField lNameField;
    @FXML
    private ComboBox<String> genderBox;
    @FXML
    private DatePicker DOBDatePicker;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField classField;
    @FXML
    private TextField GPAField;

    private Stage dialogStage;
    private Student student;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

        // Set the dialog icon.
//        this.dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
    }

    public void setPerson(Student student) {
        this.student = student;

        fNameField.setText(student.getfName());
        lNameField.setText(student.getlName());
        genderBox.getSelectionModel().select(student.getGender());
        DOBDatePicker.setValue(student.getDOB());
        addressField.setText(student.getAddress());
        phoneField.setText(student.getPhone());
        emailField.setText(student.getEmail());
        classField.setText(student.getClassCode());
        GPAField.setText(Float.toString(student.getGPA()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            student.setfName(fNameField.getText());
            student.setlName(lNameField.getText());
            student.setGender(genderBox.getValue());
            student.setDOB(DOBDatePicker.getValue());
            student.setAddress(addressField.getText());
            student.setPhone(phoneField.getText());
            student.setEmail(emailField.getText());
            student.setClassCode(classField.getText());
            student.setGPA(Float.parseFloat(GPAField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    private boolean isInputValid() {
        String errorMsg = "";

        if (fNameField.getText() == null || fNameField.getText().length() == 0)
            errorMsg += "Invalid first name!\n";
        if (lNameField.getText() == null || lNameField.getText().length() == 0)
            errorMsg += "Invalid last name!\n";
        if (addressField.getText() == null || addressField.getText().length() == 0)
            errorMsg += "Invalid address";
        if (phoneField.getText() == null || addressField.getText().length() == 0)
            errorMsg += "Invalid phone number";
        if (emailField.getText() == null || emailField.getText().length() == 0)
            errorMsg += "Invalid email";
        if (classField.getText() == null || classField.getText().length() == 0)
            errorMsg += "Invalid class";
        if (GPAField.getText() == null || !GPAField.getText().chars().allMatch(Character::isDigit))
            errorMsg += "Invalid number";
        if (errorMsg.length() == 0)
            return true;
        else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMsg);
            alert.showAndWait();

            return false;
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
