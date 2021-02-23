#include <stdio.h>
int main() {
    int a, b;
    printf("Vnesi 2 broja: \n");
    scanf("%d %d", &a, &b);
    printf("Maximum: %d\n", (a > b) ? a : b);
    return 0;
}
