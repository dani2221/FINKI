import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

//		Фреквенција на зборови Problem 4 (0 / 0)
//		Да се развие класа TermFrequency која ќе брои колку пати се јавува секој збор во даден текст (String). Притоа да не се прави разлика меѓу мали и големи букви и да се игнорираат интерпукциските знаци (запирка, точка).
//
//		Класата треба да ги има следните методи:
//
//		TermFrequency(InputStream inputStream, String[] stopWords) - конструктор кој како аргумент го прима влезниот тек од кој се чита текстот кој треба да се обработи и низа од зборови кои треба да се игнорираат (да не се бројат).
//		countTotal():int - вкупниот број на зборови во текстот.
//		countDistinct():int – вкупниот број на уникатни зборови.
//		mostOften(int k):List<String> - враќа листа која ги содржи k-те зборови кои најчесто се појавуваат во текстот подредени според бројот на појавување од најмногу до најмалку. Во случај на ист број на појавувања се подредуваат алфабетски.

public class TermFrequencyTest {
	public static void main(String[] args) throws FileNotFoundException {
		String[] stop = new String[] { "во", "и", "се", "за", "ќе", "да", "од",
				"ги", "е", "со", "не", "тоа", "кои", "до", "го", "или", "дека",
				"што", "на", "а", "но", "кој", "ја" };
		TermFrequency tf = new TermFrequency(System.in,
				stop);
		System.out.println(tf.countTotal());
		System.out.println(tf.countDistinct());
		System.out.println(tf.mostOften(10));
	}
}
// vasiot kod ovde
class TermFrequency {
	Map<String, Integer> words;
	int count;

	public TermFrequency(InputStream inputStream, String[] stopWords) {
		words = new TreeMap<>();
		count = 0;
		Scanner scanner = new Scanner(inputStream);
		while (scanner.hasNext()) {
			String word = scanner.next();
			word = word.toLowerCase().replace(',', '\0').replace('.', '\0').trim();
			if (!(word.isEmpty() || Arrays.asList(stopWords).contains(word))) {
				int br = words.computeIfAbsent(word, key -> 0);
				words.put(word, ++br);
				count++;
			}
		}
	}

	public int countTotal() {
		return count;
	}

	public int countDistinct() {
		return words.size();
	}

	public List<String> mostOften(int k) {
		return words.keySet().stream().sorted(Comparator.comparing(key -> words.get(key)).reversed())
				.collect(Collectors.toList()).subList(0, k);
	}
}
