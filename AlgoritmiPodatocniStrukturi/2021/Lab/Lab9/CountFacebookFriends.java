//import java.util.Iterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

interface Queue<E> {

    // Elementi na redicata se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty();
    // Vrakja true ako i samo ako redicata e prazena.

    public int size();
    // Ja vrakja dolzinata na redicata.

    public E peek();
    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear();
    // Ja prazni redicata.

    public void enqueue(E x);
    // Go dodava x na kraj od redicata.

    public E dequeue();
    // Go otstranuva i vrakja pochetniot element na redicata.

}

class LinkedQueue<E> implements Queue<E> {

    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Elementite se zachuvuvaat vo jazli dod SLL
    // front i rear se linkovi do prviot i posledniot jazel soodvetno.
    SLLNode<E> front, rear;
    int length;

    // Konstruktor ...

    public LinkedQueue() {
        clear();
    }

    public boolean isEmpty() {
        // Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size() {
        // Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek() {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (front == null)
            throw new NoSuchElementException();
        return front.element;
    }

    public void clear() {
        // Ja prazni redicata.
        front = rear = null;
        length = 0;
    }

    public void enqueue(E x) {
        // Go dodava x na kraj od redicata.
        SLLNode<E> latest = new SLLNode<E>(x, null);
        if (rear != null) {
            rear.succ = latest;
            rear = latest;
        } else
            front = rear = latest;
        length++;
    }

    public E dequeue() {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (front != null) {
            E frontmost = front.element;
            front = front.succ;
            if (front == null) rear = null;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }

}


class Graph<E> {

    int num_nodes;
    GraphNode<E> adjList[];

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes, E[] list) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode<E>(i, list[i]);
    }

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
    }

    int adjacent(int x, int y) {
        // proveruva dali ima vrska od jazelot so
        // indeks x do jazelot so indeks y
        return (adjList[x].containsNeighbor(adjList[y])) ? 1 : 0;
    }

    void addEdge(int x, int y) {
        // dodava vrska od jazelot so indeks x do jazelot so indeks y
        if (!adjList[x].containsNeighbor(adjList[y])) {
            adjList[x].addNeighbor(adjList[y]);
        }
    }

    void deleteEdge(int x, int y) {
        adjList[x].removeNeighbor(adjList[y]);
    }

    void dfsSearch(int node) {
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        dfsRecursive(node, visited);
    }

    void dfsRecursive(int node, boolean visited[]) {
        visited[node] = true;
        System.out.println(node + ": " + adjList[node].getInfo());

        for (int i = 0; i < adjList[node].getNeighbors().size(); i++) {
            GraphNode<E> pom = adjList[node].getNeighbors().get(i);
            if (!visited[pom.getIndex()])
                dfsRecursive(pom.getIndex(), visited);
        }
    }

    void dfsNonrecursive(int node) {
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        visited[node] = true;
        System.out.println(node + ": " + adjList[node].getInfo());
        Stack<Integer> s = new Stack<Integer>();
        s.push(node);

        GraphNode<E> pom;

        while (!s.isEmpty()) {
            pom = adjList[s.peek()];
            GraphNode<E> tmp = null;
            for (int i = 0; i < pom.getNeighbors().size(); i++) {
                tmp = pom.getNeighbors().get(i);
                if (!visited[tmp.getIndex()])
                    break;
            }
            if (tmp != null && !visited[tmp.getIndex()]) {
                visited[tmp.getIndex()] = true;
                System.out.println(tmp.getIndex() + ": " + tmp.getInfo());
                s.push(tmp.getIndex());
            } else
                s.pop();
        }

    }

    void bfs(int node) {
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        visited[node] = true;
        System.out.println(node + ": " + adjList[node].getInfo());
        Queue<Integer> q = new LinkedQueue<Integer>();
        q.enqueue(node);

        GraphNode<E> pom;

        while (!q.isEmpty()) {
            pom = adjList[q.dequeue()];
            GraphNode<E> tmp = null;
            for (int i = 0; i < pom.getNeighbors().size(); i++) {
                tmp = pom.getNeighbors().get(i);
                if (!visited[tmp.getIndex()]) {
                    visited[tmp.getIndex()] = true;
                    System.out.println(tmp.getIndex() + ": " + tmp.getInfo());
                    q.enqueue(tmp.getIndex());
                }
            }


        }

    }


    @Override
    public String toString() {
        String ret = new String();
        for (int i = 0; i < this.num_nodes; i++)
            ret += i + ": " + adjList[i] + "\n";
        return ret;
    }

}

class GraphNode<E> {
    private int index;//index (reden broj) na temeto vo grafot
    private E info;
    private LinkedList<GraphNode<E>> neighbors;

    public GraphNode(int index, E info) {
        this.index = index;
        this.info = info;
        neighbors = new LinkedList<GraphNode<E>>();
    }

    boolean containsNeighbor(GraphNode<E> o) {
        return neighbors.contains(o);
    }

    void addNeighbor(GraphNode<E> o) {
        neighbors.add(o);
    }

    void removeNeighbor(GraphNode<E> o) {
        if (neighbors.contains(o))
            neighbors.remove(o);
    }

    @Override
    public String toString() {
        String ret = "INFO:" + info + " SOSEDI:";
        for (int i = 0; i < neighbors.size(); i++)
            ret += neighbors.get(i).info + " ";
        return ret;

    }

    @Override
    public boolean equals(Object obj) {
        @SuppressWarnings("unchecked")
        GraphNode<E> pom = (GraphNode<E>) obj;
        return (pom.info.equals(this.info));
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public LinkedList<GraphNode<E>> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(LinkedList<GraphNode<E>> neighbors) {
        this.neighbors = neighbors;
    }


}

class Friend {
    private int redenBroj;
    private int distance;

    public Friend(int redenBroj) {
        this.redenBroj = redenBroj;
        this.distance = 0;
    }

    public int getRedenBroj() {
        return redenBroj;
    }


    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "" + redenBroj;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}

public class CountFacebookFriends {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        Friend[] friends = IntStream.range(0, N).mapToObj(Friend::new).toArray(Friend[]::new);
        Graph<Friend> network = new Graph<Friend>(N, friends);
        for (int i = 0; i < N; i++) {
            int numFriends = Integer.parseInt(bf.readLine());
            for (int j = 0; j < numFriends; j++) {
                int num = Integer.parseInt(bf.readLine().split("\\s+")[0]);
                network.addEdge(i, num);
                network.addEdge(num, i);
            }
        }

        int start = Integer.parseInt(bf.readLine());
        int end = Integer.parseInt(bf.readLine());
        Queue<GraphNode<Friend>> queue = new LinkedQueue<GraphNode<Friend>>();
        queue.enqueue(network.adjList[start]);
        boolean[] visited = new boolean[N];
        Arrays.fill(visited, false);
        visited[start] = true;
        while (!queue.isEmpty()) {
            GraphNode<Friend> peek = queue.peek();
            if (peek.getIndex() == end) {
                System.out.println(peek.getInfo().getDistance());
                return;
            }
            queue.dequeue();
            for (int i = 0; i < network.num_nodes; i++) {
                if (network.adjacent(i, peek.getIndex()) == 1 && !visited[i]) {
                    network.adjList[i].getInfo().setDistance(peek.getInfo().getDistance() + 1);
                    visited[i] = true;
                    queue.enqueue(network.adjList[i]);
                }
            }
        }
    }

}
