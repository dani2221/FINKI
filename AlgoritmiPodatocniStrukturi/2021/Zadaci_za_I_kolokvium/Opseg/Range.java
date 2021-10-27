package Opseg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Range {

    static long sumOfDigits(long n){
        long digits = 0;
        while (n!=0){
            digits+=n%10;
            n/=10;
        }
        return digits;
    }

    static long proveri(long N, long A, long B) {
        //  x2+s(x)+200·x=N
        long aCalc = A*A+sumOfDigits(A)+200*A;
        long bCalc = B*B+sumOfDigits(B)+200*B;
        if(aCalc==N) return A;
        else if(bCalc==N) return B;
        else if(Math.abs(A-B)<2) return -1;
        long mid = (A + B) / 2;
        long temp = mid * mid + sumOfDigits(mid) + 200 * mid;
        if (temp > N)
            return proveri(N, A, mid);
        else if (temp < N)
            return proveri(N, mid, B);
        else return mid;
    }
    
    public static void main(String[] args) throws Exception {
        int i,j,k;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        long N = Long.parseLong(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        
        long res = proveri(N, A, B);
        System.out.println(res);
        
        br.close();
        
    }
    
}
