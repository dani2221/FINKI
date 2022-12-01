package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.exceptions.CourseNameExistsException;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Type;

import java.time.LocalDateTime;
import java.util.List;

public interface CourseService {
    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentInCourse(String username, Long courseId, LocalDateTime dateTime, Character grade);
    List<Course> listAll();
    Course addCourse(String name, String surname, long teacherId) throws CourseNameExistsException;
    void delete(long id);
    Course editCourse(String name, String description, long teacherId, long courseId);
    Course getById(long id);
    List<Course> filterByType(Type type);
    List<Grade> getGradesById(long id);
}
