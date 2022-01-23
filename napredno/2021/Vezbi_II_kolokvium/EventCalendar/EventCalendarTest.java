package EventCalendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EventCalendarTest {
	public static void main(String[] args) throws ParseException {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.nextLine();
		int year = scanner.nextInt();
		scanner.nextLine();
		EventCalendar eventCalendar = new EventCalendar(year);
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		for (int i = 0; i < n; ++i) {
			String line = scanner.nextLine();
			String[] parts = line.split(";");
			String name = parts[0];
			String location = parts[1];
			Date date = df.parse(parts[2]);
			try {
				eventCalendar.addEvent(name, location, date);
			} catch (WrongDateException e) {
				System.out.println(e.getMessage());
			}
		}
		Date date = df.parse(scanner.nextLine());
		eventCalendar.listEvents(date);
		eventCalendar.listByMonth();
	}
}

// vashiot kod ovde

class WrongDateException extends Exception{
	public WrongDateException(Date date) {
		super("Wrong date: "+date.toString().replace("GMT","UTC"));
	}
}
class EventCalendar{
	private HashMap<Date, List<Event>> map;
	private int year;

	public EventCalendar(int year) {
		this.map = new HashMap<Date, List<Event>>();
		this.year = year;
	}

	public void addEvent(String name, String location, Date date) throws WrongDateException {
		if(date.getYear()!=year-1900) throw new WrongDateException(date);
		Event ev = new Event(name,location,date);
		map.compute(getDayDate(date),(k,v)->{
			if(v==null) v = new ArrayList<Event>();
			v.add(ev);
			return v;
		});
	}
	private static Date getDayDate(Date date){
		return new Date(date.getYear(),date.getMonth(),date.getDate());
	}

	public void listEvents(Date date) {
		List<Event> events = map.getOrDefault(getDayDate(date),new ArrayList<Event>());
		if(events.size()==0){
			System.out.println("No events on this day!");
			return;
		}
		Comparator<Event> cmp = Comparator.comparing(Event::getDate).thenComparing(Event::getName);
		events.stream().sorted(cmp).forEach(x-> System.out.println(x));
	}

	public void listByMonth() {
		int[] months = new int[12];
		Arrays.fill(months,0);
		map.keySet().forEach(x->months[x.getMonth()]+=map.get(x).size());
		for(int i=0;i<12;i++){
			System.out.println(i+1+" : "+months[i]);
		}
	}
}

class Event{
	private String name;
	private String location;
	private Date date;

	public Event(String name, String location, Date date) {
		this.name = name;
		this.location = location;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy HH:mm");
		String strDate = dateFormat.format(date);
		return strDate+" at "+location+", "+name;
	}
}