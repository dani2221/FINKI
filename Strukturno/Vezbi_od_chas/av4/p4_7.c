#include <stdio.h>
#include <math.h>
int main() {
    float a, b, c;
    printf("Vnesi dolzini na strani: \n");
    scanf("%f %f %f", &a, &b, &c);
    if ((a + b <= c) || (a + c <= b) || (b + c <= a))
        printf("Ne moze da se konstruira triagolnik.\n");
    else {
        if (a == b && b == c)
            printf("Tragolnikot e ravnostran.\n"); // equilateral
    	else if (a == b || b == c || a == c)
             printf("Tragolnikot e ravnokrak.\n"); // isosceles
	else
	     printf("Tragolnikot e raznostran.\n"); // scalene
    	float p, s = (a + b + c) / 2;
    	p = sqrt(s * (s - a) * (s - b) * (s - c));
    	printf("Ploshtinata mu e %7.3f\n", p);
    }
    return 0;
}
