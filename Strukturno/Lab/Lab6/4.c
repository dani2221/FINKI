// Од стандарден влез се чита позитивен непарен број n (n е најмалку 3) 
// кој ги дава димензиите на квадратната матрица од цели броеви, 
// која се чита во продолжение. Да се напише програма која од таа матрица(макс. 100 x 100) 
// ќе креира нова матрица 2 x 2, 
// која ќе ги има за елементи збировите на елементите на регионите на првата матрица, соодветно. 

#include <stdio.h>

int main(){
    int m;
    scanf("%d",&m);
    int matrica[m][m], krajna[]={0,0,0,0};
    for(int i=0;i<m;i++){
        for(int j=0;j<m;j++){
            scanf("%d",&matrica[i][j]);
        }
    }
    for(int i=0;i<m;i++){
        for(int j=0;j<m;j++){
            if(i<m/2 && j<m/2) krajna[0]+=matrica[i][j];
            if(i<m/2 && j>m/2) krajna[1]+=matrica[i][j];
            if(i>m/2 && j<m/2) krajna[2]+=matrica[i][j];
            if(i>m/2 && j>m/2) krajna[3]+=matrica[i][j];
        }
    }
    for(int i=0;i<4;i++){
        printf("%d ",krajna[i]);
        if(i==1) printf("\n");
    }

    return 0;
}