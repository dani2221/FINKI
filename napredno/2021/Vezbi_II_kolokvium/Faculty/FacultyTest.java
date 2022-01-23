package Faculty;//package mk.ukim.finki.vtor_kolokvium;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class Faculty {
    private HashMap<String,Student> students;
    private HashMap<String,Student> graduatedStudents;
    private List<String> logs;

    public Faculty() {
        students = new HashMap<String, Student>();
        graduatedStudents = new HashMap<String, Student>();
        logs = new LinkedList<String >();
    }

    void addStudent(String id, int yearsOfStudies) {
        students.put(id,new Student(id,yearsOfStudies));
    }

    void addGradeToStudent(String studentId, int term, String courseName, int grade) throws OperationNotAllowedException {
        Student st = students.get(studentId);
        st.addCompletedCourse(term,courseName,grade);
        if(st.completedCoursesCount()==st.numAvailableCourses()){
            students.remove(studentId);
            graduatedStudents.put(studentId,st);
            logs.add(String.format("Student with ID %s graduated with average grade %.2f in %d years.",studentId,st.averageGrade(),st.completedCoursesCount()/6));
        }
    }

    String getFacultyLogs() {
        return logs.stream().collect(Collectors.joining("\n"));
    }

    String getDetailedReportForStudent(String id) {
        return students.get(id).toString();
    }

    void printFirstNStudents(int n) {
        TreeSet<Student> nst = new TreeSet<Student>(Comparator.comparing(Student::completedCoursesCount).thenComparing(Student::averageGrade).thenComparing(Student::getId).reversed());
        students.values().forEach(x -> nst.add(x));
        if(nst.size()==0) return;
        System.out.println(nst.stream().limit(n)
                .map(x -> String.format("Student: %s Courses passed: %d Average grade: %.2f",x.getId(),x.completedCoursesCount(),x.averageGrade()))
                .collect(Collectors.joining("\n")));
    }

    void printCourses() {
        TreeSet<FacultyCourse> courses = new TreeSet<FacultyCourse>(Comparator.comparing(FacultyCourse::numEnrolledStudents).thenComparing(FacultyCourse::getAverageGrade).thenComparing(FacultyCourse::getName));
        Set<Student> union = new HashSet<Student>();
        union.addAll(students.values());
        union.addAll(graduatedStudents.values());
        union.forEach(st -> st.allCourses().forEach(crs ->{
            FacultyCourse fcrs = null;
            for (FacultyCourse cs : courses) {
                if(cs.getName().equals(crs.getName())){
                    fcrs = cs;
                    break;
                }
            }
            if(fcrs==null){
                fcrs = new FacultyCourse(crs.getName());

            }
            courses.remove(fcrs);
            fcrs.addStudent(st,crs.getGrade());
            courses.add(fcrs);
        }));
        courses.forEach(x -> System.out.printf("%s %d %.2f\n",x.getName(),x.numEnrolledStudents(),x.getAverageGrade()));
    }
}
class OperationNotAllowedException extends Exception{
    public OperationNotAllowedException(String message) {
        super(message);
    }
}
class Student{
    private String id;
    private TreeMap<Integer, TreeSet<Course>> completedCourses;

    public Student(String id, int yearOfStudies) {
        this.id = id;
        completedCourses = new TreeMap<Integer, TreeSet<Course>>();
        IntStream.range(1,yearOfStudies*2+1).forEach(x -> completedCourses.put(x,new TreeSet<Course>()));
    }

    public String getId() {
        return id;
    }

    public void addCompletedCourse(int term, String courseName, int grade) throws OperationNotAllowedException{
        TreeSet<Course> termCourses = completedCourses.get(term);
        if(termCourses==null) throw new OperationNotAllowedException("Term "+term+" is not possible for student with ID "+id);
        if(termCourses.size()>=3) throw new OperationNotAllowedException("Student "+id+" already has 3 grades in term "+term);
        termCourses.add(new Course(term,courseName,grade));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int completedCoursesCount(){
        return completedCourses.values().stream().mapToInt(x -> x.size()).sum();
    }
    public int numAvailableCourses(){
        return completedCourses.keySet().size()*3;
    }

    public double averageGrade() {
        if(completedCoursesCount()==0) return 5.0;
        return completedCourses.values().stream().mapToInt(x -> x.stream().mapToInt(t -> t.getGrade()).sum()).sum()/(double) completedCoursesCount();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student: "+id+"\n");
        completedCourses.forEach((k,v)->{
            sb.append("Term "+k+"\n");
            sb.append("Courses: "+v.size()+"\n");
            sb.append(String.format("Average grade for term: %.2f\n",v.stream().mapToDouble(Course::getGrade).average().orElse(5.00)));
        });
        sb.append(String.format("Average grade: %.2f\n",averageGrade()));
        sb.append("Courses attended: "+allCourses().stream().map(Course::getName).collect(Collectors.joining(",")));
        return sb.toString();
    }

    public TreeSet<Course> allCourses(){
        TreeSet<Course> allAttendedCourses = new TreeSet<Course>();
        completedCourses.forEach((k,v)->v.forEach(crs->allAttendedCourses.add(crs)));
        return allAttendedCourses;
    }
}
class Course implements Comparable<Course>{
    private int term;
    private String name;
    private int grade;

