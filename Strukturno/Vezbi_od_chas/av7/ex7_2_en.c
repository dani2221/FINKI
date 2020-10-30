#include <stdio.h>
#include <math.h>

int main() {
    int n;
    scanf("%d", &n);
    double result = pow(n, 3);

    printf("%d^3 = %.2f\n", n, result);
    return 0;
}
