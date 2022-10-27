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

@WebServlet("/CreateStudent")
public class CreateStudentServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final StudentService studentService;

    public CreateStudentServlet(SpringTemplateEngine templateEngine, StudentService studentService) {
        this.templateEngine = templateEngine;
        this.studentService = studentService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, this.getServletContext());
        this.templateEngine.process("createStudent.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getParameter("username");
        String password = (String) req.getParameter("password");
        String name = (String) req.getParameter("name");
        String surname = (String) req.getParameter("surname");
        studentService.save(username,password,name,surname);
        resp.sendRedirect("/AddStudent");
    }
}
