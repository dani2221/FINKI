# Лаб 3
## Зад 1.
Ве најмуваат од шоуто „Танц со студентите“, да имплементирате софтвер кој автоматски ќе ги контролира портите за насочување на натпреварувачите.

Влезната порта директно води кон една од гардеробите, каде учесниците треба да се пресоблечат. Притоа, во секоја од двете гардероби може паралелно да влезат повеќе учесници од ист пол, но не повеќе од 10.

По пресоблекувањето, учесниците чекаат да дојде произволен партнер, по што излегуваат од соблекувалната и влегуваат во салата за танцување. Во салата паралелно може да танцуваат максимум три пара. По завршувањето на танцот, паровите излегуваат од салата по што може да влезе нов пар на нивно место.

Во почетниот код, дефинирани се класите Masko и Zensko, кои ги симболизираат учесниците од соодветниот пол. Од секој од типовите роботи паралелно се активни повеќе инстанци, кои може да танцуваат само еднаш.

Во имплементацијата, можете да ги користите следните методи од веќе дефинираната променлива show:

show.presobleci()
Го симболизира влегувањето во соблекувалната на учесниците и нивното пресоблекување
Не смее паралелно да биде повикан повеќе од 10 пати од исти пол на учесници
show.tancuvaj()
Го симболизира почетокот на танцот
Се повикува само од машките учесници, бидејќи тие водат при танцувањето
Машкото пред повикот, треба да се осигура дека е присутна учесничка која ќе му биде партнерка
Не смее да има повеќе од три паралелни повици на овој метод
Претходно назначените методи манипулираат со споделен ресурс и ниту еден од нив не е атомичен.

Вашата задача е да ги имплементирате методите Masko.ucestvo() и Zensko.ucestvo() и init(). При имплементацијата, не смеете да додадете try-catch блок во нив. Потребните семафори, глобални променливи и променливи за состојбата на роботите треба да ги дефинирате самите.

Доколку имате грешка, ќе ја добиете пораката:

Procesot ne e sinhroniziran spored uslovite na zadacata


По што ќе ви се прикаже логот на повикување на акциите и настанатите грешки. Овој лог треба да ви послужи за увидување на тоа каде имате грешка во извршувањето на вашата задача.

Напомена: Поради конкурентниот пристап за логирањето, можно е некои од пораките да не се на позицијата каде што треба да се. Токму затоа, овие пораки користете ги само како информација, но не се ослонувајте на нив.

You are hired by the show „Dancing with the students“, in order to implement a software solution which will automatically control the gates which guide the contestants.

The entry gate leads directly to one of the changing rooms, where the contestants should change their clothes. You can have at most 10 contestants in each changing room, at the same time.

After they change, the contestants wait for a random partner from the opposite gender, after which they exit the changing room and enter the dancing room. The dancing room can have a maximum of three pairs dancing at the same time (in parallel). After they finish dancing, the pairs exit the dancing room leaving space for a new pair to enter and start dancing.

In the starter code, you have the definition of two classes, Masko (Male) and Zensko (Female), which represent contestants from the corresponding gender. Each of the classes has multiple running instances, and each instance can dance only once.

In your implementation, you can use the following methods from show:

show.presobleci()
Represents a contestant entering in the changing room and changing
Cannot be called in parallel by more than 10 contestants from the same gender
show.tancuvaj()
Represents the start of the dance
Can only be called by male contestants, as they lead the dance
The male contestant should make sure that a female contestant is present as his partner before he makes the call
There cannot be more than 3 parallel calls on this method
These methods manipulate with a shared resource and are not atomic.

Your task is to implement the methods Masko.ucestvo(), Zensko.ucestvo() and init(). In your implementation, you can not add try-catch blocks in them. You should define the necessary semaphores, global variables and state variables.

If you have an error, you will get this message:

The process in not synchronized according to the conditions in the task
After that you will see the log of actions and errors. Use this log to see what went wrong,

Note: Due to concurrent execution of the logging, it is possible that some of the messages in the log are not in the position they are supposed to be. Therefore, use them only as guideline information, do not base all of the conclusions on them.

