#include <stdio.h>
#include <stdlib.h>
int main () {
    char c, prev, startL, endL;
    int first = 1, n = 1, m = 0;
    while (1) {
        scanf ("%c", &c);
        if (c < 'a' || c > 'z')
            break;
        if (first) {
            first = 0;
        } else {
            if (abs(prev - c) != 1) {
                if (prev < c) {
                    startL = prev + 1;
                    endL = c - 1;
                }
                else {
                    startL = c + 1;
                    endL = prev - 1;
                }
                for (n = 0; startL <= endL; startL++, n++)
                    printf("%c", startL);
                printf(" %d\n", n);
                m++;
            }
        }
        prev = c;
    }
    printf ("Vkupno: %d\n", m );
    return 0;
}
