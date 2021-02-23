#include <stdio.h>

int main () {
    int poeni, ocenka = 0;
    printf("Vnesi poeni: \n");
    scanf("%d", &poeni);
    if (poeni < 0 || poeni > 100)
        printf("Nevalidna vrednost za poeni!\n");
    else {
        if (poeni > 90) ocenka = 10;
        else if (poeni > 80) ocenka = 9;
        else if (poeni > 70) ocenka = 8;
        else if (poeni > 60) ocenka = 7;
        else if (poeni > 50) ocenka = 6;
        else ocenka = 5;
        char znak = ' ';
        int pc = poeni % 10;
        if (ocenka != 5) {
            if (pc >= 1 && pc <= 3) znak = '-';
            else if (ocenka != 10 && (pc >= 8 || pc == 0))
                znak = '+';
        }
        printf("Ocenka %d%c\n", ocenka, znak);
    }
    return 0;
}
