#include <stdio.h>
int main() {
    int difference, i, n = 0, number = 0;
    int sum_odd_positions = 0, sum_even_positions = 0;
    scanf("%d", &n);
    for (i = 1; i <= n; i++) {
        scanf("%d", &number);
        if (i % 2) {
            sum_odd_positions += number;
        } else {
            sum_even_positions += number;
        }
    }
    difference = sum_even_positions - sum_odd_positions;
    if (difference < 10 && difference > -10) { //if(abs(difference) < 10){
        printf("Dvete sumi se slicni");
    } else {
        printf("Dvete sumi mnogu se razlikuvaat");
    }
    return 0;
}
