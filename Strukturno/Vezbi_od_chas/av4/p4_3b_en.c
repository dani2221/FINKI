#include <stdio.h>
int main () {
    float x, y;
    printf("Enter coordinates \n");
    scanf("%f %f", &x, &y);
    if (x > 0)
        if (y > 0)
            printf("I quadrant.\n");
        else
            printf("IV quadrant.\n");
    else if (y > 0)
        printf("II quadrant.\n");
    else
        printf("III quadrant.\n");
    return 0;
}
