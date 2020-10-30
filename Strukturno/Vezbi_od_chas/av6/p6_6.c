/*
Да се напише програма за ротирање на елементите на една низа за 'm' (се задава од тастатура) местa во десно.
*/
#include<stdio.h>
#define MAX 100
int main() {
    int n, i, j, temp, m;
    int a[MAX];
    printf("Golemina na niza:  ");
    scanf("%d", &n);

	printf("Broj na rotiranja:  ");
    scanf("%d", &m);

    for(i = 0; i < n; ++i) {
        scanf("%d", &a[i]);
    }

	for(i = 0; i < n; ++i) {
        printf("%d ", a[i]);
    }

    for(j = 0; j < m; j++) {
		temp = a[n-1];
		for(i = n - 1; i > 0; i--) {
			a[i] = a[i-1];
		}
		a[0] = temp;
	}

    printf("\n");
    for(i = 0; i < n; ++i) {
        printf("%d ", a[i]);
    }

    return 0;
}



