package Airports;

import java.util.*;
import java.util.stream.Collectors;

public class AirportsTest {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Airports airports = new Airports();
    int n = scanner.nextInt();
    scanner.nextLine();
    String[] codes = new String[n];
    for (int i = 0; i < n; ++i) {
      String al = scanner.nextLine();
      String[] parts = al.split(";");
      airports.addAirport(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
      codes[i] = parts[2];
    }
    int nn = scanner.nextInt();
    scanner.nextLine();
    for (int i = 0; i < nn; ++i) {
      String fl = scanner.nextLine();
      String[] parts = fl.split(";");
      airports.addFlights(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
    }
    int f = scanner.nextInt();
    int t = scanner.nextInt();
    String from = codes[f];
    String to = codes[t];
    System.out.printf("===== FLIGHTS FROM %S =====\n", from);
    airports.showFlightsFromAirport(from);
    System.out.printf("===== DIRECT FLIGHTS FROM %S TO %S =====\n", from, to);
    airports.showDirectFlightsFromTo(from, to);
    t += 5;
    t = t % n;
    to = codes[t];
    System.out.printf("===== DIRECT FLIGHTS TO %S =====\n", to);
    airports.showDirectFlightsTo(to);
  }
}

// vashiot kod ovde
class Airports{
  private Set<Airport> airports;

  public Airports() {
    airports = new HashSet<Airport>();
  }

  public void addAirport(String part, String part1, String part2, int parseInt) {
    airports.add(new Airport(part,part1,part2,parseInt));
  }

  public void addFlights(String from, String out, int time, int duration) {
    Airport fromAirport = getAirportFromCode(from);
    Airport toAirport = getAirportFromCode(out);
    fromAirport.addFlight(new Flight(fromAirport,toAirport,time,duration));
  }
  private Airport getAirportFromCode(String code){
    return airports.stream().filter(x -> x.getCode().equals(code)).collect(Collectors.toList()).get(0);
  }

  public void showFlightsFromAirport(String from) {
    Airport ap = getAirportFromCode(from);
    System.out.print(ap);
  }

  public void showDirectFlightsFromTo(String from, String to) {
    List<Flight> fls = getAirportFromCode(from).getFlights().stream()
            .filter(x -> x.getTo()==getAirportFromCode(to))
            .collect(Collectors.toList());
    if(fls.size()==0){
      System.out.println(String.format("No flights from %s to %s",from,to));
    }else{
      fls.forEach(System.out::println);
    }
  }

  public void showDirectFlightsTo(String to) {
    airports.stream()
            .flatMap(air -> air.getFlights().stream())
            .filter(fl -> Objects.equals(fl.getTo(), getAirportFromCode(to)))
            .collect(Collectors.toCollection(TreeSet::new)) // basically used to sort without using sorted
            .forEach(System.out::println);
  }
}

class Airport implements Comparable<Airport> {
  private String name;
  private String country;
  private String code;
  private int passengers;

  private Set<Flight> flights;

  public Airport(String name, String country, String code, int passengers) {
    this.name = name;
    this.country = country;
    this.code = code;
    this.passengers = passengers;
    flights = new TreeSet<Flight>();
  }

  public String getName() {
    return name;
  }

  public String getCountry() {
    return country;
  }

  public String getCode() {
    return code;
  }

  public int getPassengers() {
    return passengers;
  }

  public Set<Flight> getFlights() {
    return flights;
  }

  public void addFlight(Flight f){
    flights.add(f);
  }

  @Override
  public String toString() {
    String str = String.format("%s (%s)\n%s\n%d\n",getName(),getCode(),getCountry(),getPassengers());
    int i=1;
    for (Flight flight : flights) {
      str+=(i++)+". "+flight+"\n";
    }
    return str;
  }

  @Override
  public int compareTo(Airport o) {
    return this.code.compareTo(o.code);
  }
}

class Flight implements Comparable<Flight> {
  private Airport from;
  private Airport to;
  private int time;
  private int duration;

  public Flight(Airport from, Airport to, int time, int duration) {
    this.from = from;
    this.to = to;
    this.time = time;
    this.duration = duration;
  }

  public Airport getFrom() {
    return from;
  }

  public Airport getTo() {
    return to;
  }

  public int getTime() {
    return time;
  }

  public int getDuration() {
    return duration;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Flight flight = (Flight) o;
    return time == flight.time &&
            duration == flight.duration &&
            from.equals(flight.from) &&
            to.equals(flight.to);
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, to, time, duration);
  }

  @Override
  public String toString() {
    return String.format("%s-%s %s-%s %s",from.getCode(),to.getCode(),startTime(),endTime(),durationTime());
  }

  public String startTime() {
    return getTimeString(time);
  }
  public String endTime() {
   return  getTimeString(time+duration);
  }
  public String durationTime() {
    return String.format("%dh%02dm",duration/60,duration%60);
  }
  private String getTimeString(int time){
    int hours = time/60;
    String addDay = "";
    if(time/60>=24){
      hours = time/60%24;
      addDay=" +1d";
    }
    return String.format("%02d:%02d",hours,time%60)+addDay;
  }

  @Override
  public int compareTo(Flight o) {
    if(this.to.compareTo(o.to)==0){
      return Integer.compare(time,o.time);
    }else return this.to.compareTo(o.to);
  }
}

