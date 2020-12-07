// Напишете целосно рекурзивна функција triagolnik(n)
// што за даден влезен аргумент n ќе испечати на екран триаголник од броевите од 1 до n.
// Дополнителни функции се дозволени, но истите мора да се исто така рекурзивни. 
// Не е дозволено користење на циклуси!

#include <stdio.h>

void linija(int broj,int brojac){
    if(broj==brojac) return;
    printf("%d",++brojac);
    linija(broj,brojac);
}

void triagolnik(int broj, int brojac){
    if(broj==brojac) return;
    linija(++brojac,0);    
    printf("\n");
    triagolnik(broj,brojac);
}

int main(){
    int n;
    scanf("%d",&n);
    triagolnik(n,0);
    return 0;
}