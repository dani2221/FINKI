// Во дадена датотека “broevi.txt” се запишани повеќе редови со броеви 
// така што секој ред започнува со еден цел број (N>=1)
// што означува колку броеви следуваат по него во тој ред. 
// Да се напише програма која на СИ за секој ред ќе го испечати бројот со најголема најзначајна цифра. 
// Читањето на броеви завршува кога ќе се прочита 0.

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define MAX 100

//ne menuvaj!
void wtf() {
    FILE *f = fopen("broevi.txt", "w");
    int broj = -1;
    while(broj != 0) {
        scanf("%d",&broj);
        fprintf(f,"%d ",broj);
    }
    fclose(f);
}

int main()
{
    wtf();
    
    FILE *f;
    if(!(f=fopen("broevi.txt","r"))) printf("GRESKA");

    while(!feof(f)){
        int broevi;
        broevi = getw(f);
        printf("%d",broevi);
    
    }

        	
}