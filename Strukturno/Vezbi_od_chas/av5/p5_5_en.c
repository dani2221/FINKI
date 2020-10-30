#include <stdio.h>
int main () {
    int i, from, to, temp, op, digit;
    scanf("%d %d", &from, &to);
    for (i = from; i <= to; i++) {
        temp = i;
        op = 0;
        while (temp > 0) {
            digit = temp % 10;
            op = op * 10 + digit;
            temp /= 10;
        }
        if (op == i) printf("%d\t", i);
    }
    return 0;
}
