// Од стандарден влез се внесуваат два цели броеви A и P.
// Да се испечатат во опаѓачки редослед првите 5 броеви во интервалот (0,A]
// за коишто важи дека сумата на нивните цифри изнесува P проценти од самиот број.
// Ако нема такви броеви во интервалот, да се испечати порака NEMA.

#include <stdio.h>

int main(){
    int a,p,brojac=0;
    scanf("%d%d",&a,&p);
    for(a;a>0;a--){
        int pA = a;
        int suma = 0;
        while(pA!=0){
            suma+=pA%10;
            pA/=10;
        }
        if((a*p/100.0)==suma){
            brojac++;
            printf("%d\n",a);
        }
        if(brojac==5){
            return 0;
        }
    }
    if(brojac>5 || brojac==0) printf("NEMA");

}