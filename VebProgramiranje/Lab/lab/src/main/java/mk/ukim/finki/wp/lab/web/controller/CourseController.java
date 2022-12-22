package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.exceptions.CourseNameExistsException;
import mk.ukim.finki.wp.lab.exceptions.CourseNotFoundException;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.Type;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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
    public String getCoursesPage(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String filterType,
            Model model) {
        List<Course> courses = null;
        if(filterType == null){
            courses = courseService.listAll();
        }else{
            courses = courseService.filterByType(Type.valueOf(filterType));
            model.addAttribute("typeSelect",filterType);
        }

        model.addAttribute("courses",courses);
        model.addAttribute("error", error);
        model.addAttribute("types", Stream.of(Type.values()).map(Type::name).toArray(String[]::new));

        return "listCourses";
    }

    @PostMapping
    public String choseCourse(@RequestParam long courseId, HttpSession session) {
        session.setAttribute("courseId", courseId);
        return "redirect:/students";
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

        return "redirect:/courses";
    }

    @PostMapping("/delete/{id}")
    public String deleteCourse(@PathVariable long id){
        this.courseService.delete(id);
        return "redirect:/courses";
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
    @PostMapping("/filter")
    public String filterCourses(@RequestParam String type){
        return "redirect:/courses?filterType="+type;
    }
}
