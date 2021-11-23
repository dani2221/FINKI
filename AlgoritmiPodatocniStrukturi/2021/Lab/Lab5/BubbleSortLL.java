import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//		Сортирање со меурчиња кај листа Problem 3 (0 / 0)
//		Дадена е една двојно поврзана листa и со N јазли кои во себе содржат по еден природен број. Треба да се сортира листата со помош на сортирањето со меурчиња (bubble sort). Во првиот ред од влезот е даден бројот на јазли во листата, а потоа во вториот ред се дадени јазлите од кои е составена. На излез треба да се испечатат јазлите на сортираната листа.
//		Забелешка: При реализација на задачата МОРА да се користи дадената структура, а не да користат помошни структури како низи и сл.

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
		return element.toString();
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

	public DLLNode<E> find(E o) {
		if (first != null) {
			DLLNode<E> tmp = first;
			while (tmp.element != o && tmp.succ != null)
				tmp = tmp.succ;
			if (tmp.element == o) {
				return tmp;
			} else {
				System.out.println("Elementot ne postoi vo listata");
			}
		} else {
			System.out.println("Listata e prazna");
		}
		return first;
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

	public E delete(DLLNode<E> node) {
		if(node==first){
			deleteFirst();
			return node.element;
		}
		if(node==last){
			deleteLast();
			return node.element;
		}
		node.pred.succ = node.succ;
		node.succ.pred = node.pred;
		return node.element;

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

	public String toStringR() {
		String ret = new String();
		if (last != null) {
			DLLNode<E> tmp = last;
			ret += tmp + "<->";
			while (tmp.pred != null) {
				tmp = tmp.pred;
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

	public void izvadiDupliIPrebroj(){

	}
}
public class BubbleSortLL {

	static void bubbleSort(DLL<Integer> lista){
		while (true){
			boolean flag = false;
			DLLNode<Integer> iter = lista.getFirst().succ;
			while (iter!=null){
				if(iter.pred.element>iter.element){
					if(iter.succ==null){
						iter = iter.pred.pred;
						int swap2 = lista.delete(iter.succ.succ);
						int swap1 = lista.delete(iter.succ);
						lista.insertAfter(swap1,iter);
						lista.insertBefore(swap2,iter);
						iter = iter.succ;
					}
					else {
						iter = iter.succ;
						int swap1 = lista.delete(iter.pred.pred);
						int swap2 = lista.delete(iter.pred);
						lista.insertBefore(swap2, iter);
						lista.insertBefore(swap1, iter);
						flag = true;
					}
				}
				else iter = iter.succ;
			}
			if(!flag) break;
		}
		DLLNode<Integer> iter = lista.getFirst();
		while (iter!=null){
			System.out.print(iter.element+" ");
			iter = iter.succ;
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String s = stdin.readLine();
		int N = Integer.parseInt(s);
		s = stdin.readLine();
		String[] pomniza = s.split(" ");
		DLL<Integer> lista = new DLL<Integer>();
		for(String el : pomniza){
			lista.insertLast(Integer.parseInt(el));
		}
		
		bubbleSort(lista);
		
	}

}