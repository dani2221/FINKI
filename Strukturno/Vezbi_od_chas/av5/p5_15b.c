#include <stdio.h>
#define _USE_MATH_DEFINES
#include <math.h>
int main() {
    double pi = 0, znak = 4, clen = znak;
    int imenitel = 1;
    do {
        pi += clen;
        imenitel += 2;
        znak = -znak;
        clen = znak / imenitel;
    } while (fabs(clen) > 1e-6);

    /*  pi = clen;
        while (fabs(clen) > 1e-6)
            pi += clen = ((znak*=-1) / (imenitel+=2));*/

    printf("pi (priblizno) = %10.8lf \t pi = %10.8lf\n", pi, M_PI);

    return 0;
}
