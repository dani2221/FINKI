package mk.ukim.finki.wp.lab.service.Impl;

import mk.ukim.finki.wp.lab.exceptions.CourseNameExistsException;
import mk.ukim.finki.wp.lab.exceptions.CourseNotFoundException;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.Type;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.repository.TeacherRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        Student std = studentRepository.findAllStudents().stream()
                .filter(x -> x.getUsername().equals(username))
                .findFirst().orElse(null);
        Course crs = courseRepository.findById(courseId);

        return courseRepository.addStudentToCourse(std, crs);
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAllCourses();
    }

    @Override
    public Course addCourse(String name, String description, long teacherId) throws CourseNameExistsException {
        if(this.courseRepository.nameExists(name)){
            throw new CourseNameExistsException(name);
        }

        Teacher teacher = teacherRepository.getById(teacherId).get();
        Course course = new Course(name, description, teacher, new LinkedList<>(), Type.Mandatory);
        courseRepository.addCourse(course);
        return course;
    }

    @Override
    public void delete(long id) {
        this.courseRepository.delete(id);
    }

    @Override
    public Course editCourse(String name, String description, long teacherId, long courseId) throws CourseNotFoundException {
        if(courseRepository.findById(courseId) == null){
            throw new CourseNotFoundException();
        }

        Teacher teacher = teacherRepository.getById(teacherId).get();
        Course course = new Course(name, description, teacher, new LinkedList<>(), Type.Mandatory);
        courseRepository.delete(courseId);
        courseRepository.addCourse(course);
        return course;
    }

    @Override
    public Course getById(long id) {
        return this.courseRepository.findById(id);
    }

    @Override
    public List<Course> filterByType(Type type) {
        return this.courseRepository.filterByType(type);
    }
}
