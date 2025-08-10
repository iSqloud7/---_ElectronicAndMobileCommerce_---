package mk.ukim.finki.eimtprivatelessons.Model.DTO;

import mk.ukim.finki.eimtprivatelessons.Model.Student;

public record StudentRequestDTO(String firstName, String lastName) {

    public Student fromStudentRequestToStudent() {
        return new Student(firstName, lastName);
    }
}
