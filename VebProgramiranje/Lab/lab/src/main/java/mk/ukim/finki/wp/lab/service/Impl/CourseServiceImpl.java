package mk.ukim.finki.wp.lab.service.Impl;

import mk.ukim.finki.wp.lab.exceptions.CourseNameExistsException;
import mk.ukim.finki.wp.lab.exceptions.CourseNotFoundException;
import mk.ukim.finki.wp.lab.model.*;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.repository.TeacherRepository;
import mk.ukim.finki.wp.lab.repository.GradeRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final GradeRepository gradeRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, GradeRepository gradeRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findById(courseId).get().getGrades().stream().map(el -> el.getStudent()).toList();
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId, LocalDateTime dateTime, Character grade) {
        Student std = this.studentRepository.findById(username).orElseThrow();
        Course crs = courseRepository.findById(courseId).orElseThrow();

        Grade gr = new Grade(grade, std, crs, dateTime);

        gradeRepository.save(gr);

        return crs;
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course addCourse(String name, String description, long teacherId) throws CourseNameExistsException {
        if(this.courseRepository.existsByName(name)){
            throw new CourseNameExistsException(name);
        }
        Teacher teacher = null;
        if(teacherId != 0){
            teacher = teacherRepository.findById(teacherId).orElseThrow();
        }
        Course course = new Course(name, description, teacher, new LinkedList<>(), Type.Mandatory);
        courseRepository.save(course);
        return course;
    }

    @Override
    public void delete(long id) {
        this.courseRepository.deleteById(id);
    }

    @Override
    public Course editCourse(String name, String description, long teacherId, long courseId) throws CourseNotFoundException {

        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();
        course.setName(name);
        course.setDescription(description);
        course.setTeacher(teacher);
        courseRepository.save(course);
        return course;
    }

    @Override
    public Course getById(long id) {
        return this.courseRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Course> filterByType(Type type) {
        return this.courseRepository.findByType(type);
    }

    @Override
    public List<Grade> getGradesById(long id) {
        return this.courseRepository.findById(id).get().getGrades();
    }
}
