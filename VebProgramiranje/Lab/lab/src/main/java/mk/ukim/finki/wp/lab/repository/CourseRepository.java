package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Comparator;
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

        Teacher tcr1 = new Teacher("tcr1", "sur1");
        Teacher tcr2 = new Teacher("tcr2", "sur2");
        Teacher tcr3 = new Teacher("tcr3", "sur3");

        courses.add(new Course("crs1", "desc1", tcr1,
                new LinkedList<>(Arrays.asList(new Student[]{students.get(0), students.get(1)}))));
        courses.add(new Course("crs2", "desc2", tcr1,
                new LinkedList<>(Arrays.asList(new Student[]{students.get(1), students.get(2)}))));
        courses.add(new Course("crs3", "desc3", tcr2,
                new LinkedList<>(Arrays.asList(new Student[]{students.get(3), students.get(4)}))));
        courses.add(new Course("crs4", "desc4", tcr3,
                new LinkedList<>(Arrays.asList(new Student[]{students.get(0), students.get(3)}))));
        courses.add(new Course("crs5", "desc5", tcr3,
                new LinkedList<>(Arrays.asList(new Student[]{students.get(4), students.get(1)}))));

    }

    public List<Course> findAllCourses(){
        return courses
                .stream()
                .sorted(Comparator.comparing(x -> x.getName()))
                .toList();
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
    public void addCourse(Course course){
        courses.add(course);
    }
    public void delete(long id) {
        courses.removeIf(x -> x.getCourseId() == id);
    }
    public boolean nameExists(String name){
        return courses.stream().filter(x -> x.getName().equals(name)).count() > 0;
    }
}
