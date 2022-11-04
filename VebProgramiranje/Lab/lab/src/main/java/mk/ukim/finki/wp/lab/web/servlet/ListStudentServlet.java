package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AddStudent")
public class ListStudentServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final StudentService studentService;
    private final CourseService courseService;

    public ListStudentServlet(SpringTemplateEngine templateEngine, StudentService studentService, CourseService courseService) {
        this.templateEngine = templateEngine;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, this.getServletContext());
        long courseId = (long) req.getSession().getAttribute("courseId");
        webContext.setVariable("courseId", courseId);
        List<Student> students = studentService.listAll();
        webContext.setVariable("students", students);

        this.templateEngine.process("listStudents.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String student = (String) req.getParameter("student");
        if(student == null){
            String search = (String) req.getParameter("search");
            WebContext webContext = new WebContext(req, resp, this.getServletContext());
            long courseId = (long) req.getSession().getAttribute("courseId");
            List<Student> students = studentService.searchByNameOrSurname(search);
            webContext.setVariable("courseId", courseId);
            webContext.setVariable("students", students);

            this.templateEngine.process("listStudents.html", webContext, resp.getWriter());
        } else {
            long courseId = (long) req.getSession().getAttribute("courseId");
            courseService.addStudentInCourse(student, courseId);
            resp.sendRedirect("/StudentEnrollmentSummary");
        }
    }
}
