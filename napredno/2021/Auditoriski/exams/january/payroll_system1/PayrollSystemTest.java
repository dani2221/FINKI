package mk.ukim.finki.exams.january.payroll_system1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

abstract class Employee implements Comparable<Employee> {
    String ID;
    String level;
    double rate;

    public Employee(String ID, String level, double rate) {
        this.ID = ID;
        this.level = level;
        this.rate = rate;
    }

    abstract double calculateSalary();

    public String getLevel() {
        return level;
    }

    @Override
    public int compareTo(Employee o) {
        return Comparator.comparing(Employee::calculateSalary).thenComparing(Employee::getLevel).reversed().compare(this, o);
    }

    @Override
    public String toString() {
        return String.format("Employee ID: %s Level: %s Salary: %.2f", ID, level, calculateSalary());
    }
}

class HourlyEmployee extends Employee {
    double hours;
    double overtime;
    double regular;

    public HourlyEmployee(String ID, String level, double rate, double hours) {
        super(ID, level, rate);
        this.hours = hours;
        overtime = Math.max(0, hours - 40);
        regular = hours - overtime;
    }

    @Override
    double calculateSalary() {
        return regular * rate + overtime * rate * 1.5;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Regular hours: %.2f Overtime hours: %.2f", regular, overtime);
    }
}

class FreelanceEmployee extends Employee {
    List<Integer> ticketPoints;

    public FreelanceEmployee(String ID, String level, double rate, List<Integer> ticketPoints) {
        super(ID, level, rate);
        this.ticketPoints = ticketPoints;
    }

    @Override
    double calculateSalary() {
        return ticketPoints.stream().mapToInt(tp -> tp).sum() * rate;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Tickets count: %d Tickets points: %d",
                ticketPoints.size(),
                ticketPoints.stream().mapToInt(i -> i).sum()
        );
    }
}

class EmployeeFactory {
    public static Employee createEmployee(String line, Map<String, Double> hourlyRate, Map<String, Double> ticketRate) {
        String[] parts = line.split(";");
        String id = parts[1];
        String level = parts[2];
        if (parts[0].equalsIgnoreCase("H")) { //hourly
            double hours = Double.parseDouble(parts[3]);
            return new HourlyEmployee(id, level, hourlyRate.get(level), hours);
        } else { //freelance
            List<Integer> ticketPoints = Arrays.stream(parts).skip(3)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            return new FreelanceEmployee(id, level, ticketRate.get(level), ticketPoints);
        }
    }
}

class PayrollSystem {
    Map<String, Double> hourlyRate;
    Map<String, Double> ticketRate;
    List<Employee> employees;

    public PayrollSystem(Map<String, Double> hourlyRate, Map<String, Double> ticketRate) {
        this.hourlyRate = hourlyRate;
        this.ticketRate = ticketRate;
        employees = new ArrayList<>();
    }

    void readEmployees(InputStream is) {
        employees = new BufferedReader(new InputStreamReader(is)).lines()
                .map(line -> EmployeeFactory.createEmployee(line, hourlyRate, ticketRate))
                .collect(Collectors.toList());
    }

    Map<String, Set<Employee>> printEmployeesByLevels(Set<String> levels) {
        Map<String, Set<Employee>> map = employees.stream().collect(Collectors.groupingBy(
                Employee::getLevel,
                (Supplier<TreeMap<String, Set<Employee>>>) TreeMap::new,
                Collectors.toCollection(TreeSet::new)
        ));

        Set<String> keys = new HashSet<>(map.keySet());

        keys.stream()
                .filter(k -> !levels.contains(k))
                .forEach(map::remove);

        return map;
    }


}

public class PayrollSystemTest {

    public static void main(String[] args) {

        Map<String, Double> hourlyRateByLevel = new LinkedHashMap<>();
        Map<String, Double> ticketRateByLevel = new LinkedHashMap<>();
        for (int i = 1; i <= 10; i++) {
            hourlyRateByLevel.put("level" + i, 10 + i * 2.2);
            ticketRateByLevel.put("level" + i, 5 + i * 2.5);
        }

        PayrollSystem payrollSystem = new PayrollSystem(hourlyRateByLevel, ticketRateByLevel);

        System.out.println("READING OF THE EMPLOYEES DATA");
        payrollSystem.readEmployees(System.in);

        System.out.println("PRINTING EMPLOYEES BY LEVEL");
        Set<String> levels = new LinkedHashSet<>();
        for (int i=5;i<=10;i++) {
            levels.add("level"+i);
        }
        Map<String, Set<Employee>> result = payrollSystem.printEmployeesByLevels(levels);
        result.forEach((level, employees) -> {
            System.out.println("LEVEL: "+ level);
            System.out.println("Employees: ");
            employees.forEach(System.out::println);
            System.out.println("------------");
        });


    }
}
