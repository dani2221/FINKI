package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;

    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping
    public String getStudents(@SessionAttribute Long courseId, Model model){
        model.addAttribute("courseId", courseId);
        List<Student> students = studentService.listAll();
        model.addAttribute("students", students);

        return "listStudents";
    }

    @PostMapping String searchStudent(@RequestParam(required = false) String student,
                                      @RequestParam(required = false) String search,
                                      @SessionAttribute(required = false) Long courseId,
                                      @RequestParam(required = false) LocalDateTime dateTime,
                                      @RequestParam(required = false) String grade,
                                      Model model){
        if(student == null){
            List<Student> students = studentService.searchByNameOrSurname(search);
            model.addAttribute("courseId", courseId);
            model.addAttribute("students", students);

            return "listStudents";
        } else {
            Character gradeC = grade.charAt(0);
            courseService.addStudentInCourse(student, courseId, dateTime, gradeC);
            return "redirect:/students/summary";
        }
    }

    @GetMapping("/summary")
    public String getSummary(@SessionAttribute Long courseId, Model model){
        List<Grade> grades = courseService.getGradesById(courseId);
        Course course = courseService.listAll().stream().filter(x -> x.getCourseId().equals(courseId)).findFirst().orElse(null);
        model.addAttribute("grades",grades);
        model.addAttribute("course",course.getName());

        return "studentsInCourse";
    }

    @GetMapping("/create")
    public String createStudentGet(Model model){
        return "createStudent";
    }

    @PostMapping("/create")
    public String createStudent(Model model,
                                @RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String name,
                                @RequestParam String surname){
        studentService.save(username,password,name,surname);
        return"redirect:/students";
    }
}
