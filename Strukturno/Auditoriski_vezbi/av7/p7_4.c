#include <stdio.h>
#define PI 3.14

double dijametar(double radius);
double perimetar(double radius);
double ploshtina(double radius);

int main() {
    double radius, D, L, P;
    printf("Vnesete radius na krugot: ");
    scanf("%lf", &radius);

    D = dijametar(radius);
    L = perimetar(radius);
    P = ploshtina(radius);

    printf("Dijametar na krugot = %.2f\n", D);
    printf("Perimetar na krugot = %.2f\n", L);
    printf("Ploshtina na krugot = %.2f\n", P);
    return 0;
}

double dijametar(double radius) {
    return 2 * radius;
}

double perimetar(double radius) {
    return 2 * radius * PI;
}

double ploshtina(double radius) {
    return radius * radius * PI;
}
