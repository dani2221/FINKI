#include <stdio.h>
int main () {
    int n = 1, i = 0, number, div, r1, r2;
    div = r1 = r2 = 0; /*  counters */
    scanf("%d", &n);   /* numbers read from SI */
    while (i < n) {
        scanf("%d", &number);
        if (number % 3 == 0)
            div++;
        else if (number % 3 == 1)
            r1++;
        else r2++;
        i++;
    }
    printf("%d\n", div);
    printf("%d\n", r1);
    printf("%d\n", r2);
    return 0;
}
