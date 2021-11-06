package F1Race;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class F1Test {

	public static void main(String[] args) throws IOException {
		F1Race f1Race = new F1Race();
		f1Race.readResults(System.in);
		f1Race.printSorted(System.out);
	}

}

class F1Race {

	private List<Racer> rc;

	public F1Race() {
	}
	public void readResults(InputStream in) throws IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(in));
		rc = rd.lines().map(x -> new Racer(x)).collect(Collectors.toList());
		rd.close();
	}

	public void printSorted(OutputStream out){
		PrintWriter pw = new PrintWriter(new PrintStream(out));
		sort();
		for(int i=0;i<rc.size();i++){
			pw.println((i+1)+". "+rc.get(i));
		}
		pw.close();
	}
	public void sort(){
		rc.sort(Comparator.naturalOrder());
	}
}

class Racer implements Comparable<Racer> {
	private String name;
	private String bestTime;

	public Racer(String inputLine) {
		String[] contents = inputLine.split("\\s+");
		name = contents[0];
		int rawTime = Integer.MAX_VALUE;
		int bestIndex = -1;
		for(int i=1;i<contents.length;i++){
			int localRaw = getRawTime(contents[i]);
			if(localRaw<rawTime){
				rawTime = localRaw;
				bestIndex = i;
			}
		}
		bestTime = contents[bestIndex];
	}

	private int getRawTime(String time){
		String[] times = time.split(":");
		return Integer.parseInt(times[0])*60000+Integer.parseInt(times[1])*1000+Integer.parseInt(times[2]);
	}

	@Override
	public String toString() {
		String nameString = name;
		while (nameString.length()<10) nameString+=" ";
		String bestTimeString = bestTime;
		while (bestTimeString.length()<10) bestTimeString = " "+bestTimeString;
		return nameString + bestTimeString;
	}

	@Override
	public int compareTo(Racer o) {
		return Integer.compare(getRawTime(bestTime),o.getRawTime(o.bestTime));

	}
}