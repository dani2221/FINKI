// Да се имплементира функција elka(int n) што на стандарден излез ќе печати новогодишна елка од ѕвезди.
// n означува колку ѕведички има основата на елката и во сите тест примери n е непарен број.

#include <stdio.h>

void elka(int n){
    int i=0;
    while(++i){
        for(int j=0;j<n;j++){
            if(j>n/2-i && j<n/2+i) printf("*");
            else printf(" ");
        }
        if(i==n/2+1) return;
        printf("\n");
    }
}

int main(){
    int k;
    scanf("%d",&k);
    elka(k);
    return 0;
}