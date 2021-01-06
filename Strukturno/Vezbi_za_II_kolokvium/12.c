// Да се напише програма во која од дадена датотека со име "input.txt"
// за секој ред ќе се отпечати бројот на цифри во тој ред, знакот :, 
// па самите цифри подредени според ASCII кодот во растечки редослед. 
// Редовите во датотеката не се подолги од 100 знаци.

#include <stdio.h>
#include <ctype.h>
void wtf() {
    FILE *f = fopen("input.txt", "w");
    char c;
    while((c = getchar()) != EOF) {
        fputc(c, f);
    }
    fclose(f);
}
void sort(int *niza,int n){
    while(1){
        int sorted = 1;
        for(int i=0;i<n-1;i++){
            if(*(niza+i)>*(niza+i+1)){
                int temp = *(niza+i);
                *(niza+i) = *(niza+i+1);
                *(niza+i+1) = temp;
                sorted = 0;
            }
        }
        if(sorted) break;
    }
}

int main() {

    wtf();
    FILE *f;
    char red[100];
    if(!(f=fopen("input.txt","r"))) printf("GRESKA");
    while(fgets(red,100,f)!=NULL){
        int brojac = 0;
        int niza[100];
        for(int i=0;i<100;i++){
            if(red[i]=='\0') break;
            if(isdigit(red[i])){
                niza[brojac]=red[i]-'0';
                brojac++;
            }
        }

        int *pniza;
        pniza = niza;
        sort(pniza,brojac);
        printf("%d:",brojac);
        for(int i=0;i<brojac;i++){
            printf("%d",*(niza+i));
        }
        printf("\n");
    }
    return 0;
}
