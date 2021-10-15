package mk.ukim.finki.aud6_kolokviumski_2;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

abstract class Temperature {
    int value;

    public Temperature(int value) {
        this.value = value;
    }

    //Factory design pattern
    public static Temperature createTemperature (String part) {
        //49F 49C
        char type = part.charAt(part.length()-1);
        Integer value = Integer.parseInt(part.substring(0,part.length()-1));
        if (type=='C')
            return new CTemperature(value);
        else
            return new FTemperature(value);
    }

    abstract double getCelsius();
    abstract double getFahrenheit();
}

class CTemperature extends Temperature {

    public CTemperature(int value) {
        super(value);
    }

    @Override
    double getCelsius() {
        return value;
    }

    @Override
    double getFahrenheit() {
        return value*9.0/5 + 32.0;
    }

    @Override
    public String toString() {
        return String.format("%dC", value);
    }
}

class FTemperature extends Temperature {

    public FTemperature(int value) {
        super(value);
    }

    @Override
    double getCelsius() {
        return (value-32)*5.0/9.0;
    }

    @Override
    double getFahrenheit() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%dF", value);
    }
}

class DailyMeasurement implements Comparable<DailyMeasurement>{
    int day;
    List<Temperature> temperatures;

    public DailyMeasurement(int day, List<Temperature> temperatures) {
        this.day = day;
        this.temperatures = temperatures;
    }

    public static DailyMeasurement createDailyMeasurement (String line) {
        //140 47F 49F 46F 46F 47F 49F 48F 50F 45F 47F 46F 49F 50F 47F 50F 49F 49F 47F 45F
        String [] parts = line.split("\\s+");
        int day = Integer.parseInt(parts[0]);
//        List<Temperature> temperatures = new ArrayList<>();
//        for (int i=1;i<parts.length;i++) {
//            temperatures.add(Temperature.createTemperature(parts[i]));
//        }
        List<Temperature> temperatures = Arrays.stream(parts).skip(1)
                .map(part -> Temperature.createTemperature(part))
                .collect(Collectors.toList());

        return new DailyMeasurement(day, temperatures);
    }

    @Override
    public int compareTo(DailyMeasurement o) {
        return Integer.compare(this.day, o.day);
    }

    public String toString(char scale) {
        // 11: Count:   7 Min:  38.33C Max:  40.56C Avg:  39.44C
        //[ден]: Count: [вк. мерења - 3 места] Min: [мин. температура] Max: [макс. температура] Avg: [просек ]
        DoubleSummaryStatistics dss = temperatures.stream()
                .mapToDouble(t -> scale=='C' ? t.getCelsius() : t.getFahrenheit())
                .summaryStatistics();

        return String.format("%3d: Count: %3d Min: %6.2f Max: %6.2f Avg: %6.2f",
                day,
                dss.getCount(),
                dss.getMin(),
                dss.getMax(),
                dss.getAverage());
    }
}

class DailyTemperatures {

    List<DailyMeasurement> dailyMeasurements;

    DailyTemperatures() {
        dailyMeasurements = new ArrayList<>();
    }

    public void readTemperatures(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        dailyMeasurements = br.lines().map(line -> DailyMeasurement.createDailyMeasurement(line))
                .collect(Collectors.toList());


    }

    public void writeDailyStats(OutputStream out, char scale) {
        // 11: Count:   7 Min:  38.33C Max:  40.56C Avg:  39.44C
        //[ден]: Count: [вк. мерења - 3 места] Min: [мин. температура] Max: [макс. температура] Avg: [просек ]
        PrintWriter pw = new PrintWriter(out);
        dailyMeasurements.sort(Comparator.naturalOrder());
        dailyMeasurements.forEach(dm -> pw.println(dm.toString(scale)));
        pw.flush();
    }
}



/**
 * I partial exam 2016
 */
public class DailyTemperatureTest {
    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        dailyTemperatures.readTemperatures(System.in);
        System.out.println("=== Daily temperatures in Celsius (C) ===");
        dailyTemperatures.writeDailyStats(System.out, 'C');
        System.out.println("=== Daily temperatures in Fahrenheit (F) ===");
        dailyTemperatures.writeDailyStats(System.out, 'F');
    }
}

// Vashiot kod ovde
