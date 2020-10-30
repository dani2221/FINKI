#include <stdio.h>
#define MAX 100

int GCD(int m, int n){
    if (!n) {
        return m;
    }

    return GCD(n, m % n);
}


int main() {
    int i, n, a[MAX];
    scanf("%d", &n);

    for (i=0; i<n; ++i){
        scanf("%d", &a[i]);
    }

    int gcd = GCD(a[0], a[1]);

    for (i=2; i<n; ++i){
        gcd = GCD(GCD, a[i]);
    }

    printf("GCD of the elements of the array is %d",GCD);

    return 0;
}
