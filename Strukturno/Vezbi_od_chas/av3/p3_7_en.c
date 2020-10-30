#include <stdio.h>
int main() {
    int number;
    printf("Enter number:\n");
    scanf("%d", &number);
    printf("Most significant digit is ");
    printf("%d, and least significant digit is %d.\n", (number / 100),
           (number % 10));
    return 0;
}
