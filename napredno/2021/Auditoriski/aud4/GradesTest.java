package mk.ukim.finki.aud4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Student implements Comparable<Student>{
    String firstName;
    String lastName;
    int exam1;
    int exam2;
    int exam3;

    public Student(String firstName, String lastName, int exam1, int exam2, int exam3) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.exam1 = exam1;
        this.exam2 = exam2;
        this.exam3 = exam3;
    }

    public double getTotalPoints() {
        return exam1 * 0.25 + exam2 * 0.3 + exam3 * 0.45;
    }

    public char getGrade() {
        double points = getTotalPoints();
        char grade = 'F';
        if (points >= 90) {
            grade = 'A';
        } else if (points >= 80) {
            grade = 'B';
        } else if (points >= 70) {
            grade = 'C';
        } else if (points >= 60) {
            grade = 'D';
        } else if (points >= 50) {
            grade = 'E';
        }

        return grade;
    }

    public static Student createStudentFrom(String line) {
        String[] parts = line.split(":");
        return new Student(
                parts[0],
                parts[1],
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[3]),
                Integer.parseInt(parts[4]));
    }

    public String studentWithGrade () {
        return String.format("%s %s %c", firstName, lastName, getGrade());
    }

    @Override
    public String toString() {
        return String.format("%s %s %d %d %d %.2f %c", firstName, lastName, exam1, exam2, exam3, getTotalPoints(), getGrade());
    }

    @Override
    public int compareTo(Student o) {
        return Character.compare(this.getGrade(), o.getGrade());
    }
}

class Course {

    List<Student> students;

    public Course() {
        students = new ArrayList<>();
    }


    public void readStudentData(InputStream inputStream) {
        //Doe:John:100:100:100
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        students = br.lines()
                .map(line -> Student.createStudentFrom(line))
                .collect(Collectors.toList());
    }

    public void printSortedStudents(OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);
        students.stream()
                .sorted()
                .forEach(student -> pw.println(student.studentWithGrade()));
        pw.flush();
    }

    public void printDetailedReport(OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);
        students.forEach(student -> pw.println(student.toString()));
        pw.flush();
    }

    public void printGradeDistribution(OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);
        int [] gradeDistribution = new int [6];
        for (Student s : students) {
            gradeDistribution[s.getGrade() - 'A']++; //A -> 0, B -> 1
        }

        for (int i=0;i<6;i++) {
            pw.printf("%c -> %d\n", i + 'A',gradeDistribution[i]);
        }
        pw.flush();
    }


}

public class GradesTest {

    public static void main(String[] args) throws FileNotFoundException {

        Course course = new Course();
        File inputFile = new File("/Users/stefanandonov/Documents/GitWorkspace/NP2020/src/mk/ukim/finki/aud4/students");
        File outputFile = new File("/Users/stefanandonov/Documents/GitWorkspace/NP2020/src/mk/ukim/finki/aud4/results");
        course.readStudentData(new FileInputStream(inputFile));

        System.out.println("===Printing sorted students to screen===");
        course.printSortedStudents(System.out);

        System.out.println("===Printing detailed report to file===");
        course.printDetailedReport(new FileOutputStream(outputFile));

        System.out.println("===Printing grade distribution to screen===");
        course.printGradeDistribution(System.out);

    }
}
