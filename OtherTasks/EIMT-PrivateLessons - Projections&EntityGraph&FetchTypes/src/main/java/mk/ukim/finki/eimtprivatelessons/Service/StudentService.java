package mk.ukim.finki.eimtprivatelessons.Service;

import mk.ukim.finki.eimtprivatelessons.Controller.Exception.StudentNotFoundException;
import mk.ukim.finki.eimtprivatelessons.Model.DTO.StudentRequestDTO;
import mk.ukim.finki.eimtprivatelessons.Model.DTO.StudentResponseDTO;
import mk.ukim.finki.eimtprivatelessons.Model.Projections.StudentProjection;
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

    public List<StudentProjection> findAllProjections() {
        return this.studentRepository.getStudentFirstNameAndLastName();
    }

    public Student findByID(Long ID) throws StudentNotFoundException {
        return studentRepository.findById(ID).orElseThrow(StudentNotFoundException::new);
    }

    // CREATE
    public Student create(StudentRequestDTO studentRequest) {
        Student student = studentRequest.fromStudentRequestToStudent();
        this.studentRepository.save(student);

        return student;
    }

    // UPDATE
    public void update(Long ID, String firstName, String lastName) {
        Student student = this.studentRepository.findById(ID).get();

        student.setFirstName(firstName);
        student.setLastName(lastName);

        this.studentRepository.save(student);
    }

    // DELETE
    public void delete(Long ID) {
        this.studentRepository.deleteById(ID);
    }


//    EAGER -> и во двата случаи кога се земаат students, се земаат и subjects (1 query)
//    LAZY -> и во двата случаи кога се земаат students, не се земаат subjects (1 query),
//    но со експлицитно повикување на subjects за некој student, се извршува уште едно query
    //    ====================================================================================================
    public void getAllStudents() {
        // треба да се земаат subjects fetch = FetchType.EAGER
        List<Student> students = this.studentRepository.fetchAllStudents();
    }

    public void getStudent(Long ID) {
        // не треба да се земаат subjects fetch = FetchType.LAZY
        Student student = this.studentRepository.findById(ID).get();
    }
//    ====================================================================================================
}
