package StopCoronaApp;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

// ne e kompletna

interface ILocation{
    double getLongitude();

    double getLatitude();

    LocalDateTime getTimestamp();
}

public class StopCoronaTest {
    
    public static double timeBetweenInSeconds(ILocation location1, ILocation location2) {
        return Math.abs(Duration.between(location1.getTimestamp(), location2.getTimestamp()).getSeconds());
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StopCoronaApp stopCoronaApp = new StopCoronaApp();

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");

            switch (parts[0]) {
                case "REG": //register
                    String name = parts[1];
                    String id = parts[2];
                    try {
                        stopCoronaApp.addUser(name, id);
                    } catch (UserAlreadyExistException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "LOC": //add locations
                    id = parts[1];
                    List<ILocation> locations = new ArrayList<>();
                    for (int i = 2; i < parts.length; i += 3) {
                        locations.add(createLocationObject(parts[i], parts[i + 1], parts[i + 2]));
                    }
                    stopCoronaApp.addLocations(id, locations);

                    break;
                case "DET": //detect new cases
                    id = parts[1];
                    LocalDateTime timestamp = LocalDateTime.parse(parts[2]);
                    stopCoronaApp.detectNewCase(id, timestamp);

                    break;
                case "REP": //print report
                    stopCoronaApp.createReport();
                    break;
                default:
                    break;
            }
        }
    }

    private static ILocation createLocationObject(String lon, String lat, String timestamp) {
        return new ILocation() {
            @Override
            public double getLongitude() {
                return Double.parseDouble(lon);
            }

            @Override
            public double getLatitude() {
                return Double.parseDouble(lat);
            }

            @Override
            public LocalDateTime getTimestamp() {
                return LocalDateTime.parse(timestamp);
            }
        };
    }
}

class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(String id) {
        super(id+" exists");
    }
}

class StopCoronaApp{
    private HashMap<String, User> users;

    public StopCoronaApp() {
        users = new HashMap<String, User>();
    }

    public void addUser(String name, String id) throws UserAlreadyExistException {
        if(users.get(id)!=null) throw new UserAlreadyExistException(id);
        users.put(id,new User(id,name));
    }

    public void addLocations (String id, List<ILocation> iLocations){
        users.get(id).appendLocations(iLocations);
    }

    public void detectNewCase (String id, LocalDateTime timestamp){
        users.get(id).setPositive(true,timestamp);
    }

    private static double getDistance (ILocation l1, ILocation l2){
        return Math.sqrt(Math.pow(l2.getLatitude()-l1.getLatitude(),2)+Math.pow(l2.getLongitude()-l1.getLongitude(),2));
    }
    private static long timeDifferenceSeconds (LocalDateTime time1, LocalDateTime time2){
        return Math.abs(time1.toEpochSecond(ZoneOffset.UTC)-time2.toEpochSecond(ZoneOffset.UTC));
    }

    public Map<User, Integer> getDirectContacts (User u){
        List<User> directContacts= new ArrayList<User>();
        u.getVisitedLocations().forEach(x -> directContacts.addAll(usersAtLocationAtTime(x)));
        return directContacts.stream().filter(x -> x!=u).collect(
                Collectors.toMap(
                        Function.identity(),
                        usr -> 1,
                        (o,n)-> o+1
                )
        );
    }

    public Collection<User> getIndirectContacts (User u){
        HashSet<User> indirect= new HashSet<User>();
        getDirectContacts(u).keySet()
                .forEach(x -> x.getVisitedLocations().forEach(loc->indirect.addAll(usersAtLocationAtTime(loc))));
        return indirect;
    }

    private List<User> usersAtLocationAtTime(ILocation location){
        List<User> us = new ArrayList<User>();
        users.values().forEach(x -> {
            x.getVisitedLocations().forEach(loc -> {
                if(StopCoronaApp.getDistance(loc,location)<=2
                        && StopCoronaApp.timeDifferenceSeconds(location.getTimestamp(),loc.getTimestamp())<=5*60) us.add(x);
            });
        });
        return us;
    }

    public void createReport() {
        users.values().stream().filter(User::isPositive)
                .forEach(x ->{
                    System.out.println(x.getName()+" "+x.getId()+" "+x.getPositiveDate());
                    System.out.println("Direct contacts:");
                    getDirectContacts(x).keySet().forEach(dc-> System.out.println(dc.getName()+" "+dc.getId().substring(0,5)+"*** "+getDirectContacts(x).get(dc)));
                    System.out.println("Indirect contacts:");
                    getIndirectContacts(x).forEach(ic-> System.out.println(ic.getName()+" "+ic.getId().substring(0,5)+"***"));
                });
    }
}

class User{
    private String id;
    private String name;
    private List<ILocation> visitedLocations;
    private boolean isPositive;
    private LocalDateTime positiveDate;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        visitedLocations = new ArrayList<ILocation>();
    }

    public void setPositive(boolean positive, LocalDateTime timestamp) {
        isPositive = positive;
        positiveDate = timestamp;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void appendLocations(List<ILocation> visitedLocations) {
        this.visitedLocations.addAll(visitedLocations);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ILocation> getVisitedLocations() {
        return visitedLocations;
    }

    public boolean isPositive() {
        return isPositive;
    }

    public LocalDateTime getPositiveDate() {
        return positiveDate;
    }
}
