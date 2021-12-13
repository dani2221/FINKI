import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//		Лозинки Problem 1
//		Потребно е да се симулира најава на еден систем. Притоа корисникот внесува корисничко име и лозинка. Доколку корисничкото име одговара со лозинката тогаш се печати Najaven, доколку не одговара се печати Nenajaven и на корисникот му се дава повторна шанса на корисникот да внесе корисничко име и лозинка. Во моментот кога корисникот ќе биде најавен престануваат обидите за најава.
//
//		Влез: Прво се дава број N на кориснички имиња и лозинки кои ќе бидат внесени во системот. Во наредните N реда се дадени корисничките имиња и лозинки разделени со едно празно место. Потоа се даваат редови со кориснички имиња и лозинки на корисници кои се обидувата да се најават (Пр. ana banana) За означување на крај на обидите во редицата се дава зборот KRAJ
//
//		Излез: За секој од влезовите кои се обид за најава се печати Nenajaven се додека не дoбиеме Najaven или додека имаме обиди за најава.
//
//		Пример. Влез: 3 ana banana pero zdero trpe trpi ana ana ana banana trpe trpi KRAJ
//
//		Излез: Nenajaven Najaven
//
//		Забелешка: Работете со хеш табела со затворени кофички. Самите решавате за големината на хеш табела, а хеш функцијата ви е дадена.

// uncomment this ->

//public class Lozinki {
//	public static void main (String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int N = Integer.parseInt(br.readLine());
//
//		CBHT<String,String> tbl = new CBHT<String, String>(10);
//		for(int i=1;i<=N;i++){
//			String imelozinka = br.readLine();
//			String[] pom = imelozinka.split(" ");
//			tbl.insert(pom[0],pom[1]);
//		}
//		while (true){
//			String vnes = br.readLine();
//			if(vnes.equals("KRAJ")) break;
//			String[] pom = vnes.split(" ");
//			if(tbl.search(pom[0])==null){
//				System.out.println("Nenajaven");
//				continue;
//			}
//			if(!tbl.search(pom[0]).element.value.equals(pom[1])) System.out.println("Nenajaven");
//			else {
//				System.out.println("Najaven");
//				break;
//			}
//		}
//
//	}
//}
//
//class CBHT<K extends Comparable<K>, E> {
//
//	// An object of class CBHT is a closed-bucket hash table, containing
//	// entries of class MapEntry.
//	private SLLNode<MapEntry<K,E>>[] buckets;
//
//	@SuppressWarnings("unchecked")
//	public CBHT(int m) {
//		// Construct an empty CBHT with m buckets.
//		buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
//	}
//
//	private int hash(K key) {
//		// Translate key to an index of the array buckets.
//		return Math.abs(key.hashCode()) % buckets.length;
//	}
//
//	public SLLNode<MapEntry<K,E>> search(K targetKey) {
//		// Find which if any node of this CBHT contains an entry whose key is
//		// equal
//		// to targetKey. Return a link to that node (or null if there is none).
//		int b = hash(targetKey);
//		for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
//			if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
//				return curr;
//		}
//		return null;
//	}
//
//	public void insert(K key, E val) {		// Insert the entry <key, val> into this CBHT.
//		MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
//		int b = hash(key);
//		for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
//			if (key.equals(((MapEntry<K, E>) curr.element).key)) {
//				// Make newEntry replace the existing entry ...
//				curr.element = newEntry;
//				return;
//			}
//		}
//		// Insert newEntry at the front of the 1WLL in bucket b ...
//		buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
//	}
//
//	public void delete(K key) {
//		// Delete the entry (if any) whose key is equal to key from this CBHT.
//		int b = hash(key);
//		for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
//			if (key.equals(((MapEntry<K,E>) curr.element).key)) {
//				if (pred == null)
//					buckets[b] = curr.succ;
//				else
//					pred.succ = curr.succ;
//				return;
//			}
//		}
//	}
//
//	public String toString() {
//		String temp = "";
//		for (int i = 0; i < buckets.length; i++) {
//			temp += i + ":";
//			for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
//				temp += curr.element.toString() + " ";
//			}
//			temp += "\n";
//		}
//		return temp;
//	}
//
//}
//class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {
//
//	// Each MapEntry object is a pair consisting of a key (a Comparable
//	// object) and a value (an arbitrary object).
//	K key;
//	E value;
//
//	public MapEntry (K key, E val) {
//		this.key = key;
//		this.value = val;
//	}
//
//	public int compareTo (K that) {
//		// Compare this map entry to that map entry.
//		@SuppressWarnings("unchecked")
//		MapEntry<K,E> other = (MapEntry<K,E>) that;
//		return this.key.compareTo(other.key);
//	}
//
//	public String toString () {
//		return "<" + key + "," + value + ">";
//	}
//}
//class SLLNode<E> {
//	protected E element;
//	protected SLLNode<E> succ;
//
//	public SLLNode(E elem, SLLNode<E> succ) {
//		this.element = elem;
//		this.succ = succ;
//	}
//
//	@Override
//	public String toString() {
//		return element.toString();
//	}
//}


