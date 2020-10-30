#include <stdio.h>
int main() {
    char odgovor;
    printf("Dali SP e lesen predmet? (d/n): ");
    //odgovor=getchar();
    scanf("%c", &odgovor);

    switch (odgovor) {
    case 'D':
    case 'd': printf("I jas mislam taka!\n");
        break;
    case 'N':
    case 'n': printf("Navistina?\n");
        break;
    default:
        printf("Ova e DA ili NE?\n");
    }

    return 0;
}
