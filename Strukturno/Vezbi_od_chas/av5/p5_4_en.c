#include <stdio.h>
int main() {
    int i, n, sum, first_digit, digit;
    i = 1000;
    while (i <= 9999) {
        first_digit = i / 1000;
        n = i % 1000;
        sum = 0;
        while (n > 0) {
            digit = n % 10;
            sum += digit;
            n /= 10;
        }
        if (sum == first_digit) printf("%d\t", i);
        i++;
    }
    return 0;
}
