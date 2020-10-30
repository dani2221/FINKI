#include<stdio.h>
#define MAX 100
int main() {
    int a[MAX][MAX], n, m, i, j, sumKol = 0, sumRed = 0;
    scanf("%d %d", &n, &m);
    for (i = 0; i < n; ++i)
        for (j = 0; j < m; ++j)
            scanf("%d", &a[i][j]);

    for (i = 0; i < n; ++i)
        for (j = 0; j < m; ++j) {
            if ((j + 1) % 2)
                sumKol += a[i][j];
            if (!((i + 1) % 2))
                sumRed += a[i][j];
        }
    printf("%d", sumKol - sumRed);
    return 0;
}
