package mk.ukim.finki.aud7.finalists;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Finalists {
    public static List<Integer> pick(int finalistsCount, int awards) {
        Random random = new Random();
        List<Integer> awarded = new ArrayList<>();

        while (awarded.size() != awards) {
            int awardedFinalist = random.nextInt(finalistsCount) + 1;
            if (!awarded.contains(awardedFinalist)) {
                awarded.add(awardedFinalist);
            }
        }
        return awarded;
    }

    public static void main(String[] args) {
        List<Integer> awarded = pick(30, 3);
        System.out.println(awarded);
    }
}
