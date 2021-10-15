package mk.ukim.finki.exams.june.vakcinacija1;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

enum Type {
    PRIORITY,
    BASIC
}


class InvalidApplicationException extends Exception {
    public InvalidApplicationException(String name, String surname, String ID) {
        super(String.format("The application for %s %s with ID: %s is not allowed, because the citizen is under 18 years old.", name, surname, ID));
    }
}

abstract class Application implements Comparable<Application>{

    private String ID;
    private String name;
    private String surname;
    private String profession;
    private Map<Integer, String> vaccines;
    private Integer priority;

    public Application(String ID, String name, String surname, String profession, String vaccine1, String vaccine2, String vaccine3, Integer priority) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.profession = profession;
        this.vaccines = new TreeMap<>();
        this.vaccines.putIfAbsent(1, vaccine1);
        this.vaccines.putIfAbsent(2, vaccine2);
        this.vaccines.putIfAbsent(3, vaccine3);
        this.priority = priority;
    }

    public String getProfession() {
        return profession;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public abstract Type getApplicationType();

    public static Application createApplication(String line) throws InvalidApplicationException {
        String[] parts = line.split("\\s+");
        if(getAge(parts[0]) < 18)
            throw new InvalidApplicationException(parts[1], parts[2], parts[0]);

        if(parts[3].equals("DOCTOR") || parts[3].equals("NURSE") || parts[3].equals("POLICEMAN") || parts[3].equals("FIREMAN"))
            return new PriorityApplication(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
        else return new BasicApplication(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
    }

    public static int getAge(String ID) {
        int day = Integer.parseInt(ID.substring(0, 2));
        int month = Integer.parseInt(ID.substring(2, 4));
        String year = ID.substring(4, 7);
        String fullYear;
        if(year.charAt(0) == '0')
            fullYear = "2" + year;
        else
            fullYear = "1" + year;
        if(month < 6)
            return 2021 - Integer.parseInt(fullYear);
        else if (month == 6 && day <= 14)
            return 2021 - Integer.parseInt(fullYear);
        else
            return 2021 - Integer.parseInt(fullYear) - 1;
    }

    private int getAge(){
        return getAge(this.ID);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(this.ID + " " + this.name + " " + this.surname + " " + this.getAge() + " "+ this.getApplicationType() + " ");

        for(String vaccine: this.vaccines.values()){
            result.append(vaccine).append(" ");
        }

        result.substring(0, result.length()-1);
        return result.toString();
    }

    @Override
    public int compareTo(Application application) {
        return Comparator.comparing(Application::getPriority)
                .reversed()
                .thenComparing(Application::getAge)
                .reversed()
                .thenComparing(Application::getName)
                .compare(this, application);
    }
}

class PriorityApplication extends Application{

    public PriorityApplication(String ID, String name, String surname, String profession, String vaccine1, String vaccine2, String vaccine3) {
        super(ID, name, surname, profession, vaccine1, vaccine2, vaccine3, 1);
    }

    @Override
    public Type getApplicationType() {
        return Type.PRIORITY;
    }
}

class BasicApplication extends Application{

    public BasicApplication(String ID, String name, String surname, String profession, String vaccine1, String vaccine2, String vaccine3) {
        super(ID, name, surname, profession, vaccine1, vaccine2, vaccine3, 2);
    }

    @Override
    public Type getApplicationType() {
        return Type.BASIC;
    }
}

class VaccinationSystem {
    private List<Application> applications;

    public VaccinationSystem() {
        this.applications = new ArrayList<>();
    }

    public void readApplications(InputStream inputStream) {
        this.applications = new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .map(line -> {
                    try {
                        return Application.createApplication(line);
                    } catch (InvalidApplicationException exception) {
                        System.out.println(exception.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public void printApplications(OutputStream outputStream) {
        PrintWriter printWriter = new PrintWriter(outputStream);
        this.applications.stream().sorted().forEach(printWriter::println);
        printWriter.flush();
        printWriter.close();
    }
}

public class VaccinationSystemTest1 {

    public static void main(String[] args) {
        VaccinationSystem vaccinationSystem = new VaccinationSystem();

        System.out.println("--- READING FROM INPUT STREAM ---");
        vaccinationSystem.readApplications(System.in);

        System.out.println();
        System.out.println("--- PRINTING TO OUTPUT STREAM ---");
        vaccinationSystem.printApplications(System.out);
    }
}
