#include <stdio.h>

int zbir_po_2cifri(int n) {
    return n % 100 + n / 100;
}

int main() {
    int i;
    int count = 0;
    for(i = 1000; i <= 9999; ++i) {
        if(i % zbir_po_2cifri(i) == 0) {
            printf("%d\n", i);
            ++count;
        }
    }
    printf("Vkupno: %d\n", count);
    return 0;
}
