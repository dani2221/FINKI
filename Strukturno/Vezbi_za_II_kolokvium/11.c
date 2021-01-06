// Во датотеката podatoci.txt се запишани редови со низи од знаци (секој не подолг од 80 знаци).
// Од стандарден влез се читаат два знака z1 и z2. 
// Да се напише програма со која на стандарден излез ќе се испечатат поднизите 
// од секој ред од датотеката составени од знаците што се наоѓаат меѓу z1 и z2 (без нив). 
// Секоја подниза се печати во нов ред.
// Се смета дека секој ред од датотеката точно еднаш ги содржи знаците z1 и z2, 
// знакот z1 секогаш се наоѓа пред знакот z2, а меѓу z1 и z2 секогаш има барем еден знак.

#include <stdio.h>
#include <ctype.h>
// ne menuvaj ovde
void wtf() {
    FILE *f = fopen("dat.txt", "w");
    char c;
    while((c = getchar()) != '#') {
        fputc(c, f);
    }
    fclose(f);
}

int main() {
    wtf();
    char ch = getchar(); 
    char z1,z2;
    scanf("%c%c",&z1,&z2);
    FILE *f;
    if(!(f=fopen("dat.txt","r"))) printf("GRESKA");
    while(!feof(f)){
        char red[100];
        fgets(red,100,f);
        if(feof(f)) break;
        int print = 0;
        for(int i=0;i<100;i++){
            if(red[i]==z2) break;
            if(print) putchar(red[i]);
            if(red[i]=='#') break;
            if(red[i]==z1) print=1;

        }
        printf("\n");

    }

    return 0;
}