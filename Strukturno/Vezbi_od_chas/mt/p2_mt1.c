#include <stdio.h>

int main() {
    int number;
    int is_cyclic;
    int prethodna, current, next;
    int total = 0;

    while (scanf("%d", &number)) {
        is_cyclic = 1;
        int tmp = number;
        while (tmp) {
            if (tmp == b) {
                prethodna = tmp % 10;
                tmp /= 10;
                continue;
            }
            if (tmp > 9) {
                current = tmp % 10;
                next = tmp % 100 / 10;

                if ( (current <= next && current >= prethodna) ||
                        (current >= next && current <= prethodna) )
                    is_cyclic = 0;
            }
            else {
                break;
            }
            prethodna = current;
            tmp /= 10;
        }

        if (is_cyclic) {
            printf("%d\n", b);
            total++;
        }

    }

    printf("Total %d cyclic numbers\n", total);

}
