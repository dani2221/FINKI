#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
int main() {
    char c;
    int brPojavuvanja = 0;
    FILE *dat;
    if ((dat = fopen("dat.txt", "r")) == NULL) {
        printf("Datotekata %s ne se otvora!\n", "dat.txt");
        exit(-1);
    }
    char zbor[50];
    printf("Vnesete zbor za koj kje se bara brojot na pojavuvanja:");
    gets(zbor);
    int i = 0, br = 0;
    while ((c = fgetc(dat)) != EOF) {
        if (isdigit(c)) {
            if (c != zbor[i++]) {
                if (br == strlen(zbor)) {
                    brPojavuvanja++;
                }
                br = 0;
                i = 0;
            } else {
                br++;
            }
        } else {
            if (br == strlen(zbor)) {
                brPojavuvanja++;
            }
            br = 0;
            i = 0;
        }
    }
    printf("Zborot %s se pojavuva %d pati vo datotekata\n", zbor,
           brPojavuvanja);
    return 0;
}
