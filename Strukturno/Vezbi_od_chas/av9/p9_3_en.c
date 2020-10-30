#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define MAX 1000000

int linear_search(int *a, int n, int key) {
    int i;
    for (i = 0; i < n; i++) {
        if (*(a + i) == key) return i;
    }
    return -1;
}
int binary_search(int *a, int n, int key) {
    int start = 0;
    int end = n - 1;
    while (start <= end) {
        int mid = (start + end) / 2;
        if (*(a + mid) == key) return mid;
        else if (*(a + mid) > key) end = mid - 1;
        else start = mid + 1;
    }
    return -1;
}


int main() {
    int i;
    int *a = malloc(sizeof(int) * MAX);
    for (i = 0; i < MAX; i++) {
        *(a + i) = i + 1;
    }
    srand(time(NULL));
    int key = rand() % MAX + 1;
    printf("Element for search: %d\n", key);
    int found = linear_search(a, MAX, key);
    printf("Found with linear search at position: %d\n", found);
    found = binary_search(a, MAX, key);
    printf("Found with binary search at position: %d\n", found);
    return 0;
}
