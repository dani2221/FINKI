#include <stdio.h>

double kub(int x) {
    return x * x * x;
}

int main() {
    int n;
    printf("Vnesete eden priroden broj: ");
    scanf("%d", &n);
    double rezultat = kub(n);

    printf("Kubot na brojot %d e %.2f\n", n, rezultat);
    return 0;
}
