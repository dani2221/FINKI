// Да се напише програма во која во даден опсег на природни броеви [a,b],
// ќе се најдат и отпечатат сите броеви чијшто збир на цифри кои се деливи со 2,
// но (н) се деливи со 3, е поголем од 0 и е делив со 7. На крај да се отпечати и бројот на вакви броеви.

#include <stdio.h>

int main(){
    int a, b, brojac=0;
    scanf("%d%d",&a,&b);
    for(int i=a;i<=b;i++){
        int ph=i;
        int suma=0;
        while(ph!=0){
            if(ph%10%2==0 && ph%10%3!=0) suma+=ph%10;
            ph/=10;
        }
        if(suma>0 && suma%7==0){
            printf("%d\n",i);
            brojac++;
        }
    }
    printf("Vkupno: %d\n",brojac);
    return 0;
}