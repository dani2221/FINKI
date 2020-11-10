// Да се напише програма, каде што за даден број N внесен преку тастатура, 
// ќе испечати: "Tik" доколку е делив со 3, "Tak" доколку е делив со 5,
// "Tik - Tak" доколку е делив со 3 и со 5. 
// Доколку бројот не е делив со 3 ни со 5, тогаш да се испечати "Losh Broj".

#include <stdio.h>

int main(){
    int n;
    scanf("%d",&n);
    if(n%3==0 && n%5==0) printf("Tik - Tak");
    else if(n%3==0) printf("Tik");
    else if(n%5==0) printf("Tak");
    else printf("Losh Broj");

    return 0;
}