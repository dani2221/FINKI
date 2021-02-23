#include <stdio.h>
#define MAX 81

int main() {
    char linija[MAX], *c;
    FILE *vlezna, *izlezna;
    if ((vlezna = fopen("vlezna.txt", "r")) == NULL) {
        printf("Datotekata %s ne mozhe da se otvori.\n", "vlezna.txt");
        return -1;
    }
    if ((izlezna = fopen("izlezna.txt", "w")) == NULL) {
        printf("Datotekata %s ne mozhe da se otvori.\n", "izlezna.txt");
        return -1;
    }

    while ((fgets(linija, MAX, vlezna)) != NULL) {
        int br = strlen(linija);
        fprintf(izlezna, "%d\n%s", br, linija);
    }
    fclose(vlezna);
    fclose(izlezna);
    return 0;
}
