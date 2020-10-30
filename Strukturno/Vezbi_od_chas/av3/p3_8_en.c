#include <stdio.h>
int main() {
    long int date;
    printf("Enter birth date in format (ddmmYYYY):\n");
    scanf("%ld", &date);
    int day = date / 1000000;
    int month = (date / 10000) % 100;
    printf("The day and the month of birth are %02d.%02d\n", day, month);
    return 0;
}