```
public class TancSoStudentite {
    //TODO: Definicija na globalni promenlivi i semafori

    

    public void init() {
        //TODO: da se implementira
        
    }

    class Masko extends Thread {
        //TODO: Definicija  na promenlivi za sostojbata

        public void ucestvo() throws InterruptedException {
            //TODO: da se implementira
            show.presobleci();
            // samo maskoto go povikuva metodot tancuvaj
            show.tancuvaj();
        }

        @Override
        public void run() {
            try {
                ucestvo();
            } catch (InterruptedException e) {
                // Do nothing
            } catch (Exception e) {
                exception = e;
                hasException = true;
            }
        }

        @Override
        public String toString() {
            return String.format("m\t%d", getId());
        }
        public Exception exception = null;
    }

    class Zensko extends Thread {
        //TODO: Definicija  na promenlivi za sostojbata

        public void ucestvo() throws InterruptedException {
            //TODO: da se implementira
            show.presobleci();
            

        }

        @Override
        public void run() {
            try {
                ucestvo();
            } catch (InterruptedException e) {
                // Do nothing
            } catch (Exception e) {
                exception = e;
                hasException = true;
            }
        }

        @Override
        public String toString() {
            return String.format("z\t%d", getId());
        }
        public Exception exception = null;
    }

    public static void main(String[] args) {
        try {
            TancSoStudentite environment = new TancSoStudentite();
            environment.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void start() throws Exception {
        show = new Show();
        init();
        HashSet<Thread> threads = new HashSet<Thread>();
        for (int i = 0; i < BROJ_INSTANCI; i++) {
            Zensko z = new Zensko();
            Masko m = new Masko();
            threads.add(z);
            threads.add(m);
        }

        for (Thread t : threads) {
            t.start();
        }

        boolean valid = true;
        for (Thread t : threads) {
            if (!hasException) {
                t.join();
            } else {
                t.interrupt();
            }
        }
        show.printStatus();

    }

    public class Show {

        public static final int BROJ_GARDEROBA = 10;
        public static final int BROJ_TEREN = 3;
        public static final int TYPE_MASKO = 1;
        public static final int TYPE_ZENSKO = 2;
        public static final int TYPE_UNKNOWN = -1;

        public Show() {
        }
        public int brojMaskiGarderoba = 0;
        public int brojZenskiGarderoba = 0;
        public int brojTancuvanja = 0;
        public int maxMaskiGarderoba = 0;
        public int maxZenskiGarderoba = 0;
        public int maxTancuvanja = 0;

        public void presobleci() throws RuntimeException {
            log(null, "presobleci start");
            Thread t = Thread.currentThread();
            if (t instanceof Masko) {
                synchronized (RANDOM) {
                    brojMaskiGarderoba++;
                    if (brojMaskiGarderoba > 10) {
                        exception("Ne moze da ima poveke od 10 maski vo maskata garderoba.");
                    }
                    if (brojMaskiGarderoba > maxMaskiGarderoba) {
                        maxMaskiGarderoba = brojMaskiGarderoba;
                    }
                }
                waitRandom();
                synchronized (RANDOM) {
                    brojMaskiGarderoba--;
                }
            } else {
                synchronized (RANDOM) {
                    brojZenskiGarderoba++;
                    if (brojZenskiGarderoba > 10) {
                        exception("Ne moze da ima poveke od 10 zenski vo zenskata garderoba.");
                    }
                    if (brojZenskiGarderoba > maxZenskiGarderoba) {
                        maxZenskiGarderoba = brojZenskiGarderoba;
                    }
                }
                waitRandom();
                synchronized (RANDOM) {
                    brojZenskiGarderoba--;
                }
            }
            log(null, "presobleci kraj");
        }

        public void tancuvaj() throws RuntimeException {
            log(null, "tancuvaj start");
            synchronized (RANDOM) {
                brojTancuvanja++;
                if (brojTancuvanja > BROJ_TEREN) {
                    exception("Ne moze paralelno da tancuvaat poveke od 3 para.");
                }

                if (brojTancuvanja > maxTancuvanja) {
                    maxTancuvanja = brojTancuvanja;
                }
            }
            waitRandom();
            synchronized (RANDOM) {
                brojTancuvanja--;
            }
            log(null, "tancuvaj kraj");
        }

        private void waitRandom() {
            try {
                int r;
                synchronized (RANDOM) {
                    r = RANDOM.nextInt(RANDOM_RANGE);
                }
                Thread.sleep(r);
            } catch (Exception e) {
                //do nothing
            }
        }

        private void exception(String message) {
            RuntimeException e = new RuntimeException(message);
            log(e, null);
            hasError = true;
            throw e;
        }

        public int getType() {
            Thread t = Thread.currentThread();
            if (t instanceof Masko) {
                return TYPE_MASKO;
            } else if (t instanceof Zensko) {
                return TYPE_ZENSKO;
            } else {
                return TYPE_UNKNOWN;
            }
        }

        private synchronized void log(RuntimeException e, String action) {
            Thread t = Thread.currentThread();
            if (e == null) {
                actions.add(t.toString() + "\t(a): " + action);
            } else {
                actions.add(t.toString() + "\t(e): " + e.getMessage());
            }
        }

        public synchronized void printLog() {
            System.out.println("Poradi konkurentnosta za pristap za pecatenje, mozno e nekoja od porakite da ne e na soodvetnoto mesto.");
            System.out.println("Log na izvrsuvanje na akciite:");
            System.out.println("=========================");
            System.out.println("(tip m<=>Masko, tip z<=>Zensko)");
            System.out.println("tip\tid\takcija/error");
            System.out.println("=========================");
            for (String l : actions) {
                System.out.println(l);
            }
        }

        public void printStatus() {
            if (!hasError) {
                int poeni = 25;
                System.out.println("Procesot e uspesno sinhroniziran");
                if (show.maxMaskiGarderoba == 1 || show.maxZenskiGarderoba == 1) {
                    System.out.println("\t-no ima maksimum eden ucesnik vo garderobata.");
                    poeni -= 5;
                }
                if (show.maxTancuvanja == 1) {
                    System.out.println("\t-no ima maksimum edna proverka vo eden moment.");
                    poeni -= 5;
                }

                System.out.println("Osvoeni poeni: " + poeni);

            } else {
                System.out.println("Procesot ne e sinhroniziran spored uslovite na zadacata");
                show.printLog();
                System.out.println("Maksimum mozni poeni: 15");
            }

        }
        private List<String> actions = new ArrayList<String>();
        private boolean hasError = false;
    }
    // Konstanti
    public static int BROJ_INSTANCI = 1000;
    public static final Random RANDOM = new Random();
    public static final int RANDOM_RANGE = 3;
    // Instanca od bafferot
    public Show show;
    public boolean hasException = false;
}
```

