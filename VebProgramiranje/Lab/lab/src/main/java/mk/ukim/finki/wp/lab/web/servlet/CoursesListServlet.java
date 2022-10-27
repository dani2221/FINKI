package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.Course;
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

@WebServlet("/listCourses")
public class CoursesListServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final CourseService courseService;

    public CoursesListServlet(SpringTemplateEngine templateEngine, CourseService courseService) {
        this.templateEngine = templateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> courses = courseService.listAll();
        WebContext webContext = new WebContext(req, resp, this.getServletContext());
        webContext.setVariable("courses", courses);

        this.templateEngine.process("listCourses.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long courseId = Long.valueOf(req.getParameter("courseId"));
        req.getSession().setAttribute("courseId", courseId);
        resp.sendRedirect("/AddStudent");
    }
}
