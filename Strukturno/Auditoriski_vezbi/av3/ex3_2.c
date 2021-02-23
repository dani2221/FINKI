#include <stdio.h>
int main() {
    int integer1; /* prviot broj sto kje go vnesuva korisnikot */
    int integer2; /* vtoriot broj sto kje go vnesuva korisnikot */
    int sum; /* promenliva vo koja kje se zachuva sumata */
    float kol; /* promenliva vo koja kje se zachuva kolichnikot */

    printf("Vnesi prv cel broj\n");
    scanf("%d", &integer1);

    printf("Vnesi vtor cel broj\n"); /* procitaj cel broj */
    scanf("%d", &integer2); /* procitaj cel broj */

    sum = integer1 + integer2; /* dodeli go zbirot na sum */
    kol = (float) integer1 / integer2; /* dodeli go kolicnikot na kol */

    printf("Nivnata suma e %d\n", sum); /* pecati sum */
    printf("Nivniot kolicnik e %.2f\n", kol); /* pecati kol */

    return 0;
}
