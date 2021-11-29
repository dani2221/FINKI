package Chocolate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Chocolate {

    public static int chocolate(int n) {
        int[] nacini = new int[n + 1];
        Arrays.fill(nacini, 0);
        nacini[0] = 0;
        nacini[1] = 1;
        nacini[2] = 2;
        for (int i = 3; i <= n; i++) {
            nacini[i] = nacini[i - 1] + nacini[i - 2];
        }
        return nacini[n];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(chocolate(n));
    }

}