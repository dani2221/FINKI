// Да се напише програма во која во даден опсег на природни броеви [a,b],
// ќе се најдат и отпечатат сите броеви чијшто збир на цифри 
// кои се деливи со 2 е поголем од 0 и е делив со 4. 
// На крај да се отпечати и бројот на вакви броеви.

#include <stdio.h>

int main(){
    int dolna, gorna, brojac=0;
    scanf("%d%d",&dolna,&gorna);
    for(int i=dolna;i<=gorna;i++){
        int ph = i;
        int suma = 0;
        while(ph!=0){
            if(ph%10%2==0) suma+=ph%10;
            ph/=10;
        }
        if(suma>0 && suma%4==0){
            printf("%d\n",i);
            brojac++;
        }
    }
    printf("Vkupno: %d\n",brojac);
    return 0;
}