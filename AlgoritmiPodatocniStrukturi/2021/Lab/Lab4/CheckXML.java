import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Stack;

//		Модифициран XML код
//		Даден е некој модифициран XML код. Модифицираниот XML код ги користи симболите '[' и ']', за отварање и затворање на таг, соодветно, наместо стандардните '
//
//		' и '>'. Треба да се провери дали сите тагови во кодот се правилно вгнездени (дали кодот е валиден) т.е. дали секој отворен таг има соодветен затворен таг со истото име на соодветното место во кодот. За поедноставување, дадено е дека секој отворен таг мора да има свој затворен таг и дека таговите немаат атрибути.
//
//		На влез е даден бројот на редови во кодот и самиот XML со секој таг во посебен ред, а на излез треба да се испечати 1 или 0 за валиден или невалиден код, соодветно.
//
//		Објаснување: Во модифицираниот XML код секој отворен таг е во облик [imeNaTag], а соодветниот затворен таг е во облик [/imeNaTag].

public class CheckXML {	
   
	public static void main(String[] args) throws Exception{
          
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        String s = br.readLine();
		int n = Integer.parseInt(s);
		String [] redovi = new String[n];
	
		for(int i=0;i<n;i++)
			redovi[i] = br.readLine();
       
		int valid = 1 ;
    	
        // Vasiot kod tuka
        // Moze da koristite dopolnitelni funkcii ako vi se potrebni
		Stack<String> stek = new Stack<String>();
		for(String red : redovi){
			if(red.charAt(1)!='/' && red.charAt(0)=='[') stek.push(red);
			else{
				if(red.charAt(0)!='[') continue;
				try {
					if (!stek.pop().substring(1).equals(red.substring(2))) valid = 0;
				}catch (Exception e){
					valid = 0;
				}
			}
		}
        
        System.out.println(valid);
        
        br.close();
	}
}