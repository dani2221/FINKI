#include <stdio.h>

int main() {
    int a[100][100];
    int rows, cols;
    int i, j;
    scanf("%d %d", &rows, &cols);
    for (i = 0; i < rows; ++i) {
        for (j = 0; j < cols; ++j) {
            scanf("%d", &a[i][j]);
        }
    }
    int count=0;
    int flag;
    int c;
    for (i = 0; i < rows; ++i) {
        c = 0;
        flag = 0;
        for (j = 0; j < cols; ++j) {
            if (a[i][j] == 1) {
                ++c;
            } else {
                if (c >= 3) {
                    flag = 1;
                    ++count;
                    break;
                }
                c = 0;
            }
        }
        if (!flag&&c >= 3) {
            ++count;
        }
    }
    for (i = 0; i < cols; ++i) {
        c = 0;
        flag = 0;
        for (j = 0; j < rows; ++j) {
            if (a[j][i] == 1) {
                ++c;
            } else {
                if (c >= 3) {
                    flag = 1;
                    ++count;
                    break;
                }
                c = 0;
            }
        }
        if (!flag&&c >= 3) {
            ++count;
        }
    }
    printf("%d\n", count);
    return 0;
}
