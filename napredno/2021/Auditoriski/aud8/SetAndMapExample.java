package mk.ukim.finki.aud8;

import java.util.*;
import java.util.function.Function;

public class SetAndMapExample {



    public static void main(String[] args) {
        //Sets
        HashSet<String> hashSet = new HashSet<>();
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        TreeSet<String> treeSet = new TreeSet<>(Comparator.comparing(String::length)
                .thenComparing(t -> t));

        hashSet.add("123142");
        hashSet.add("Stefan");
        hashSet.add("Lazo");
        hashSet.add("Cacko");

        linkedHashSet.add("123142");
        linkedHashSet.add("Stefan");
        linkedHashSet.add("Lazo");
        linkedHashSet.add("Cacko");

        treeSet.add("123142");
        treeSet.add("Stefan");
        treeSet.add("Lazo");
        treeSet.add("Cacko");


//        System.out.println(hashSet);
//        System.out.println(linkedHashSet);
//        System.out.println(treeSet);

        //Maps
        HashMap<String,String> hashMap = new HashMap<>();

        hashMap.put("123142", "12345678");
        hashMap.put("Stefan", "finki1234*");
        hashMap.put("Lazo", "idamkajtebe");
        hashMap.put("Cacko", "kapetnajs");

        System.out.println(hashMap);

        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap.put("123142", "12345678");
        linkedHashMap.put("Stefan", "finki1234*");
        linkedHashMap.put("Lazo", "idamkajtebe");
        linkedHashMap.put("Cacko", "kapetnajs");

        System.out.println(linkedHashMap);

        TreeMap<String,String> treeMap = new TreeMap<>();

        treeMap.put("123142", "12345678");
        treeMap.put("Stefan", "finki1234*");
        treeMap.put("Lazo", "idamkajtebe");
        treeMap.put("Cacko", "kapetnajs");

        System.out.println(treeMap);



    }
}
