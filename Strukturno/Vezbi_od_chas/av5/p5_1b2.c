#include <stdio.h> 
int main () { 
    int i = 11, sum = 0; 
    while (i <= 97) { 
        printf("%d + ", i); 
        sum = sum + i; 
        i+=2; 
    } 
    printf(" %d", i); 
    sum = sum + i; 
    printf(" = %d\n", sum); 
    return 0; 
}
