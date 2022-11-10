package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.List;
import java.util.Random;

@Data
public class Course {
    private Long courseId;
    private String name;
    private String description;
    private Teacher teacher;
    private List<Student> students;

    public Course(String name, String description, Teacher teacher, List<Student> students) {
        Random random = new Random();
        this.courseId = random.nextLong(1000000000);
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.students = students;
    }
}
