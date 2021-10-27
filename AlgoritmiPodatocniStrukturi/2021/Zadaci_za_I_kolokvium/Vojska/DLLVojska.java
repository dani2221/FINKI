package Vojska;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class DLLNode<E> {
	protected E element;
	protected DLLNode<E> pred, succ;

	public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
		this.element = elem;
		this.pred = pred;
		this.succ = succ;
	}

	@Override
	public String toString() {
		return "<-"+element.toString()+"->";
	}
}


class DLL<E> {
	private DLLNode<E> first, last;

	public DLL() {
		// Construct an empty SLL
		this.first = null;
		this.last = null;
	}

	public void deleteList() {
		first = null;
		last = null;
	}

	public int length() {
		int ret;
		if (first != null) {
			DLLNode<E> tmp = first;
			ret = 1;
			while (tmp.succ != null) {
				tmp = tmp.succ;
				ret++;
			}
			return ret;
		} else
			return 0;

	}

	public void insertFirst(E o) {
		DLLNode<E> ins = new DLLNode<E>(o, null, first);
		if (first == null)
			last = ins;
		else
			first.pred = ins;
		first = ins;
	}

	public void insertLast(E o) {
		if (first == null)
			insertFirst(o);
		else {
			DLLNode<E> ins = new DLLNode<E>(o, last, null);
			last.succ = ins;
			last = ins;
		}
	}

	public void insertAfter(E o, DLLNode<E> after) {
		if(after==last){
			insertLast(o);
			return;
		}
		DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
		after.succ.pred = ins;
		after.succ = ins;
	}

	public void insertBefore(E o, DLLNode<E> before) {
		if(before == first){
			insertFirst(o);
			return;
		}
		DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
		before.pred.succ = ins;
		before.pred = ins;
	}

	public E deleteFirst() {
		if (first != null) {
			DLLNode<E> tmp = first;
			first = first.succ;
			if (first != null) first.pred = null;
			if (first == null)
				last = null;
			return tmp.element;
		} else
			return null;
	}

	public E deleteLast() {
		if (first != null) {
			if (first.succ == null)
				return deleteFirst();
			else {
				DLLNode<E> tmp = last;
				last = last.pred;
				last.succ = null;
				return tmp.element;
			}
		}
		// else throw Exception
		return null;
	}


	@Override
	public String toString() {
		String ret = new String();
		if (first != null) {
			DLLNode<E> tmp = first;
			ret += tmp + "<->";
			while (tmp.succ != null) {
				tmp = tmp.succ;
				ret += tmp + "<->";
			}
		} else
			ret = "Prazna lista!!!";
		return ret;
	}

	public DLLNode<E> getFirst() {
		return first;
	}

	public DLLNode<E> getLast() {

		return last;
	}

}

public class DLLVojska {


	public static void main(String[] args) throws IOException {
		DLL<Integer> lista = new DLL<Integer>();
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String s = stdin.readLine();
		int N = Integer.parseInt(s);
		s = stdin.readLine();
		String[] ids = s.split(" ");
		for (int i = 0; i < N; i++) {
			lista.insertLast(Integer.parseInt(ids[i]));
		}

		s = stdin.readLine();
		String interval[] = s.split(" ");
		int a = Integer.parseInt(interval[0]);
		int b = Integer.parseInt(interval[1]);

		s = stdin.readLine();
		interval = s.split(" ");
		int c = Integer.parseInt(interval[0]);
		int d = Integer.parseInt(interval[1]);


		DLL<Integer> result = vojska(lista, a, b, c, d);


		DLLNode<Integer> node = result.getFirst();
		System.out.print(node.element);
		node = node.succ;
		while(node != null){
			System.out.print(" "+node.element);
			node = node.succ;
		}

	}

	private static DLL<Integer> vojska(DLL<Integer> lista, int a, int b, int c, int d) {

		DLLNode<Integer> firstStart = null;
		DLLNode<Integer> firstEnd = null;
		DLLNode<Integer> secondStart = null;
		DLLNode<Integer> secondEnd = null;

		DLLNode<Integer> steps = lista.getFirst();
		while (steps != null) {
			if (steps.element == a) firstStart = steps;
			if (steps.element == b) firstEnd = steps;
			if (steps.element == c) secondStart = steps;
			if (steps.element == d) secondEnd = steps;
			steps = steps.succ;
		}
		// 10 50 40 25 1
		steps = lista.getLast();
		DLLNode<Integer> tempStart = firstStart.pred;
		DLLNode<Integer> tempEnd = firstEnd.succ;
		if (secondStart.pred != firstEnd){
			firstStart.pred = secondStart.pred;
			if (firstStart.pred != null) firstStart.pred.succ = firstStart;
			firstEnd.succ = secondEnd.succ;
			if (firstEnd.succ != null) firstEnd.succ.pred = firstEnd;
			secondStart.pred = tempStart;
			if (secondStart.pred != null) secondStart.pred.succ = secondStart;
			secondEnd.succ = tempEnd;
			if (secondEnd.succ != null) secondEnd.succ.pred = secondEnd;
		}
		else {
			firstEnd.succ = secondEnd.succ;
			if (firstEnd.succ != null) firstEnd.succ.pred = firstEnd;
			firstStart.pred = secondEnd;
			firstStart.pred.succ = firstStart;
			secondStart.pred = tempStart;
			if (secondStart.pred != null) secondStart.pred.succ = secondStart;
		}
		while(steps.pred != null){
			steps = steps.pred;
		}
		DLL<Integer> newList = new DLL<Integer>();
		newList.insertFirst(steps.element);
		DLLNode<Integer> adder = newList.getFirst();
		while(steps.succ != null){
			newList.insertAfter(steps.succ.element,adder);
			steps = steps.succ;
			adder = adder.succ;
		}
		return newList;
	}
}