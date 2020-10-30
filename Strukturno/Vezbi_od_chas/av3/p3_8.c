#include <stdio.h>
int main() {
    long int datum;
    int den, mesec;
    printf("Vnesete datum na ragjanje:\n");
    scanf("%ld", &datum);
    den = datum / 1000000;
    mesec = (datum / 10000) % 100;
    printf("Denot i mesecot na ragjanje se %02d.%02d\n", den, mesec);
    return 0;
}
