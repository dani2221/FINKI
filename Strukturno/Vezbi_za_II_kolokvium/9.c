// Во дадена датотека dat.txt да се најде најдолгиот ред во кој има барем 2 цифри. 
// На стандарден излез да се испечатат знаците од најдолгиот ред кои се наоѓаат помеѓу првата
// и последната цифра (заедно со тие 2 цифри) во истиот редослед. 
// Доколку има повеќе такви редови се печати последниот. 
// Се претпоставува дека ниту еден ред на датотеката не е подолг од 100 знаци.

#include <stdio.h>
#include <ctype.h>
#include <string.h>
// ne menuvaj ovde
void wtf() {
    FILE *f = fopen("dat.txt", "w");
    char c;
    while((c = getchar()) != EOF) {
        fputc(c, f);
    }
    fclose(f);
}
int imaDveCifri(char niza[]){
    int broj = 0;
    for(int i=0;i<100;i++){
        if(niza[i]=='\0') break;
        if(isdigit(niza[i])) broj++;
    }
    return broj>=2;
}

int main() {
    wtf();
	
    FILE *f;
    char red[100];

    if(!(f=fopen("dat.txt","r"))) printf("GRESKA");
    int maks = 0,linija = 0,i=0;
    while(fgets(red,100,f)!=NULL){
        int length = strlen(red);
        if(!imaDveCifri(red)) length=0;
        if(length>=maks){
            maks=length;
            linija = i;
        }
        i++;
    }
    fclose(f);
    if(!(f=fopen("dat.txt","r"))) printf("GRESKA");
    int br=0;
    while(fgets(red,100,f)!=NULL){
        if(br==linija){
            int startPrint = 0;
            int start,end;
            int p = 0;
            while(red[p]!='\0'){
                if(isdigit(red[p]) && startPrint==0){
                    start = p;
                    startPrint = 1;
                }
                if(isdigit(red[p])){
                    end = p;
                }
                p++;
            }
            for(int i=start;i<=end;i++){
                printf("%c", red[i]);
            }
            break;
        }
        br++;
    }

    return 0;
}