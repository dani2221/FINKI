package mk.ukim.finki.aud7.benford;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class BenfordLawTest {
    public static String INPUT_FILE = "library_books.txt";

    public int[] counts(List<Integer> numbers) {
        int[] result = new int[10];
        for (Integer number : numbers) {
            int digit = firstDigit(number);
            result[digit]++;
        }
        return result;
    }

    public int[] countsFunc(List<Integer> numbers) {
        return numbers.stream()
                .map(BenfordLawTest::firstDigit)
                .map(x -> {
                    int[] res = new int[10];
                    res[x]++;
                    return res;
                })
                .reduce(new int[10], (left, right) -> {
                    Arrays.setAll(left, i -> left[i] + right[i]);
                    return left;
                });
    }

    static int firstDigit(int num) {
        while (num >= 10) {
            num /= 10;
        }
        return num;
    }

    public static void main(String[] args) throws FileNotFoundException {
        NumbersReader numbersReader = new LineNumbersReader();
        List<Integer> numbers = numbersReader.read(new FileInputStream(INPUT_FILE));
        BenfordLawTest benfordLawTest = new BenfordLawTest();
        CountVisualizer visualizer = new CountVisualizer(100);
        visualizer.visualize(System.out, benfordLawTest.counts(numbers));
    }
}
