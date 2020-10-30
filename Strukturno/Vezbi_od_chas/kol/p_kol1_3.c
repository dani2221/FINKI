#include <stdio.h>
int main () {
    int i, n, maxPoeni, maxIndeks, f = 1;
    int pobedi, porazi, poeni, nivo;
    scanf ("%d", &n);
    for (i = 0; i < n; i ++) {
        scanf ("%d %d %d", &nivo, &pobedi, &porazi);
        if (nivo == 1)
            poeni = pobedi * 13 - porazi;
        if (nivo == 2)
            poeni = pobedi * 13 - porazi * 3;
        if (poeni > 0) printf("Dobar igrac\n");
        else printf("Los igrac\n");
        if (f) {
            f = 0;
            maxPoeni = poeni;
            maxIndeks = 0;
        }
        else if (poeni > maxPoeni) {
            maxPoeni = poeni ;
            maxIndeks = i;
        }
    }
    printf ("Najdobar igrac: br. %d, %d poeni\n", maxIndeks + 1, maxPoeni);
    return 0;
}
