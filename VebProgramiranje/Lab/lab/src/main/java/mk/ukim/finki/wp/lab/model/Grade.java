package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Grade {
    @Id
    @GeneratedValue
    private Long id;
    private Character grade;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    public Grade() {
    }

    public Grade(Character grade, Student student, Course course, LocalDateTime dateTime) {
        this.grade = grade;
        this.student = student;
        this.course = course;
        this.timestamp = dateTime;
    }
}
