#include <stdio.h>
int main() {
    char op;
    float num1, num2, result = 0;
    printf("num1 operator num2\n");
    scanf("%f %c %f", &num1, &op, &num2);
    switch (op) {
    case '+':
        result = num1 + num2;
        break;
    case '-':
        result = num1 - num2;
        break;
    case '*':
        result = num1 * num2;
        break;
    case '/':
        if (num2 == 0) {
            printf("Division with 0\n");
        } else {
            result = num1 / num2;
        }
        break;
    default:
        printf("Unknown operator %c\n", op);
        break;
    }
    if (result) printf("%.2f %c %.2f = %f", num1, op, num2, result);
    return 0;
}
