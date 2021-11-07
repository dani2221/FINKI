package MinMax;

import java.util.Scanner;

public class MinAndMax {
	public static void main(String[] args) throws ClassNotFoundException {
		Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        MinMax<String> strings = new MinMax<String>();
        for(int i = 0; i < n; ++i) {
            String s = scanner.next();
            strings.update(s);
        }
		System.out.println(strings);
		MinMax<Integer> ints = new MinMax<Integer>();
        for(int i = 0; i < n; ++i) {
           	int x = scanner.nextInt();
            ints.update(x);
        }
        System.out.println(ints);
	}
}

class MinMax<T extends Comparable<T>>{
    private T min;
    private T max;
    private int processedElements;
    private int maxCount;
    private int minCount;

    public MinMax() {
        this.processedElements = 0;
        this.maxCount = 0;
        this.minCount = 0;
    }
    public void update(T element){
        if (processedElements == 0) max = min = element;
        if (max.compareTo(element) == 0) ++maxCount;
        if (min.compareTo(element) == 0) ++minCount;
        if (max.compareTo(element) < 0) {
            max = element;
            maxCount = 1;
        }
        if (min.compareTo(element) > 0) {
            min = element;
            minCount = 1;
        }
        ++processedElements;
    }

    @Override
    public String toString() {
        return min+" "+max+" "+(processedElements-maxCount-minCount)+"\n";
    }
}