import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

//		Дадени се две еднострано поврзани листи чии што јазли содржат по еден природен број. Треба да се спојат двете листи во една резултантна на тој начин што наизменично прво ќе се додаваат првите два јазли од првата листа во резултантната, па првите два од втората листа, па следните два од првата, па следните два од втората итн. Јазлите што ќе останат треба да се додадат на крај во резултантната листа, прво оние што останале од првата листа, потоа оние што останале од втората листа.
//
//		Во првиот ред од влезот се дадени броевите од кои се составени јазлите по редослед во првата листа, а во вториот ред броевите од кои се составени јазлите по редослед во втората листа. На излез треба да се испечатат јазлите по редослед во резултантната споена листа.
//
//		Забелешка: Да се креира податочна структура еднострано поврзана листа и истата да се искористи во задачата.

class SllNode<E> implements Comparable<SllNode<E>>{
	public E element;
	public SllNode<E> next;

	public SllNode(E element, SllNode<E> next) {
		this.element = element;
		this.next = next;
	}

	@Override
	public int compareTo(SllNode<E> o) {
		if(o.element instanceof Integer){
			return Integer.compare((Integer) element,(Integer) o.element);
		}
		//throw exception not implemented (out of scope for this lab)
		return 0;
	}
}

class Sll<E>{
	public SllNode<E> first;

	public Sll() {
	}

	public void insertLast(E element){
		SllNode<E> newNode = new SllNode<E>(element,null);
		if(first==null){
			first = newNode;
			return;
		}
		SllNode<E> temp = first;
		while (temp.next!=null){
			temp = temp.next;
		}
		temp.next = newNode;
		return;
	}

	public static<E> Sll<E> specialJoin(Sll<E> firstList,Sll<E> otherList){
		SllNode<E> thisTemp = firstList.first;
		SllNode<E> otherTemp = otherList.first;
		Sll<E> joined = new Sll<E>();
		while (true){
			if(thisTemp == null) break;
			joined.insertLast(thisTemp.element);
			thisTemp = thisTemp.next;
			if(thisTemp == null) break;
			joined.insertLast(thisTemp.element);
			thisTemp = thisTemp.next;

			if(otherTemp == null) break;
			joined.insertLast(otherTemp.element);
			otherTemp = otherTemp.next;
			if(otherTemp == null) break;
			joined.insertLast(otherTemp.element);
			otherTemp = otherTemp.next;
		}
		while (thisTemp!=null) {
			joined.insertLast(thisTemp.element);
			thisTemp = thisTemp.next;
		}
		while (otherTemp!=null) {
			joined.insertLast(otherTemp.element);
			otherTemp = otherTemp.next;
		}
		return joined;
	}

	@Override
	public String toString() {
		String str = "";
		SllNode<E> temp = first;
		while (temp!=null){
			str+=temp.element+" ";
			temp = temp.next;
		}
		return str;
	}
}

public class SpecialSLLJoin {
	
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
		String s = stdin.readLine();
		int N = Integer.parseInt(s);
		s = stdin.readLine();
		String[] pomniza = s.split(" ");
		Sll<Integer> lista1 = new Sll<Integer>();
		for (int i = 0; i < N; i++) {
			lista1.insertLast(Integer.parseInt(pomniza[i]));
		}

		s = stdin.readLine();
		N = Integer.parseInt(s);
		s = stdin.readLine();
		pomniza = s.split(" ");
		Sll<Integer> lista2 = new Sll<Integer>();
		for (int i = 0; i < N; i++) {
			lista2.insertLast(Integer.parseInt(pomniza[i]));
		}
		
		Sll<Integer> spoeni = Sll.specialJoin(lista1,lista2);
		System.out.println(spoeni);
	}
}
