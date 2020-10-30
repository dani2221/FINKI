#include <stdio.h> 
int main() {
    int ocena, suma = 0, broceni = 0, padnal = 0;
    while (scanf("%d", &ocena) == 1) {
        switch (ocena) {
            case 6:
            case 7:
            case 8:
            case 9:
            case 10: suma += ocena; 
                     broceni++; break;
            case 5:  padnal++; break;
            default: printf("vnesi ocena od 5 do 10 : "); break;
        }
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
