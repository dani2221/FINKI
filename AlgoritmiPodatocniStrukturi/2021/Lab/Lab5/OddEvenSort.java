import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//		Непарно парно сортирање
//		Дадена е низа со N природни броеви. Треба да се сортира низата така што во првиот дел од низата ќе бидат подредени непарните броеви од неа во растечки редослед, а во вториот дел парните броеви во опаѓачки редослед. Во првиот ред од влезот даден е бројот на елементи во низата N, а во вториот ред се дадени броевите. На излез треба да се испечати сортираната низа.
//		Име на класата: OddEvenSort

public class OddEvenSort {

	static boolean changePlaces(int a[], int firstIndex, int secondIndex){
		int temp = a[firstIndex];
		a[firstIndex] = a[secondIndex];
		a[secondIndex] = temp;
		return true;
	}
	
	static void oddEvenSort(int a[], int n)
	{
		while(true){
			boolean flag = false;
			for(int i=1; i<a.length;i++){
				if(a[i-1]%2==0 && a[i]%2==1){
					flag = changePlaces(a,i-1,i);

				}
				else if(a[i-1]%2==0 && a[i]%2==0){
					if(a[i-1]<a[i]){
						flag = changePlaces(a,i-1,i);
					}
				}
				else if(a[i-1]%2==1 && a[i]%2==1){
					if(a[i-1]>a[i]){
						flag = changePlaces(a,i-1,i);
					}
				}
			}
			if(!flag) break;
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
		oddEvenSort(a,n);
		for(i=0;i<n-1;i++)
			System.out.print(a[i]+" ");
		System.out.print(a[i]);
	}
}