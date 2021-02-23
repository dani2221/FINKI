#include <stdio.h>

int first_two(int n) {
    while(n > 99) {
        n /= 10;
    }
    return n;
}

int last_two(int n) {
    return n % 10;
}

int divisible(int n, int x) {
    return n % x == 0;
}

int main() {
    int i;
    int count = 0;
    for(i = 1000; i <= 9999; ++i) {
        if(divisible(i, first_two(i) + last_two(i))) {
            printf("%d\n", i);
            ++count;
        }
    }
    printf("Total: %d\n", count);
    return 0;
}
