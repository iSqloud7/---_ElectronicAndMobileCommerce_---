package mk.ukim.finki.eimtprivatelessons.Repository;

import mk.ukim.finki.eimtprivatelessons.Model.Projection.StudentProjection;
import mk.ukim.finki.eimtprivatelessons.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
