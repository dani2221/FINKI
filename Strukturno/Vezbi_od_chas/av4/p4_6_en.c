#include <stdio.h>

int main() {
    float a, b, c;
    printf("Enter lengths of sides: \n");
    scanf("%f %f %f", &a, &b, &c);
    if ((a + b <= c) || (a + c <= b) || (b + c <= a))
        printf("A triangle can not be constructed.\n");
    else {
        if (a >= b) {
            float tmp = a; 
            a = b; 
            b = tmp;
        }
        if (a >= c) {
            float tmp = a; 
            a = c; 
            c = tmp;
        }
        if (b >= c) {
            float tmp = b; 
            b = c; 
            c = tmp;
        }
        // now the longest side is in the variable c
        if (c * c == a * a + b * b) {
            printf("The triangle is right triangle.\n");
            printf("Area is %7.3f\n", a * b / 2);
        } else {
            printf("The triangle is NOT a right triangle.\n");
        }
    }
    return 0;
}
