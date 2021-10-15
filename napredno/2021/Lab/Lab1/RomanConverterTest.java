import java.util.Scanner;
import java.util.stream.IntStream;

// Да се напише метод кој ќе прима еден цел број и ќе ја печати неговата репрезентација како Римски број.
public class RomanConverterTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        IntStream.range(0, n)
                .forEach(x -> System.out.println(RomanConverter.toRoman(scanner.nextInt())));
        scanner.close();
    }
}


class RomanConverter {
    /**
     * Roman to decimal converter
     *
     * @param n number in decimal format
     * @return string representation of the number in Roman numeral
     */
    public static String toRoman(int n) {
        int level = 0;
        String finalString = "";
        while(n>0){
            int mod = n%10;
            if(level>=6) {
                String copyStr = "";
                for(int i=0;i<n;i++) {
                    copyStr+=getRomanNumFromLevel(level);
                }
                finalString = copyStr + finalString;
                return finalString;
            }
            else if(mod==9) {
                finalString = getRomanNumFromLevel(level) + getRomanNumFromLevel(level+2) + finalString;
            }
            else if(mod>=5) {
                String copyStr = "";
                for(int i=0;i<mod-5;i++) {
                    copyStr+=getRomanNumFromLevel(level);
                }
                finalString = getRomanNumFromLevel(level+1) + copyStr + finalString;
            }
            else if(mod==4) {
                finalString = getRomanNumFromLevel(level) + getRomanNumFromLevel(level+1) + finalString;
            }
            else {
                String copyStr = "";
                for(int i=0;i<mod;i++) {
                    copyStr+=getRomanNumFromLevel(level);
                }
                finalString = copyStr + finalString;
            }
            level+=2;
            n/=10;
        }
        return finalString;
    }

    private static String getRomanNumFromLevel(int level) {
        if(level==0) return "I";
        if(level==1) return "V";
        if(level==2) return "X";
        if(level==3) return "L";
        if(level==4) return "C";
        if(level==5) return "D";
        return "M";
    }

}
