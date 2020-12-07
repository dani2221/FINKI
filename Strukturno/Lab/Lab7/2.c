// Од стандарден влез се чита цел број К, по што се читаат N цели броеви. 
// Да се напише програма која за секој прочитан број на стандарден излез
// ќе ги отпечати неговите цифри кои се поголеми од К, како и збирот на цифрите кои се отпечатени. 
// Печатењето на цифрите поголеми од K да се реализира со посебна рекурзивна функција.

#include <stdio.h>

int f(int broj,int k,int suma){
    if(broj<10){
        if(broj>k){
            printf("%d",broj);
            suma+=broj;
        }
        return suma;
    }
    suma = f(broj/10,k,suma);
    if(broj%10>k){
        printf("%d",broj%10);
        suma+=broj%10;
    }
    return suma;
}

int main(){
    int k,n;
    scanf("%d %d",&k,&n);
    for(int i=0;i<n;i++){
        int broj;
        scanf("%d",&broj);
        printf(" : %d\n",f(broj,k,0));
    }
    return 0;
}