#include <stdio.h>
#define MAX 100
int main() {
    int n, element, a[MAX], i;
    short rastecka = 1, opagacka = 1;
    scanf("%d", &n);
    for (i = 0; i < n; ++i)
        scanf("%d", &a[i]);
    for (i = 0; i < n - 1; ++i) {
        if (a[i] >= a[i + 1]) {
            rastecka = 0;
            break;
        }
    }
    for (i = 0; i < n - 1; ++i) {
        if (a[i] <= a[i + 1]) {
            opagacka = 0;
            break;
        }
    }
    if (!opagacka && !rastecka)
        printf("Nizata ne e nitu rastechka nitu opagjachka\n");
    else if (opagacka)
        printf("Nizata e opagjachka\n");
    else if (rastecka)
        printf("Nizata e rastechka\n");
    return 0;
}

