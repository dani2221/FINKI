#include <stdio.h>

int main() {
    int godina;
    printf ("Vnesi godina: \n");
    scanf ("%d", &godina);
    if ((godina % 4 == 0 && godina % 100 != 0) || godina % 400 == 0)
        printf("%d e prestapna.\n", godina);
    else
        printf("%d e prosta.\n", godina);
    return 0;
}
