#include <stdio.h>
#include <string.h>
#define MAX 100

int main() {
    char s[MAX], dest[MAX];
    int pozicija, dolzhina;
    gets(s);
    scanf("%d %d", &pozicija, &dolzhina);
    if (pozicija <= strlen(s)) {
        strncpy(dest, s + pozicija - 1, dolzhina);
        dest[dolzhina] = '\0';
        printf("Rezultat: ");
        puts(dest);
    } else
        printf("Nevaliden vnes, prochitaniot string ima samo %d znaci.\n",
               strlen(s));
    return 0;
}
