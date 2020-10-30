#include <stdio.h>
#define MAX 100
int main () {
    int a[MAX][MAX], n, i, j, simetrichna = 1;
    printf("Vnesete dimenzija na kvadratna matrica: \n");
    scanf("%d", &n);
    printf("Vnesete gi elementite: \n");
    for (i = 0; i < n; ++i)
        for (j = 0; j < n; ++j)
            scanf ("%d", &a[i][j]);
    for (i = 0; i < n - 1; ++i) {
        for (j = i + 1; j < n; ++j)
            if (a[i][j] != a[j][i]) {
                simetrichna = 0;
                break;
            }
        if (!simetrichna) break;
    }
    if (simetrichna)
        printf("Matricata e SIMETRICHNA vo odnos na glavnata dijagonala\n");
    else
        printf("Matricata ne e SIMETRICHNA vo odnos na glavnata dijagonala\n");
    return 0;
}
