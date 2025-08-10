package mk.ukim.finki.eimtprivatelessons.Controller;

import mk.ukim.finki.eimtprivatelessons.Model.DTO.StudentDTO;
import mk.ukim.finki.eimtprivatelessons.Model.Student;
import mk.ukim.finki.eimtprivatelessons.Service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    /*
    @GetMapping("/")
    public String getData() {
// I-начин: се враќа објект од тип ResponseEntity<> и се наведува од каков тип ќе биде истиот (String, List, Student etc.)
// II-начин: се враќа String, но не може да се специфицира (StatusCode, Headers etc.)

        return "ivKeeX"; // враќа String во json формат на /api/
    }
    */

    @GetMapping("/")
    public ResponseEntity<List<Student>> getStudents() { // READ

        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{ID}")
    public ResponseEntity<Student> getStudent(@PathVariable Long ID) throws Exception {
        return ResponseEntity.ok(studentService.findByID(ID));
    }

    // GET: @RequestParam
    // POST: @RequestBody

    @PostMapping("/")
    public ResponseEntity<Student> createStudent(@RequestBody StudentDTO studentDTO) { // CREATE
        Student student = studentService.create(studentDTO.getFirstName(), studentDTO.getLastName());

        return ResponseEntity.ok(student);
    }

    @PostMapping("/{ID}")
    public ResponseEntity<Void> updateStudent(@PathVariable Long ID,
                                              @RequestBody StudentDTO studentDTO) { // UPDATE

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{ID}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long ID) throws Exception { // DELETE
        studentService.findByID(ID);

        return ResponseEntity.noContent().build();
    }
}
