#include <stdio.h>

int sum(int n) {
    int i;
    int s = 0;
    for(i = 1; i <= n; ++i) {
        s += i;
    }
    return s;
}

int factorial(int n) {
    int result = 1;
    int i;
    for(i = 1; i <= n; ++i) {
        result *= i;
    }
    return result;
}

int main() {
    int n;
    scanf("%d", &n);
    if(n > 0) {
        int i;
        int result = 0;
        int s;
        for(i = 1; i < n; ++i) {
            s = sum(i);
            result += factorial(s);
            printf("%d! + ", s);
        }
        s = sum(n);
        result += factorial(s);
        printf("%d! = %d\n", s, result);
    } else {
        printf("Invalid value\n");
    }
    return 0;
}
