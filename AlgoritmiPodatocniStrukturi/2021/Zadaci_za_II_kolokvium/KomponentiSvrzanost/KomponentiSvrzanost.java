package KomponentiSvrzanost;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.LinkedList;

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

// Implementacija na redica

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

class GraphNode<E> {
	private int index;//index (reden broj) na temeto vo grafot
	private E ime;
	private E prezime;
	private LinkedList<GraphNode<E>> neighbors;

	public GraphNode(int index, E ime, E prezime) {
		this.index = index;
		this.ime = ime;
		this.prezime = prezime;
		neighbors = new LinkedList<GraphNode<E>>();
	}

	public GraphNode(int index) {
		this.index = index;

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


	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}


	public LinkedList<GraphNode<E>> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(LinkedList<GraphNode<E>> neighbors) {
		this.neighbors = neighbors;
	}
}

class Graph<E> {

	int num_nodes;
	GraphNode<E> adjList[];


	@SuppressWarnings("unchecked")
	public Graph(int num_nodes) {
		this.num_nodes = num_nodes;
		adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
		for (int i = 0; i < num_nodes; i++)
			adjList[i] = new GraphNode<E>(i);
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

	List<Integer> najdikomponenti(int start) {
		Boolean[] visited = new Boolean[num_nodes];
		Arrays.fill(visited, false);
		Queue<Integer> queue = new LinkedQueue<Integer>();
		visited[start] = true;
		queue.enqueue(start);
		while (!queue.isEmpty()) {
			int temp = queue.dequeue();
			for (int i = 0; i < num_nodes; i++) {
				if (this.adjacent(temp, i) == 1 && !visited[i]) {
					visited[i] = true;
					queue.enqueue(i);
				}
			}
		}
		List<Integer> same = new LinkedList<Integer>();
		for (int i = 0; i < num_nodes; i++) {
			if (visited[i]) same.add(i);
		}
		return same;
	}


	@Override
	public String toString() {
		String ret = new String();
		for (int i = 0; i < this.num_nodes; i++)
			ret += i + ": " + adjList[i] + "\n";
		return ret;
	}

}


public class KomponentiSvrzanost {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());


		Graph<String> g = new Graph<String>(N);

		int sosedIndex;
		int i_num_nodes;
		String pom;
		for (int i = 0; i < N; i++) {
			i_num_nodes = Integer.parseInt(br.readLine());
			for (int j = 0; j < i_num_nodes; j++) {
				pom = br.readLine();
				sosedIndex = Integer.parseInt(pom);

				g.addEdge(i, sosedIndex);
			}
		}


		int teme = Integer.parseInt(br.readLine());
		List<Integer> komponenti = g.najdikomponenti(teme);
		komponenti.forEach(x -> System.out.println(x));


		br.close();
	}

}
