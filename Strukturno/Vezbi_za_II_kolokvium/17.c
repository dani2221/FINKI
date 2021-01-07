// Да се напише рекурзивна функција за наоѓање на максималната цифра од даден цел број.
// Од стандарден влез се внесуваат непознат број цели броеви се додека не се внесе нешто што не е број. 
// За секој од нив да се испечати максималната цифра во посебен ред.

#include <stdio.h>

int r(int broj){
    if(broj==0) return 0;
    int pr = r(broj/10);
    if(broj%10>pr) return broj%10;
    else return pr;
}

int main(){
    int broj;
    while(scanf("%d",&broj)){
        printf("%d\n",r(broj));
    }
    return 0;
}