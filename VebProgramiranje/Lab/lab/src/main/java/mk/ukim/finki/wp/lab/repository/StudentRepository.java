package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class StudentRepository {
    private List<Student> students;

    public StudentRepository() {
        this.students = new LinkedList<>();
        students.add(new Student("std1","pass1", "name1", "sur1"));
        students.add(new Student("std2","pass2", "name2", "sur2"));
        students.add(new Student("std3","pass3", "name3", "sur3"));
        students.add(new Student("std4","pass4", "name4", "sur4"));
        students.add(new Student("std5","pass5", "name5", "sur5"));
    }

    public List<Student> findAllStudents(){
        return students;
    }

    public List<Student> findAllByNameOrSurname(String text){
        return students.stream()
                .filter(x -> x.getName().contains(text) || x.getSurname().contains(text))
                .toList();
    }

    public void addStudent(Student student){
        this.students.add(student);
    }
}
