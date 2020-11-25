// Од тастатура се внесува цел број m, а потоа непознат број цели броеви. Да се
// испечатат должините на секвенците составени од најмалку два
// последователни броја за кои истовремено важи:
// • следниот број е строго поголем од претходниот и
// • секој од нив има точно m цифри.
// Задачата да се реши без користење на низи.

#include <stdio.h>

int main(){
    int m,prv = 1,dolzina=1,prethoden;
    scanf("%d",&m);
    printf("Dolzini: ");
    while(1){
        int broj;
        scanf("%d",&broj);
        if(!prv){
            int ph = prethoden,pha=broj,prethodenCifri=0,brojCifri=0;
            while(ph!=0){
                prethodenCifri++;
                ph/=10;
            }
            while(pha!=0){
                brojCifri++;
                pha/=10;
            }
            //printf("DolzinaBroj: %d \t DolzinaPret: %d,broj: %d, pret: %d",brojCifri,prethodenCifri,broj,prethoden);
            if(broj>prethoden && prethodenCifri==brojCifri && brojCifri==m){
                dolzina++;
            }else{
                if(dolzina>1){
                    printf("%d ",dolzina);
                }
                dolzina=1;
            }
            prethoden=broj;
        }else{ 
            prv=0;
            prethoden = broj;
        }
    }


    return 0;
}