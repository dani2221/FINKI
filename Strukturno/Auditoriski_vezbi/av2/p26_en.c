#include <stdio.h>
#define PI 3.14

int main() {
    float radius;
    scanf("%f", &radius);

    float perimeter = 2 * radius * PI ;
    float area = radius * radius * PI;
    printf("L = %f\n", perimeter);
    printf("P = %f\n", area);
    return 0;
}
