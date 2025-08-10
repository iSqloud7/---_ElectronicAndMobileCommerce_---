package mk.ukim.finki.eimtprivatelessons.Repository;

import mk.ukim.finki.eimtprivatelessons.Model.Projections.StudentProjection;
import mk.ukim.finki.eimtprivatelessons.Model.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s.firstName as firstName, s.lastName as lastName from Student as s")
    List<StudentProjection> getStudentFirstNameAndLastName();

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH, // FETCH -> LAZY-EAGER, LOAD -> DEFAULT како што е на ниво на ентитетот
            attributePaths = {"subjects"}
    )
    @Query("select s from Student as s")
    List<Student> fetchAllStudents(); // менување на однесувањето во поглед на fetch type-от
}
