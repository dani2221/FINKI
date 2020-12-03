// Да се напише програма во која се вчитува матрица (со димензии M и N, 1 < M,N <= 100) од цели броеви.
// Сите елементи од матрицата се поставени на вредност 0 или 1.
// Да се формира нова матрица така што на местото на сите елементи што имаат вредност 0, 
// треба да се смести бројот на елементи со вредност 1 околу тој елемент во сите осум насоки.
// Новодобиената матрица да се отпечати на стандарден излез, 
// при што наместо елементите со вредност 1 од оригиналната матрица ќе се отпечати знак *.

#include <stdio.h>

int main(){
    int m,n;
    scanf("%d %d",&m,&n);
    int matrica[m+2][n+2],krajnaMatrica[m+2][n+2]; 
    for(int i=0; i<m+2; i++){
        for(int j=0; j<n+2; j++){
            if(i==0 || j==0 || i==m+1 || j==n+1 ) matrica[i][j] = 0;
            else scanf("%d",&matrica[i][j]);
        }
    }
    for(int i=1; i<m+1; i++){
        for(int j=1; j<n+1; j++){
            if(matrica[i][j]==1) krajnaMatrica[i][j] = '*';
            else {
                krajnaMatrica[i][j]=
                                matrica[i+1][j]
                                +matrica[i+1][j+1]
                                +matrica[i][j+1]
                                +matrica[i-1][j]
                                +matrica[i-1][j-1]
                                +matrica[i][j-1]
                                +matrica[i+1][j-1]
                                +matrica[i-1][j+1];
            }

        }
    }
    for(int i=1; i<m+1; i++){
        for(int j=1; j<n+1; j++){
            if(krajnaMatrica[i][j]=='*')    printf("* ");
            else printf("%d ",krajnaMatrica[i][j]);
        }
        printf("\n");
    }
    return 0;
}