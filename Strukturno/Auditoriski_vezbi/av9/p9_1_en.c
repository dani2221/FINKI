#include <stdio.h>
#define MAX 100

void max_increasing(int x[], int n, int *pos, int *len) {
    int i, start, currLen;
    *pos = 0;
    *len = 1;
    for (i = 0; i < n - 1; i++) {
        start = i;
        currLen = 1;
        while ((x[i] < x[i + 1])) {
            currLen++;
            i++;
            if (i == n - 1) break;
        }
        if (currLen > *len) {
            *len = currLen;
            *pos = start;
        }
    }
}

int main() {
    int a[MAX];
    int i, n, pos, len;

    scanf("%d", &n);
    for (i = 0; i < n; i++)
        scanf("%d", &a[i]);

    max_increasing(a, n, &pos, &len);

    printf("Start: %d, Length: %d\n", pos, len);
    return 0;
}
