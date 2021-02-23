#include <stdio.h>
#define _USE_MATH_DEFINES
#include <math.h>
int main() {
    double member = 1, pi = 0;
    int i, sign = 1, denominator = 1;
    for(i=0; i<100; i++) {
        pi += member;
        denominator += 2;
        sign = -sign;
        member = (double)sign / denominator;
    }
    pi*=4;
    printf("pi (approximate) = %lf\n", pi);
    return 0;
}
