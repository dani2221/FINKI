package CoinProblem;

import java.util.Arrays;
import java.util.Scanner;

public class PrvKolokvium2015 {
    public static int presmetaj(int[] niza) {
        return 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] line = in.nextLine().split("\\s+");
        int[] niza = Arrays.stream(line).mapToInt(x -> Integer.parseInt(x)).toArray();
        System.out.println(presmetaj(niza));
    }
}
