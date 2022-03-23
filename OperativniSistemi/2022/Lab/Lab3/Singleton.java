import java.util.HashSet;

public class Singleton {

    private static volatile Singleton singleton;

    private Singleton() {

    }

    public static synchronized Singleton getInstance() {
        if(singleton == null) singleton = new Singleton();
        return singleton;
    }

    public static void main(String[] args) throws InterruptedException {
        HashSet<Thread> threads = new HashSet<>();
        for(int i = 0; i < 100; i++){
            threads.add(new Thread(()-> System.out.println(Singleton.getInstance())));
        }
        for(Thread t : threads){
            t.start();
        }
        for(Thread t : threads){
            t.join();
        }
    }
    
}