[TancSoStudentite.java](TancSoStudentite.java)

## Зад 2.
Потребно е да направите систем за синхронизација на турнир во кошарка, кој се одржува според следните правила:

На турнирот учествуваат 100 кошаркари, кои произволно се групираат во тимови. Во салата истовремено може да влезат најмногу 20 играчи. По влегувањето во салата, секој кошаркар треба да испечати Player inside.. Потоа кошаркарите треба да се пресоблечат за што имаат на располагање кабина со капацитет 10, односно може да се пресоблекуваат 10 играчи во исто време. При влегувањето во соблекувалната, треба да се испечати In dressing room.. По пресоблекувањето, играчите се чекаат меѓусебно. Откако сите ќе завршат со пресоблекувањето, започнуваат со натпреварот, при што сите печатат Game started.. Откако ќе заврши натпреварот, сите печатат Player done., а последниот го повикува печати Game finished., со што означува дека салата е слободна. Потоа, во салата може да влезат нови 20 играчи и да започне нов натпревар.

Во почетниот код кој е даден, дефинирани се класите BasketballTournament и Player. Во main методот од класата BasketballTournament потребно е да стартувате 100 играчи, кои се репрезентирани преку класата Player. Потоа секој од играчите треба да започне да го извршува претходно дефинираното сценарио во позадина. Однесувањето на играчите треба да го дефинрате во execute методот од Player класата, кој треба да се извршува паралелно кај сите играчи. По стартувањето на сите играчи, во main треба да се чека секој од играчите да заврши за 5 секунди (5000 ms). Доколку некој од играчите не заврши за 5 секунди, треба да се испечати Possible deadlock! и да се терминира, а доколку сите играчи завршиле во предвиденото време, да се испечати Tournament finished..

Вашата задача е да го дополните дадениот код според барањата на задачата, при што треба да внимавате не настане Race Condition и Deadlock.

You need to implement a system for synchronizing a basketball tournament according to the following rules:

There are 100 players participating to the tournament grouped in arbitrary teams. At most 20 players can enter in the tournament arena at once. After the enterence in the arena, each player should print Player inside.. Then the players should change their clothes in a dressing room with capacity of 10, meaning that at most 10 players can be in the dressing room at once. When the players enter in the dressing room, they should print In dressing room.. When a player exits from the dressing room, it waits for the other players in order to start with the game. After all players are ready, the game can start, and every player should print Game started.. After the game is over, all players should print Player done., and the last one should print Game finished., denoting that the arena is free for another game. Then, another 20 players can enter in the arena.

