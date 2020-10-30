#include <stdio.h>
int main() {
    int date;
    scanf("%d", &date);
    int n;
    scanf("%d", &n);
    int i;
    int day = date / 100000;
    int month = (date / 10000) % 100;
    int year = date % 10000;
    for (i = 0; i < n; ++i) {
        int d;
        scanf("%d", &d);
        int dd = d / 100000;
        int mm = (d / 10000) % 100;
        int yy = d % 10000;
        int years = year - yy;
        if (years < 18) {
            printf("NE\n");
        } else if (years == 18) {
            if (mm < month) {
                printf("DA\n");
            } else if (mm == month) {
                if (dd <= day) {
                    printf("DA\n");
                } else {
                    printf("NE\n");
                }
            }  else {
                printf("NE\n");
            }
        } else if (years > 18) {
            printf("DA\n");
        }
    }
    return 0;
}
