#include <stdio.h>
#define MAX 100
int main() {
    int n, element, a[MAX], i;
    short ascending = 1, descending = 1;
    scanf("%d", &n);
    for (i = 0; i < n; ++i)
        scanf("%d", &a[i]);
    for (i = 0; i < n - 1; ++i) {
        if (a[i] >= a[i + 1]) {
            ascending = 0;
            break;
        }
    }
    for (i = 0; i < n - 1; ++i) {
        if (a[i] <= a[i + 1]) {
            descending = 0;
            break;
        }
    }
    if (!descending && !ascending)
        printf("Array is not ascending and not descending\n");
    else if (descending)
        printf("Array is descending\n");
    else if (ascending)
        printf("Array is ascending\n");
    return 0;
}

