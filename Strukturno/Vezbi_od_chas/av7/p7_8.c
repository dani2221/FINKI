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

int main() {
    int i, count = 0;
    for(i = 1; i < 998; ++i) {
        if(prost(i) && prost(i + 2)) {
            printf("%d %d\n", i, i + 2);
            ++count;
        }
    }
    printf("Vkupno: %d\n", count);
    return 0;
}
