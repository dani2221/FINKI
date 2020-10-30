#include<stdio.h>

int poramni(int a){
    if (a<=0)
        return 0;
    int retval = a % 10;
    if (retval == 9)
        retval = 7;
    return poramni(a/10) *10 + retval;
}
int max_index(int *a, int n){
    int max_i = 0;
    int i;
    for (i = 0; i < n; ++i){
        if (a[i]>a[max_i]){
            max_i = i;
        }
    }
    return max_i;
}
void sort_asc(int *a, int n){
    int i, j, tmp;
    for(i = 0; i < n - 1; i++){
        for(j = 0; j < n - i - 1; j++){
            if(a[j] > a[j+1]){
                tmp = a[j];
                a[j] = a[j+1];
                a[j+1] = tmp;
            }
        }
    }
}
void print_arr(int *a, int n){
    int i;
    for (i = 0; i < n; ++i){
        printf("%d ", a[i]);
    }
}
int main() {
    int x, k, n, max_i;
    int a[5];
    n=0;
    while (scanf("%d", &x)){
        k = poramni(x);
        if (n>=5){
            max_i = max_index(a, 5);
            if (a[max_i] > k){
                a[max_i] = k;
            }
        }
        else {
            a[n] = k;
            n++;
        }
    }
    sort_asc(a,n);
    print_arr(a, n);
    return 0;
}
