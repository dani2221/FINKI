package mk.ukim.finki.aud9_kolokviumski_1.nGrams;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

public class NGrams {
    private List<String> texts;
    private Map<String, Integer> allNgramsMap;
    private List<Map<String, Integer>> textNgramsList;

    public NGrams() {
        this.texts = new ArrayList<>();
        this.allNgramsMap = new TreeMap<>();
        this.textNgramsList = new ArrayList<>();
    }

    public static List<String> getNGrams(String word) {
        List<String> nGrams = new ArrayList<>();
        for (int i = 1; i <= word.length(); i++) {
            for (int j = 0; j <= word.length() - i; j++) {
                nGrams.add(word.toLowerCase().substring(j, j + i));
            }
        }
        return nGrams;
    }

    private void fillMap(Map<String, Integer> map, String word) {
        map.putIfAbsent(word, 0);
        map.computeIfPresent(word, (key, value) -> {
            value++;
            return value;
        });
    }

    private void fillMaps() {
        this.allNgramsMap.keySet().forEach(word -> {
            this.textNgramsList.forEach(map -> {
                map.putIfAbsent(word, 0);
            });
        });
    }

    public void readTexts(InputStream is) {
        Scanner scanner = new Scanner(is);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            this.texts.add(line);
            line = line.replaceAll("[^A-Za-z0-9\\s+]", "");
            Map<String, Integer> textNgrams = new TreeMap<>();

            String[] words = line.split("\\s");
            Arrays.stream(words)
                    .map(NGrams::getNGrams)
                    .flatMap(Collection::stream)
                    .forEach(word -> {
                        fillMap(this.allNgramsMap, word);
                        fillMap(textNgrams, word);
                    });
            this.textNgramsList.add(textNgrams);
        }
        fillMaps();
    }

    public void printVectors(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);
        this.textNgramsList.forEach(pw::println);
        pw.flush();
    }

    public void printAllNGrams(OutputStream os, int n, String sortBy) {
        PrintWriter pw = new PrintWriter(os);
        // used only in if part
        // else part is another way of writing the same functionality
        Function<Map.Entry<String, Integer>, String> mapToString = e -> String.format("%s : %d", e.getKey(), e.getValue());
        Stream<Map.Entry<String, Integer>> filtered = this.allNgramsMap.entrySet()
                .stream().filter(entry -> entry.getKey().length() == n);

        if (sortBy.equals("n-grams")) {
            filtered.map(mapToString).forEach(pw::println);
        } else {
            this.allNgramsMap.entrySet()
                    .stream().filter(entry -> entry.getKey().length() == n)
                    .sorted(Map.Entry.comparingByValue())
                    .map(e -> String.format("%s : %d", e.getKey(), e.getValue()))
                    .forEach(pw::println);
        }
        pw.flush();
    }

    public void mostSimilarTexts(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);
        double maxSimilarity = 0;
        int iMax = 0, jMax = 0;
        for (int i = 0; i < texts.size(); i++) {
            for (int j = 0; j < texts.size(); j++) {
                if (i != j) {
                    double similarity =
                            CosineSimilarityCalculator.cosineSimilarity(
                                    textNgramsList.get(i).values(),
                                    textNgramsList.get(j).values()
                            );
                    if(similarity > maxSimilarity) {
                        maxSimilarity = similarity;
                        iMax = i;
                        jMax = j;
                    }
                }
            }
        }
        pw.println(texts.get(iMax));
        pw.println(texts.get(jMax));
        pw.println(String.format("%.10f", maxSimilarity));
        pw.flush();
    }
}
