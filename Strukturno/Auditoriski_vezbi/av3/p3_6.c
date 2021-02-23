#include <stdio.h>

int main() {
    float cena, kamata, rata, vkupno;
    int brRati;
    printf("Vnesete ja cenata na proizvodot: ");
    scanf("%f", &cena);
    printf("Vnesete go brojot na rati: ");
    scanf("%d", &brRati);
    printf("Vnesete ja kamatata: ");
    scanf("%f", &kamata);
    vkupno = cena * (1 + kamata / 100);
    rata = vkupno / brRati;
    printf("Edna rata kje iznesuva: %.3f\n", rata);
    printf("Vkupnata isplatena suma ke bide %.3f\n", vkupno);
    return 0;
}
