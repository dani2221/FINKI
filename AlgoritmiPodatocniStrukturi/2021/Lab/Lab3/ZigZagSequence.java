import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

//      Цик цак секвенца Problem 2 (0 / 0)
//      Секвенца од броеви се смета за цик цак секвенца ако броевите во секвенцата се наизменично позитивни и негативни т.е. за секој пар од последователни броеви важи дека едниот е позитивен, а другиот е негативен.
//      На пример -1 2 -9 8 -4 е цик цак секвенца, но -1 9 7 -3 8 -3 не е, затоа што 9 и 7 се соседни броеви, но и двата се позитивни. Цик цак секвенцата може да почне или со позитивен или со негативен број. Секвенца од само еден ненулти број се смета како цик цак секвенца. За дадена низа од броеви да се напише алгоритам кој ќе ја врати должината на најдолгата подниза која претставува цик цак секвенца.
//      Во првиот ред од влезот даден е бројот N за должината на низата. Во секој од следните N редови е даден по еден број од оригиналната низа. На излез треба да се испечати должината на најдолгата подниза која е цик цак секвенца од оригиналната низа.
//      Име на класата: ZigZagSequence
public class ZigZagSequence {
    
    static int najdiNajdolgaCikCak(int a[]) {
		int[] memorija = new int[a.length];
        Arrays.fill(memorija,1);
		for(int i=1;i<a.length;i++){
		    if((a[i]>0 && a[i-1]<0) || (a[i]<0 && a[i-1]>0)){
		        memorija[i]=memorija[i-1]+1;
            }
        }
		return Arrays.stream(memorija).max().getAsInt();
	}
    
    public static void main(String[] args) throws Exception {
        int i,j,k;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i=0;i<N;i++)
            a[i] = Integer.parseInt(br.readLine());
        
        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);
        
        br.close();
       	
    }
    
}
