#include<stdio.h>

int main(){
    int i, j, n;
    float  broj, a[100][100], x = 0.0, y = 0.0;
    scanf("%d", &n);
    for(i = 0; i < n; i++) {
        for(j = 0; j < n; j++) {
        	scanf("%f", &broj);
            if(i > j)
                x += broj;
            if(i + j >= n)
                y += broj;
    	}
    }
    for(i = 0; i < n; i++) {
        for(j = 0; j < n; j++) {
        	if(i == j)
                a[i][j] = x;
            else if(i + j == n - 1)
                a[i][j] = y;
            else
                a[i][j] = 0;
        }

    }
    if(n % 2)
        a[n / 2][n / 2] = x + y;
    for(i = 0; i < n; i++) {
        for(j = 0; j < n; j++) {
        	printf("%.1f ", a[i][j]);
        }
        printf("\n");
    }
	return 0;
}
