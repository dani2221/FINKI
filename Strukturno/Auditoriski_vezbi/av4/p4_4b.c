#include <stdio.h>

int main () {
    int poeni, ocena = 0;
    printf("Vnesi poeni: \n");
    scanf("%d", &poeni);
    if (poeni < 0 || poeni > 100)
        printf("Nevalidna vrednost za poeni!\n");
    else {
        if (poeni > 90) ocena = 10;
        else if (poeni > 80) ocena = 9;
        else if (poeni > 70) ocena = 8;
        else if (poeni > 60) ocena = 7;
        else if (poeni > 50) ocena = 6;
        else ocena = 5;
        printf("Ocena %d\n", ocena);
    }
    return 0;
}
