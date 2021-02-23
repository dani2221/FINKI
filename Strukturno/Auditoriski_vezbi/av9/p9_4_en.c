#include <stdio.h>

void bubble_sort(int *a, int n) {
    int i, j;
    for (i = 0; i < n; i++) {
        for (j = 0; j < n - i - 1; j++) {
            if (a[j] > a[j + 1])
                swap(&a[j], &a[j + 1]);
        }
    }
}

void selection_sort(int a[], int n, int m) {
    if (n - m == 1)
        return;
    else {
        int smallest = a[m];
        int smallest_index = m;
        int i;
        for (i = m; i < n; ++i)
            if (a[i] < smallest) {
                smallest = a[i];
                smallest_index = i;
            }
        swap(&a[m], &a[smallest_index]);
        selection_sort(a, n, m + 1);
    }
}

void insertion_sort(int a[], int n) {
    int i, j;
    for (i = 1; i < n; i++) {
        int temp = a[i];
        j = i - 1;
        while (temp < a[j] && j >= 0) {
            a[j + 1] = a[j];
            j--;
        }
        a[j + 1] = temp;
    }
}

void insert(int a[], int n) {
    int i;
    for(i = 0; i < n; i++) {
        printf("a[%d] = ", i);
        scanf("%d", &a[i]);
    }
}
void print(int *a, int n) {
    int i;
    for(i = 0; i < n; i++) {
        printf("%d\t", *(a + i));
    }
    printf("\n");
}
int main() {
    int a[MAX], n;
    scanf("%d", &n);
    insert(a, n);
    bubble_sort(a, n);
    //selection_sort(a, n, 0);
    //insertion_sort(a, n);
    print(a, n);
    return 0;
}
