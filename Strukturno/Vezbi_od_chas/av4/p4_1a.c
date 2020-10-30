#include <stdio.h>

int main() {
    int a, b;
    printf("Vnesi 2 broja: \n");
    scanf("%d %d", &a, &b);
    if (a > b)
        printf("Maximum: %d\n", a);
    else
        printf("Maximum: %d\n", b);
    return 0;
}
