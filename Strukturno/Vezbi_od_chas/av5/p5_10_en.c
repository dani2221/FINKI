#include <stdio.h>
int main() {
    int pol_position, position, max_sum, sum, previous, next;
    scanf("%d%d", &previous, &next);
    pol_position = position = 2;
    max_sum = sum = previous + next;
    while (1) {
        if (previous < 0 && next < 0) {
            break;
        }
        sum = previous + next;
        if (sum > max_sum) {
            max_sum = sum;
            pol_position = position;
        }
        previous = next;
        scanf("%d", &next);
        position++;
    }
    if (position > 2)
        printf("Numbers are found on positions %d and %d with sum: %d\n",
               pol_position - 1, pol_position, max_sum);
    return 0;
}
