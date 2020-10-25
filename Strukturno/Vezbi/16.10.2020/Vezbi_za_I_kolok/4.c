// Од стандарден влез се читаат знаци се додека не се прочита извичник. 
// Во вака внесениот текст се скриени цели броеви (помали од 100). 
// Да се напише програма што ќе ги прочита сите знаци и 
// на излез ќе го испечати збирот на сите броеви скриени во текстот.


#include <stdio.h>

int main(){
    char nowch;
    int zbir = 0;
    int prethodna = 0;
    while(nowch!='!'){
        scanf("%c",&nowch);
        if((int) nowch > 47 && (int) nowch < 58){  //ASCII kodovi za cifri 0-9
            if(prethodna==0){
                prethodna = (int) nowch - 48;
            }else{
                zbir+=prethodna*10+(int) nowch - 48;
                prethodna=0;
            }

        }else if(prethodna != 0){
            zbir+=prethodna;
            prethodna=0;
        }   
    }
    printf("%d",zbir);
    
    return 0;
}
