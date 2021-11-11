import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//        Сума од апсолутни разлики (Тип колоквиумска задача)
//        Дадена е низа од N природни броеви и еден број K. Нека броевите се означени од a0 до aN−1. Да ја дефинираме сумата од апсолутни разлики како abs(a1−a0)+abs(a2−a1)+…+abs(aN−1−aN−2). Да се изберат точно K броеви од низата, така што кога ќе се спојат во една низа, сумата од апсолутни разлики е максимална. Да се испечати оваа сума.
//
//        Влез: Во првата линија ви се дадени два броеви N (1≤N≤100) и K (1≤K≤100, K≤N). Во втората линија ви се дадени N позитивни природни броеви, секој од броевите е помал од 1,000.
//
//        Излез: Да се испечати бараната максималната сума од апсолутни разлики.
//
//        Забелешка: Броевите се земаат во оној редослед во кој што се дадени во првата низа. Не смее да се менува редоследот на броевите во новодобиената низа.
//
//        Име на класата (Java): SumOfAbsoluteDifferences.
//
//        Делумно решение: Задачата се смета за делумно решена доколку се поминати 5 тест примери.

public class SumOfAbsoluteDifferences {

    // nadezno deka ke nema vakva na kolokvium :)

    private static int solve(int numbers[], int N, int K) {
        // vasiot kod ovde
        // mozete da napisete i drugi funkcii dokolku vi se potrebni
        int[][] best = new int[K + 1][N + 1];
        for (int i = 2; i <= K; ++i)
            for (int j = i - 1; j < N; ++j)
                for (int k = j - 1; k >= i - 2; --k)
                    best[i][j] = Math.max(best[i][j], best[i - 1][k] + Math.abs(numbers[k] - numbers[j]));
        int max = 0;
        for (int i = K - 1; i < N; ++i)
            max = Math.max(max, best[K][i]);
        return max;
    }
    
    public static void main(String[] args) throws Exception {
        int i,j,k;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int numbers[] = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for (i=0;i<N;i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        
        int res = solve(numbers, N, K);
        System.out.println(res);
        
        br.close();
        
    }
    
}