#include <stdio.h> 
int main () { 
    int i = 10, sum = 0; 
    while (i <= 98) { 
        sum = sum + i; 
        i+=2; 
    } 
    printf("%d\n", sum); 
    return 0; 
}

