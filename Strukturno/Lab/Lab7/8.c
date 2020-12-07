// Напишете целосно рекурзивна функција triagolnik(n) 
// што за даден влезен аргумент n ќе испечати на екран превртен триаголник од броевите од 1 до n, 
// како во примерот подолу. Дополнителни функции се дозволени, но истите мора да се рекурзивни. 
// Не е дозволено користење на циклуси!

#include <stdio.h>

void linija(int broj,int brojac){
    if(broj==brojac) return;
    printf("%d ",++brojac);
    linija(broj,brojac);
}

void triagolnik(int broj){
    if(!broj) return;
    linija(broj,0);    
    printf("\n");
    triagolnik(--broj);
}

int main(){
    int n;
    scanf("%d",&n);
    triagolnik(n);
    return 0;
}

