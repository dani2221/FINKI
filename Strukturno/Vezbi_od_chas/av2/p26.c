#include <stdio.h>
#define PI 3.14

int main() {
    float radius;
    scanf("%f", &radius);

    float perimetar = 2 * radius * PI ;
    float plostina = radius * radius * PI;
    printf("L = %f\n", perimetar);
    printf("P = %f\n", plostina);
    return 0;
}
