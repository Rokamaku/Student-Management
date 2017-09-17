package StudentManagement.Model;

import StudentManagement.Util.LocalDateAdapter;
import javafx.beans.property.*;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.List;

public class Student {

    private final StringProperty id;
    private final StringProperty fName;
    private final StringProperty lName;
    private final ObjectProperty<LocalDate> DOB;
    private final StringProperty gender;
    private final StringProperty phone;
    private final StringProperty email;
    private final StringProperty address;
    private final StringProperty classCode;
    private final FloatProperty GPA;

    public Student() {
        this.id = new SimpleStringProperty();
        this.fName = new SimpleStringProperty();
        this.lName = new SimpleStringProperty();
        this.DOB = new SimpleObjectProperty<>();
        this.gender = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.classCode = new SimpleStringProperty();
        GPA = new SimpleFloatProperty();
    }

    public Student(String id, String fName,
                   String lName, LocalDate DOB,
                   String gender, String phone,
                   String email, String address,
                   String classCode, Float GPA) {

        this.id = new SimpleStringProperty(id);
        this.fName = new SimpleStringProperty(fName);
        this.lName = new SimpleStringProperty(lName);
        this.DOB = new SimpleObjectProperty<>(DOB);
        this.gender = new SimpleStringProperty(gender);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
        this.classCode = new SimpleStringProperty(classCode);
        this.GPA = new SimpleFloatProperty(GPA);
    }

    public static String createNewStudentId(List<Student> students) {
        String newId = "";
        String strYear = Integer.toString(LocalDate.now().getYear());
        String yearCode = strYear.substring(strYear.length() - 2, strYear.length());
        String newIdMark = "";
        if (students.size() == 0) {
            newIdMark = "0000";
        }
        else {
            String lastStudentId = students.get(students.size() - 1).getId();
            int idMark = Integer.parseInt(lastStudentId.substring(
                    lastStudentId.length() - 4, lastStudentId.length()));
            newIdMark = Integer.toString(++idMark);
            for (int i = newIdMark.length(); i < 4; i++) {
                newIdMark = "0" + newIdMark;
            }
        }
        return yearCode + "52" + newIdMark;
    }

    public float getGPA() {
        return GPA.get();
    }

    public void setGPA(float GPA) {
        this.GPA.set(GPA);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getfName() {
        return fName.get();
    }

    public void setfName(String fName) {
        this.fName.set(fName);
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getDOB() {
        return DOB.get();
    }

    public void setDOB(LocalDate DOB) {
        this.DOB.set(DOB);
    }

    public String getGender() {
        return gender.get();
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getClassCode() {
        return classCode.get();
    }

    public void setClassCode(String classCode) {
        this.classCode.set(classCode);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty fNameProperty() {
        return fName;
    }

    public ObjectProperty<LocalDate> DOBProperty() {
        return DOB;
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public StringProperty classCodeProperty() {
        return classCode;
    }

    public FloatProperty GPAProperty() {
        return GPA;
    }
    public String getlName() {
        return lName.get();
    }

    public StringProperty lNameProperty() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName.set(lName);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}