package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.exceptions.CourseNameExistsException;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.repository.TeacherRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class LabApplicationTests {

    MockMvc mockMvc;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;
    private static boolean dataInitialized = false;

    @BeforeEach
    public void setup(WebApplicationContext wac) throws CourseNameExistsException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        initData();
    }

    private void initData() throws CourseNameExistsException {
        if (!dataInitialized) {
            Teacher t1 = teacherRepository.save(new Teacher("test", "test"));
            Course c1 = courseService.addCourse("t0", "t0", 1L);
            dataInitialized = true;
        }
    }


    @Test
    public void testGetCourses() throws Exception {
        MockHttpServletRequestBuilder productRequest = MockMvcRequestBuilders.get("/courses");
        this.mockMvc.perform(productRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("courses"))
                .andExpect(MockMvcResultMatchers.view().name("listCourses"));
    }

    @Test
    public void testGetStudentsWithoutSessionAttributeCourseId() throws Exception {
        MockHttpServletRequestBuilder productRequest = MockMvcRequestBuilders.get("/students");
        this.mockMvc.perform(productRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testGetStudentsWithSessionAttributeCourseId() throws Exception {
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/students")
                        .sessionAttr("courseId", 1);
        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("listStudents"));
    }

    @Test
    public void testAddCourse() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/courses/add")
                        .param("name", "test name")
                        .param("description", "test desc")
                        .param("teacherId", "1");
        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/courses"));
    }
}
