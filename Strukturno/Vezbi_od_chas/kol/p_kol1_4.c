#include <stdio.h>
int main () {
    int c, prev;
    int first = 1, tmp, noDigits, prevNoDigits;
    int n = 1, m;
    scanf("%d", &m);
    while (scanf ("%d", &c)) {
        tmp = c;
        noDigits = 0;
        while (tmp) {
            noDigits++;
            tmp /= 10;
        }
        if (first) {
            first = 0;
        } else {
            if (c > prev && noDigits == m && prevNoDigits == m) {
                n++;
            } else {
                if (n >= 2 ) {
                    printf("%d ", n);
                }
                n = 1;
            }
        }
        prev = c;
        prevNoDigits = noDigits;
    }
    if (n >= 2 ) {
        printf("%d", n);
    }
    return 0;
}
