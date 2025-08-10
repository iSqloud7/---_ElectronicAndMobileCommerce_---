package mk.ukim.finki.eimtprivatelessons.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "SUBJECTS")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String name;

    // FetchType.LAZY -> кога се повлекуваат subjects од база, да не се повлекуваат students од база
    // - само експлицитно кога ќе се повикаат students, само тогаш се повлекуваат од база subject.getStudents()
    // FetchType.EAGER -> кога се повлекуваат subjects од база, се повлекуваат и students од база
    @ManyToMany(mappedBy = "subjects")
    private List<Student> students;
}
