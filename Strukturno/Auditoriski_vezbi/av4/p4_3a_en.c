#include <stdio.h>

int main () {
    float x, y;
    printf ("Enter coordinates \n");
    scanf ("%f %f", &x, &y);
    if (x > 0 && y > 0)
        printf("I quadrant.\n");
    if (x > 0 && y < 0)
        printf("IV quadrant.\n");
    if (x < 0 && y > 0)
        printf("II quadrant.\n");
    if (x < 0 && y < 0)
        printf("III quadrant.\n");
    return 0;
}
