// Еден елемент од матрица ја дели матрицата на 4 квадранти (прикажани на сликата). 
// Притоа самиот елемент кој ја дели матрицата припаѓа во четвртиот квадрант (-5 во примерот на сликата).
// Од стандарден влез се внесува матрица со димензии NxM (1 <= N, M < 100). 
// Потоа се внесуваат два броеви кои претставуваат индекси на еден елемент од матрицата.
// Да се најдат сумите на секој од квадрантите и да се испечатат на стандарен излез. 
// Притоа се печати сумата за првиот квадрант, па за вториот, па третиот и на крај за четвртиот. 
// Доколку не може да се креира квадрант, тогаш за сумата на тој квадрант треба да се испечати 0.

#include <stdio.h>

int main(){
    int n,m;
    scanf("%d %d",&n,&m);
    int matrica[n][m];
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            scanf("%d",&matrica[i][j]);
        }
    }
    int pozX,pozY;
    int k1=0,k2=0,k3=0,k4=0;
    scanf("%d %d",&pozX,&pozY);
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            if(i<pozX){
                if(j<pozY) k2+=matrica[i][j];
                else k1+=matrica[i][j];
            }else{
                if(j<pozY) k3+=matrica[i][j];
                else k4+=matrica[i][j];
            }
        }
    }
    printf("%d %d %d %d",k1,k2,k3,k4);
    return 0;
}