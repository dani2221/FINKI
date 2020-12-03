// За дадена квадратна матрица со големина N, 
// најдете ја разликата помеѓу главната и споредната дијагонала, 
// а потоа разликата помеѓу првата колона и последната колона на матрицата.

#include <stdio.h>

int main(){
    int m;
    scanf("%d",&m);
    int glavnaDiagonala=0,sporednaDiagonala=0,prvaKolona=0,poslednaKolona=0;
    for(int i=0;i<m;i++){
        for(int j=0;j<m;j++){
            int a;
            scanf("%d",&a);
            if(i==j) glavnaDiagonala+=a;
            if(i==m-j-1) sporednaDiagonala+=a;
            if(j==0) prvaKolona+=a;
            if(j==m-1) poslednaKolona+=a;
        }
    }
    printf("%d\n",glavnaDiagonala-sporednaDiagonala);
    printf("%d\n",prvaKolona-poslednaKolona);
    return 0;
}