package LabExercises;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LabExercisesTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LabExercises labExercises = new LabExercises();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] parts = input.split("\\s+");
            String index = parts[0];
            List<Integer> points = Arrays.stream(parts).skip(1)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());

            labExercises.addStudent(new Student(index, points));
        }

        System.out.println("===printByAveragePoints (ascending)===");
        labExercises.printByAveragePoints(true, 100);
        System.out.println("===printByAveragePoints (descending)===");
        labExercises.printByAveragePoints(false, 100);
        System.out.println("===failed students===");
        labExercises.failedStudents().forEach(System.out::println);
        System.out.println("===statistics by year");
        labExercises.getStatisticsByYear().entrySet().stream()
                .map(entry -> String.format("%d : %.2f", entry.getKey(), entry.getValue()))
                .forEach(System.out::println);

    }
}

class Student{
    private String index;
    private List<Integer> labPoints;

    public Student(String index, List<Integer> labPoints) {
        this.index = index;
        this.labPoints = labPoints;
    }

    public double sumPoints(){
        return labPoints.stream().mapToInt(x -> x).sum() /10.0;
    }

    public String getIndex() {
        return index;
    }

    public List<Integer> getLabPoints() {
        return labPoints;
    }

    public boolean isFailed(){
        return labPoints.size()<8;
    }

    public int getYear(){
        return 20 - Integer.parseInt(index.substring(0,2));
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f",index,isFailed()?"NO":"YES",sumPoints());
    }
}

class LabExercises{
    private List<Student> students;

    public LabExercises() {
        students = new ArrayList<Student>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void printByAveragePoints(boolean asc, int limit) {
        Comparator<Student> comp = Comparator.comparing(Student::sumPoints).thenComparing(Student::getIndex);
        if(!asc) comp = comp.reversed();
        students.stream().sorted(comp).limit(limit).forEach(x -> System.out.println(x));
    }

    public List<Student> failedStudents() {
        Comparator<Student> comp = Comparator.comparing(Student::getIndex).thenComparing(Student::sumPoints);
        return students.stream().filter(Student::isFailed).sorted(comp).collect(Collectors.toList());
    }

    public Map<Integer, Double> getStatisticsByYear() {
        return students.stream().filter(x -> !x.isFailed())
                .collect(Collectors.groupingBy(Student::getYear,Collectors.averagingDouble(Student::sumPoints)));

    }
}