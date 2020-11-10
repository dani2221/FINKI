// od sekundi da se presmetaat chasovi minuti i preostanati sekundi


#include <stdio.h>

int main(){
    int vnesSekundi,prvicniSekundi;
    scanf("%d",&vnesSekundi);
    prvicniSekundi=vnesSekundi;

    int s,m,h;
    h = vnesSekundi/3600;
    vnesSekundi -= 3600*h;
    m = vnesSekundi/60;
    vnesSekundi-=60*m;
    s = vnesSekundi;

    printf("%d sekundi se %d casovi, %d minuti i %d sekundi",prvicniSekundi,h,m,s);

    return 0;
}