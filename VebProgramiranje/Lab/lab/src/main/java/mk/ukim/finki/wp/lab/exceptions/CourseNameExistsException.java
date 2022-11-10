package mk.ukim.finki.wp.lab.exceptions;

public class CourseNameExistsException extends Exception{
    public CourseNameExistsException(String name) {
        super("Course with name " + name + " already exists");
    }
}
