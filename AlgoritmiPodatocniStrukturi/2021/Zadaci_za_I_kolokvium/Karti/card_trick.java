package Karti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import java.util.Stack;

import java.util.NoSuchElementException;
interface Queue<E> {
	boolean isEmpty();

	int size();

	E peek();

	void clear();

	void enqueue(E x);

	E dequeue();
}
class ArrayQueue<E> implements Queue<E> {
	private E[] elements;
	private int front, rear, length;

	@SuppressWarnings("unchecked")
	public ArrayQueue(int maxSize) {
		elements = (E[]) new Object[maxSize];
		clear();
	}

	@Override
	public boolean isEmpty() {
		return length == 0;
	}

	@Override
	public int size() {
		return length;
	}

	@Override
	public E peek() {
		if (length != 0) {
			return elements[front];
		} else throw new NoSuchElementException();
	}

	@Override
	public void clear() {
		length = front = rear = 0;
	}

	@Override
	public void enqueue(E x) {
		if (length != elements.length) {
			if (rear == elements.length)
				rear = 0;
			elements[rear++] = x;
			++length;
		}
	}

	@Override
	public E dequeue() {
		if (length != 0) {
			E element = elements[front++];
			if (front == elements.length)
				front = 0;
			--length;
			return element;
		} else throw new NoSuchElementException();
	}
}
public class card_trick {
	public static int count(int N){
		// start queue
		ArrayQueue<Integer> redica = new ArrayQueue<Integer>(51);
		for(int i=1;i<52;i++){
			redica.enqueue(i);
		}

		int brojac = 0;
		while (true){

			if(redica.peek()==N){
				return brojac;
			}

			//prevrti prvite 7 karti so stack
			Stack<Integer> prevrteni = new Stack<Integer>();
			for(int i=0;i<7;i++){
				prevrteni.push(redica.dequeue());
			}
			for(int i=0;i<7;i++){
				redica.enqueue(prevrteni.pop());
				redica.enqueue(redica.dequeue());
			}
			brojac++;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
		System.out.println(count(Integer.parseInt(br.readLine())));
	}

}