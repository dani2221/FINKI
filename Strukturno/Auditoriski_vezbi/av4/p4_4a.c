#include <stdio.h>
int main () {
    int poeni, ocenka = 0;
    printf("Vnesi poeni: \n");
    scanf("%d", &poeni);
    if (poeni >= 0 && poeni <= 50) ocenka = 5;
    else if (poeni > 50 && poeni <= 60) ocenka = 6;
    else if (poeni > 60 && poeni <= 70) ocenka = 7;
    else if (poeni > 70 && poeni <= 80) ocenka = 8;
    else if (poeni > 80 && poeni <= 90) ocenka = 9;
    else if (poeni > 90 && poeni <= 100) ocenka = 10;
    else printf("Nevalidna vrednost na poeni!\n");
    printf("Ocenka %d\n", ocenka);
    return 0;
}
