#include <stdio.h>
int main () {
    float x, y;
    printf ("Enter coordinates \n");
    scanf ("%f %f", &x, &y);
    if (x > 0)
        if (y > 0)
            printf("I quadrant.\n");
        else if (y < 0)
            printf("IV quadrant.\n");
        else
            printf("Positive X axis.\n");
    else if (x < 0)
        if (y > 0)
            printf("II quadrant.\n");
        else if (y < 0)
            printf("III quadrant.\n");
        else
            printf("Negative X axis.\n");
    else if (y > 0)
        printf("Positive Y axis.\n");
    else if (y < 0)
        printf("Negative Y oska.\n");
    else
        printf("Origin.\n");
    return 0;
}
