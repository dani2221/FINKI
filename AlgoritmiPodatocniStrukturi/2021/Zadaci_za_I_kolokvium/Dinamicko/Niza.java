package Dinamicko;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Niza {

    public static int niza(int[] n, int k) {
        int[] pomosna = Arrays.copyOf(n, n.length);
        for (int t = 2; t <= k; t++) {
            for (int i = pomosna.length - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    pomosna[i] = Math.max(pomosna[i], pomosna[j] + t * n[i]);
                }
            }
            Arrays.stream(pomosna).forEach(x -> System.out.print(x + " "));
            System.out.println();
        }
        return Arrays.stream(pomosna).max().getAsInt();


    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split("\\s+");
        int k = Integer.parseInt(br.readLine());
        int n[] = Arrays.stream(s).mapToInt(x -> Integer.parseInt(x)).toArray();
        System.out.println(niza(n, k));
    }

}