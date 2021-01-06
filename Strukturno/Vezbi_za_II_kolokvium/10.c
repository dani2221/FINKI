// Да се имплементира рекурзивна функција која за низа од цели броеви 
// a{0}, a{1}, ..., a_{n-1} ќе ја пресмета вредноста на непрекинатата дропка дефинирана.
// Да се напише програма во која се чита цел број N, 
// по што се читаат елементите на низа од N цели броеви (не повеќе од 100). 
// Потоа се повикува рекурзивната функција и се печати резултатот во нов ред.

#include <stdio.h>

float r(int niza[], int index, int size){
    if(index==size-1){
        return niza[index];
    }
    return niza[index]+1/r(niza,index+1,size);
}

int main(){
    int n;
    scanf("%d",&n);
    int niza[n];
    for(int i=0;i<n;i++){
        scanf("%d",&niza[i]);
    }
    printf("%f",r(niza,0,n));

    return 0;
}