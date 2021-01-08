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
    while(1){
        int main;
        scanf("%d",&main);
        fprintf(f,"%d ",main);
        if(main==0) break;
        for(int i=0;i<main;i++){
            int br;
            scanf("%d",&br);
            fprintf(f,"%d ",br);
        }
        putc('\n',f);
    }
    fclose(f);
}

int main()
{
    wtf();
    
    FILE *f;
    if(!(f=fopen("broevi.txt","r"))) printf("GRESKA");

    char redd[1000];
    while(fgets(redd,1000,f)!=NULL){
        char *red = redd;
        int maks = 0,maksCif = 0;
        int n = atoi(red);
        for(int i=0;i<n;i++){
            while(*red!=' ') red++;
            int num = atoi(red);
            int fullnum = num;
            while(num>=10) num/=10;
            if(num>maksCif){
                maksCif = num;
                maks = fullnum;
            }
            red++;
        }
        if(maks!=0) printf("%d\n",maks);
    }
    return 0;
        	
}