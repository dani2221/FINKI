import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountLetter {

    /**
     * Promenlivata koja treba da go sodrzi brojot na pojavuvanja na bukvata A
     */
    int count = 0;
    Semaphore semaphore;

    public void init() {
        semaphore = new Semaphore(1);
    }

    class Counter extends Thread {

        public void count(String data) throws InterruptedException {
            for(int i = 0; i< data.length(); i++) {
                if(data.charAt(i)=='E') {
                    semaphore.acquire();
                    count++;
                    semaphore.release();
                }
            }
        }
        private String data;

        public Counter(String data) {
            this.data = data;
        }

        @Override
        public void run() {
            try {
                count(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            CountLetter environment = new CountLetter();
            environment.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void start() throws Exception {

        init();

        HashSet<Thread> threads = new HashSet<Thread>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String pom = bf.readLine();
        String [] data = pom.split("");
        
        for(int i = 0; i< data.length; i++) {
            
            Counter c = new Counter(data[i]);
            threads.add(c);
        }


        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        System.out.println(count);


    }
}