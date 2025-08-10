package mk.ukim.finki.eimtprivatelessons.Controller;

import mk.ukim.finki.eimtprivatelessons.Service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String getIndexPage(Model model){
        model.addAttribute("students", studentService.findAll());

        return "index";
    }
}
