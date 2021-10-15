package mk.ukim.finki.aud4;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PalindromeTest {

    public static List<String> readWords (InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        return br.lines()
                .map(word -> word.toLowerCase())
                .filter(word -> isPalindrome(word))
                .collect(Collectors.toList());
    }

    public static boolean isPalindrome (String word) {
        String reverseWord = new StringBuilder().append(word).reverse().toString();
        return word.equals(reverseWord);
    }

    public static boolean isPalindromeBasic (String word) {
        for (int i=0;i<word.length()/2;i++) {
            if (word.charAt(i)!=word.charAt(word.length()-i-1))
                return false;
        }

        return true;
    }

    public static boolean isPalindromeStream (String word) {
        return IntStream.range(0,word.length())
                .allMatch(i -> word.charAt(i)==word.charAt(word.length()-i-1));
    }

    public static void main(String[] args) {
        File file = new File("/Users/stefanandonov/Documents/GitWorkspace/NP2020/src/mk/ukim/finki/aud4/words.txt");
        try {
            List<String> words = readWords(new FileInputStream(file));

            String maxLengthPalindrome = words.get(0);
            for (String word : words) {
                if (word.length() > maxLengthPalindrome.length())
                    maxLengthPalindrome = word;
            }

            System.out.println(maxLengthPalindrome);

            maxLengthPalindrome = words.stream()
                    .max(Comparator.comparingInt(w -> w.length()))
                    .get();

            System.out.println(maxLengthPalindrome);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
