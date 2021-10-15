package mk.ukim.finki.exams.january.payroll_system2;

//package mk.ukim.finki.january.task2;


import java.util.*;
import java.util.stream.Collectors;

class BonusNotAllowedException extends Exception {
    BonusNotAllowedException(String bonus) {
        super(String.format("Bonus of %s is not allowed", bonus));
    }

}

interface Employee {
    double calculateSalary();

    double getBonus();

    double getOvertime();

    int getTicketsCount();

    void updateBonus(double amount);

    String getLevel();
}

abstract class EmployeeBase implements Employee, Comparable<EmployeeBase> {
    String ID;
    String level;
    double rate;
    double totalBonus;


    public EmployeeBase(String ID, String level, double rate) {
        this.ID = ID;
        this.level = level;
        this.rate = rate;
        this.totalBonus = 0.0;
    }

    public String getLevel() {
        return level;
    }

    @Override
    public int compareTo(EmployeeBase o) {
        return Comparator.comparing(EmployeeBase::calculateSalary).thenComparing(EmployeeBase::getLevel).compare(this, o);
    }

    @Override
    public String toString() {
        return String.format("Employee ID: %s Level: %s Salary: %.2f", ID, level, calculateSalary() + totalBonus);
    }

    @Override
    public double getBonus() {
        return totalBonus;
    }

    @Override
    public void updateBonus(double amount) {
        this.totalBonus += amount;
    }
}

class HourlyEmployee extends EmployeeBase {
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
    public double calculateSalary() {
        return regular * rate + overtime * rate * 1.5;
    }

    @Override
    public double getOvertime() {
        return overtime * rate * 1.5;
    }

    @Override
    public int getTicketsCount() {
        return -1;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Regular hours: %.2f Overtime hours: %.2f", regular, overtime);
    }
}

class FreelanceEmployee extends EmployeeBase {
    List<Integer> ticketPoints;

    public FreelanceEmployee(String ID, String level, double rate, List<Integer> ticketPoints) {
        super(ID, level, rate);
        this.ticketPoints = ticketPoints;
    }

    @Override
    public double calculateSalary() {
        return ticketPoints.stream().mapToInt(tp -> tp).sum() * rate;
    }

    @Override
    public double getOvertime() {
        return -1;
    }

    @Override
    public int getTicketsCount() {
        return ticketPoints.size();
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Tickets count: %d Tickets points: %d",
                ticketPoints.size(),
                ticketPoints.stream().mapToInt(i -> i).sum()
        );
    }
}

abstract class BonusDecorator implements Employee {
    Employee employee;

    public BonusDecorator(Employee employee) {
        this.employee = employee;
    }

    @Override
    public double getOvertime() {
        return employee.getOvertime();
    }

    @Override
    public int getTicketsCount() {
        return employee.getTicketsCount();
    }

    @Override
    public void updateBonus(double amount) {
        employee.updateBonus(amount);
    }

    @Override
    public String getLevel() {
        return employee.getLevel();
    }

    @Override
    public String toString() {
        return employee.toString() + String.format(" Bonus: %.2f", getBonus());
    }
}

class FixedBonusDecorator extends BonusDecorator {

    double fixedAmount;

    public FixedBonusDecorator(Employee employee, double fixedAmount) {
        super(employee);
        this.fixedAmount = fixedAmount;
        employee.updateBonus(fixedAmount);
    }

    @Override
    public double calculateSalary() {
        double salaryWithoutBonus = employee.calculateSalary();
        return salaryWithoutBonus + fixedAmount;
    }

    @Override
    public double getBonus() {
        return fixedAmount;
    }
}


class PercentageBonusDecorator extends BonusDecorator {
    double percent;
    double bonus;

    public PercentageBonusDecorator(Employee employee, double percent) {
        super(employee);
        this.percent = percent;
        bonus = employee.calculateSalary() * percent / 100.0;
        employee.updateBonus(bonus);
    }

    @Override
    public double calculateSalary() {
        double salaryWithoutBonus = employee.calculateSalary();
        return salaryWithoutBonus + bonus;
    }

    @Override
    public double getBonus() {
        return bonus;
    }
}

