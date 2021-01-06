// Дадена е текстуална датотека text.txt.
// Да се избројат и испечатат сите последнователни појавувања 
// на соседни самогласки во датотеката. Појавата на големи и мали букви да се игнорира. 
// Пронајдените парови самогласки да се испечатат на екран, секој во нов ред со мали букви. 
// Потоа во нов ред се печати бројот на појавувања на паровите самогласки.

#include <stdio.h>
#include <ctype.h>
#include <string.h>

void writeToFile() {
    FILE *f = fopen("text.txt", "w");
    char c;
    while((c = getchar()) != '#') {
        fputc(c, f);
    }
    fclose(f);
}
int isSamoglaska(char bukva){
    if(tolower(bukva)=='a' 
    || tolower(bukva)=='e' 
    || tolower(bukva)=='i'
    || tolower(bukva)=='o'
    || tolower(bukva)=='u') return 1;
    return 0;
}

int main() {
    
    writeToFile();
   
    FILE *f;
    if(!(f = fopen("text.txt","r"))){
        printf("greska pri otvaranje file");
    }
    int brojac = 0;
    char bukva,prev = 'q';
    while((bukva=fgetc(f))!=EOF){
        if(isSamoglaska(bukva)){
            if(isSamoglaska(prev)){ 
                printf("%c%c\n",tolower(prev),tolower(bukva)); 
                brojac++;
            }
        }
        prev = bukva;
    }
    printf("%d",brojac);
    fclose(f);  
    return 0;
}
