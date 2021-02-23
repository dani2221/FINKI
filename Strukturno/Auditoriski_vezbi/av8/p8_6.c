#include <stdio.h>
#define MAX 100

int NZD(int m, int n){
    if (!n) {
        return m;
    }

    return NZD(n, m % n);
}


int main() {
    int i, n, a[MAX];

    printf("Vnesi ja goleminata na nizata: ");
    scanf("%d", &n);

    printf("Vnesi gi elementite na nizata: \n");
    for (i=0; i<n; ++i){
        scanf("%d", &a[i]);
    }

    int nzd = NZD(a[0], a[1]);

    for (i=2; i<n; ++i){
        nzd = NZD(nzd, a[i]);
    }
    
    printf("NZD na elementite od nizata e %d",nzd);

    return 0;
    
}
