#include <stdio.h>

int factorial(int n) {
    if (n == 0)
        return 1;
    else
        return n * factorial(n - 1);
}

int sum(int k) {
    if (k == 0)
        return 0;
    else
        return k + sum(k - 1);
}

int main() {
    int i, n, result = 0;
    scanf("%d", &n);
    if (n > 0) {
        for (i = 1; i < n; i++) {
            int s = sum(i);
            result += factorial(s);
            printf("%d! + ", s);
        }
        int s = sum(n);
        result += factorial(s);
        printf("%d! = %d\n", s, result);
    } else
        printf("Wrong input\n");
    return 0;
}
