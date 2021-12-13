import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//Аптека Problem 4
//Потребно е да се направи компјутерска апликација со која ќе се забрза работењето на една аптека. Притоа апликацијата треба да му овозможи на корисникот (фармацевтот) брзо да пребарува низ огромното множество со лекови кои се внесени во системот. Начинот на кој тој треба да пребарува е следен: доволно е да ги внесе првите 3 букви од името на лекот за да може да му се излиста листа од лекови кои ги има во системот. Работата на фармацевтот е да провери дали внесениот лек го има во системот и да му даде информација на клиентот. Информацијата што треба да му ја даде на клиентот е дали лекот се наоѓа на позитивната листа на лекови, која е цената и колку парчиња од лекот има на залиха. Доколку лекот постои клиентот го нарачува со што кажува колку парчиња ќе купи. Оваа акција фармацевтот треба да ја евидентира на системот (односно да ја намали залихата на лекови за онолку парчиња колку што му издал на клиентот). Доколку нарачката на клиентот е поголема од залихата на лекот што ја има во системот, не се презема никаква акција.
//
//Влез: Од стандарден влез прво се дава број N кој претставува број на лекови кои ќе бидат внесени во системот. Во наредните N реда се дадени имињата на лековите, дали ги има на позитивната листа (1/0), цената и број на парчиња, сите разделени со по едно празно место. Потоа се даваат редови со имиња на лекови и број на парчиња нарачани од клиентот. За означување на крај се дава зборот KRAJ.
//
//Излез: На стандарден излез треба да се испечати за секој од влезовите следната информација: IME POZ/NEG CENA BR_LEKOVI. Доколку лекот не е најден се печати Nema takov lek. Доколку нарачката на клиентот е поголема од залихата се печати Nema dovolno lekovi инаку Napravena naracka.
//
//Забелешка: Задачата да се реши со хeш табела. Функцијата со која се врши мапирање на имињата на лековите во број е следна: h(w)=(29∗(29∗(29∗0+ASCII(c1))+ASCII(c2))+ASCII(c3))%102780 каде зборот w=c1c2c3c4c5…. е составен од сите големи букви.
//
//Исто така за лековите да се направи посебна класа која како атрибути ќе ги има наведените карактеристики на лекот во системот.

// uncomment this ->

