#include <stdio.h>
#include <stdlib.h>
#define MAX 100
int main() {
    int i, j, m, n;
    float a[MAX][MAX], b[MAX][MAX];
    FILE *input, *output;
    if ((input = fopen("mat1.txt", "r")) == NULL) {
        printf("The file `mat1.txt` can not be opened!\n");
        exit(1);
    }
    if (!feof(input))
        fscanf(input, "%d %d", &m, &n);

    if ((m > MAX) || (n > MAX)) {
        printf("Very large matrix!");
        return (-1);
    }
    for (i = 0; i < m && !feof(input); i++)
        for (j = 0; j < n && !feof(input); j++)
            fscanf(input, "%f", &a[i][j]);
    fclose(input);
    if (i != m || j != n) {
        printf("Not enough data in the file!");
        return (-1);
    }
    for (i = 0; i < m; i++)
        for (j = 0; j < n; j++)
            b[j][i] = a[i][j];
    if ((output = fopen("mat2.txt", "w")) == NULL) {
        printf("The file `mat2.txt` can not be opened!\n");
        exit(1);
    }
    fprintf(output, "%d %d\n", n, m); /* reverse */

    for (i = 0; i < n; i++)
        for (j = 0; j < m; j++)
            fprintf(output, "%7.2f\n", b[i][j]);
    fclose(output);
    return (0);
}
