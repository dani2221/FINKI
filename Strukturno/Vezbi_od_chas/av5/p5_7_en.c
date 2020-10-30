#include <stdio.h>
int main() {
    int n, max;
    if (scanf("%d", &max)) {
        while (scanf("%d", &n)) {
            if (n > 100) continue;
            if (max < n) {
                max = n;
            }
        }
        printf("%d", max);
    } else {
        printf("This is not a number.");}
    return 0;
}
