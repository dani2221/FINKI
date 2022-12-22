package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.exceptions.CourseNameExistsException;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.Type;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.TeacherRepository;
import mk.ukim.finki.wp.lab.service.Impl.CourseServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AddCourseTest {
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private TeacherRepository teacherRepository;

    private CourseServiceImpl courseService;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(courseRepository.existsByName("example")).thenReturn(true);
        Mockito.when(teacherRepository.findById(Mockito.any())).thenReturn(Optional.of(new Teacher("dsa", "sd")));
        this.courseService = Mockito.spy(new CourseServiceImpl(courseRepository,null, teacherRepository, null));
    }

    @Test
    public void testAddCourse_courseWithNameExists(){
        Assert.assertThrows(CourseNameExistsException.class,()->this.courseService.addCourse("example","asd",1));
    }
    @Test
    public void testAddCourse_successfully() throws CourseNameExistsException {
        Course crs = this.courseService.addCourse("example1","asd",1);
        Assert.assertNotNull(crs);
    }
}
