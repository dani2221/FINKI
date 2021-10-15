package mk.ukim.finki.aud9_kolokviumski_1.nGrams;

public class NGramsTest {
    public static void main(String[] args) {
        NGrams nGrams = new NGrams();

        nGrams.readTexts(System.in);

        System.out.println("===PRINT VECTORS===");
        nGrams.printVectors(System.out);

        System.out.println("PRINT 3-grams SORTED BY FREQUENCY");
        nGrams.printAllNGrams(System.out, 3, "value");

        System.out.println("PRINT 1-grams SORTED BY FREQUENCY");
        nGrams.printAllNGrams(System.out, 1, "n-grams");

        System.out.println("===MOST SIMILAR TEXTS===");
        nGrams.mostSimilarTexts(System.out);
    }
}

