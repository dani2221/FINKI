// Да се напише програма која вчитува матрица со димензии MxN (макс. 100x100).
// На почетокот се внесуваат димензиите на матрицата,
// а потоа и елементите на матрицата кои се само вредностите 1 и 0. 
// Програмата треба да изброи и отпечати на СИ во колку од редиците и колоните има барем 3
// последователни елементи со вредност 1.

#include <stdio.h>

int main(){
    int m,n,vkupno=0;
    scanf("%d %d",&m,&n);
    int matrica[m][n];
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            scanf("%d",&matrica[i][j]);
        }
    }
    for(int i=0;i<m;i++){
        int kecovi = 0;
        for(int j=0;j<n;j++){
            kecovi+=matrica[i][j];
            if(kecovi>2){
                vkupno++;
                break;
            }
            if(matrica[i][j]==0) kecovi = 0;
        }

    }
    for(int j=0;j<n;j++){
        int kecovi = 0;
        for(int i=0;i<m;i++){
            kecovi+=matrica[i][j];
            if(kecovi>2){
                vkupno++;
                break;
            }
            if(matrica[i][j]==0) kecovi = 0;
        }
    }
    printf("%d",vkupno);


    return 0;
}