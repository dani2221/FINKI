package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByName(String name);
    List<Course> findByType(Type type);
}
