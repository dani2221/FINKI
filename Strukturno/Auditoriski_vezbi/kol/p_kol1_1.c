#include <stdio.h>
int main () {
    int i, n, maxL = 0, maxIndeks = 0;
    int a, b, c, l;
    scanf ("%d", &n);
    for (i = 0; i < n; i ++) {
        scanf ("%d %d %d", &a, &b, &c);
        if (a + b > c && a + c > b && b + c > a) {
            printf("Moze\n");
            l = a + b + c;
            if (l >= maxL) {
                maxL = l ;
                maxIndeks = i;
            }
        }
        else
            printf("Ne moze\n");
    }
    printf ("Najgolem perimetar: %d, reden broj %d\n", maxL, maxIndeks + 1);
    return 0;
}
