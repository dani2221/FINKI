// Од стандарден влез се чита цел број n. 
// Потоа се вчитуваат n низи од цели броеви (со максимална должина од 100 елементи),
// при што за секоја прво се внесува должината на низата, 
// а потоа сите елементи на низата, па на крај бројот k.
// Да се напише програма која за секоја низа ќе ја трансформира истата,
// т.ш. за секој k-ти елемент ќе се направи замена на местата помеѓу него и неговиот претходник.
// Се смета дека важи k е најмалку 2.

#include <stdio.h>

int main(){
    int n;
    scanf("%d",&n);
    int matrica[n][100];
    for(int i=0;i<n;i++){
        int golemina;
        scanf("%d",&golemina);
        for(int j=0;j<golemina;j++){
            scanf("%d",&matrica[i][j]);
        }
        int k;
        scanf("%d",&k);
        for(int j=0;j<golemina;j++){
            if((j+1)%k==0){
                int ph = matrica[i][j];
                matrica[i][j] = matrica[i][j-1];
                matrica[i][j-1] = ph;
            }
        }
        for(int j=0;j<golemina;j++){
            printf("%d ",matrica[i][j]);
        }
        printf("\n");
    }

    return 0;
}