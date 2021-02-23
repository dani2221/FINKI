#include <stdio.h>
int main () {
    int n = 1, i = 0, number, div, r1, r2;
    div = r1 = r2 = 0; /*  brojaci */
    scanf("%d", &n);   /* koklu broevi kje bidat vneseni */
    for (i = 0; i < n; ++i) {
        scanf("%d", &number);
        if (number % 3 == 0)
            div++;
        else if (number % 3 == 1)
            r1++;
        else r2++;
    }
    printf("%d\n", div);
    printf("%d\n", r1);
    printf("%d\n", r2);
    return 0;
}
