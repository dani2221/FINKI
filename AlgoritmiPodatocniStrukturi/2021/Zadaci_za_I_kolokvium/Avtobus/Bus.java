package Avtobus;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bus {
    
    public static void main(String[] args) throws Exception {
        int i,j,k;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        
        br.close();
        
        // Vasiot kod tuka
        int min = (M-N)>0 ? 100*N+100*(M-N) : 100*N;
        int max = (M-1)>=0 ? (M-1)*100+N*100 : 100*N;
        System.out.println(min);
        System.out.println(max);

        
        
    }
    
}
