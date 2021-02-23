#include<stdio.h>
#define MAX 100
int main() {
    int n, i;
    int a[MAX];
    scanf("%d", &n);

    for(i = 0; i < n; ++i) {
        scanf("%d", &a[i]);
    }

    int temp = a[n-1];
    for(i = n - 1; i > 0; i--) {
        a[i] = a[i-1];
    }
    a[0] = temp;

    for(i = 0; i < n; ++i) {
        printf("%d ", a[i]);
    }

    return 0;
}



