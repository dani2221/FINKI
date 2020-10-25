// Благ број е број што е составен само од парни цифри (0, 2, 4, 6, 8). 
// Во зададен опсег (почетокот m и крајот на опегот n се цели броеви
// чија вредност се внесува од тастатура), 
// да се најде и испечати најмалиот „благ број“. 
// Ако не постои таков број, да се испечати NE.


#include <stdio.h>

int main(){
    int d_opseg,g_opseg;
    scanf("%d%d",&d_opseg,&g_opseg);
    for(int i=d_opseg;i<=g_opseg;i++){
        int num = i;

        while (num!=0){
            if(num%10%2!=0){
                break;
            }
            num/=10;
        }
        if(num==0){
            printf("%d",i);
            break;
        }else{
            if(i==g_opseg) printf("NE");
        }
        
    }
    return 0;
}