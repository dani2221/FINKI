#include <stdio.h>

int main() {
    float cena;
    printf("Vnesete ja cenata na proizvodot: ");
    scanf("%f", &cena);
    printf("Vkupnata cena na proizvodot e %.2f\n", cena * 1.18);
    return 0;
}
