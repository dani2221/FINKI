#include <stdio.h>

int suma(int n) {
    int i;
    int s = 0;
    for(i = 1; i <= n; ++i) {
        s += i;
    }
    return s;
}

int faktoriel(int n) {
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
            s = suma(i);
            result += faktoriel(s);
            printf("%d! + ", s);
        }
        s = suma(n);
        result += faktoriel(s);
        printf("%d! = %d\n", s, result);
    } else {
        printf("Nevalidna vrednost\n");
    }
    return 0;
}
