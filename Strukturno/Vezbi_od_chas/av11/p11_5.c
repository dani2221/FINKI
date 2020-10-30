#include <stdio.h>
#include <ctype.h>
#define DOLZINA 21

int ima_poveke_od2isti(char *w) {
    char *c;
    int isti;
    while (*w) {
        c = w + 1;
        isti = 1;
        while (*c) {
            if (tolower(*w) == tolower(*c))
                isti++;
            c++;
        }
        if (isti > 2)
            return 1;
        w++;
    }
    return 0;
}
int main() {
    char zbor[DOLZINA];
    FILE *f;
    int brzb = 0;
    if ((f = fopen("zborovi.txt", "r")) == NULL) {
        printf("Datotekata %s ne se otvora.\n", "zborovi.txt");
        return -1;
    }
    while (fgets(zbor, DOLZINA, f) != NULL) {
        if (ima_poveke_od2isti(zbor)) {
            puts(zbor);
            brzb++;
        }
    }
    printf("\nVkupno %d zborovi.\n", brzb);
    fclose(f);
    return 0;
}
