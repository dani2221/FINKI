import java.io.BufferedReader;
import java.io.InputStreamReader;

//      Аритметички израз
//      Даден е некој аритметички израз. Аритметичкиот израз е во облик (A+B) или (A-B) каде што А и B истовремено се други аритметички изрази или цифри од 0-9. Потребно е да го евалуирате дадениот израз.
//      Име на класата (Java): ArithmeticExpression

public class ArithmeticExpression {
    
    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int presmetaj(char c[], int l, int r) {
        int open = 0;
        int closed = 0;
        int middle = 0;
        for(int i= l+1;i<=r;i++){
            if(c[i]=='(') open++;
            if(c[i]==')') closed++;
            if(open==closed){
                middle = i+1;
                break;
            }
        }
        if(c[l+1]=='(' && c[middle+1]=='('){
            if(c[middle]=='+') return presmetaj(c,l+1,middle-1) + presmetaj(c,middle+1,r-1);
            else return presmetaj(c,l+1,middle-1) - presmetaj(c,middle+1,r-1);
        }
        else if(c[l+1]=='('){
            if(c[middle]=='+') return presmetaj(c,l+1,middle-1) + Integer.parseInt(String.valueOf(c[middle+1]));
            else return presmetaj(c,l+1,middle-1) - Integer.parseInt(String.valueOf(c[middle+1]));
        }
        else if(c[middle+1]=='('){
            if(c[middle]=='+') return Integer.parseInt(String.valueOf(c[l+1])) + presmetaj(c,middle+1,r-1);
            else return  Integer.parseInt(String.valueOf(c[l+1])) - presmetaj(c,middle+1,r-1);
        }
        else{
            if(c[middle]=='+') return Integer.parseInt(String.valueOf(c[l+1])) + Integer.parseInt(String.valueOf(c[middle+1]));
            else return  Integer.parseInt(String.valueOf(c[l+1])) - Integer.parseInt(String.valueOf(c[middle+1]));
        }
    }
    
    public static void main(String[] args) throws Exception {
        int i,j,k;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String expression = br.readLine();
        char exp[] = expression.toCharArray();
        
        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);
        
        br.close();
        
    }
    
}
