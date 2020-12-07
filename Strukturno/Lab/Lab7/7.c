// Дефинирајте ја рекурзивната функција "sumOfDigits(int number)" така што за дадениот аргумент 
// "number" ќе ја пресмета сумата на сите негови цифри. 
// Пример за аргумент "1234" функцијата треба да врати 1 + 2 + 3 + 4 = 10.


#include <stdio.h>

int sumOfDigits(int broj,int suma){
    if(!broj) return suma;
    suma+=broj%10;
    return sumOfDigits(broj/10,suma);
}

int main(){
    int n,krajnaSuma=0;
    scanf("%d",&n);
    for(int i=0;i<n;i++){
        int broj;
        scanf("%d",&broj);
        int suma = sumOfDigits(broj,0);
        krajnaSuma+= suma;
        printf("%d\n",suma);
    }
    printf("%d",krajnaSuma);
    return 0;
}

