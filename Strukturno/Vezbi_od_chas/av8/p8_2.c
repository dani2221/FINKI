#include <stdio.h>

int is_prime(int n, int i);
int first_larger_prime(int n);

int main() {
    int number, difference;
    scanf("%d", &number);
    difference = first_larger_prime(number) - number;
    printf("%d - %d : %d\n", first_larger_prime(
               number), number, difference);
    return 0;
}
int is_prime(int n, int i) {
    if (n < 4)
        return 1;
    else if ((n % 2) == 0) return 0;
    else if (n % i == 0) return 0;
    else if (i * i > n) return 1;
    else return is_prime(n, i + 2);
}
int first_larger_prime(int n) {
    if (is_prime(n + 1, 3)) return n + 1;
    else return first_larger_prime(n + 1);
}
