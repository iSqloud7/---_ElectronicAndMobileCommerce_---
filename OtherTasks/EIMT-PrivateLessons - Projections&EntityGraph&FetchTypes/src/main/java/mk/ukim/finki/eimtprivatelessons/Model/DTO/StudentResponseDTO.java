package mk.ukim.finki.eimtprivatelessons.Model.DTO;

import mk.ukim.finki.eimtprivatelessons.Model.Student;

import java.util.ArrayList;
import java.util.List;

public record StudentResponseDTO(Long ID, String firstName, String lastName) {

    public static List<StudentResponseDTO> fromStudentToStudentResponse(List<Student> students) {
        List<StudentResponseDTO> studentsResponse = new ArrayList<>();

        for (Student student : students) {
            StudentResponseDTO studentResponse = new StudentResponseDTO(student.getStudentID(), student.getFirstName(), student.getLastName());
            studentsResponse.add(studentResponse);
        }

        return studentsResponse;
    }
}
