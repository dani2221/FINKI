package mk.ukim.finki.aud0;

public class RefactoringExample {

    int countAllNumbersDivisibleWithDigitsSum (int start, int end) {
        int count = 0;
        for (int i=start; i<=end; i++) {
            if (i%getSumOfDigits(i)==0)
                ++count;
        }
        return count;
    }

    private int getSumOfDigits(int temp) {
        int sumOfDigits = 0;
        while (temp >0) {
            sumOfDigits+=(temp %10);
            temp /=10;
        }
        return sumOfDigits;
    }
}
