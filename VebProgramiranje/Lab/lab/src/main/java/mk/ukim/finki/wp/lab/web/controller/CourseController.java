package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.exceptions.CourseNameExistsException;
import mk.ukim.finki.wp.lab.exceptions.CourseNotFoundException;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model){
        List<Course> courses = courseService.listAll();
        model.addAttribute("courses",courses);
        model.addAttribute("error", error);

        return "listCourses";
    }

    @PostMapping
    public String choseCourse(@RequestParam long courseId, HttpSession session) {
        session.setAttribute("courseId", courseId);
        return "redirect:/AddStudent";
    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam long teacherId,
                             @RequestParam(required = false) Long courseEditId) {
        if(courseEditId == null){
            try {
                Course crs = this.courseService.addCourse(name, description, teacherId);
            } catch (CourseNameExistsException e) {
                return "redirect:/courses/add-form?error="+e.getMessage();
            }
        }
        else{
            Course crs = this.courseService.editCourse(name, description, teacherId, courseEditId);
        }

        return "redirect:/listCourses";
    }

    @PostMapping("/delete/{id}")
    public String deleteCourse(@PathVariable long id){
        this.courseService.delete(id);
        return "redirect:/listCourses";
    }

    @GetMapping("/add-form")
    public String addNewCoursePage(Model model, @RequestParam(required = false) String error){
        List<Teacher> teachers = this.teacherService.findAll();
        model.addAttribute("teachers", teachers);
        model.addAttribute("error", error);

        return "add-course";
    }
    @GetMapping("/edit-form/{id}")
    public String editCourse(Model model, @PathVariable(required = false) Long id){
        List<Teacher> teachers = this.teacherService.findAll();
        Course crs = this.courseService.getById(id);
        if(crs == null){
            return "redirect:/courses?error=Course not found";
        }
        model.addAttribute("course", crs);
        model.addAttribute("teachers", teachers);

        return "add-course";
    }
}
