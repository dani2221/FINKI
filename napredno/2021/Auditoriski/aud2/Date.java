package mk.ukim.finki.aud2;

import java.util.Objects;

public class Date implements Comparable<Date>{

    private static final int FIRST_YEAR = 1800;
    private static final int LAST_YEAR = 2500;
    private static final int DAYS_IN_YEAR = 365;
    private static final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] daysTillFirstOfMonth;
    private static final int[] daysTillNewYear;
    private int days;

    // static block - used to initialize the static variables (executed when the class is loaded in memory)
    static {
        daysTillFirstOfMonth = new int[12];
        for(int i=1; i<12; i++){
            daysTillFirstOfMonth[i] = daysTillFirstOfMonth[i-1] + daysOfMonth[i-1];
        }
        int totalYears = LAST_YEAR - FIRST_YEAR + 1;
        daysTillNewYear = new int[totalYears];
        int currentYear = FIRST_YEAR;
        for(int i=1; i< totalYears; i++){
            if(isLeapYear(currentYear))
                daysTillNewYear[i] = daysTillNewYear[i-1] + DAYS_IN_YEAR +1;
            else
                daysTillNewYear[i] = daysTillNewYear[i-1] + DAYS_IN_YEAR;
            currentYear++;
        }
    }

    public Date(int days) {
        this.days = days;
    }

    public Date(int day, int month, int year){
        int days = 0;
        if(year < FIRST_YEAR || year > LAST_YEAR) throw new RuntimeException();

        days += daysTillNewYear[year - FIRST_YEAR];
        days += daysTillFirstOfMonth[month-1];
        if(month >= 2 && isLeapYear(year))
            days++;
        days += day;
        this.days = days;
    }

    private static boolean isLeapYear(int year){
        return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
    }

    public int subtract(Date date){
        return this.days - date.days;
    }

    public Date increment(int days){
        return new Date(this.days + days);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return days == date.days;
    }

    @Override
    public int hashCode() {
        return Objects.hash(days);
    }


    @Override
    public int compareTo(Date o) {
        return this.days - o.days;
    }

    @Override
    public String toString() {
        int allDays = days;
        int i;
        for(i=0; i< daysTillNewYear.length; i++){
            if(daysTillNewYear[i] >= days) break;
        }
        allDays -= daysTillNewYear[i-1];
        int year = i - 1 + FIRST_YEAR;
        if(isLeapYear(year)) allDays--;

        for(i=0; i< daysTillFirstOfMonth.length; i++){
            if(daysTillFirstOfMonth[i] >= allDays) break;
        }
        int month = i;
        allDays -= daysTillFirstOfMonth[i-1];
        return String.format("%02d.%02d.%4d", allDays, month, year);
    }

    public static void main(String[] args) {
        Date date = new Date(1, 10, 2020);
        System.out.println(date.subtract(new Date(1, 1, 2000)));
        System.out.println(date);

        date = new Date(1, 1, 1800);
        System.out.println(date);

        date = new Date(31, 12, 2500);
        System.out.println(date);
        System.out.println(date.days);
        System.out.println(daysTillNewYear[daysTillNewYear.length -1]);

        date = new Date(1, 12, 2020);
        System.out.println(date);
        date = date.increment(100);
        System.out.println(date);
    }
}
