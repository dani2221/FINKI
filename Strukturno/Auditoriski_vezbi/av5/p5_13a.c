#include <stdio.h>
int main() {
    int ocena, suma = 0, broceni = 0, padnal = 0;
    while (scanf("%d", &ocena) == 1) {
        while (ocena < 5 || ocena > 10) {
            printf("vnesi ocena od 5 do 10 : ");
            if (scanf("%d", &ocena) != 1) break;
        }
        /* presmetuvanje */
        if (ocena >= 6 && ocena <= 10) {
            suma += ocena;
            broceni++;
        }
        else /*if (ocena == 5) */
            padnal++;
    }
    if (broceni == 0)
        printf("Nema polozeni ispiti");
    else
        printf("Prosekot e %4.2f\n", (float)suma / broceni);
    if (padnal != 0)
        printf("Padnal na %d ispiti\n", padnal);
    else
        printf("Ne padnal na ispit\n");
    return 0;
}
