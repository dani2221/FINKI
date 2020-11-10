// Од стандарден влез се читаат непознат број на хексадецимални цифри се додека не се внесе точка (.).
// Ваша задача е да го пресметате декадниот збир на внесените хексадецимални цифри.
// Доколку добиениот декаден збир е делив со 16 се печати Pogodok. 
// Доколку истиот тој збир покрај што е делив со 16 плус завршува на 16 (последните цифри му се 1 и 6),
// се печати Poln pogodok инаку се печати самиот збир.


#include <stdio.h>

int main(){
    int suma=0;
    while(1){
        char hexBroj;
        scanf("%c ",&hexBroj);
        if(hexBroj=='.') break;
        if(hexBroj>='0' && hexBroj<='9') suma+= hexBroj-'0';
        if(hexBroj>='a' && hexBroj<='f') suma+= hexBroj-'a'+10;
        if(hexBroj>='A' && hexBroj<='F') suma+= hexBroj-'A'+10;
    }
    if(suma%16==0){
        if(suma%10==6 && suma/10%10==1){
            printf("Poln pogodok");
        }else{
            printf("Pogodok");
        }
    }else{
        printf("%d",suma);
    }
    return 0;
}