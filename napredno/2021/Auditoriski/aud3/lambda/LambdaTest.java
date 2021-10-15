package mk.ukim.finki.aud3.lambda;

public class LambdaTest {

    public static void main(String[] args) {
        FunctionalInterface functionalInterface1 = (a, b) -> a + b;
        System.out.println(functionalInterface1.doOperation(7, 4));

        FunctionalInterface functionalInterface2 = (a, b) -> a - b;
        System.out.println(functionalInterface2.doOperation(7, 4));

        FunctionalInterface functionalInterface3 = (a, b) -> a * b;
        System.out.println(functionalInterface3.doOperation(7, 4));

        FunctionalInterface functionalInterface4 = (a, b) -> a / b;
        System.out.println(functionalInterface4.doOperation(7, 4));
    }
}
