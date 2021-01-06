// Дадена е текстуална датотека (livce.txt) која претставува ливче во спортска обложувалница.
// На почетокот во датотеката, во посебен ред е запишана сумата на уплата (цел број).
// Потоа во секој ред од датотеката е запишан по еден тип во следниот формат:
// ab12 1 1.25
// Првиот број е шифрата на типот (низа од знаци која не е подолга од 9 знаци), 
// вториот број е типот (може да биде 1, 0 или 2) додека третиот број е коефициентот (реален број).
// Ваша задача е да се испечати типот со најголем коефициент како и можната добивка на ливчето. 
// Доколку има повеќе типови со ист максимален коефициент, да се испечати првиот.
// Можната добивка се пресметува како производ на сите коефициенти со сумата на уплата.

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// ne menuvaj ovde
void wf() {
    FILE *f = fopen("livce.txt", "w");
    char c;
    while((c = getchar()) != '#') {
        fputc(c, f);
    }
    fclose(f);
}

int main() {
    wf();
	FILE *f;
    if(!(f=fopen("livce.txt","r"))) printf("GRESKA");
    char red[100];
    fgets(red,10,f);
    
    int kesh = atoi(red);
    float dobivka = kesh;
    int maks = 0,brojac = 1;
    float maksKoef = 0.0;
    char rec[100][100];

    while(fgets(red,100,f)!=NULL){

        int spaces=0;
        char bukva;
        int i=1;
        while((bukva=red[i])!='\0'){
            if(bukva==' ') spaces++;
            if(spaces==2){
                float koef = atof(red+i+1);
                dobivka *= koef;
                printf("%.2f\n",koef);
                if(koef>maksKoef){
                    maks = brojac;
                    maksKoef=koef;
                }
                break;
            }
            i++;
        }
        brojac++;
    }
    fclose(f);
    if(!(f=fopen("livce.txt","r"))) printf("GRESKA");
    int br=0;
    while(1){
        fgets(red,10,f);
        if(br==brojac){
            printf("%s\n",red);
            break;
        }
        br++;
    }
    printf("%.2f",dobivka);

	return 0;    
}