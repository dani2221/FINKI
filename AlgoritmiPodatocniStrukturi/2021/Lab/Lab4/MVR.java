import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//		Луѓето доаѓаат наутро во МВР за да извадат еден или повеќе документи.
//		Документите може да бидат:
//		1. Лична карта
//		2. Пасош
//		3. Возачка дозвола
//		Кога се отвора шалтерот прво се услужуваат луѓето кои чекаат за лична карта, па потоа оние за пасош и на крај оние за возачка дозвола.
//		Секој човек кога ќе дојде си застанува во редицата за соодветната исправа која ја вади (т.е. или во редицата за лични карти или во редицата за пасоши или во редицата за возачки дозволи). Доколку еден човек има повеќе документи за вадење прво вади лична карта, па пасош и на крај возачка. Така ако еден човек треба да вади и лична карта и возачка дозвола прво застанува во редицата за лични карти и откако ќе заврши таму оди на крајот на редицата за возачки дозволи.
//
//		Влез: Првиот ред означува колку луѓе вкупно дошле во МВР. Потоа за секој човек се внесуваат четири реда, во првиот е името и презимето на човекот, а во останатите три реда се кажува кој документ соодветно (лична карта, пасош и возачка) треба да се земе, притоа 1 значи дека треба да се земе тој документ, 0 значи дека не треба да се земе.

class Gragjanin{
	private String imePrezime;
	private int lKarta;
	private int pasos;
	private int vozacka;

	public String getImePrezime() {
		return imePrezime;
	}

	public int getlKarta() {
		return lKarta;
	}

	public int getPasos() {
		return pasos;
	}

	public int getVozacka() {
		return vozacka;
	}

	public Gragjanin(String imePrezime, int lKarta, int pasos, int vozacka) {
		this.imePrezime = imePrezime;
		this.lKarta = lKarta;
		this.pasos = pasos;
		this.vozacka = vozacka;
	}
}


public class MVR {

	public static void main(String[] args) {

		Scanner br = new Scanner(System.in);

		int N = Integer.parseInt(br.nextLine());

		Queue<Gragjanin> lkarti = new LinkedList<Gragjanin>();
		Queue<Gragjanin> pasosi = new LinkedList<Gragjanin>();
		Queue<Gragjanin> vozacki = new LinkedList<Gragjanin>();
		for (int i = 1; i <= N; i++) {
			String imePrezime = br.nextLine();
			int lKarta = Integer.parseInt(br.nextLine());
			int pasos = Integer.parseInt(br.nextLine());
			int vozacka = Integer.parseInt(br.nextLine());
			Gragjanin covek = new Gragjanin(imePrezime, lKarta, pasos, vozacka);
			if (covek.getlKarta() == 1) lkarti.add(covek);
			else if (covek.getPasos() == 1) pasosi.add(covek);
			else vozacki.add(covek);
		}
		while (lkarti.peek() != null) {
			Gragjanin covek = lkarti.poll();
			if (covek.getPasos() == 1) pasosi.add(covek);
			else if (covek.getVozacka() == 1) vozacki.add(covek);
			else System.out.println(covek.getImePrezime());
		}
		while (pasosi.peek() != null) {
			Gragjanin covek = pasosi.poll();
			if (covek.getVozacka() == 1) vozacki.add(covek);
			else System.out.println(covek.getImePrezime());
		}
		while (vozacki.peek() != null) {
			Gragjanin covek = vozacki.poll();
			System.out.println(covek.getImePrezime());
		}
	}
}
