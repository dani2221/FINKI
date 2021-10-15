package mk.ukim.finki.aud8;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class BirthdayParadoxTest {

    public static boolean singleTrial (int person) {
        Random random = new Random();
        Set<Integer> birthdays = new HashSet<>();
        for (int i=0;i<person;i++) {
            int rdmBirthday = random.nextInt(364)+1;
            if (birthdays.contains(rdmBirthday))
                return true;
            birthdays.add(rdmBirthday);
        }
        return false;
    }

    public static double experiment (int person) {
        int counter = 0;
//        return IntStream.range(0,5000)
//                .filter(i -> singleTrial(person))
//                .count()/5000.0;
        for (int i=0;i<10000;i++) {
            if (singleTrial(person))
                ++counter;
        }
        return counter/10000.0;
    }

    public static void main(String[] args) {
        for (int i=2;i<=50;i++)
            System.out.printf("For %d people, the probability of two birthdays is about %.5f\n",
                    i,
                    experiment(i));
    }
}
