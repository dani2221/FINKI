import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class QueueTest{
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new BlockingQueue<>(15);
        HashSet<Thread> threads = new HashSet<>();
        for(int i=0;i<3;i++){
            threads.add(new QueueThread(queue));
        }
        for(Thread th : threads){
            th.start();
        }
        for(Thread th : threads){
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class QueueThread extends Thread{
    BlockingQueue<Integer> queue;
    String name;

    public QueueThread(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for(int i=0;i<6;i++){
            System.out.println(i+" try enqueue");
            try {
                queue.enqueue(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i=0;i<3;i++){
            System.out.println(i+" try dequeue");
            try {
                queue.dequeue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class BlockingQueue<T> {

    T[] contents;
    int capacity;
    int current;

    Semaphore semaphoreEnqueue = new Semaphore(15);
    Semaphore semaphoreDequeue = new Semaphore(9);
    Semaphore controller = new Semaphore(1);

    public BlockingQueue(int capacity) {
        contents = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.current = 0;
    }

    public void enqueue(T item) throws InterruptedException {
        semaphoreEnqueue.acquire();
        controller.acquire();
        while (current==capacity) {
            controller.release();
            Thread.sleep(1000);
            controller.acquire();
        }
        contents[current] = item;
        current++;
        controller.release();
        semaphoreDequeue.release();
    }


    public T dequeue() throws InterruptedException {
        semaphoreDequeue.acquire();
        T item = null;
        controller.acquire();
        while (current==0){
            controller.release();
            Thread.sleep(1000);
            controller.acquire();
        }
        current--;
        item = contents[current];
        controller.release();
        semaphoreEnqueue.release();
        return item;
    }
}

