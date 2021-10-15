package mk.ukim.finki.aud4;

import java.util.Random;
import java.util.function.*;

public class FunctionalInterfacesTest {

    public static void main(String[] args) {
        //Supplier -> ne prima argumenti, samo vrakja nekakov rezultat
        //anonimna klasa
        Supplier<Integer> integerSupplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt(100);
            }
        };

        //lambda expression
        Supplier<Integer> integerSupplierLambda = () -> new Random().nextInt(100);

        //Consumer -> prima eden argument, ne vrakja rezultat
        //Java 8 -> .foreach(
        //anonimna klasa
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        };

        //lambda expression
        Consumer<Integer> integerConsumerLambda = integer -> System.out.println(integer);

        //Predicate -> prima eden argument, vrakja boolean
        //Vo Java 8 se koristat kaj .filter .anyMatch .allMatch .findFirst
        //anonimna klasa
        Predicate<Integer> lessThenTen = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer<10;
            }
        };

        //lambda expression
        Predicate<Integer> lessThenTenLambda = integer -> integer<10;

        //Function -> prima eden argument, a vrakja nekoj drug argument
        // .map
        //anonimna klasa
        Function<Integer, Integer> addFiveToTheNumbers = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer+5;
            }
        };

        //lambda expression
        Function<Integer, Integer> addFiveToTheNumbersL = integer -> integer+5;

        //BiFunction -> prima 2 argumenti, a vrakja eden izlez
        //anonimna klasa
        BiFunction<Integer, Integer, String> biFunction = new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer integer, Integer integer2) {
                return String.valueOf(integer % integer2);
            }
        };

        //lambda expression
        BiFunction<Integer, Integer, String> biFunctionL = (integer, integer2) -> String.valueOf(integer % integer2);




    }
}
