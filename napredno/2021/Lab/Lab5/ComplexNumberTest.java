import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;


//		Комплексни броеви Problem 1
//		Треба да се развие генеричка класа за работа со комплексни броевиComplexNumber со два генерички параметри T и U кои мора да бидат од некоја класа која наследува од класата Number (T extends Number). ComplexNumber има две променливи од кои едната се однесува на реалниот дел, а другата на имагинарниот дел и треба да ги имплементира следните методи:
//
//		ComplexNumber(T real, U imaginary) - конструктор кој ги иницијализира сите променливи
//		getReal():T
//		getImaginary():U
//		modul():double - го пресметува модулот на комплексниот број
//		compareTo(ComplexNumber<?, ?> o) - прави споредување врз основа на модулите на двата комплексни броја
//		toString():String - го печати бројот во следниот формат 2.30+3.00i

public class ComplexNumberTest {

	public static void main(String[] args) {
		Scanner jin = new Scanner(System.in);
		int k = jin.nextInt();
		if ( k == 0 ) { //test simple functions int
			int r = jin.nextInt();int i = jin.nextInt();
			ComplexNumber<Integer, Integer> c = new ComplexNumber<Integer, Integer>(r, i);
			System.out.println(c);
			System.out.println(c.getReal());
			System.out.println(c.getImaginary());
			System.out.println(c.modul());
		}
		if ( k == 1 ) { //test simple functions float
			float r = jin.nextFloat();
			float i = jin.nextFloat();
			ComplexNumber<Float, Float> c = new ComplexNumber<Float, Float>(r, i);
			System.out.println(c);
			System.out.println(c.getReal());
			System.out.println(c.getImaginary());
			System.out.println(c.modul());
		}
		if ( k == 2 ) { //compareTo int
			LinkedList<ComplexNumber<Integer,Integer>> complex = new LinkedList<ComplexNumber<Integer,Integer>>();
			while ( jin.hasNextInt() ) {
				int r = jin.nextInt(); int i = jin.nextInt();
				complex.add(new ComplexNumber<Integer, Integer>(r, i));
			}
			System.out.println(complex);
			Collections.sort(complex);
			System.out.println(complex);
		}
		if ( k == 3 ) { //compareTo double
			LinkedList<ComplexNumber<Double,Double>> complex = new LinkedList<ComplexNumber<Double,Double>>();
			while ( jin.hasNextDouble() ) {
				double r = jin.nextDouble(); double i = jin.nextDouble();
				complex.add(new ComplexNumber<Double, Double>(r, i));
			}
			System.out.println(complex);
			Collections.sort(complex);
			System.out.println(complex);
		}
		if ( k == 4 ) { //compareTo mixed
			LinkedList<ComplexNumber<Double,Integer>> complex = new LinkedList<ComplexNumber<Double,Integer>>();
			while ( jin.hasNextDouble() ) {
				double r = jin.nextDouble(); int i = jin.nextInt();
				complex.add(new ComplexNumber<Double, Integer>(r, i));
			}
			System.out.println(complex);
			Collections.sort(complex);
			System.out.println(complex);
		}
	}
}

class ComplexNumber<T extends Number, U extends Number> implements Comparable<ComplexNumber<T, U>> {
	private T real;
	private U imaginary;

	public ComplexNumber(T real, U imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	public T getReal() {
		return real;
	}

	public U getImaginary() {
		return imaginary;
	}

	public double modul() {
		return Math.sqrt(real.doubleValue()*real.doubleValue()+imaginary.doubleValue()*imaginary.doubleValue());
	}

	@Override
	public String toString() {
		return imaginary.doubleValue()>=0 ? String.format("%.2f+%.2fi",real.doubleValue(),imaginary.doubleValue()) : String.format("%.2f%.2fi",real.doubleValue(),imaginary.doubleValue());
	}

	@Override
	public int compareTo(ComplexNumber<T, U> o) {
		return Double.compare(this.modul(),o.modul());
	}
}