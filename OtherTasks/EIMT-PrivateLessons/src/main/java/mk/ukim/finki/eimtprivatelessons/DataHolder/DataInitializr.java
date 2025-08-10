package mk.ukim.finki.eimtprivatelessons.DataHolder;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.eimtprivatelessons.Model.Student;
import mk.ukim.finki.eimtprivatelessons.Repository.StudentRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializr {

    private final StudentRepository studentRepository;

    public DataInitializr(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostConstruct
    public void initializeData() {
        Student student1 = new Student("Ivan", "Pupinoski");
        Student student2 = new Student("Marija", "Dimitrieska");

        studentRepository.save(student1);
        studentRepository.save(student2);
    }
}