The classes BasketballTournament and Payer are given in the starter code below. In the main method from the class BasketballTournament you should start 100 basketball players, which are represented with the class Payer. Then, each of the players should start executing the previously defined scenario in background. The players behavior should be implemented in the execute method from the Payer class, which should be executed in parallel for all players. After starting all players, the main method should wait for each of the players to finish for 5 seconds (5000 ms). If some of the players does not finish for 5 seconds, you should print Possible deadlock! and terminate the thread, and if all payers finished in the given time, you should print Tournament finished..

Your task is to complete the code provided below according to the requirements, and be careful not to create a Race Condition or a Deadlock.

```
import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class BasketballTournament {

    public static void main(String[] args) {
        HashSet<Player> threads = new HashSet<>();
        for (int i = 0; i < 60; i++) {
            Player p = new Player();
            threads.add(p);
        }
        // run all threads in background

        // after all of them are started, wait each of them to finish for maximum 5_000 ms

        // for each thread, terminate it if it is not finished
        System.out.println("Possible deadlock!");
        System.out.println("Tournament finished.");

    }
}

class Player {

    public void execute() throws InterruptedException {
        // at most 20 players should print this in parallel
        System.out.println("Player inside.");
        // at most 10 players may enter in the dressing room in parallel
        System.out.println("In dressing room.");
        Thread.sleep(10);// this represent the dressing time
        // after all players are ready, they should start with the game together
        System.out.println("Game started.");
        Thread.sleep(100);// this represent the game duration
        System.out.println("Player done.");
        // only one player should print the next line, representing that the game has finished
        System.out.println("Game finished.");
    }
}
```
[BasketballTournament.java](BasketballTournament.java)

# Зад 3.
На една тркалезна маса седат 6 филозофи кои јадат и мислат. Меѓу нив има и 6 вилушки за јадење. Потребно е да се синхронизира вечерата на филозофите. Користе те го следниот почетен код.

There are 6 philosophers are sitting in a roundtable, who think and eat. There are only 6 forks between them. You need to synchronize the dining philosophers. Use the following starter kod.
```
class Demo {

    public static void main(String args[]) throws InterruptedException {
        DiningPhilosophers.runTest();
    }
}

class DiningPhilosophers {

    private static Random random = new Random(System.currentTimeMillis());
    private Semaphore[] forks = new Semaphore[6];

    public DiningPhilosophers() {
        forks[0] = new Semaphore(1);
        forks[1] = new Semaphore(1);
        forks[2] = new Semaphore(1);
        forks[3] = new Semaphore(1);
        forks[4] = new Semaphore(1);
        forks[5] = new Semaphore(1);

    }

    public void lifecycleOfPhilosopher(int id) throws InterruptedException {

        while (true) {
            think();
            eat(id);
        }
    }

    void think() throws InterruptedException {
        Thread.sleep(random.nextInt(50));
    }

    void eat(int id) throws InterruptedException {
        // TODO: synchronize
    }

    static void runPhilosopher(DiningPhilosophers dp, int id) {
        try {
            dp.lifecycleOfPhilosopher(id);
        } catch (InterruptedException ie) {

        }
    }

    public static void runTest() throws InterruptedException {
        final DiningPhilosophers dp = new DiningPhilosophers();

        Thread p1 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 0);
            }
        });

        Thread p2 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 1);
            }
        });

        Thread p3 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 2);
            }
        });

        Thread p4 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 3);
            }
        });

        Thread p5 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 4);
            }
        });

        Thread p6 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 5);
            }
        });

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();

        p1.join();
        p2.join();
        p3.join();
        p4.join();
        p5.join();
        p6.join();

    }
}
```

# Зад 4.
Потребно е да се синхронизира креирањето на една единствена инстанца од класата Singleton. Повеќе нишки паралелно се обидуваат да ја добијат инстанцата од Singleton, меѓутоа само една инстанца мора да биде креирана. 

Multiple threads are trying to access the same Singleton instance. However, you need to synchronize the creation of the singleton instance, in a way that only one instance would exist.


```
public class Singleton {

    private static volatile Singleton singleton;

    private Singleton() {

    }

    public static Singleton getInstance() {
        // TODO: 3/29/20 Synchronize this 
        singleton = new Singleton();

        return singleton;
    }

    public static void main(String[] args) {
        // TODO: 3/29/20 Simulate the scenario when multiple threads call the method getInstance    
    }
    
}
```
[Singleton.java](Singleton.java)

