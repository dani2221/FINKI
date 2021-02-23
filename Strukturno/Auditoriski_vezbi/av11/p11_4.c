#include <stdio.h>
#include <stdlib.h>
int e_samoglaska(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
}
int main() {
    int red = 0, vkupno = 0;
    FILE *dat; char c;
    if ((dat = fopen("SP_primer.txt", "r")) == NULL) {
        printf("Datotekata SP_primer.txt ne se otvora");
        exit(-1);
    }
    int samoglaski = 0;
    while ((c = fgetc(dat)) != EOF) {
        if(e_samoglaska(tolower(c))) {
            ++samoglaski;
            ++vkupno;
        }
        if (c == '\n') {
            if (samoglaski > 10) {
                red++;
            }
            samoglaski = 0;
        }
    }
    if (samoglaski > 10) {
            red++;
    }
    printf("Vkupno %d reda imaat povekje od 10 samoglaski\n", red);
    printf("Vo datotekata ima vkupno %d samoglaski.\n", vkupno);
    return 0;
}