//public class Apteka {
//    public static void main(String[] args) throws IOException {
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(bf.readLine());
//        CBHT<String, List<Lek>> tbl = new CBHT<String, List<Lek>>(2*n);
//        for(int i=0;i<n;i++){
//            String[] split = bf.readLine().split("\\s+");
//            Lek k = new Lek(split[0].toUpperCase(),Integer.parseInt(split[1]),Integer.parseInt(split[2]),Integer.parseInt(split[3]));
//            if(tbl.search(split[0].substring(0,3)) == null){
//                List<Lek> lekovi = new ArrayList<Lek>();
//                lekovi.add(k);
//                tbl.insert(split[0].substring(0,3).toUpperCase(),lekovi);
//            }
//            else{
//                List<Lek> lekovi = tbl.search(split[0].substring(0,3)).element.value;
//                lekovi.add(k);
//                tbl.insert(split[0].substring(0,3).toUpperCase(),lekovi);
//            }
//        }
//        while (true){
//            String line = bf.readLine();
//            if(line.equals("KRAJ")) break;
//            String lek = line.toUpperCase();
//            if(tbl.search(lek.substring(0,3)) == null){
//                System.out.println("Nema takov lek");
//                bf.readLine();
//                continue;
//            }
//            boolean found = false;
//            for (Lek l : tbl.search(lek.substring(0, 3)).element.value) {
//                if(l.getName().equals(lek)){
//                    found = true;
//                    int broj = Integer.parseInt(bf.readLine());
//                    System.out.println(l);
//                    if(l.getBroj()<broj){
//                        System.out.println("Nema dovolno lekovi");
//                        continue;
//                    }
//                    l.setBroj(l.getBroj()-broj);
//                    System.out.println("Napravena naracka");
//                    break;
//                }
//            }
//            if(!found){
//                bf.readLine();
//                System.out.println("Nema takov lek");
//            }
//        }
//    }
//}
//
//class Lek{
//    private String name;
//    private int poz;
//    private int cena;
//    private int broj;
//
//    public void setBroj(int broj) {
//        this.broj = broj;
//    }
//
//    public Lek(String name, int poz, int cena, int broj) {
//        this.name = name;
//        this.poz = poz;
//        this.cena = cena;
//        this.broj = broj;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getPoz() {
//        return poz;
//    }
//
//    public int getCena() {
//        return cena;
//    }
//
//    public int getBroj() {
//        return broj;
//    }
//
//    @Override
//    public String toString() {
//        return name+"\n"+(poz==0?"NEG":"POZ")+"\n"+cena+"\n"+broj;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Lek lek = (Lek) o;
//        return poz == lek.poz &&
//                cena == lek.cena &&
//                broj == lek.broj &&
//                name.equals(lek.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return (29 * (29 * (29 * 0 + name.charAt(0)) + name.charAt(1)) % 102780 + name.charAt(2));
//    }
//}
//
//class CBHT<K extends Comparable<K>, E> {
//
//    // An object of class CBHT is a closed-bucket hash table, containing
//    // entries of class MapEntry.
//    private SLLNode<MapEntry<K,E>>[] buckets;
//
//    @SuppressWarnings("unchecked")
//    public CBHT(int m) {
//        // Construct an empty CBHT with m buckets.
//        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
//    }
//
//    private int hash(K key) {
//        // Translate key to an index of the array buckets.
//        return Math.abs(key.hashCode()) % buckets.length;
//    }
//
//    public SLLNode<MapEntry<K,E>> search(K targetKey) {
//        // Find which if any node of this CBHT contains an entry whose key is
//        // equal
//        // to targetKey. Return a link to that node (or null if there is none).
//        int b = hash(targetKey);
//        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
//            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
//                return curr;
//        }
//        return null;
//    }
//
//    public void insert(K key, E val) {		// Insert the entry <key, val> into this CBHT.
//        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
//        int b = hash(key);
//        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
//            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
//                // Make newEntry replace the existing entry ...
//                curr.element = newEntry;
//                return;
//            }
//        }
//        // Insert newEntry at the front of the 1WLL in bucket b ...
//        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
//    }
//
//    public void delete(K key) {
//        // Delete the entry (if any) whose key is equal to key from this CBHT.
//        int b = hash(key);
//        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
//            if (key.equals(((MapEntry<K,E>) curr.element).key)) {
//                if (pred == null)
//                    buckets[b] = curr.succ;
//                else
//                    pred.succ = curr.succ;
//                return;
//            }
//        }
//    }
//
//    public String toString() {
//        String temp = "";
//        for (int i = 0; i < buckets.length; i++) {
//            temp += i + ":";
//            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
//                temp += curr.element.toString() + " ";
//            }
//            temp += "\n";
//        }
//        return temp;
//    }
//
//}
//class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {
//
//    // Each MapEntry object is a pair consisting of a key (a Comparable
//    // object) and a value (an arbitrary object).
//    K key;
//    E value;
//
//    public MapEntry (K key, E val) {
//        this.key = key;
//        this.value = val;
//    }
//
//    public int compareTo (K that) {
//        // Compare this map entry to that map entry.
//        @SuppressWarnings("unchecked")
//        MapEntry<K,E> other = (MapEntry<K,E>) that;
//        return this.key.compareTo(other.key);
//    }
//
//    public String toString () {
//        return "<" + key + "," + value + ">";
//    }
//}
//class SLLNode<E> {
//    protected E element;
//    protected SLLNode<E> succ;
//
//    public SLLNode(E elem, SLLNode<E> succ) {
//        this.element = elem;
//        this.succ = succ;
//    }
//
//    @Override
//    public String toString() {
//        return element.toString();
//    }
//}

