#include <stdio.h>
#define MAX 100

int factoriel(int n) {
    if (n==1) {
        return 1;
    }

    return n * factoriel(n-1);
}

int main() {
    
    int n, result;

    printf("Vnesi broj na koj sakas da presmetas faktoriel: ");

    scanf("%d", &n);

    result = factoriel(n);
    
    printf("Factoriel na %d e %d.", n, result);

    return 0;
}
