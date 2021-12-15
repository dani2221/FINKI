package Audition;

import java.util.*;

public class AuditionTest {
	public static void main(String[] args) {
		Audition audition = new Audition();
		List<String> cities = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split(";");
			if (parts.length > 1) {
				audition.addParticpant(parts[0], parts[1], parts[2],
						Integer.parseInt(parts[3]));
			} else {
				cities.add(line);
			}
		}
		for (String city : cities) {
			System.out.printf("+++++ %s +++++\n", city);
			audition.listByCity(city);
		}
		scanner.close();
	}
}

class Participant{
	private String code;
	private String city;
	private String name;
	private int age;

	public Participant(String code, String city, String name, int age) {
		this.code = code;
		this.city = city;
		this.name = name;
		this.age = age;
	}

	public String getCode() {
		return code;
	}

	public String getCity() {
		return city;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Participant that = (Participant) o;
		return code.equals(that.code);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public String toString() {
		return code+" "+name+" "+age;
	}
}

class Audition{
	private HashMap<String, HashSet<Participant>> participants;

	public Audition() {
		participants = new HashMap<String, HashSet<Participant>>();
	}

	public void addParticpant(String part, String part1, String part2, int parseInt) {
		participants.compute(part,(k,v)->{
			Participant p = new Participant(part1,part,part2,parseInt);
			if(v==null){
				v = new HashSet<Participant>();
				v.add(p);
			}else{
				if(!v.contains(p)){
					v.add(p);
				}
			}
			return v;
		});
	}

	public void listByCity(String city) {
		participants.get(city).stream()
				.sorted(Comparator.comparing(Participant::getName).thenComparing(Participant::getAge).thenComparing(Participant::getCode))
				.forEach(x -> System.out.println(x));
	}
}