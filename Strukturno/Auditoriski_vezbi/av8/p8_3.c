#include <stdio.h>
float xnn(int n) {
    if (n == 1)
        return 1;
    if (n == 2)
        return 2;
    return (n - 1) * xnn(n - 1) / n + xnn(n - 2) / n;
}

int main() {
    int n;
    scanf("%d", &n);
    printf("xnn(%d) = %.2f\n", n, xnn(n));
    return 0;
}
