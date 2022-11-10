package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRepository {
    private List<Teacher> teachers;

    public TeacherRepository() {
        this.teachers = new LinkedList<>();
        teachers.add(new Teacher("tcr1", "sur1"));
        teachers.add(new Teacher("tcr2", "sur2"));
        teachers.add(new Teacher("tcr3", "sur3"));
        teachers.add(new Teacher("tcr4", "sur4"));
        teachers.add(new Teacher("tcr5", "sur5"));
    }

    public List<Teacher> findAll() {
        return teachers;
    }
    public Optional<Teacher> getById(long id) {
        return teachers.stream().filter(x -> x.getId() == id).findFirst();
    }
}
