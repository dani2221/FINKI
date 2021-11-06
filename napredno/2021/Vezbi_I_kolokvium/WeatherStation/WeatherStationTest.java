package WeatherStation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WeatherStationTest {
	public static void main(String[] args) throws ParseException {
		Scanner scanner = new Scanner(System.in);
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        int n = scanner.nextInt();
        scanner.nextLine();
		WeatherStation ws = new WeatherStation(n);
		while (true) {
			String line = scanner.nextLine();
			if (line.equals("=====")) {
				break;
			}
			String[] parts = line.split(" ");
			float temp = Float.parseFloat(parts[0]);
			float wind = Float.parseFloat(parts[1]);
			float hum = Float.parseFloat(parts[2]);
			float vis = Float.parseFloat(parts[3]);
			line = scanner.nextLine();
			Date date = df.parse(line);
			ws.addMeasurment(temp, wind, hum, vis, date);
		}
		String line = scanner.nextLine();
		Date from = df.parse(line);
		line = scanner.nextLine();
		Date to = df.parse(line);
		scanner.close();
		System.out.println(ws.total());
		try {
			ws.status(from, to);
		} catch (RuntimeException e) {
			System.out.println(e);
		}
	}
}

// vashiot kod ovde
class WeatherStation{
	private int days;
	List<Measurement> measurements;

	public WeatherStation(int days) {
		this.days = days;
		this.measurements = new ArrayList<Measurement>();
	}

	public void addMeasurment(float temperature, float wind, float humidity, float visibility, Date date){
		deleteOldMeasurements(date);
		if(!isRecent(date)) measurements.add(new Measurement(temperature,wind,humidity,visibility,date));
	}

	private void deleteOldMeasurements(Date newDate){
		long ms = days*24*3600*1000;
		List<Measurement> toBeRemoved = new ArrayList<Measurement>();
		for(Measurement m : measurements){
			long diff = newDate.getTime()-m.getDate().getTime();
			if(diff>ms){
				toBeRemoved.add(m);
			}
		}
		toBeRemoved.forEach(x -> measurements.remove(x));
	}
	private boolean isRecent(Date newDate){
		if(measurements.size()==0) return false;
		return newDate.getTime() - measurements.get(measurements.size()-1).getDate().getTime()<2.5*60*1000;
	}
	public int total(){
		return measurements.size();
	}
	public void status(Date from, Date to){
		measurements.sort(Comparator.naturalOrder());
		float temp = 0;
		int sizeSample = 0;
		for(Measurement m : measurements){
			if(m.getDate().compareTo(from)!=-1 && m.getDate().compareTo(to)!=1){
				temp+=m.getTemperature();
				System.out.println(m.toString());
				sizeSample++;
			}
		}
		if(sizeSample==0) throw new RuntimeException();
		System.out.println(String.format("Average temperature: %.2f",temp/sizeSample));
	}
}

class Measurement implements Comparable<Measurement>{
	private float temperature;
	private float wind;
	private float humidity;
	private float visibility;
	private Date date;

	public Measurement(float temperature, float wind, float humidity, float visibility, Date date) {
		this.temperature = temperature;
		this.wind = wind;
		this.humidity = humidity;
		this.visibility = visibility;
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public float getTemperature() {
		return temperature;
	}

	@Override
	public int compareTo(Measurement o) {
		return date.compareTo(o.date);
	}

	@Override
	public String toString() {
		return temperature +" "+ wind + " km/h " +humidity + "% "+ visibility +" km "+ date.toString();
	}
}