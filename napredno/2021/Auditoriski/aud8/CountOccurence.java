package mk.ukim.finki.aud8;

import java.util.Collection;

public class CountOccurence {

    public static int count(Collection<Collection<String>> c, String str) {
        int counter = 0;
        for (Collection<String> subc : c) {
            for (String s : subc) {
                if (s.equalsIgnoreCase(str))
                    ++counter;
            }
        }
        return counter;
    }

    public static int countWithStream(Collection<Collection<String>> c, String str) {
//        return (int) c.stream().flatMap(sub -> sub.stream())
//                .filter(s -> s.equalsIgnoreCase(str))
//                .count();

        return c.stream().
                flatMapToInt(sub -> sub.stream().mapToInt(s -> s.equalsIgnoreCase(str) ? 1 :0))
                .sum();

//        return c.stream()
//                .mapToInt(sub -> sub.stream().mapToInt(s -> s.equalsIgnoreCase(str) ? 1 :0).sum())
//                .sum();


    }

    public static void main(String[] args) {

    }
}
