#include <stdio.h>

int main() {
    float a = 5.0;
    float b = 7.5;
    float c = 10.2;
    float L = a + b + c;
    float s = L / 2;
    float P = s * (s - a) * (s - b) * (s - c);
    printf("Perimetarot e: %.2f\n", L);
    printf("Kvadratot na ploshtinata e: %.2f\n", P);
    return 0;
}
