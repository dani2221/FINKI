#include <stdio.h>
#define MAX 100
int main () {
    int a[MAX][MAX], n, i, j, is_symmetrical = 1;
    scanf("%d", &n);
    for (i = 0; i < n; ++i)
        for (j = 0; j < n; ++j)
            scanf ("%d", &a[i][j]);
    for (i = 0; i < n - 1; ++i) {
        for (j = i + 1; j < n; ++j)
            if (a[i][j] != a[j][i]) {
                is_symmetrical = 0;
                break;
            }
        if (!is_symmetrical) break;
    }
    if (is_symmetrical)
        printf("Symmetrical\n");
    else
        printf("Not symmetrical\n");
    return 0;
}
