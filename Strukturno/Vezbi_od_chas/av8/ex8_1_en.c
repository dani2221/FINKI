#include <stdio.h>
#define MAX 100

int factorial(int n) {
    if (n == 1) {
        return 1;
    }
    return n * factorial(n-1);
}

int main() {
    int n, result;
    scanf("%d", &n);
    printf("%d! = %d\n", n, factorial(n));
    return 0;
}
