#include <stdio.h>
int main() {
    int integer1; /* first number entered by the user */
    int integer2; /* second number entered by the user */
    int sum; /* variable for storing the sum */
    float quotient; /* the resut from the division */

    printf("Enter the first integer\n");
    scanf("%d", &integer1);

    printf("Enter the second integer\n");
    scanf("%d", &integer2); /* read integer */

    sum = integer1 + integer2; /* assign the sum with the result from the addition */
    quotient = (float) integer1 / integer2; /* assign the quotient with the result from the division */

    printf("The sum is %d\n", sum); /* prints the sum */
    printf("Their quotient is %.2f\n", kol); /* prints the quotient */

    return 0;
}
