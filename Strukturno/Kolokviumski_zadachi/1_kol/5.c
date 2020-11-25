// Од стандарден влез се чита еден број кој претставува датум во формат DDMMYYYY
// (DD-ден, MM-месец, YYYY-година) кој го означува денешниот датум. Потоа се
// вчитува цел број N, по кој се вчитуваат N датуми на раѓање во дадениот
// формат. За секој од прочитаните N датуми на раѓање треба да се отпечати "DA"
// ако на денешниот ден (вчитан на почетокот) има повеќе или точно 18 години,
// а во спортивно "NE"

#include <stdio.h>

int main(){

    int datum,den,mesec,godina,n;
    scanf("%d %d",&datum, &n);
    godina = datum%10000;
    mesec = datum/10000%100;
    den = datum/1000000;
    for(int i=0;i<n;i++){
        int datum1,godina1,mesec1,den1;
        scanf("%d",&datum1);
        godina1 = datum1%10000;
        mesec1 = datum1/10000%100;
        den1 = datum1/1000000;
        if(godina-godina1>18){
            printf("DA");
            continue;
        }else if(godina-godina1==18){
            if(mesec1>=mesec){
                if(den1>=den1){
                    printf("DA");
                    continue; 
                }
            }
        }
        printf("NE");
    }


    return 0;
}