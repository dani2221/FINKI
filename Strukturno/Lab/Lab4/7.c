// За дадена низа со големина N, прочитајте ги сите елементи од дадениот влез,
// а потоа сортирајте ја низата. Испечатете ја во растечки редослед.

#include <stdio.h>

int main(){
    int n;
    scanf("%d",&n);
    int niza[n];
    for(int i=0;i<n;i++){
        scanf("%d",&niza[i]);
    }
    int dobri=0;
    while(1){
        for(int i=0;i<n-1;i++){
            if(niza[i]>niza[i+1]){
                int ph = niza[i];
                niza[i] = niza[i+1];
                niza[i+1]= ph;
            }
            else dobri++;
        }
        if(dobri == n-1) break;
        dobri=0;
    }
    for(int i=0;i<n;i++){
        printf("%d ",niza[i]);
    }
    return 0;
}