package mk.ukim.finki.eimtprivatelessons.Repository;

import mk.ukim.finki.eimtprivatelessons.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
