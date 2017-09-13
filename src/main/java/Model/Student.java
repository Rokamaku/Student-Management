package Model;

import javafx.beans.property.*;

import java.io.Serializable;
import java.time.LocalDate;

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

    public Student(StringProperty id, StringProperty fName,
                   StringProperty lName, ObjectProperty<LocalDate> DOB,
                   StringProperty gender, StringProperty phone,
                   StringProperty email, StringProperty address,
                   StringProperty classCode, FloatProperty GPA) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.DOB = DOB;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.classCode = classCode;
        this.GPA = GPA;
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