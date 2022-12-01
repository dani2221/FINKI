package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

public interface GradeService {
    Character getGradeForStudentCourse(String username, Long course_id);
}
