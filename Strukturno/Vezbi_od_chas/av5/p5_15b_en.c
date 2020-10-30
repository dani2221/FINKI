#include <stdio.h>
#define _USE_MATH_DEFINES
#include <math.h>
int main() {
    double pi = 0, sign = 4, member = sign;
    int denominator = 1;
    do {
        pi += member;
        denominator += 2;
        sign = -sign;
        member = sign / denominator;
    } while (fabs(member) > 1e-6);

    /*  pi = member;
        while (fabs(member) > 1e-6)
            pi += member = ((sign*=-1) / (denominator+=2));*/

    printf("pi (aproximate) = %10.8lf \t pi = %10.8lf\n", pi, M_PI);

    return 0;
}