# Зад 5.
Во една фабрика потребно е производство на оцет - C2H4O2.

Во процесот на производство треба да се присутни 2 јаглеродни (C) атоми, 4 водородни (H) и 2 кислородни (О) атоми. Молекулите на C2H4O2 се формираат една по една.

Секој од атомите е претставен преку соодветна класа, во која execute() методот треба да се извршува во позадина. Во execute методот, треба да овозможите да се извршуваат паралелно максимум 2 јаглеродни (C) атоми, 4 водородни (H) и 2 кислородни (О) атоми. По влегувањето на секој од атомите треба да се испечати порака дека е присутен. Потоа, атомите треба да чекаат додека сите потребни атоми за молекулата пристигнат, по што се печати Molecule bonding. од страна на сите атоми. Откако ќе заврши спојувањето, секој од методите печати дека е завршен. На крајот треба само еден атом да испечати Molecule created. и да овозможи креирање на нова молекула.

Вашата задача е во main методот да стартувате 20 јаглеродни, 40 водородни и 20 кислородни атоми, кои ќе се извршуваат во позадина. Потоа треба да почекате 2 секунди за да завршат сите. Они кои не завршиле, треба да ги прекинете и да испечатите Possible deadlock!. Ако сите завршиле без да ги прекинете, испечатете Process finished..

Вашата задача е да го дополните дадениот код според барањата на задачата, при што треба да внимавате не настане Race Condition и Deadlock.


A factory hires you to help synchronize the process of vinegar production - C2H4O2.

For the production process you need 2 carbon (C) atoms, 4 hydrogen (H) and 2 oxygen (О) atoms. The C2H4O2 molecules are created one by one.

Each atom is represented with the corresponding class, where the execute() method should be executed in background. The execute method should allow parallel execution of maximum 2 carbon (C) atoms, 4 hydrogen (H) and 2 oxygen (О) atoms. After its execution, each atom should print that it is present. Then, the atoms should wait until all other atoms required for the molecule are present, and than they all print Molecule bonding.. Afterwards, each of the methods should print that it is done. At the end, only one atom should print Molecule created. and should enable the creation of the next molecule.

Your task is to run in the background 20 carbon, 40 hydrogen and 20 oxygen atoms in the main method. Than, you need to wait 2 seconds for all of them to finish. If some of the atoms do not finish within 2 seconds, you should print Possible deadlock! and terminate the thread, and if all atoms have finished in the given time, you should print Process finished..

Your task is to complete the starter code according to the above requirements, while making sure you avoid a Race Condition or a Deadlock.

```
import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class Vinegar {

    public static void main(String[] args) {
        HashSet<Thread> threads = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            threads.add(new C());
            threads.add(new H());
            threads.add(new H()); 
            threads.add(new O());
        }
        // run all threads in background

        // after all of them are started, wait each of them to finish for maximum 2_000 ms

        // for each thread, terminate it if it is not finished
        System.out.println("Possible deadlock!");
        System.out.println("Process finished.");

    }

    static class C {

        public void execute() throws InterruptedException {
            // at most 2 atoms should print this in parallel
            System.out.println("C here.");
            // after all atoms are present, they should start with the bonding process together
            System.out.println("Molecule bonding.");
            Thread.sleep(100);// this represent the bonding process
            System.out.println("C done.");
            // only one atom should print the next line, representing that the molecule is created
            System.out.println("Molecule created.");
        }
    }

    static class H {

        public void execute() throws InterruptedException {
            // at most 4 atoms should print this in parallel
            System.out.println("H here.");
            // after all atoms are present, they should start with the bonding process together
            System.out.println("Molecule bonding.");
            Thread.sleep(100);// this represent the bonding process
            System.out.println("H done.");
            // only one atom should print the next line, representing that the molecule is created
            System.out.println("Molecule created.");
        }
    }

    static class O {

        public void execute() throws InterruptedException {
            // at most 2 atoms should print this in parallel
            System.out.println("O here.");
            // after all atoms are present, they should start with the bonding process together
            System.out.println("Molecule bonding.");
            Thread.sleep(100);// this represent the bonding process
            System.out.println("O done.");
            // only one atom should print the next line, representing that the molecule is created
            System.out.println("Molecule created.");
        }
    }

}
```
[Vinegar.java](Vinegar.java)