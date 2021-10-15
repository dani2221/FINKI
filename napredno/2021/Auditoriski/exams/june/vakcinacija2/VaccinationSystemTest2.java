package mk.ukim.finki.exams.june.vakcinacija2;

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

abstract class Application implements Comparable<Application> {

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

    public Map<Integer, String> getVaccines() {
        return vaccines;
    }

    public static Application createApplication(String line) throws InvalidApplicationException {
        String[] parts = line.split("\\s+");
        if (getAge(parts[0]) < 18)
            throw new InvalidApplicationException(parts[1], parts[2], parts[0]);

        if (parts[3].equals("DOCTOR") || parts[3].equals("NURSE") || parts[3].equals("POLICEMAN") || parts[3].equals("FIREMAN"))
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

    private int getAge() {
        return getAge(this.ID);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(this.ID + " " + this.name + " " + this.surname + " " + this.getAge() + " " + this.getApplicationType() + " ");

        for (String vaccine : this.vaccines.values()) {
            result.append(vaccine).append(" ");
        }
        result.substring(0, result.length() - 1);
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

class PriorityApplication extends Application {

    public PriorityApplication(String ID, String name, String surname, String profession, String vaccine1, String vaccine2, String vaccine3) {
        super(ID, name, surname, profession, vaccine1, vaccine2, vaccine3, 1);
    }

    @Override
    public Type getApplicationType() {
        return Type.PRIORITY;
    }
}

class BasicApplication extends Application {

    public BasicApplication(String ID, String name, String surname, String profession, String vaccine1, String vaccine2, String vaccine3) {
        super(ID, name, surname, profession, vaccine1, vaccine2, vaccine3, 2);
    }

    @Override
    public Type getApplicationType() {
        return Type.BASIC;
    }
}

class VaccinesAccumulator {
    private int PH;
    private int AZ;
    private int SF;

    public VaccinesAccumulator(int pfizer, int astraZeneca, int sinofarm) {
        this.PH = pfizer;
        this.AZ = astraZeneca;
        this.SF = sinofarm;
    }

    public void combine (VaccinesAccumulator vaccinesAccumulator) {
        this.PH += vaccinesAccumulator.PH;
        this.AZ += vaccinesAccumulator.AZ;
        this.SF += vaccinesAccumulator.SF;
    }

    private boolean takePH() {
        if (PH >= 2) {
            PH -= 2;
            return true;
        } else {
            return false;
        }
    }

    private boolean takeAZ() {
        if (AZ >= 2) {
            AZ -= 2;
            return true;
        } else {
            return false;
        }
    }

    private boolean takeSF() {
        if (SF >= 2) {
            SF -= 2;
            return true;
        } else {
            return false;
        }
    }

    private boolean takeDose(String vaccineName) {
        switch (vaccineName) {
            case "PH":
                return takePH();
            case "AZ":
                return takeAZ();
            case "SF":
                return takeSF();
            default:
                return false;
        }
    }

    public String getDoses(Application application) {
        String first = application.getVaccines().get(1);
        String second = application.getVaccines().get(2);
        String third = application.getVaccines().get(3);

        if (!takeDose(first)) {
            if (!takeDose(second)) {
                if (takeDose(third)) {
                    return third;
                } else {
                    return null;
                }
            } else {
                return second;
            }
        } else {
            return first;
        }
    }
}

class VaccinationSystem {
    private List<Application> applications;
    private Map<String, String> vaccinated;
    private Map<String, Application> applicationsMap;
    private VaccinesAccumulator vaccinesAccumulator;

    public VaccinationSystem() {
        this.applications = new ArrayList<>();
        this.vaccinated = new TreeMap<>();
        this.applicationsMap = new TreeMap<>();
        vaccinesAccumulator = new VaccinesAccumulator(0,0,0);
    }

    public void vaccinate(int numPH, int numAZ, int numSF) {
        Collections.sort(applications);

        vaccinesAccumulator.combine(new VaccinesAccumulator(numPH, numAZ, numSF));

        for (Application application : this.applications) {
            if (!vaccinated.containsKey(application.getID())) {
                String appliedVaccine = vaccinesAccumulator.getDoses(application);
                if (appliedVaccine == null) {
                    break;
                }
                vaccinated.putIfAbsent(application.getID(), appliedVaccine);
            }
        }
    }

    public Map<String, String> vaccinatedCitizens() {
        return this.vaccinated;
    }

    public Map<Integer, Integer> appliedVaccinesPerPriority() {
        Map<Integer, Integer> result = new HashMap<>();
        result.putIfAbsent(1, 0);
        result.putIfAbsent(2, 0);
        result.putIfAbsent(3, 0);

        for (int i = 1; i <= 3; i++) {
            int finalI = i;
            int count = (int) this.vaccinated.entrySet().stream()
                    .filter(k -> k.getValue().equals(this.applicationsMap.get(k.getKey()).getVaccines().get(finalI)))
                    .count();
            result.put(i, count);
        }
        return result;
    }

    public Map<String, List<Application>> vaccinatedCitizensByVaccineType() {
        Map<String, List<Application>> result = new HashMap<>();

        for (Map.Entry<String, String> entry : this.vaccinated.entrySet()) {
            result.putIfAbsent(entry.getValue(), new ArrayList<>());
            result.get(entry.getValue()).add(this.applicationsMap.get(entry.getKey()));
        }
        return result;
    }

    public Collection<Application> nextToVaccinate(int n) {
        return this.applications.stream().filter(i -> i.getApplicationType().name().equals("BASIC"))
                .filter(i -> !this.vaccinated.containsKey(i.getID()))
                .limit(n)
                .collect(Collectors.toList());
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
                .sorted()
                .collect(Collectors.toList());

        this.applications.forEach(i -> applicationsMap.putIfAbsent(i.getID(), i));
    }

    public void printApplications(OutputStream outputStream) {
        PrintWriter printWriter = new PrintWriter(outputStream);
        this.applications.stream().sorted().forEach(printWriter::println);
        printWriter.flush();
        printWriter.close();
    }
}

public class VaccinationSystemTest2 {

    public static Map<Integer, List<Integer>> populateCombinations() {
        Map<Integer, List<Integer>> map = new HashMap<>();

        map.put(1, new ArrayList<>(Arrays.asList(2, 0, 2)));
        map.put(2, new ArrayList<>(Arrays.asList(2, 2, 2)));
        map.put(3, new ArrayList<>(Arrays.asList(2, 4, 2)));
        map.put(4, new ArrayList<>(Arrays.asList(2, 4, 4)));
        map.put(5, new ArrayList<>(Arrays.asList(4, 4, 4)));

        return map;
    }

    public static void main(String[] args) {
        VaccinationSystem vaccinationSystem = new VaccinationSystem();

        System.out.println("------------ READING FROM INPUT STREAM ------------");
        vaccinationSystem.readApplications(System.in);
        System.out.println("------------ READING SUCCESSFUL ------------");
        System.out.println();

        Map<Integer, List<Integer>> combinations = populateCombinations();

        for (int i = 1; i <= combinations.size(); i++) {
            List<Integer> combination = combinations.get(i);

            System.out.println("------------ CASE " + i + "------------");
            System.out.println();
            vaccinationSystem.vaccinate(combination.get(0), combination.get(1), combination.get(2));

            System.out.println("------------------------ TEST VACCINATED CITIZENS ------------");
            Map<String, String> map1 = vaccinationSystem.vaccinatedCitizens();
            map1.forEach((key, value) -> System.out.println(key + " " + value));
            System.out.println();

            System.out.println("------------------------ TEST APPLIED VACCINES PER PRIORITY ------------");
            Map<Integer, Integer> map2 = vaccinationSystem.appliedVaccinesPerPriority();
            map2.forEach((key, value) -> System.out.println(key + " " + value));
            System.out.println();

            System.out.println("------------------------ TEST VACCINATED CITIZENS BY VACCINE TYPE ------------");
            Map<String, List<Application>> map3 = vaccinationSystem.vaccinatedCitizensByVaccineType();
            map3.forEach((key, value) -> System.out.println(key + " " + value));
            System.out.println();

            System.out.println("------------------------ TEST NEXT TO VACCINATE 3 ------------");
            Collection<Application> collection1 = vaccinationSystem.nextToVaccinate(3);
            collection1.forEach(System.out::println);
            System.out.println();

            System.out.println("------------------------ TEST NEXT TO VACCINATE 1 ------------");
            Collection<Application> collection2 = vaccinationSystem.nextToVaccinate(1);
            collection2.forEach(System.out::println);
            System.out.println();
        }
    }
}

