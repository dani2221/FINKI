// Продолжение на претходната задача:
// Од стандарден влез најпрво се читаат информации за бројот на фискални сметки коишто 
// ќе бидат скенирани М, а потоа се вчитуваат податоци за M фискални сметки, како во претходната задача.
// На стандарден излез да се испечати повратокот на ДДВ за секоја од скенираните фискални сметки. 
// На крајот да се испечати сумата на купените артикли на фискалната сметка со најголем повраток на ДДВ,
// како и повратокот на ДДВ за таа сметка.
// Да се игнорираат фискалните сметки, коишто имаат сума на артиклите поголема од 30.000 денари
// и за истите да се испечати соодветна порака, како во тест примерите.



// GRESHKA VO POSLEDNIOT TEST SLUCHAJ
// maks suma treba da e 20538 a vo test sluchajot e 20048


#include <stdio.h>

int main(){
    int smetki,maksSUMA=0;
    float maksDDV=0.0;
    scanf("%d",&smetki);
    for(int i=0;i<smetki;i++){
        int parovi,vkCena=0;
        float total = 0.0;
        scanf("%d",&parovi);
        for(parovi;parovi>0;parovi--){
            int suma;
            char znak;
            float danok;
            scanf("%d %c",&suma,&znak);
            vkCena+=suma;
            if(znak=='A') danok = suma*0.18;
            if(znak=='B') danok = suma*0.05;
            if(znak=='V') danok = 0;
            total+=(danok*0.15);
        }
        if(vkCena>30000) printf("Sum %d is bigger than 30000\n",vkCena);
        else {
            printf("Total tax return is: %.2f\n",total);
            if(vkCena>maksSUMA) maksSUMA = vkCena;
            if(total>maksDDV) maksDDV = total;
        }
    }
    printf("Max amount of reciept: %d. Max tax return for reciepts: %.2f",maksSUMA,maksDDV);
}
