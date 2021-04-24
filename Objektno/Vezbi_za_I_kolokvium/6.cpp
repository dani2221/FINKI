// Да се креира структура Воз во која се чуваат податоци за релацијата по која се движи возот 
// (низа од најмногу 50 знаци), предвиден бројот на километри што треба да се поминат (реален број),
// како и бројот на патници во возот (цел број).
// Потоа да се креирa структура ZeleznickaStanica во која се чуваат податоци за градот во кој се наоѓа 
// (низа од 20 знаци), низа од возови што поаѓаат од станицата (најмногу 30) и бројот на возови (цел број).
// Треба да се направи функција со потпис
// void najkratkaRelacija(ZeleznickaStanica* zs, int n, char* grad)
// во која се печати релацијата и бројот на километри на возот што поминува најкратка релација 
// (најмалку километри), а поаѓа од железничката станица од градот што се проследува како влезен аргумент. 
// Ако има повеќе возови со ист најмал број на километри, да се испечати релацијата на последниот таков.

#include <iostream>
#include <cstring>
using namespace std;

struct Voz{
    char relacija[50];
    double km;
    int patnici;
};
struct ZeleznickaStanica{
    char grad[20];
    Voz vozovi[30];
    int broj;
};
void najkratkaRelacija(ZeleznickaStanica* zs, int n, char* grad){
    for(int i=0;i<n;i++){
        if(!strcmp(zs[i].grad,grad)){
            int smolest = 0;
            double smolestKm = zs[i].vozovi[0].km;
            for(int j = 0; j<zs[i].broj;j++){
                if(zs[i].vozovi[j].km<=smolestKm){
                    smolestKm = zs[i].vozovi[j].km;
                    smolest = j;
                }
            }
            cout<<"Najkratka relacija: "<<zs[i].vozovi[smolest].relacija<<" ("<<zs[i].vozovi[smolest].km<<" km)"<<endl;
            return;
        }
    }
}

int main(){
	
    int n;
	cin>>n; //se cita brojot na zelezlnichki stanici

    ZeleznickaStanica zStanica[100];
    for (int i=0;i<n;i++){
        //se citaat infomracii za n zelezlnichkite stanici i se zacuvuvaat vo poleto zStanica
        char grad[20];
        int k;
        cin>>grad;
        strcpy(zStanica[i].grad,grad);
        cin>>k;
        zStanica[i].broj=k;
        for(int j=0;j<k;j++){
            char rel[50];
            double km;
            int pat;
            cin>>rel>>km>>pat;
            strcpy(zStanica[i].vozovi[j].relacija,rel);
            zStanica[i].vozovi[j].km=km;
            zStanica[i].vozovi[j].patnici=pat;
        }
    }
    
    char grad[25];
    cin>>grad;

	najkratkaRelacija(zStanica,n,grad);
	return 0;
}
