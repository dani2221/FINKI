// За дадени освоени поени пишете ја оценката која го следи студентот според дадените табели.
// 0-50 = 5
// 51-60 = 6
// 61-70 = 7
// 71-80 = 8
// 81-90 = 9
// 91-100 = 10
// Покрај оценките да се испечатат и знаците + и – 
// во зависност од вредноста на последната цифра на поените:
// 1 - 3 = "-"
// 4 - 7 = " "
// 8-0 = "+"


//ima greshka vo predposledniot test sluchaj
#include <stdio.h>

int main(){
    int poeni;
    scanf("%d",&poeni);
    char z;
    if(poeni%10<=3 && poeni%10>0) z='-';
    if(poeni%10<=7 && poeni%10>3) z=' ';
    if(poeni%10>7 || poeni%10==0) z='+';
    if(poeni<0 || poeni>100){ 
        printf("Nevalidna vrednost za poeni!");
        return 0;
    }
    if(poeni<=50) printf("Ocenka 5%c",z);
    else if(poeni<=60) printf("Ocenka 6%c",z);
    else if(poeni<=70) printf("Ocenka 7%c",z);
    else if(poeni<=80) printf("Ocenka 8%c",z);
    else if(poeni<=90) printf("Ocenka 9%c",z);
    else printf("Ocenka 10%c",z);

    return 0;
}