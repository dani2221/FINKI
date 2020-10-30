#include <stdio.h>

int is_prime(int n) {
    if(n < 4) return 1;
    else {
        if(n % 2 == 0) return 0;
        else {
            int i;
            for(i = 3; i * i <= n; i += 2) {
                if(n % i == 0) {
                    return 0;
                }
            }
        }
    }
    return 1;
}

int sum_digits(int n) {
    int sum = 0;
    while(n != 0) {
        sum += n % 10;
        n /= 10;
    }
    return sum;
}

int main() {
    int i, count = 0;
    for(i = 2; i <= 9999; ++i) {
        if(is_prime(i) && is_prime(sum_digits(i))) {
            printf("%d\t", i);
            ++count;
        }
    }
    printf("\nVkupno: %d\n", count);
    return 0;
}
