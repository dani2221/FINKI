#include <stdio.h>
#define MAX 100
int main() {
    int a[MAX], n, i, j, k, removed = 0;
    scanf("%d", &n);
    for (i = 0; i < n; ++i)
        scanf("%d", &a[i]);
    for (i = 0; i < n - removed; ++i)
        for (j = i + 1; j < n - removed; ++j)
            if (a[i] == a[j]) {
                for (k = j; k < n - 1 - removed; ++k)
                    a[k] = a[k + 1];
                removed++;
                --j;
            }
    n -= removed;
    for (i = 0; i < n; ++i)
        printf("%d\t", a[i]);
    return 0;
}

