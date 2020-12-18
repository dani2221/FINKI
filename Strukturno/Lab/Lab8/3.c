// Да се напишат следните функции за работа со низи од децимални броеви:
// Функцијата double max (double * niza, int n), 
// што како резултат ќе го врати максимумот во низата niza со n елементи.
// Функцијата double min (double * niza, int n), 
// што како резултат ќе го врати минимумот во низата niza со n елементи.
// Функцијата void normalize (double * niza, int n), 
// што ќе го нормализира секој елемент од низата по следната формула:
// x = (x-min(niza)) / (max(niza) - min(niza))

#include <stdio.h>

double max(double *niza, int n){
    double max = *niza;
    for(int i=1;i<n;i++){
        if(*(niza+i)>max) max = *(niza+i);
    }
    return max;
}
double min(double *niza, int n){
    double min = *niza;
    for(int i=1;i<n;i++){
        if(*(niza+i)<min) min = *(niza+i);
    }
    return min;
}
void normalize(double *niza, int n){
    double k = max(niza,n);
    double r = min(niza,n);
    for(int i=0;i<n;i++){
        *(niza+i) = (*(niza+i)-r) / (k - r);
    }
}

int main(){
    int n;
    scanf("%d",&n);
    double *niza;
    for(int i=0;i<n;i++){
        scanf("%lf",(niza+i));
    }
    printf("MAX -> %.3lf\n",max(niza,n));
    printf("MIN -> %.3lf\n",min(niza,n));
    normalize(niza,n);
    for(int i=0;i<n;i++){
        printf("%.3lf ",*(niza+i));
    }
    return 0;
}