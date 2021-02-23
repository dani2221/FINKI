#include <stdio.h>
int main () {
    float x, y;
    printf ("Vnesi koordinati \n");
    scanf ("%f %f", &x, &y);
    if (x > 0)
        if (y > 0)
            printf("I kvadrant.\n");
        else
            printf("IV kvadrant.\n");
    else if (y > 0)
        printf("II kvadrant.\n");
    else
        printf("III kvadrant.\n");
    return 0;
}
