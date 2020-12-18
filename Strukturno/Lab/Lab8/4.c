// Да се напише функција revertString(char *a, char *b, char *comparator) 
// која ќе ги преврти стринговите a и b и потоа ќе врати еден од нив во зависност од стрингот comparator,
// кој доколку има вредност pogolem се враќа лексикографски поголемиот 
// или доколку има вредност pomal се враќа лексикографски помалиот. 
// Доколку двата стрингови се еднакви, не е битно кој стринг ќе се врати.
// Во главниот дел од програмата треба N пати да се прочитаат 3 стрингови 
// и за нив да се испечати резулататот од revertString функцијата.
// Забелешка: За стрингот comparator се смета дека не е case-sensitive. 
// На пример за вредностите poGolEm и POGoleM функцијата треба да врати ист резултат. 
// Доколку пак comparator има непозната вредност да се врати стрингот "Invalid comparator".

#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>

void *rvsString(char *a){
    char new[strlen(a)];
    for(int i=0; i<strlen(a);i++){
        new[i] = *(a+strlen(a)-i-1);
    }
    for(int i=0; i<strlen(a);i++){
        *(a+i) = *(new+i);
    }
}

char *revertString(char *a, char *b, char *comparator){
    rvsString(a);
    rvsString(b);
    int i=0;
    char *mal = "pomal";
    char *golem = "pogolem";
    int isMal=1,isGolem=1;

    while (*(comparator+i)!='\0'){
        if(isMal){
            if(tolower(*(comparator+i))!=*(mal+i)){
                isMal=0;
            }
        }
        if(isGolem){
            if(tolower(*(comparator+i))!=*(golem+i)){
                isGolem=0;
            }
        }
        i++;
    }
    if(isMal){
        if(strcmp(a,b)>0) return b;
        else return a;
    }
    else if(isGolem){
        if(strcmp(a,b)>0) return a;
        else return b;
    }else{
        char *err = "Invalid comparator";
        return err;
    }
    

}

int main(){
    int n;
    scanf("%d",&n);
    char a[1024],b[1024],c[1024];
    for(int i=0;i<n;i++){
        scanf("%s",a);
        scanf("%s",b);
        scanf("%s",c);
        printf("%s\n",revertString(a,b,c));
    }
    return 0;
}