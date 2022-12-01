package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
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

@WebServlet("/StudentEnrollmentSummary")
public class StudentEnrollmentSummaryServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final CourseService courseService;

    public StudentEnrollmentSummaryServlet(SpringTemplateEngine templateEngine, CourseService courseService) {
        this.templateEngine = templateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, this.getServletContext());
        long courseId = (long) req.getSession().getAttribute("courseId");
        List<Grade> grades = courseService.getGradesById(courseId);
        Course course = courseService.listAll().stream().filter(x -> x.getCourseId().equals(courseId)).findFirst().orElse(null);
        webContext.setVariable("grades",grades);
        webContext.setVariable("course",course.getName());

        this.templateEngine.process("studentsInCourse.html", webContext, resp.getWriter());
    }

}
