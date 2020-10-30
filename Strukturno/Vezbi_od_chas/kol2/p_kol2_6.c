#include <stdio.h>
#include <string.h>
#define MAX 100

int digit(int n) {
    while(n >= 10) {
        n /= 10;
    }
    return n;
}

int main(){
   FILE* f = fopen("broevi.txt", "r");
    int i;
    while(!feof(f)) {
        int n, x;
        fscanf(f, "%d", &n);
        if(n == 0) break;
        int max = 0;
        int maxN = 0;
        for(i = 0; i < n; ++i) {
            fscanf(f, "%d", &x);
            int y = digit(x);
            if(y > max) {
                max = y;
                maxN = x;
            }
        }
        printf("%d\n", maxN);
    }

    fclose(f);
    return 0;

}
