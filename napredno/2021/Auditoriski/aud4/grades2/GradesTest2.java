package mk.ukim.finki.aud4.grades2;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
        Scanner sc = new Scanner(inputStream);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            students.add(Student.createStudentFrom(line));
        }
    }

    public void printSortedStudents(OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);
        Collections.sort(students);
        for (Student s : students) {
            pw.println(s.studentWithGrade());
        }
        pw.flush();
    }

    public void printDetailedReport(OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);
        for (Student s : students) {
            pw.println(s.toString());
        }
        pw.flush();
    }

    public void printGradeDistribution(OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);
        int [] gradeDistribution = new int [6];
        for (Student s : students) {
            gradeDistribution[s.getGrade() - 'A']++; //A -> 0 B -> 1
        }

        for (int i=0;i<6;i++) {
            pw.printf("%c -> %d\n", i + 'A',gradeDistribution[i]);
        }
        pw.flush();
    }


}

public class GradesTest2 {
}
