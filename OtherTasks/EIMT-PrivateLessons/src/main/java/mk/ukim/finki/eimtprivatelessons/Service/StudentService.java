package mk.ukim.finki.eimtprivatelessons.Service;

import mk.ukim.finki.eimtprivatelessons.Model.Student;
import mk.ukim.finki.eimtprivatelessons.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // READ
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findByID(Long ID) throws Exception {
        return studentRepository.findById(ID).orElseThrow(Exception::new);
    }

    // CREATE
    public Student create(String firstName, String lastName) {
        Student student = new Student(firstName, lastName);
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
