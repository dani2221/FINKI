import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class BasketballTournament {

    public static void main(String[] args) {
        HashSet<Player> threads = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            Player p = new Player();
            threads.add(p);
        }
        // run all threads in background
        for(Player player : threads){
            player.start();
        }

        // after all of them are started, wait each of them to finish for maximum 5_000 ms
        for(Player player : threads) {
            try {
                player.join(50000);
            } catch (InterruptedException e) {
                System.out.println("Possible deadlock!");
                System.out.println("Tournament finished.");
                Thread.currentThread().interrupt();
            }
        }

    }
}

class Player extends Thread {
    static Semaphore sala = new Semaphore(20);
    static Semaphore garderoba = new Semaphore(10);
    static int ready = 0;
    static Semaphore readyLock = new Semaphore(1);
    static Semaphore canEnter = new Semaphore(0);

    public void execute() throws InterruptedException {
        // at most 20 players should print this in parallel
        sala.acquire();
        System.out.println("Player inside.");
        // at most 10 players may enter in the dressing room in parallel
        garderoba.acquire();
        System.out.println("In dressing room.");
        Thread.sleep(10);// this represent the dressing time
        garderoba.release();
        readyLock.acquire();
        ready++;
        if(ready==20){
            canEnter.release(20);
            ready = 0;
        }
        readyLock.release();
        // after all players are ready, they should start with the game together
        canEnter.acquire();
        System.out.println("Game started.");
        Thread.sleep(100);// this represent the game duration
        System.out.println("Player done.");
        readyLock.acquire();
        ready++;
        // only one player should print the next line, representing that the game has finished
        if(ready==20) {
            System.out.println("Game finished.");
            ready = 0;
            sala.release(20);
        }
        readyLock.release();
    }

    @Override
    public void run() {
        try {
            execute();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
