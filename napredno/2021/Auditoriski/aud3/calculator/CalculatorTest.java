package mk.ukim.finki.aud3.calculator;

import java.util.Scanner;

public class CalculatorTest {

    public static char getCharLower(String line) {
        if (line.trim().length() > 0) {
            return Character.toLowerCase(line.charAt(0));
        }
        return '?';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            Calculator calculator = new Calculator();
            System.out.println(calculator.init());

            while(true){
                String line = scanner.nextLine();
                char choice = getCharLower(line);
                if(choice == 'r'){
                    System.out.println(String.format("final result = %f", calculator.getResult()));
                    break;
                }
                String[] parts = line.split("\\s+");
                char operator = parts[0].charAt(0);
                double value = Double.parseDouble(parts[1]);
                try {
                    String result = calculator.execute(operator, value);
                    System.out.println(result);
                    System.out.println(calculator);
                } catch (UnknownOperatorException unknownOperatorException) {
                    System.out.println(unknownOperatorException.getMessage());
                }
            }
            System.out.println("(Y/N)");
            String line = scanner.nextLine();
            char choice = getCharLower(line);
            if(choice == 'n') break;
        }
    }
}
