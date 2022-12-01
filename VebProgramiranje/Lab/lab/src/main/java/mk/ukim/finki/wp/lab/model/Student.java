package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Student {
    @Id
    private String username;
    private String password;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "student")
    private List<Grade> grades;

    public Student(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public Student() {
    }
}
