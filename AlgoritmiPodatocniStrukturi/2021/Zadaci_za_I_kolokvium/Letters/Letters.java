package Letters;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Letters {
    static boolean isSamoglaska(char c) {
        return c == 'a' || c == 'i' || c == 'u' || c == 'o' || c == 'e';
    }

    static int solve(String S, int N, int K) {
        return 0;
    }

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int N = S.length();
        int K = Integer.parseInt(br.readLine());

        int res = solve(S, N, K);
        System.out.println(res);

        br.close();

    }
}