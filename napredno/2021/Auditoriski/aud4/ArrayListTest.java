package mk.ukim.finki.aud4;

import java.util.ArrayList;
import java.util.LinkedList;

public class ArrayListTest {

    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();

        integers.add(5);
        integers.add(6);
        integers.add(7);

        System.out.println(integers); // [5, 6, 7] elementite vo array listot da imaat toString preoptovareno

        integers.add(1, 8);

        System.out.println(integers);

        System.out.println(integers.size()); //golemina na array list
        System.out.println(integers.contains(9)); //false
        System.out.println(integers.indexOf(9)); //indeks na prvo pojavuvanje na 9ka
        System.out.println(integers.contains(8)); //true
        System.out.println(integers.indexOf(8)); //indeksot na prvoto pojavuvanje na 8ca
        System.out.println(integers.get(2)); //integers[2]
        //System.out.println(integers.indexOf(8));
        System.out.println(integers.removeIf(x -> x>6));
        System.out.println(integers);


        ArrayList<Integer> newArrayList = new ArrayList<>();
//        newArrayList.add(7);
//        newArrayList.add(6);
//        newArrayList.addAll(integers);
//        System.out.println(newArrayList);

        long start = System.currentTimeMillis();;
        for (int i=0;i<100000000;i++) {
            newArrayList.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println(newArrayList.size());
        System.out.println(end - start);

        LinkedList<Integer> linkedList = new LinkedList<>();
        start = System.currentTimeMillis();
        for (int i=0;i<100000000;i++) {
            linkedList.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
