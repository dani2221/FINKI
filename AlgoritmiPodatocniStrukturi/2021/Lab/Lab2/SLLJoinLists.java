import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;
//		Дадени се две еднострано поврзани листи чии јазли содржат по еден природен број. Листите се сортирани во растечки редослед. Треба да се спојат двете листи во една така што резултантната листа да е сортирана. Сортирањето е подредување со слевање. Јазлите кои се јавуваат како дупликати (од иста листа или од различна) да се отстранат.
//
//		Во првиот ред од влезот е даден бројот на јазли во првата листа, потоа во вториот ред се дадени броевите од кои се составени јазлите по редослед во првата листа, па во третиот ред е даден бројот на јазли во втората листа, и на крај во четвртиот ред броевите од кои се составени јазлите по редослед во втората листа. На излез треба да се испечатат јазлите по редослед во резултантната споена листа.
//
//		Име на класата (Java): SLLJoinLists
//
//		Забелешка: Да се креира податочна структура еднострано поврзана листа и истата да се искористи во задачата.

class SLLNode<E> implements Comparable<SLLNode<E>>{
	public E element;
	public SLLNode<E> next;

	public SLLNode(E element, SLLNode<E> next) {
		this.element = element;
		this.next = next;
	}

	@Override
	public int compareTo(SLLNode<E> o) {
		if(o.element instanceof Integer){
			return Integer.compare((Integer) element,(Integer) o.element);
		}
		//throw exception not implemented (out of scope for this lab)
		return 0;
	}
}

class SLL<E> implements Iterable<E>{
	public SLLNode<E> first;

	public SLL() {
	}

	public void insertLast(E element){
		SLLNode<E> newNode = new SLLNode<E>(element,null);
		if(first==null){
			first = newNode;
			return;
		}
		SLLNode<E> temp = first;
		while (temp.next!=null){
			temp = temp.next;
		}
		temp.next = newNode;
		return;
	}

	public SLL<E> joinLists(SLL<E> other){
		SLLNode<E> thisTemp = first;
		SLLNode<E> otherTemp = other.first;
		SLL<E> joined = new SLL<E>();
		while (thisTemp!=null && otherTemp!=null){
			while (thisTemp.next!=null && thisTemp.compareTo(thisTemp.next)==0){
				thisTemp = thisTemp.next;
			}
			while (otherTemp.next!= null && otherTemp.compareTo(otherTemp.next)==0){
				otherTemp = otherTemp.next;
			}
			if(thisTemp.compareTo(otherTemp)<0){
				joined.insertLast(thisTemp.element);
				thisTemp = thisTemp.next;
			}
			else if(thisTemp.compareTo(otherTemp)>0){
				joined.insertLast(otherTemp.element);
				otherTemp = otherTemp.next;
			}
			else {
				joined.insertLast(otherTemp.element);
				thisTemp = thisTemp.next;
				otherTemp = otherTemp.next;
			}
		}
		while (thisTemp!=null) {
			while (thisTemp.next!=null && thisTemp.compareTo(thisTemp.next)==0){
				thisTemp = thisTemp.next;
			}
			joined.insertLast(thisTemp.element);
			thisTemp = thisTemp.next;
		}
		while (otherTemp!=null) {
			while (otherTemp.next!= null && otherTemp.compareTo(otherTemp.next)==0){
				otherTemp = otherTemp.next;
			}
			joined.insertLast(otherTemp.element);
			otherTemp = otherTemp.next;
		}
		return joined;
	}

	@Override
	public Iterator<E> iterator() {
		Iterator<E> it = new Iterator<E>() {
			@Override
			public boolean hasNext() {
				return first !=null;
			}

			@Override
			public E next() {
				E el = first.element;
				first = first.next;
				return el;
			}
		};
		return it;
	}

	//for debug
	@Override
	public String toString() {
		String str = "";
		SLLNode<E> temp = first;
		while (temp!=null){
			str+=temp.element+" ";
			temp = temp.next;
		}
		return str;
	}
}

public class SLLJoinLists {
	public static void main(String[] args) throws IOException {

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String s = stdin.readLine();
		int N = Integer.parseInt(s);
		s = stdin.readLine();
		String[] pomniza = s.split(" ");
		SLL<Integer> lista1 = new SLL<Integer>();
		for (int i = 0; i < N; i++) {
			lista1.insertLast(Integer.parseInt(pomniza[i]));
		}

		s = stdin.readLine();
		N = Integer.parseInt(s);
		s = stdin.readLine();
		pomniza = s.split(" ");
		SLL<Integer> lista2 = new SLL<Integer>();
		for (int i = 0; i < N; i++) {
			lista2.insertLast(Integer.parseInt(pomniza[i]));
		}

		SLL<Integer> spoeni = lista1.joinLists(lista2);
		Iterator<Integer> it = spoeni.iterator();
		while (it.hasNext()) {
			System.out.print(it.next());
            if(it.hasNext())
                System.out.print(" ");
		}
        System.out.println();
	}
}
