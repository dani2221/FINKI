package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findAllByNameOrSurname(String name, String surname);
}
