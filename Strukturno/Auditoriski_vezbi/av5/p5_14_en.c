#include <stdio.h>
/* #define _USE_MATH_DEFINES */
#include <math.h>
int main() {
    float x, y;
    int step, min, sec;
    printf("  x\tdegrees\tminutes\tseconds\n");
    for (x = 0.02; x <= 0.8; x += 0.01) {
        y = 180/M_PI * atan(x / sqrt(1 - x*x));
        step = y;
        min = (y - step) * 60;
        sec = ((y - step) * 60 - min) * 60 + 0.5;
        printf("%5.2f\t%3d\t %2d\t %2d\n", x, step, min, sec);
    }
    return 0;
}
