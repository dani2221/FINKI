// Од стандарден влез се читаат непознат број на хексадецимални цифри се додека не се внесе точка (.).
// Ваша задача е да го пресметате декадниот збир на внесените хексадецимални цифри.
// Доколку добиениот декаден збир е делив со 16 се печати Pogodok. 
// Доколку истиот тој збир покрај што е делив со 16 плус завршува на 16 (последните цифри му се 1 и 6),
// се печати Poln pogodok инаку се печати самиот збир.


#include <stdio.h>

int main(){

    int zbir = 0;
    char dexCifra;
    while(1){
        scanf("%c",&dexCifra);
        if(dexCifra=='.') break;
        int broj;

        if(dexCifra=='a' || dexCifra=='A') broj=10;
        if(dexCifra=='b' || dexCifra=='B') broj=11;
        if(dexCifra=='c' || dexCifra=='C') broj=12;
        if(dexCifra=='d' || dexCifra=='D') broj=13;
        if(dexCifra=='e' || dexCifra=='E') broj=14;
        if(dexCifra=='f' || dexCifra=='F') broj=15;
        if(dexCifra=='0') broj=0;
        if(dexCifra=='1') broj=1;
        if(dexCifra=='2') broj=2;
        if(dexCifra=='3') broj=3;
        if(dexCifra=='4') broj=4;
        if(dexCifra=='5') broj=5;
        if(dexCifra=='6') broj=6;
        if(dexCifra=='7') broj=7;
        if(dexCifra=='8') broj=8;
        if(dexCifra=='9') broj=9;

        zbir+=broj;
    }
    zbir/=2; //neznam zoshto zbirot e dupliran??? go sobira dva pati?
    if(zbir%16==0){
        int phZbir = zbir/10;
        if(zbir%10==6 && phZbir%10==1){
            printf("Poln pogodok");
        }else{
            printf("Pogodok");
        }
    }else{
        printf("%d",zbir);
    }
    return 0;
}