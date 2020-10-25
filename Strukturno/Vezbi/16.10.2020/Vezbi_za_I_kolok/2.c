// Eден природен e „интересен“ ако неговиот обратен број е делив со неговиот број на цифри.
// Обратен број е бројот составен од истите цифри, но во обратен редослед 
// (на пример, 653 е обратен број на бројот 356). Од тастатура се внесува природен број n ( n > 9). 
// Да се најде и отпечати најголемиот природен број помал од n кој што е „интересен“. 
// Ако внесениот број не е валиден, да се отпечати соодветна порака (Brojot ne e validen).



#include <stdio.h>

int main(){
    int broj;
    scanf("%d",&broj);
    if(broj<10){
        printf("Brojot ne e validen");
        return 0;
    }
    broj-=1;
    while(broj>0){
        int n = broj,novBroj=0,cifri=0;
        while(n!=0){
            novBroj+=n%10;
            n/=10;
            if(n!=0) novBroj*=10;
            cifri++;
        }
        if(novBroj%cifri==0){
            printf("%d",broj);
            return 0;
        }
        broj--;
    }
    return 0;
}