package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
