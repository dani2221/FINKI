#include <stdio.h>

double cube(int x) {
    return x * x * x;
}

int main() {
    int n;
    scanf("%d", &n);
    double result = cube(n);

    printf("%d^3 = %.2f\n", n, result);
    return 0;
}
