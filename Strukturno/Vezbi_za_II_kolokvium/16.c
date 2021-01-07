// За еден природен број а велиме дека е порамнување на друг природен број b 
// ако и само ако цифрите еднакви на 9 во бројот b се заменети со цифрата 7 во бројот а.
// Пример. Бројот 734775 е порамнување на бројот 934795.
// Од стандарден влез се внесуваат непознат број на цели броеви (не повеќе од 100), 
// се додека не се внесе нешто што не може да се интерпретира како цел број.
// Ваша задача е да ги отпечатите најмалите 5 од порамнувањата на сите внесени броеви, 
// по редослед од најмалиот кон најголемиот.
// Забелешка: Доколку се внесат помалку од 5 броеви, 
// тогаш печатите толку броеви колку што се соодветно внесени.
// Наоѓањето на порамнувањето на даден број треба да се реализира во 
// посебна рекурзивна функција poramnet(int a).

#include <stdio.h>

int poramnet(int a){
    if(a==0) return 0;
    int ostatok = a%10;
    if(ostatok==9) ostatok = 7;
    return ostatok + 10*poramnet(a/10);
}
int maksNizaPoz(int niza[]){
    int maks = -1,poz = 0;
    for(int i=0;i<5;i++){
        if(niza[i]>maks){
            maks = niza[i];
            poz = i;
        }
    }
    return poz;
}
void sort(int *niza){
    while(1){
        int sorted = 1;
        for(int i=0;i<4;i++){
            if(*(niza+i)>*(niza+i+1)){
                int temp = *(niza+i);
                *(niza+i) = *(niza+i+1);
                *(niza+i+1) = temp;
                sorted = 0;
            }
        }
        if(sorted==1) break;
    }
}

int main(){
    int broj,brojac = 0;
    int niza[]={0,0,0,0,0};
    while(scanf("%d",&broj)){
        int prm = poramnet(broj);
        if(brojac<5){
            for(int i=0;i<5;i++){
                if(niza[i]==0){
                    niza[i] = prm;
                    brojac++;
                    break;
                }
            }
        }else{
            if(prm<niza[maksNizaPoz(niza)]) niza[maksNizaPoz(niza)]=prm;
        }
    }
    sort(niza);
    for(int i=0;i<5;i++){
        if(niza[i]!=0){
            printf("%d ",niza[i]);
        }
    }
    return 0;
}