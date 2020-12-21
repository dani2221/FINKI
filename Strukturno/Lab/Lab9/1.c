// Да се напише програма која што од датотека со име "text.txt" 
// ќе ги одреди и ќе ги испечати на стандарден излез:
// релативната фреквенција на сите мали букви
// релативната фреквенција на сите големи букви
// Релативната фреквенција на даден(и) карактер(и) се пресметува како
// количник на вкупното појавување на тој/тие карактер/и 
// со вкупниот број на карактери во текстот (да се игнорираат празните места и специјалните знаци).

#include <stdio.h>
#include <stdlib.h>

void writeToFile() {
    FILE *f = fopen("text.txt", "w");
    char c;
    while((c = getchar()) != '#') {
        fputc(c, f);
    }
    fclose(f);
}

int main() {
    writeToFile();
    
    FILE *f;
    if(!(f=fopen("text.txt","r"))){
        printf("Greska vo chitanje file");
        return -1;
    }
    char bukva;
    int golemi=0,mali=0,vkupno=0;

    while((bukva = fgetc(f)) != EOF) {
        if(bukva>='a' && bukva<='z'){ mali++; vkupno++;}
        if(bukva>='A' && bukva<='Z'){ golemi++; vkupno++;}
    }
    
    printf("%.4f\n%.4f",(float) golemi/vkupno,(float) mali/vkupno);

    fclose(f);
    return 0;
}
