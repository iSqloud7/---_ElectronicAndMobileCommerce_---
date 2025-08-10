package mk.ukim.finki.eimtprivatelessons.Service;

import mk.ukim.finki.eimtprivatelessons.Controller.Exception.StudentNotFoundException;
import mk.ukim.finki.eimtprivatelessons.Model.DTO.StudentRequestDTO;
import mk.ukim.finki.eimtprivatelessons.Model.DTO.StudentResponseDTO;
import mk.ukim.finki.eimtprivatelessons.Model.Student;
import mk.ukim.finki.eimtprivatelessons.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // READ
    public List<StudentResponseDTO> findAll() {
        return StudentResponseDTO.fromStudentToStudentResponse(this.studentRepository.findAll());
    }

    public Student findByID(Long ID) throws StudentNotFoundException {
        return studentRepository.findById(ID).orElseThrow(StudentNotFoundException::new);
    }

    // CREATE
    public Student create(StudentRequestDTO studentRequest) {
        Student student = studentRequest.fromStudentRequestToStudent();
        studentRepository.save(student);

        return student;
    }

    // UPDATE
    public void update(Long ID, String firstName, String lastName) {
        Student student = studentRepository.findById(ID).get();

        student.setFirstName(firstName);
        student.setLastName(lastName);

        studentRepository.save(student);
    }

    // DELETE
    public void delete(Long ID) {
        studentRepository.deleteById(ID);
    }

}