    @Override
    public int compareTo(Course o) {
        return this.name.compareTo(o.name);
    }

    public int getTerm() {
        return term;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public Course(int term, String name, int grade) {
        this.term = term;
        this.name = name;
        this.grade = grade;
    }
}

class FacultyCourse{
    private HashSet<Student> enrolledStudents;
    private int sumGrade;
    private String name;

    public FacultyCourse(String name) {
        this.name = name;
        enrolledStudents = new HashSet<Student>();
    }

    public double getAverageGrade() {
        return sumGrade/(double)enrolledStudents.size();
    }
    public int numEnrolledStudents() {
        return enrolledStudents.size();
    }

    public String getName() {
        return name;
    }

    public void addStudent(Student s, int grade) {
        enrolledStudents.add(s);
        sumGrade+=grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultyCourse that = (FacultyCourse) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
public class FacultyTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();

        if (testCase == 1) {
            System.out.println("TESTING addStudent AND printFirstNStudents");
            Faculty faculty = new Faculty();
            for (int i = 0; i < 10; i++) {
                faculty.addStudent("student" + i, (i % 2 == 0) ? 3 : 4);
            }
            faculty.printFirstNStudents(10);

        } else if (testCase == 2) {
            System.out.println("TESTING addGrade and exception");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            try {
                faculty.addGradeToStudent("123", 7, "NP", 10);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
            try {
                faculty.addGradeToStudent("1234", 9, "NP", 8);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
        } else if (testCase == 3) {
            System.out.println("TESTING addGrade and exception");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            for (int i = 0; i < 4; i++) {
                try {
                    faculty.addGradeToStudent("123", 1, "course" + i, 10);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }
            for (int i = 0; i < 4; i++) {
                try {
                    faculty.addGradeToStudent("1234", 1, "course" + i, 10);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else if (testCase == 4) {
            System.out.println("Testing addGrade for graduation");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            int counter = 1;
            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 3; j++) {
                    try {
                        faculty.addGradeToStudent("123", i, "course" + counter, (i % 2 == 0) ? 7 : 8);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                    ++counter;
                }
            }
            counter = 1;
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 3; j++) {
                    try {
                        faculty.addGradeToStudent("1234", i, "course" + counter, (j % 2 == 0) ? 7 : 10);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                    ++counter;
                }
            }
            System.out.println("LOGS");
            System.out.println(faculty.getFacultyLogs());
            System.out.println("PRINT STUDENTS (there shouldn't be anything after this line!");
            faculty.printFirstNStudents(2);
        } else if (testCase == 5 || testCase == 6 || testCase == 7) {
            System.out.println("Testing addGrade and printFirstNStudents (not graduated student)");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), i % 5 + 6);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            if (testCase == 5)
                faculty.printFirstNStudents(10);
            else if (testCase == 6)
                faculty.printFirstNStudents(3);
            else
                faculty.printFirstNStudents(20);
        } else if (testCase == 8 || testCase == 9) {
            System.out.println("TESTING DETAILED REPORT");
            Faculty faculty = new Faculty();
            faculty.addStudent("student1", ((testCase == 8) ? 3 : 4));
            int grade = 6;
            int counterCounter = 1;
            for (int i = 1; i < ((testCase == 8) ? 6 : 8); i++) {
                for (int j = 1; j < 3; j++) {
                    try {
                        faculty.addGradeToStudent("student1", i, "course" + counterCounter, grade);
                    } catch (OperationNotAllowedException e) {
                        e.printStackTrace();
                    }
                    grade++;
                    if (grade == 10)
                        grade = 5;
                    ++counterCounter;
                }
            }
            System.out.println(faculty.getDetailedReportForStudent("student1"));
        } else if (testCase==10) {
            System.out.println("TESTING PRINT COURSES");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            faculty.printCourses();
        } else if (testCase==11) {
            System.out.println("INTEGRATION TEST");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 2 : 3); k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }

            }

            for (int i=11;i<15;i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= 3; k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            System.out.println("LOGS");
            System.out.println(faculty.getFacultyLogs());
            System.out.println("DETAILED REPORT FOR STUDENT");
            System.out.println(faculty.getDetailedReportForStudent("student2"));
            try {
                System.out.println(faculty.getDetailedReportForStudent("student11"));
                System.out.println("The graduated students should be deleted!!!");
            } catch (NullPointerException e) {
                System.out.println("The graduated students are really deleted");
            }
            System.out.println("FIRST N STUDENTS");
            faculty.printFirstNStudents(10);
            System.out.println("COURSES");
            faculty.printCourses();
        }
    }
}
