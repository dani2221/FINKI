#include <stdio.h>
#include <stdlib.h>
#define MAX 100
int main() {
    int i, j, m, n;
    float a[MAX][MAX], b[MAX][MAX];
    FILE *input, *output;
    if ((input = fopen("matrica1.txt", "r")) == NULL) {
        printf("Datotekata matrica1.txt ne se otvora!\n");
        exit(1);
    }
    if (!feof(input))
        fscanf(input, "%d %d", &m, &n);

    if ((m > MAX) || (n > MAX)) {
        printf("Mnogu golema matrica!");
        return (-1);
    }
    for (i = 0; i < m && !feof(input); i++)
        for (j = 0; j < n && !feof(input); j++)
            fscanf(input, "%f", &a[i][j]);
    fclose(input);
    if (i != m || j != n) {
        printf("Nema dovolno podatoci vo datotekata!");
        return (-1);
    }
    for (i = 0; i < m; i++)
        for (j = 0; j < n; j++)
            b[j][i] = a[i][j];
    if ((output = fopen("matrica2.txt", "w")) == NULL) {
        printf("Datotekata matrica2.txt ne se otvora!\n");
        exit(1);
    }
    fprintf(output, "%d %d\n", n, m); /* obratno */

    for (i = 0; i < n; i++)
        for (j = 0; j < m; j++)
            fprintf(output, "%7.2f\n", b[i][j]);
    fclose(output);
    return (0);
}
