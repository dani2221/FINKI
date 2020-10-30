#include <stdio.h>
#define MAX 100
int main() {
    int i, n, a[MAX], count_even = 0, count_odd = 0, sum_even = 0, sum_odd = 0;
    scanf("%d", &n);
    for (i = 0; i < n; ++i)
        scanf("%d", &a[i]);
    for (i = 0; i < n; ++i) {
        if (a[i] % 2) {
            count_odd++;
            sum_odd += a[i];
        } else {
            count_even++;
            sum_even += a[i];
        }
    }
    printf("Sum even: %d\nSum odd: %d\n", sum_even, sum_odd);
    printf("Ratio: %.2f\n", (float)count_even / count_odd);
    return 0;
}

