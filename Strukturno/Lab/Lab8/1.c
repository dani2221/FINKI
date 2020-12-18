// Да се напише функцијата matematickaOperacija (int a, int b, char operation, int rezultat)
// која што нема да враќа резултат. 
// Целта на функцијата е врз основа на аргументот operation да изврши определена математичка операција, 
// и резултатот од истата да го смести во rezultat. Возможни вредности за operation се:

// + (собирање)
// - (разлика)
// * (производ)
// / (делење)
// % (остаток)

#include <stdio.h>

void matematickaOperacija (int a, int b, char operation, int *rezultat){
    switch (operation)
    {
    case '+':
        *rezultat = a+b;
        break;
    case '-':
        *rezultat = a-b;
        break;
    case '*':
        *rezultat = a*b;
        break;
    case '/':
        *rezultat = a/b;
        break;
    case '%':
        *rezultat = a%b;
        break;
    }
}

int main(){
    int a,b,*rezultat,r;
    rezultat=&r;
    char op[]={'+','-','*','/','%'};
    scanf("%d %d",&a,&b);
    for(int i=0;i<5;i++){
        matematickaOperacija(a,b,op[i],rezultat);
        printf("%d %c %d -> %d\n",a,op[i],b,*rezultat);
    }
    return 0;

}