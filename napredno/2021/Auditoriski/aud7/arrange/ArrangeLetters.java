package mk.ukim.finki.aud7.arrange;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrangeLetters {
    public static String arrange(String input) {
        String[] parts = input.split("\\s+");

        for (int i = 0; i < parts.length; i++) {
            char[] tmp = parts[i].toCharArray();
            Arrays.sort(tmp);
            parts[i] = new String(tmp);
        }
        return Arrays.stream(parts)
                .sorted().collect(Collectors.joining(" "));
    }

    public static String arrangeFunctional1(String input) {
        String[] parts = input.split("\\s+");

        return Arrays.stream(parts).map(part -> {
            char[] tmp = part.toCharArray();
            Arrays.sort(tmp);
            return new String(tmp);
        }).sorted().collect(Collectors.joining(" "));
    }

    public static String arrangeFunctional2(String input) {
        String[] parts = input.split("\\s+");

        return IntStream.range(0, parts.length)
                .mapToObj(i -> {
                    char[] tmp = parts[i].toCharArray();
                    Arrays.sort(tmp);
                    return new String(tmp);
                }).sorted().collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        System.out.println(arrange(input));
        System.out.println(arrangeFunctional1(input));
        System.out.println(arrangeFunctional2(input));
    }
}
