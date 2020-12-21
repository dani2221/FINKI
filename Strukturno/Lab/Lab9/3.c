// Да се напише програма која што од дадена влезна датотека "text.txt"
// ќе испечати колку зборови има во секој ред, 
// како и ќе испечати просечен број на зборови во редовите во датотеката. 
// Во рамките на секој ред, кои било два збора се разделени точно со едно празно место.
// Дополнително да се испечати редот со најголем број на зборови во него, 
// на начин што секоја мала буква ќе биде голема, а секоја голема ќе биде мала.

#include <stdio.h>
#include <string.h>
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
        printf("Propblem so chitanje file");
        return -1;
    }
    int redovi = 0,suma = 0,max = 0,max_index = 0;
    while(!feof(f)){
        char line[1000];
        fgets(line,1000,f);
        if (feof(f))
            break;
        int index=0,zborovi=1;;
        while(line[index]!='\n'){
            if(line[index]==' ') zborovi++;
            index++;
        }
        if(zborovi>max) {
            max = zborovi;
            max_index = redovi;
        }
        printf("%d\n",zborovi);
        suma+=zborovi;
        redovi++;
    }
    printf("%.2f\n",(float)suma/redovi);
    fclose(f);
        if(!(f=fopen("text.txt","r"))){
        printf("Propblem so chitanje file");
        return -1;
    }
    redovi = 0;
    while(!feof(f)){
        char line[1000];
        fgets(line,1000,f);
        if(redovi==max_index){
            int index=0,zborovi=0;
            while(line[index]!='\n'){
                if(isupper(line[index])) printf("%c",tolower(line[index]));
                else printf("%c",toupper(line[index]));
                index++;
            }
            return 0;
        }
        redovi++;
    }
    return 0;
}
