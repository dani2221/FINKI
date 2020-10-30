#include <stdio.h>
int main () {
    float x, y;
    printf ("Vnesi koordinati \n");
    scanf ("%f %f", &x, &y);
    if (x > 0)
        if (y > 0)
            printf("I kvadrant.\n");
        else if (y < 0)
            printf("IV kvadrant.\n");
        else
            printf("Pozitivna X oska.\n");
    else if (x < 0)
        if (y > 0)
            printf("II kvadrant.\n");
        else if (y < 0)
            printf("III kvadrant.\n");
        else
            printf("Negativna X oska.\n");
    else
        if (y > 0)
            printf("Pozitivna Y oska.\n");
        else if (y < 0)
            printf("Negativna Y oska.\n");
        else
            printf("Koordinaten pocetok.\n");
    return 0;
}
