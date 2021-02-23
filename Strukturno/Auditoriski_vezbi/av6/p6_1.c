#include<stdio.h>
#define MAX 100
int main() {
    int n1, n2, element, i;
    int a[MAX], b[MAX];
    printf("Golemina na prvata niza:  ");
    scanf("%d", &n1);
    printf("Golemina na vtorata niza:  ");
    scanf("%d", &n2);
    if (n1 != n2)
        printf("Nizite ne se ednakvi\n");
    else {
        printf("Elementi na prvata niza: \n");
        for (i = 0; i < n1; ++i) {
            printf("a[%d] = ", i);
            scanf("%d", &a[i]);
        }
        printf("Elementi na vtorata niza: \n");
        for (i = 0; i < n2; ++i) {
            printf("b[%d] = ", i);
            scanf("%d", &b[i]);
        }
        // check if arrays are equal:
        for (i = 0; i < n1; ++i)
            if (a[i] != b[i])
                break;
        if (i == n1)
            printf("Nizite se ednakvi\n");
        else
            printf("Nizite ne se ednakvi\n");
    }
    return 0;
}



