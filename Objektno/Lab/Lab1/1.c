// За програмски јазик C.
// Да се напише програма во која од стандарден влез се вчитува N (бројот на производи),
// а потоа се вчитуваат податоците за N производи (име, цена, количина). 
// Програмата треба на стандарден излез да ја отпечати листата на купени производи 
// и вкупната сума која треба да се плати

#include <stdio.h>
#include <ctype.h>

typedef struct s{
    char ime[100];
    float cena;
    float kolichina;
} Smetka;

int main(){
    int n;
    scanf("%d\n",&n);
    Smetka niza[n];
    float total = 0;
    for(int i=0;i<n;i++){
        Smetka sm;
        scanf("%s",sm.ime);
        scanf("%f\n%f",&sm.cena,&sm.kolichina);
        niza[i]= sm;
        char clear = getchar(); 

    }
    for(int i=0;i<n;i++){
        total+=(niza[i].cena*niza[i].kolichina);
        printf("%d. %s\t%.2f x %.1f = %.2f\n",i+1,niza[i].ime,niza[i].cena,niza[i].kolichina,niza[i].cena*niza[i].kolichina);
    }
    printf("Total: %.2f",total);
}