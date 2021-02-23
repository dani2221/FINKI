#include <stdio.h>
#define PI 3.14

double diameter(double radius);
double perimeter(double radius);
double area(double radius);

int main() {
    double radius, D, L, P;
    scanf("%lf", &radius);

    D = diameter(radius);
    L = perimeter(radius);
    P = area(radius);

    printf("diameter of circle = %.2f\n", D);
    printf("perimeter of circle = %.2f\n", L);
    printf("area of circle = %.2f\n", P);
    return 0;
}

double diameter(double radius) {
    return 2 * radius;
}

double perimeter(double radius) {
    return 2 * radius * PI;
}

double area(double radius) {
    return radius * radius * PI;
}
