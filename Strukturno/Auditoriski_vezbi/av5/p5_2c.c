#include <stdio.h> 
int main () { 
    int counter, n; 
    float x, y; 
    printf("x: "); 
    scanf("%f", &x); 
    printf("n: "); 
    scanf("%d", &n); 
    for(counter = 1, y = x; counter < n; counter++) {
        y *= x;
    } 
    printf("%f^%d = %f\n", x, n, y); 
    return 0; 
}

