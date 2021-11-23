import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//      Shaker (cocktail) сортирање Problem 2 (0 / 0)
//		Дадена е низа со N природни броеви. Треба да се сортира низата со помош на таканареченото shaker (cocktail) сортирање. Ова сортирање е варијација на сортирањето со меурчиња (bubble sort) со тоа што во секоја итерација низата се изминува два пати. Во првото поминување најмалиот елемент се поместува на почетокот на низата, а при второто најголемиот елемент се поместува на крајот. Во првиот ред од влезот даден е бројот на елементи во низата N, а во вториот ред се дадени броевите. На излез треба да се испечати низата по секое изминување во посебен ред.

public class ShakerSort {
	
	static void shakerSort(int a[], int n) {
		for (int i = 0; i < n - i; ++i) {
			boolean change = false;

			for (int j = n - i - 1; j > i; --j) {
				if (a[j] < a[j - 1]) {
					int tmp = a[j];
					a[j] = a[j - 1];
					a[j - 1] = tmp;
					change = true;
				}
			}
			for (int j = 0; j < n; ++j)
				System.out.print(a[j] + " ");
			System.out.println();

			for (int j = i + 1; j < n - i - 1; ++j) {
				if (a[j] > a[j + 1]) {
					int tmp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = tmp;
					change = true;
				}
			}
			for (int j = 0; j < n; ++j)
				System.out.print(a[j] + " ");
			System.out.println();

			if (!change)
				break;
		}
	}

	public static void main(String[] args) throws IOException{
		int i;
		BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in)); 
		String s = stdin.readLine();
		int n = Integer.parseInt(s);
		
		s = stdin.readLine();
		String [] pom = s.split(" ");
		int [] a = new int[n];
		for(i=0;i<n;i++)
			a[i]=Integer.parseInt(pom[i]);
		shakerSort(a,n);
	}
}