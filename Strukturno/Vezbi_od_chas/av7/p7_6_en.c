#include <stdio.h>

int prime(int n) {
    int i;
    for(i = 2; i * i <= n; ++i) {
        if(n % i == 0) {
            return 0;
        }
    }
    return 1;
}

int first_larger_prime(int n) {
    ++n;
    while(!prime(n)) {
        ++n;
    }
    return n;
}

int main() {
    int n;
    scanf("%d", &n);
    int larger_prime = first_larger_prime(n);
    printf("%d - %d = %d\n", larger_prime, n, larger_prime - n);
    return 0;
}
