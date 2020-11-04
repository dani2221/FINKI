// Од влез се внесува цел позитивен број n, така што n > 2. Со помош на ѕвездички, 
// да се исцрта полн правоаголен триаголник со висина и ширина n, како во примерот:
// За n=5
//     *
//    **
//   ***
//  ****
// *****

#include <stdio.h>

int main(){
    int n;
    scanf("%d",&n);
    if(n<=2){
        printf("Nevaliden vlez");
        return 0;
    }
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            if(j>n-i-2) printf("*");
            else printf(" ");
            if(j==n-1) printf("\n");
        }
    }
}