package mk.ukim.finki.eimtprivatelessons.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "STUDENTS")
public class Student {

    @Id
    @GeneratedValue
    private Long studentID;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    // FetchType.LAZY -> кога се повлекуваат students од база, да не се повлекуваат subjects од база
    // - само експлицитно кога ќе се повикаат subjects, само тогаш се повлекуваат од база student.getSubjects()
    // FetchType.EAGER -> кога се повлекуваат students од база, се повлекуваат и subjects од база
    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    private List<Subject> subjects;

    public Student() {
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
