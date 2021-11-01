import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalDouble;

//      За дадена низа од N (1≤N≤50) природни броеви, да се најде бројот кој е најблиску до нивниот просек. Ако постојат два броја со исто растојание до просекот, да се врати помалиот од нив. На пример за низата 1, 2, 3, 4, 5 просекот е (1 + 2 + 3 + 4 + 5) / 5 = 15 / 5 = 3, што значи дека бројот кој треба да се врати и е најблиску до просекот е 3.
//
//		За низата 1, 2, 3, 4, 5, 6 просекот е 3.5 и двата броја 3 и 4 се на исто растојание од просекот. Точната вредност која треба да се врати е помалиот од нив, а тоа е 3.
//
//		Во низата може да има дупликати.
//
//		Првиот број од влезот е бројот на елементи во низата N, а потоа во секој ред се дадени броевите.
//
//		Име на класата (Java): Array
//
//		**Забелешка:** Да се креира податочна структура низа и истата да се искористи во задачата.

public class Array<E> {


	public static int brojDoProsek(int[] niza){
		int avg = (int) Arrays.stream(niza).average().getAsDouble();
		int minAbsDelta = Integer.MAX_VALUE;
		int minEl = -1;
		for (int el : niza){
			if(Math.abs(el-avg)<minAbsDelta){
				minAbsDelta = Math.abs(el-avg);
				minEl = el;
			}
			else if(Math.abs(el-avg)==minAbsDelta){
				if(el<minEl) minEl = el;
			}
		}
		return minEl;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in)); 
		String s = stdin.readLine();
		int N = Integer.parseInt(s);
        //Vashiot kod tuka...
		int[] niza = new int[N];
		for(int i=0;i<N;i++){
			String broj = stdin.readLine();
			niza[i] = Integer.parseInt(broj);
		}
        
        
		System.out.println(brojDoProsek(niza));		
	}
	
	

}
