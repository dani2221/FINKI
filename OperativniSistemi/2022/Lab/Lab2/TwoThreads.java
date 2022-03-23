import java.util.Arrays;
import java.util.List;

public class TwoThreads {


public static void main(String[] args) throws InterruptedException {
    Character[] bukvi = new Character[]{'A','B','C','D','E','F','G','H','I','J'};
    Character[] brojki = new Character[]{'0','1','2','3','4','5','6','7','8','9'};
    ThreadClassLettersNumbers letters = new ThreadClassLettersNumbers(Arrays.asList(bukvi));
    ThreadClassLettersNumbers numbers = new ThreadClassLettersNumbers(Arrays.asList(brojki));
    letters.runThread();
    numbers.runThread();
}


}

class ThreadClassLettersNumbers {
    private List<Character> chars;

    public ThreadClassLettersNumbers(List<Character> chars) {
        this.chars = chars;
    }

    public void runThread() throws InterruptedException {
        Thread t1 = new Thread(() -> chars.forEach(x -> System.out.println(x)));
        t1.start();
        t1.join();
    }
}