class EmployeeFactory {
    public static Employee createEmployee(String line, Map<String, Double> hourlyRate, Map<String, Double> ticketRate) throws BonusNotAllowedException {
        String[] partsBySpace = line.split("\\s+");

        Employee e = createSimpleEmployee(partsBySpace[0], hourlyRate, ticketRate);

        if (partsBySpace.length > 1) {
            if (partsBySpace[1].contains("%")) { //percentage bonus
                double percentage = Double.parseDouble(partsBySpace[1].substring(0, partsBySpace[1].length() - 1));
                if (percentage > 20)
                    throw new BonusNotAllowedException(partsBySpace[1]);
                e = new PercentageBonusDecorator(e, percentage);

            } else { //fixed bonus
                double bonusAmount = Double.parseDouble(partsBySpace[1]);
                if (bonusAmount > 1000)
                    throw new BonusNotAllowedException(partsBySpace[1] + "$");
                e = new FixedBonusDecorator(e, bonusAmount);

            }
        }
        return e;
    }

    public static Employee createSimpleEmployee(String subline, Map<String, Double> hourlyRate, Map<String, Double> ticketRate) {
        String[] parts = subline.split(";");
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

    Employee createEmployee(String line) throws BonusNotAllowedException {
        Employee e = EmployeeFactory.createEmployee(line, hourlyRate, ticketRate);
        employees.add(e);
        return e;
    }

    Map<String, Double> getOvertimeSalaryForLevels() {
        Map<String, Double> result = employees.stream().collect(Collectors.groupingBy(
                Employee::getLevel,
                Collectors.summingDouble(Employee::getOvertime)
        ));

        List<String> keysWithZeros = result.keySet().stream().filter(key -> result.get(key) == -1).collect(Collectors.toList());

        keysWithZeros.forEach(result::remove);

        return result;
    }

    void printStatisticsForOvertimeSalary() {
        DoubleSummaryStatistics dss = employees.stream()
                .filter(e -> e.getOvertime() != -1)
                .mapToDouble(Employee::getOvertime)
                .summaryStatistics();

        System.out.printf("Statistics for overtime salary: Min: %.2f Average: %.2f Max: %.2f Sum: %.2f", dss.getMin(), dss.getAverage(), dss.getMax(), dss.getSum());
    }

    Map<String, Integer> ticketsDoneByLevel() {
        return employees.stream().filter(e -> e.getTicketsCount() != -1)
                .collect(Collectors.groupingBy(
                        Employee::getLevel,
                        Collectors.summingInt(Employee::getTicketsCount)
                ));
    }

    Collection<Employee> getFirstNEmployeesByBonus(int n) {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getBonus).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

}

public class PayrollSystemTest2 {

    public static void main(String[] args) {

        Map<String, Double> hourlyRateByLevel = new LinkedHashMap<>();
        Map<String, Double> ticketRateByLevel = new LinkedHashMap<>();
        for (int i = 1; i <= 10; i++) {
            hourlyRateByLevel.put("level" + i, 11 + i * 2.2);
            ticketRateByLevel.put("level" + i, 5.5 + i * 2.5);
        }

        Scanner sc = new Scanner(System.in);

        int employeesCount = Integer.parseInt(sc.nextLine());

        PayrollSystem ps = new PayrollSystem(hourlyRateByLevel, ticketRateByLevel);
        Employee emp = null;
        for (int i = 0; i < employeesCount; i++) {
            try {
                emp = ps.createEmployee(sc.nextLine());
            } catch (BonusNotAllowedException e) {
                System.out.println(e.getMessage());
            }
        }

        int testCase = Integer.parseInt(sc.nextLine());

        switch (testCase) {
            case 1: //Testing createEmployee
                if (emp != null)
                    System.out.println(emp);
                break;
            case 2: //Testing getOvertimeSalaryForLevels()
                ps.getOvertimeSalaryForLevels().forEach((level, overtimeSalary) -> {
                    System.out.printf("Level: %s Overtime salary: %.2f\n", level, overtimeSalary);
                });
                break;
            case 3: //Testing printStatisticsForOvertimeSalary()
                ps.printStatisticsForOvertimeSalary();
                break;
            case 4: //Testing ticketsDoneByLevel
                ps.ticketsDoneByLevel().forEach((level, overtimeSalary) -> {
                    System.out.printf("Level: %s Tickets by level: %d\n", level, overtimeSalary);
                });
                break;
            case 5: //Testing getFirstNEmployeesByBonus (int n)
                ps.getFirstNEmployeesByBonus(Integer.parseInt(sc.nextLine())).forEach(System.out::println);
                break;
        }

    }
}
