#include <stdio.h>
#define _USE_MATH_DEFINES
#include <math.h>
int main() {
    double clen = 1, pi = 0;
    int i, znak = 1, imenitel = 1;
    for(i=0; i<100; i++) {
        pi += clen;
        imenitel += 2;
        znak = -znak;
        clen = (double)znak / imenitel;
    }
    pi*=4;
    printf("pi (priblizno) = %lf\n", pi);
    return 0;
}
