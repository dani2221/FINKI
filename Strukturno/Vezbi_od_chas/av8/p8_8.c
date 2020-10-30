#include <stdio.h>

int sum_elements(int array[], int n);

int main() {
    int i, n, a[100];
    scanf("%d", &n);

    for (i = 0; i < n; i++)
        scanf("%d", &a[i]);

    printf("The sum of all of the array elements is: %d \n", sum_elements(a, n - 1));

    return 0;
}

int sum_elements(int array[], int n) {
    if (n == 0) {
        return array[n];
    }
    else {
        return array[n] + sum_elements(array, n-1);
    }
}
