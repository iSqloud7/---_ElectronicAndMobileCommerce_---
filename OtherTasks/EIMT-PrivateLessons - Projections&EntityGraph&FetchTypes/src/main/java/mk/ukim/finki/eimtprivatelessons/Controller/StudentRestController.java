package mk.ukim.finki.eimtprivatelessons.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.eimtprivatelessons.Controller.Exception.StudentNotFoundException;
import mk.ukim.finki.eimtprivatelessons.Model.DTO.StudentRequestDTO;
import mk.ukim.finki.eimtprivatelessons.Model.DTO.StudentResponseDTO;
import mk.ukim.finki.eimtprivatelessons.Model.Projections.StudentProjection;
import mk.ukim.finki.eimtprivatelessons.Model.Student;
import mk.ukim.finki.eimtprivatelessons.Service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Student API", description = "Endpoints for managing students!")
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
    @Operation(summary = "Retrieve all students", description = "Returns a list of all registered students.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the list of students."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No students found."
                    )
            }
    )
    public ResponseEntity<List<StudentResponseDTO>> getStudents() { // READ

        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{ID}")
    @Operation(summary = "Retrieve student by ID", description = "Fetches a student's details using their unique ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Student found successfully."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Student with the given ID was not found."
                    )
            }
    )
    public ResponseEntity<Student> getStudent(@PathVariable Long ID) {

        try {
            Student student = this.studentService.findByID(ID);
            return ResponseEntity.ok(student);
        } catch (StudentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET: @RequestParam
    // POST: @RequestBody

    @PostMapping("/")
    public ResponseEntity<Student> createStudent(@RequestBody StudentRequestDTO studentRequest) { // CREATE
        Student student = this.studentService.create(studentRequest);

        return ResponseEntity.ok(student);
    }

    @PutMapping("/{ID}")
    public ResponseEntity<Void> updateStudent(@PathVariable Long ID,
                                              @RequestBody StudentRequestDTO studentRequest) { // UPDATE

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{ID}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long ID) throws Exception { // DELETE
        studentService.findByID(ID);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/projections")
    public ResponseEntity<List<StudentProjection>> getStudentsProjection() {
        return ResponseEntity.ok(this.studentService.findAllProjections());
    }
}
