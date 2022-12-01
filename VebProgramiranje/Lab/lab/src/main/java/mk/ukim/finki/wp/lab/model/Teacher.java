package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import mk.ukim.finki.wp.lab.converters.TeacherFullNameConverter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Random;

@Data
@Entity
public class Teacher {
    @Id
    @GeneratedValue
    private Long id;
    @Convert(converter = TeacherFullNameConverter.class)
    private TeacherFullName fullName;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfEmployment;

    public Teacher(String name, String surname) {
        Random random = new Random();
        this.id = random.nextLong(1000000000);
        this.fullName = new TeacherFullName(name, surname);
        this.dateOfEmployment = LocalDate.now();
    }

    public Teacher() {
    }
}
