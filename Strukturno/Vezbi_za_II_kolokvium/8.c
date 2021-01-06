// Од тастатура се внесуваат димензиите на една матрица (m, n <= 100),
// а потоа и елементите од матрицата. Да се генерира низа (со најмногу m) 
// така што секој елемент од низата се добива со наоѓање на елементот во 
// секоја редица од матрицата што е најоддалечен од аритметичката средина во рамки на таа редица. 
// Ако постојат повеќе елементи што се најоддалечени од аритметичката средина, 
// тогаш се зема предвид првиот. 
// Редоследот на запишување на елементите во низата одговара на редоследот на редиците.

#include <stdio.h>
#include <math.h>


int main(){
    int m,n;
    scanf("%d %d",&m,&n);
    int matrica[m][n];
    float sredini[m];
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            scanf("%d",&matrica[i][j]);
            sredini[i]+=matrica[i][j];
        }
        sredini[i]/=n;
    }
    for(int i=0;i<m;i++){
        float maks = -0.1;
        int num = 0;
        for(int j=0;j<n;j++){
            if(fabsf(sredini[i]-matrica[i][j])>maks){
                //printf("%.2f \t %.2f\n",maks,fabsf(sredini[i]-matrica[i][j]));
                maks = fabsf(sredini[i]-matrica[i][j]);
                num = matrica[i][j];
            }
        }
        printf("%d ",num);
    }

    return 0;
}