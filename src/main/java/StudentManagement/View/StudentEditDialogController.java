package StudentManagement.View;

import StudentManagement.Model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.regex.Pattern;


public class StudentEditDialogController {

    @FXML
    private TextField idField;
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
        this.dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
    }

    public void setStudent(Student student) {
        this.student = student;

        idField.setText(student.getId());
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
            errorMsg += "Invalid address!\n";
        if (phoneField.getText() == null || addressField.getText().length() == 0)
            errorMsg += "Invalid phone number!\n";
        if (emailField.getText() == null || emailField.getText().length() == 0)
            errorMsg += "Invalid email!\n";
        if (classField.getText() == null || classField.getText().length() == 0)
            errorMsg += "Invalid class!\n";
        if (GPAField.getText() == null || !isFloat(GPAField.getText()))
            errorMsg += "Invalid number!\n";
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

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private static final Pattern DOUBLE_PATTERN = Pattern.compile(
            "[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)" +
                    "([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|" +
                    "(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))" +
                    "[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*");

    public static boolean isFloat(String s)
    {
        return DOUBLE_PATTERN.matcher(s).matches();
    }
}
