#include <stdio.h> 
int main () { 
    int counter = 0, n; 
    float x, y = 1; 
    printf("x: "); 
    scanf("%f", &x); 
    printf("n: "); 
    scanf("%d", &n); 
    do { 
        y *= x; 
        counter++; 
    } while (counter < n); 
    printf("%f^%d = %f\n", x, n, y); 
    return 0; 
 }
