#include <stdio.h>
int main() {
    char op;
    float num1, num2, result;
    printf("Enter two numbers and operator in format\n");
    printf(" num1 operator num2\n");
    scanf("%f %c %f", &num1, &op, &num2);
    if (op == '*')  {
        result = num1 * num2;
    }
    else if (op == '+')  {
        result = num1 + num2;
    }
    else if (op == '-') {
        result = num1 - num2;
    }
    else if (op == '/') {
        if (num2) {
            result = num1 / num2;
        }
        else {
            printf("Division by 0!\n");
            return 0;
        }
    } else {
        printf("Invalid operator!\n");
        return 0;
    }
    printf("%f %c %f = %f\n", num1, op, num2, result);
    return 0;
}

