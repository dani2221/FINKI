#include <stdio.h>
int sum_digits(int n) {
    if (n == 0) return 0;
    return n % 10 + sum_digits(n / 10);
}
