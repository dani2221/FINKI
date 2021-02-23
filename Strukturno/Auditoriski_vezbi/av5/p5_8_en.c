#include <stdio.h>
int main() {
    int n, max1, max2, temp;
    if (scanf("%d%d", &max1, &max2) == 2) {
        if (max2>max1){
            temp = max1;
            max1 = max2;
            max2 = temp;
        }
        while(scanf("%d", &n)) {
            if(n > max1){
                max2 = max1;
                max1 = n;
            } else if (n>max2) {
                max2 = n;
            }
        }
        printf("%d\n", max1);
        printf("%d\n", max2);
    } else {
        printf("Enter at least 2 numbers");
    }
    return 0;
}
