#include <stdio.h>
#include <math.h>
int main() {
    int factors = 1, factor_b = 1;
    double pi = 1, member = 1;
    /* the loop computes pi/2 */
    do
    {
        factors += 2;
        member *= (double)factor_b / factors;
        factor_b++;
        pi += member;
    } while (member > 1e-6);


/*    do
        pi += member*= (double)factor_b++ / (factors+=2);
    while (member > 1e-6); */

    pi *= 2;
    printf("pi (approximate) = %10.8lf \t pi = %10.8lf\n", pi, M_PI);

    return 0;
}
