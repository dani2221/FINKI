package mk.ukim.finki.aud3.lambda;

public class AnonymousClassExample {

    FunctionalInterface Addition = new FunctionalInterface() {
        @Override
        public double doOperation(double a, double b) {
            return a + b;
        }
    };

    public static void main(String[] args) {
        AnonymousClassExample anonymousClassExample = new AnonymousClassExample();
        System.out.println(anonymousClassExample.Addition.doOperation(5, 7));
    }
}
