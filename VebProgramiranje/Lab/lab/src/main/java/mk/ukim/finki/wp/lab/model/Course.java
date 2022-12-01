package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long courseId;
    private String name;
    private String description;
    @ManyToOne
    private Teacher teacher;
    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Grade> grades;
    @Enumerated(value = EnumType.STRING)
    private Type type;

    public Course(String name, String description, Teacher teacher, List<Grade> grades, Type type) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.grades = grades;
        this.type = type;
    }

    public Course() {
    }
}
