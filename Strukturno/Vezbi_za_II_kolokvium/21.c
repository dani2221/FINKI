// Од стаднарден влез се читаат N низи од знаци (стрингови) не подолги од 80 знаци.
// На почетокот на програмата се читаат два цели броеви:
// N - бројот на низи од знаци кои ќе се читаат и
// X - поместување.
// Секоја од вчитаните низи од знаци треба да се трансформира на тој начин што 
// секоја од малите и големите букви (a-z, A-Z) се заменува со истата буква 
// поместена X места понапред во азбуката (a-z). 
// Ако се надмине опсегот на буквите во азбуката, се продолжува циклично од почетокот на азбуката. 
// Трансформираната низа да се отпечати на СИ.
// Трансформацијата да се имплементира со посебна рекурзивна функција.

#include <stdio.h>
#include <string.h>
#include <ctype.h>

void pomestuvanje(char* string,int x){
    if(*string == '\0') return;
    if(isalpha(*string)){
        int bukva = *string + x;
        if(islower(*string)){
            if(bukva>'z') bukva = bukva%'z' + 'a' - 1;
        }
        if(isupper(*string)){
            if(bukva>'Z') bukva = bukva%'Z' + 'A' - 1;
        }
        *string = bukva;
    }
    pomestuvanje(string+1,x);
}

int main(){
    int n,x;
    scanf("%d%d\n",&n,&x);
    for(int i=0;i<n;i++){
        char string[80];
        fgets(string,80,stdin);
        pomestuvanje(string,x);
        printf("%s",string);
    }
    return 0;
}