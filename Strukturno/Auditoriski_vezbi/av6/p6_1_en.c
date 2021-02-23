#include<stdio.h>
#define MAX 100
int main() {
    int n1, n2, element, i;
    int a[MAX], b[MAX];
    printf("First array size:  ");
    scanf("%d", &n1);
    printf("Second array size:  ");
    scanf("%d", &n2);
    if (n1 != n2)
        printf("Arrays are equal\n");
    else {
        printf("Elements of the first array: \n");
        for (i = 0; i < n1; ++i) {
            printf("a[%d] = ", i);
            scanf("%d", &a[i]);
        }
        printf("Elements of the second array: \n");
        for (i = 0; i < n2; ++i) {
            printf("b[%d] = ", i);
            scanf("%d", &b[i]);
        }
        // check if arrays are equal:
        for (i = 0; i < n1; ++i)
            if (a[i] != b[i])
                break;
        if (i == n1)
            printf("Arrays are equal\n");
        else
            printf("Arrays are not equal\n");
    }
    return 0;
}



