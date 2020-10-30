#include <stdio.h>

int prost(int n) {
    int i;
    for(i = 2; i * i <= n; ++i) {
        if(n % i == 0) {
            return 0;
        }
    }
    return 1;
}

int prv_pogolem_prost(int n) {
    ++n;
    while(!prost(n)) {
        ++n;
    }
    return n;
}

int main() {
    int n;
    scanf("%d", &n);
    int pogolem_prost = prv_pogolem_prost(n);
    printf("%d - %d = %d\n", pogolem_prost, n, pogolem_prost - n);
    return 0;
}
