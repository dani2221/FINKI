// da se podeli suma na pari po banknoti


#include <stdio.h>

int main(){

    int k5, k1, s5, s1, d5, d1, e5, e2, e1, suma;

    scanf("%d", &suma);

    k5 = suma/5000;
    suma-=k5*5000;
    k1 = suma/1000;
    suma -= k1*1000;
    s5 = suma/500;
    suma -= s5*500;
    s1 = suma/100;
    suma -= s1*100;
    d5 = suma/50;
    suma -= d5*50;
    d1 = suma/10;
    suma -= d1*10;
    e5 = suma/5;
    suma -= e5*5;
    e2 = suma/2;
    suma -= e2*2;
    e1 = suma;

    printf("%d*5000\n",k5);
    printf("%d*1000\n",k1);
    printf("%d*500\n",s5);
    printf("%d*100\n",s1);
    printf("%d*50\n",d5);
    printf("%d*10\n",d1);
    printf("%d*5\n",e5);
    printf("%d*2\n",e2);
    printf("%d*1\n",e1);

    return 0;
}