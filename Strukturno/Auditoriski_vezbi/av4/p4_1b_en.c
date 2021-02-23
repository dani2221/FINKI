#include <stdio.h>
int main() {
    int a, b;
    printf("Enter two numbers: \n");
    scanf("%d %d", &a, &b);
    printf("Maximum: %d\n", (a > b) ? a : b);
    return 0;
}
