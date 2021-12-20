import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//        Black Friday Problem 2
//        Еден маркет за бела технка одлучил да ги спушти цените на своите производи еден работен петок дури до 80% по повод престојните Божикни празници. Поради оваа промоција јасно им било дека ќе има многу заинтересирани купувачи, па затоа одлучиле да воспостaват ред при влеувањето/излегувањето во маркетот. Купувачите влегуваат според времето на пристигнување. Потребно е да се пронајде колкав е максималниот број на купувачи кои ќе бидат присутни во маркетот. Во првата линија е даден бројот на купувачи кои чекаат да влезат, N. Секоја од наредните линии го означува часот и минутите на доаѓање на купувачот, како и колку време (во минути) ќе се задржи во маркетот, во формат: HH:MM d
//
//        За да се реализира оваа задача потребно е да се користи соодветна податочна структура со која со најмала сложеност ќе се постигне бараниот резултат. Притоа е обезбедено да не може да се случи во ист момент да има влегување односно излегување од маркетот. Маркетот работи до 23:59.
//
//        На излез треба да се испечати максималниот број купувачи кои што истовремено ќе бидат присутни во маркетот.
//
//        Име на класа во Java: BlackFriday


// zemeno od https://pastebin.com/Y6UqctGN
public class BlackFriday {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        Heap<Buyer> priorityQueue = new Heap<>(n);

        for (int i = 0; i < n; i++) {
            String[] parts = bf.readLine().split(" ");
            priorityQueue.insert(new Buyer(parts[0], Integer.parseInt(parts[1])));
        }

        int br = 1;
        int max = 0;
        for (int i = 0; i < n - 1; i++) {
            Buyer b = priorityQueue.removeMax();
            for (int j = 0; j < priorityQueue.getSize(); j++) {
                if (b.inTheSameTime(priorityQueue.getAt(j)))
                    br++;
            }
            max = Math.max(max, br);
            br = 1;
        }
        System.out.println(max);
    }

}

class Buyer implements Comparable<Buyer> {
    private int timeOfEntryInMinutes;
    private int timeInside; // minutes

    public static int MAX_ENTRY_TIME = 1439; // in minutes

    public Buyer(String timeOfEntry, int timeInside) {
        String[] parts = timeOfEntry.split(":");
        // minutite koga vlegol + casot * 60 = vkupno vreme vo minuti koga vlegol
        this.timeOfEntryInMinutes = Integer.parseInt(parts[1]) + (Integer.parseInt(parts[0]) * 60);
        this.timeInside = timeInside;
        // resavanje na problemot ako nekoj saka da sede posle zatvaranje
        if (timeOfEntryInMinutes + timeInside > MAX_ENTRY_TIME) {
            this.timeInside -= (timeOfEntryInMinutes + timeInside) - MAX_ENTRY_TIME;
        }
    }

    @Override
    public int compareTo(Buyer arg0) { // najgolem prioritet ima onoj koj prv ke izleze
        return -((timeOfEntryInMinutes + timeInside) - (arg0.timeOfEntryInMinutes + arg0.timeInside));
    }

    public boolean inTheSameTime(Buyer other) {
        // ako vremeto na izleguvanje na this e pogolemo od vremeto na vleguvanje na other,
        //togash se vo dukanot vo isto vreme
        if ((timeOfEntryInMinutes + timeInside) >= (other.timeOfEntryInMinutes))
            return true;
        return false;
    }
}

class Heap<E extends Comparable<E>> {
    private E[] elements;
    private int size;

    // go zgolemuvame za 1, bidejki indeksot pocnuva od 0
    public int getParent(int i) {
        return (i + 1) / 2 - 1;
    }

    public int getLeft(int i) {
        return ((i + 1) * 2) - 1;
    }

    public int getRight(int i) {
        return (i + 1) * 2;
    }
    // go zgolemuvame za 1, bidejki indeksot pocnuva od 0

    public E getAt(int i) {
        return elements[i];
    }

    public void setElement(int index, E insert) {
        elements[index] = insert;
    }

    public void swap(int i, int j) {
        E tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;
    }

    public void buildMaxHeap() {
        for (int i = elements.length / 2 - 1; i >= 0; i--)
            adjustMax(i, size);
    }

    private void adjustMax(int i, int n) {
        if (i >= n) return;
        int left = getLeft(i);
        int right = getRight(i);
        int max = i;

        if ((left < n) && elements[left].compareTo(elements[max]) > 0)
            max = left;
        if ((right < n) && elements[right].compareTo(elements[max]) > 0)
            max = right;
        if (max == i)
            return;

        swap(i, max);
        adjustMax(max, n);
    }

    public void buildMinHeap() {
        for (int i = elements.length / 2 - 1; i >= 0; i--)
            adjustMin(i, size);
    }

    private void adjustMin(int i, int n) {
        if (i >= n) return;
        int left = getLeft(i);
        int right = getRight(i);
        int min = i;

        if ((left < n) && elements[left].compareTo(elements[min]) < 0)
            min = left;
        if ((right < n) && elements[right].compareTo(elements[min]) < 0)
            min = right;
        if (min == i)
            return;

        swap(i, min);
        adjustMin(min, n);
    }


    public Heap(E[] arr) {
        this.elements = arr;
        this.size = arr.length;
    }

    public void heapSort() {
        buildMaxHeap();
        for (int i = size; i > 1; i--) {
            swap(0, i - 1);
            adjustMax(0, i - 1);
        }

    }

    // prioritetna redica implmentacija, el so max prioritet e na prva pozicija
    // so sekoj insert ili delete na element se adjust heap-ot, za max prioritet da
    // ostane na prva pozicija

    @SuppressWarnings("unchecked")
    public Heap(int size) {
        this.size = 0;
        this.elements = (E[]) new Comparable[size];
    }

    public boolean insert(E elem) {
        if (size == elements.length) return false;
        elements[size] = elem;
        size++;
        adjustUp(size - 1);
        return true;
    }

    private void adjustUp(int i) {
        if (i <= 0) return;
        int parent = getParent(i);
        if (elements[i].compareTo(elements[parent]) <= 0)
            return;
        else {
            swap(i, parent);
            adjustUp(parent);
        }
    }

    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    public E getMax() {
        return isEmpty() ? null : elements[0];
    }

    public int getSize() {
        return size;
    }

    public E removeMax() {
        if (isEmpty()) return null;
        E tmp = elements[0];
        elements[0] = elements[size - 1];
        size--;
        adjustMax(0, size);
        return tmp;
    }

    // zavrsuva implementacijata na Prioritetnata redica
}