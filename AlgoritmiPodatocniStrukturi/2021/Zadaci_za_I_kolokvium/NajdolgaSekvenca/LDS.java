package NajdolgaSekvenca;

import java.util.Arrays;
import java.util.Scanner;


public class LDS {

	private static int najdolgaOpagackaSekvenca(int[] a) {
		// so dinamicko programiranje

		int[] najdolgi = new int[a.length];
		Arrays.fill(najdolgi,1);

		for(int i=1; i<a.length; i++){
			for(int j=0; j<i; j++){
				if(a[i]<a[j] && najdolgi[i]<najdolgi[j]+1){
					najdolgi[i] = najdolgi[j]+1;
				}
			}
		}
        return Arrays.stream(najdolgi).max().getAsInt();
	}
	
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		
		int n = stdin.nextInt();
		int a[] = new int[n];
		for (int i = 0; i < a.length; i++) {
			a[i] = stdin.nextInt();
		}
		System.out.println(najdolgaOpagackaSekvenca(a));
	}


}
