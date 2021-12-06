import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

//		Група анаграми
//		Да се напише програма која од дадена листа со зборови (секој збор е во нов ред) ќе ги најде групите со пет или повеќе анаграми (анаграм е збор составен од истите букви). Откако ќе ги најде групите треба да се отпечататат на стандарден излез сортирани според азбучен ред и тоа секоја група од анаграми во нов ред, а анаграмите одделени со празно место (внимавајте да нема празно место на крајот од редот). Редоследот на печатење на групите од анаграми е соодветен на редоследот на зборовите кои дошле на влез како први преставници на соодветната група од анаграми.

public class Anagrams {

	public static void main(String[] args) {
		findAll(System.in);
	}
    
    public static void findAll(InputStream inputStream) {
		TreeMap<String, TreeSet<String>> words = new TreeMap<String, TreeSet<String>>();
		BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
		bf.lines().forEach(t ->{
			boolean addnew = true;
			for(String x : words.keySet()) {
				char[] a = x.toCharArray();
				char[] b = t.toCharArray();
				Arrays.sort(a);
				Arrays.sort(b);
				if(Arrays.equals(a,b)){
					TreeSet<String> st = words.get(x);
					st.add(t);
					words.put(x,st);
					addnew = false;
					break;
				}
			}
			if(addnew){
				TreeSet<String> st = new TreeSet<String>();
				st.add(t);
				words.put(t,st);
			}
		});
		StringBuilder sb = new StringBuilder();

		for(String key : words.keySet()){
			if(words.get(key).size() >= 5){
				sb.append(String.join(" ", words.get(key)));
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
}
