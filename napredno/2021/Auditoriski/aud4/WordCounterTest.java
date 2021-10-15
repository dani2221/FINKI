package mk.ukim.finki.aud4;

import java.io.*;
import java.util.Scanner;
import java.util.function.Consumer;

class LineConsumer implements Consumer<String> {
    int lines = 0, words = 0, chars = 0;

    @Override
    public void accept(String s) {
        ++lines;
        words += s.split("\\s+").length;
        chars += s.length();
    }

    @Override
    public String toString() {
        return String.format("Lines: %d Words: %d Chars: %d", lines, words, chars);
    }
}

class LineCounter {
    int lines;
    int words;
    int chars;

    public LineCounter(int lines, int words, int chars) {
        this.lines = lines;
        this.words = words;
        this.chars = chars;
    }

    public LineCounter(String line) {
        this.lines = 1;
        this.words = line.split("\\s+").length;
        this.chars = line.length();
    }

    public LineCounter sum(LineCounter other) {
        return new LineCounter(
                this.lines + other.lines,
                this.words + other.words,
                this.chars + other.chars
        );
    }

    @Override
    public String toString() {
        return String.format("Lines: %d Words: %d Chars: %d", lines, words, chars);
    }
}

public class WordCounterTest {

    /*
    *
    1. Map (sekoja linija -> {l,w,c}
    2. Reduce (da gi sobereme site takvi {l,w,c} vo edno rezultantno {l_r, w_r, c_r}
    * */
    public static void calculateWithBufferedReaderAndMapReduce(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        LineCounter result = br.lines()
                .map(line -> new LineCounter(line))
                .reduce(
                        new LineCounter(0, 0, 0),
                        (left, right) -> left.sum(right)
                );
        System.out.println(result);
    }

    public static void calculateWithBufferedReaderAndConsumer(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        LineConsumer lineConsumer = new LineConsumer();
        br.lines().forEach(lineConsumer);
        System.out.println(lineConsumer);
    }

    public static void calculateWithBufferedReader(InputStream inputStream) throws IOException {
        int lines = 0, words = 0, chars = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = br.readLine()) != null) {
            ++lines;
            words += line.split("\\s+").length;
            chars += line.length();
        }

        System.out.printf("Lines: %d Words: %d Chars: %d", lines, words, chars);
    }

    public static void calculateWithScanner(InputStream inputStream) {
        int lines = 0, words = 0, chars = 0;
        Scanner sc = new Scanner(inputStream);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            ++lines;
            words += line.split("\\s+").length;
            chars += line.length();
        }

        System.out.printf("Lines: %d Words: %d Chars: %d", lines, words, chars);
    }

    public static void main(String[] args) {
        File file = new File("/Users/stefanandonov/Documents/GitWorkspace/NP2020/src/mk/ukim/finki/aud4/text");
        try {
            calculateWithBufferedReader(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
