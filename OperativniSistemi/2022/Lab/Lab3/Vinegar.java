import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class Vinegar {
    static Semaphore carbon = new Semaphore(2);
    static Semaphore hydrogen = new Semaphore(4);
    static Semaphore oxygen = new Semaphore(2);
    static Semaphore hydrogenHere = new Semaphore(0);
    static Semaphore oxygenHere = new Semaphore(0);
    static int carbonCount = 0;
    static Semaphore carbonCountLock = new Semaphore(1);
    static Semaphore canBond = new Semaphore(0);
    static Semaphore hydrogenDone = new Semaphore(0);
    static Semaphore oxygenDone = new Semaphore(0);
    static Semaphore canCreateNext = new Semaphore(8);

    public static void main(String[] args) {
        HashSet<Thread> threads = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            threads.add(new C());
            threads.add(new H());
            threads.add(new H()); 
            threads.add(new O());
        }
        // run all threads in background
        for (Thread th : threads){
            th.start();
        }

        // after all of them are started, wait each of them to finish for maximum 2_000 ms
        for (Thread th : threads){
            try {
                th.join(2000);
            } catch (InterruptedException e) {
                System.out.println("Possible deadlock!");
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Process finished.");
    }

    static class C extends Thread{
        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void execute() throws InterruptedException {
            // at most 2 atoms should print this in parallel
            carbon.acquire();
            canCreateNext.acquire();
            System.out.println("C here.");
            carbonCountLock.acquire();
            carbonCount++;
            if(carbonCount==2){
                carbonCount = 0;
                oxygenHere.acquire(2);
                hydrogenHere.acquire(4);
                canBond.release(8);
            }
            carbonCountLock.release();
            // after all atoms are present, they should start with the bonding process together
            canBond.acquire();
            System.out.println("Molecule bonding.");
            Thread.sleep(100);// this represent the bonding process
            System.out.println("C done.");
            carbonCountLock.acquire();
            carbonCount++;
            if(carbonCount==2){
                carbonCount = 0;
                oxygenDone.acquire(2);
                hydrogenDone.acquire(4);
                System.out.println("Molecule created.");
                canCreateNext.release(8);
            }
            carbonCountLock.release();
            carbon.release();
        }
    }

    static class H extends Thread{
        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void execute() throws InterruptedException {
            // at most 4 atoms should print this in parallel
            hydrogen.acquire();
            canCreateNext.acquire();
            System.out.println("H here.");
            hydrogenHere.release();
            // after all atoms are present, they should start with the bonding process together
            canBond.acquire();
            System.out.println("Molecule bonding.");
            Thread.sleep(100);// this represent the bonding process
            System.out.println("H done.");
            hydrogenDone.release();
            hydrogen.release();
        }
    }

    static class O extends Thread{
        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void execute() throws InterruptedException {
            // at most 2 atoms should print this in parallel
            oxygen.acquire();
            canCreateNext.acquire();
            System.out.println("O here.");
            oxygenHere.release();
            // after all atoms are present, they should start with the bonding process together
            canBond.acquire();
            System.out.println("Molecule bonding.");
            Thread.sleep(100);// this represent the bonding process
            System.out.println("O done.");
            oxygenDone.release();
            oxygen.release();
        }
    }

}
