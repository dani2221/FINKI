// Од стандарден влез се чита позитивен број n кој ги дава димензиите на квадратна матрица од цели броеви,
// која се чита во продолжение. Да се напише програма која за таа матрица(макс. 100 x 100)
// ќе ја испечати должината на најдолгата строго растечка подниза, 
// доколку матрицата ја гледаме ред по ред.

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
    int maksDolzinaNiza = 1, maksDolzinaMatrica = 1;
    for(int i=0;i<n;i++){
        for(int j=0;j<n-1;j++){
            if(matrica[i][j]<matrica[i][j+1]){
                maksDolzinaNiza++;
            }else{
                if(maksDolzinaNiza>maksDolzinaMatrica){
                    maksDolzinaMatrica = maksDolzinaNiza;
                }
                maksDolzinaNiza=1;
            }
        }
        if(maksDolzinaNiza>maksDolzinaMatrica){
            maksDolzinaMatrica = maksDolzinaNiza;
        }
        maksDolzinaNiza=1;
    }
    printf("%d",maksDolzinaMatrica);
    return 0;
}