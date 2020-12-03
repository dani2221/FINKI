// Од стандарден влез се чита позитивен број n кој ги дава димензиите на квадратна матрица од цели броеви,
// која се чита во продолжение. Да се напише програма која за таа матрица(макс. 100 x 100) 
//cќе ја испечати должината на најдолгата строго растечка подниза, 
//доколку матрицата ја гледаме ред по ред.

#include <stdio.h>

int main(){
    int n;
    scanf("%d",&n);
    int matrica[n][n];
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            scanf("%d",&matrica[i][j]);
        }
    }
    int maks = 1, dolzina =1;
    for(int i=0;i<n;i++){
        if(dolzina>maks) {
            maks = dolzina;
            dolzina=1;
        }
        for(int j=1;j<n;j++){
            if(matrica[i][j] > matrica[i][j-1]) dolzina++;
            else{
                if(dolzina>maks) maks=dolzina;
                dolzina=1;
            }
        }
    }
    printf("%d",maks);

    return 0;
}

