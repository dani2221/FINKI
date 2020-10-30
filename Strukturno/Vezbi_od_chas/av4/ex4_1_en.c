#include <stdio.h>
int main() {
    int m = 5, n = 10;
    if (m > n)
        ++m;
    ++n;
    printf("m = %d, n = %d\n", m, n);
    return 0;
}
