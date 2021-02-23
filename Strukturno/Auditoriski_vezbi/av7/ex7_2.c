#include <stdio.h>
#include <math.h>

int main() {
    int n;
    printf("Vnesete eden priroden broj: ");
    scanf("%d", &n);
    double rezultat = pow(n, 3);

    printf("Kubot na brojot %d e %.2f\n", n, rezultat);
    return 0;
}
