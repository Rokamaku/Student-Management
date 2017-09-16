package StudentManagement.DataAccess.FileAccess;


import StudentManagement.Model.Student;
import StudentManagement.Model.StudentListWrapper;
import javafx.collections.ObservableList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class StudentDAFile {

    public StudentListWrapper loadStudentDataFromFile(File file) throws JAXBException {

        JAXBContext context = JAXBContext
                .newInstance(StudentListWrapper.class);
        Unmarshaller um = context.createUnmarshaller();

        // Reading XML from the file and unmarshalling.
        StudentListWrapper wrapper = (StudentListWrapper) um.unmarshal(file);
        return wrapper;

    }

    public void saveStudentDataToFile(File file, ObservableList<Student> studentsData) throws JAXBException {
        JAXBContext context = JAXBContext
                .newInstance(StudentListWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Wrapping our person data.
        StudentListWrapper wrapper = new StudentListWrapper();
        wrapper.setStudents(studentsData);

        // Marshalling and saving XML to the file.
        m.marshal(wrapper, file);

    }


}