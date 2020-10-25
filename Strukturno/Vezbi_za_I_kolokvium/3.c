// Од стандарден влез се чита еден природен број n. 
// Меѓу природните броеви помали од n, да се најде оној чиј што збир на делителите е најголем. 
// Во пресметувањето на збирот на делителите на даден број, да не се зема предвид самиот број.

#include <stdio.h>

int main(){
    int broj;
    scanf("%d",&broj);
    broj--;
    int max = 0;
    int maxBroj = 0;

    while(broj>0){
        int zbir = 0;
        for(int i=1;i<broj;i++){
            if(broj%i==0){
                zbir+=i;
            }
        }
        if(zbir>max){
            max=zbir;
            maxBroj=broj;
        }
        broj--;
    }
    printf("%d",maxBroj);
    
    

    return 0;
}