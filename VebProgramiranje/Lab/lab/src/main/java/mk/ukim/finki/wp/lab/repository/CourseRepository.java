package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CourseRepository {
    private List<Course> courses;

    public CourseRepository() {
        this.courses = new LinkedList<>();
        List<Student> students = new LinkedList<>();
        students.add(new Student("std1","pass1", "name1", "sur1"));
        students.add(new Student("std2","pass2", "name2", "sur2"));
        students.add(new Student("std3","pass3", "name3", "sur3"));
        students.add(new Student("std4","pass4", "name4", "sur4"));
        students.add(new Student("std5","pass5", "name5", "sur5"));

        courses.add(new Course(1L, "crs1", "desc1",
                new LinkedList<>(Arrays.asList(new Student[]{students.get(0), students.get(1)}))));
        courses.add(new Course(2L, "crs2", "desc2",
                new LinkedList<>(Arrays.asList(new Student[]{students.get(1), students.get(2)}))));
        courses.add(new Course(3L, "crs3", "desc3",
                new LinkedList<>(Arrays.asList(new Student[]{students.get(3), students.get(4)}))));
        courses.add(new Course(4L, "crs4", "desc4",
                new LinkedList<>(Arrays.asList(new Student[]{students.get(0), students.get(3)}))));
        courses.add(new Course(5L, "crs5", "desc5",
                new LinkedList<>(Arrays.asList(new Student[]{students.get(4), students.get(1)}))));

    }

    public List<Course> findAllCourses(){
        return courses;
    }
    public Course findById(Long courseId){
        return courses.stream().filter(x -> x.getCourseId().equals(courseId)).findFirst().orElse(null);
    }
    public List<Student> findAllStudentsByCourse(Long courseId){
        return this.findById(courseId).getStudents();
    }
    public Course addStudentToCourse(Student student, Course course){
        List<Student> students = course.getStudents();
        students.add(student);
        course.setStudents(students);
        return course;
    }
}
