// (40 поени)
// Да се креира класа Patnik во која се чуваат податоци за патниците на еден воз и тоа: 
// име (низа од максимум 100 знаци), класа на вагон во која се вози (цел број 1 или 2) 
// и велосипед (булова променлива).
// За оваа класа да се преоптоварат:
// Оператор << - за печатење на информациите за патникот во формат:
// Име на патник
// Бројот на класата (1 или 2)
// Дали има велосипед?
// Потоа да се креира клaса Voz во која се чува: крајна дестинација (низа од максимум 100 знаци),
// динамички алоцирана низа од објекти од класата Patnik, 
// како и број на елементи во низата (цел број), број на дозволени велосипеди (цел број).
// За класата да се обезбедат:
// Оператор += – за додавање нов патник во воз. 
// Патник со велосипед ќе може да се качи во воз само ако возот го дозволува тоа.
// Оператор << - за печатење на крајната дестинација до која вози и листата на патници
// Функција со потпис: patniciNemaMesto(). 
// Со оваа функција се пресметува вкупниот број на патници (од 1-ва класа и 2-ра класа) 
// за кои нема место да влезат во возот со велосипед.
// Притоа треба да се внимава дека во пресметувањето на вкупниот број патници 
// со велосипеди кои ќе влезат во возот прво треба да се земат предвид патниците од 1-ва класа, 
// а потоа се дозволува да влегуваат патниците од 2-ра класа 
// се додека не се достигне максималниот број на дозволени велосипеди во возот.
// На крај се печати бројот на патници со велосипеди кои остануваат (не влегуваат во возот) 
// од 1-ва класа, а потоа од 2-ра класа.

#include<iostream>
#include<cstring>
using namespace std;

class Patnik{
    private:
        char ime[100];
        int klasa;
        bool velosiped;
    public:
        Patnik(){}
        Patnik(char ime[], int klasa, bool velosiped){
            strcpy(this->ime,ime);
            this->klasa=klasa;
            this->velosiped=velosiped;
        }
        friend ostream &operator<<(ostream &os, Patnik &pk){
            os<<pk.ime<<endl<<pk.klasa<<endl<<pk.velosiped<<endl;
        }
        Patnik &operator=(Patnik &pk){
            if(this==&pk) return *this;
            strcpy(this->ime,pk.ime);
            this->klasa=pk.klasa;
            this->velosiped=pk.velosiped;
            return *this;
        }
        bool isVelo(){return velosiped;}
        int getKlasa(){return klasa;}
	
};

class Voz{
    private:
        char destinacija[100];
        Patnik *patnici;
        int broj;
        int velosipedi;
    public:
        Voz(char destinacija[],int maks){
            strcpy(this->destinacija,destinacija);
            this->velosipedi=maks;
            this->broj = 0;
        }
        Voz &operator+=(Patnik &pk){
            // int velos = 0;
            Patnik *temp = new Patnik[broj+1];
            for(int i=0;i<broj;i++){
                temp[i] = patnici[i];
                // if(patnici[i].isVelo()) velos++;
            }
            if(velosipedi==0&&pk.isVelo()){
                delete [] temp;
                return *this;
            }
            temp[broj] = pk;
            if(broj) delete [] patnici;
            patnici = temp;
            broj++;
            return *this;
        }
        friend ostream& operator<<(ostream &os, Voz &voz){
            os<<voz.destinacija<<endl;
            for(int i=0;i<voz.broj;i++){
                os<<voz.patnici[i]<<endl;
            }
            return os;
        }
        void patniciNemaMesto(){
            int velos = 0,odbieni1=0,odbieni2=0;
            for(int i=0;i<broj;i++){
                if(patnici[i].getKlasa()==1 && patnici[i].isVelo()){
                    velos++;
                    if(velos>velosipedi) odbieni1++;
                }
            }
            for(int i=0;i<broj;i++){
                if(patnici[i].getKlasa()==2 && patnici[i].isVelo()){
                    velos++;
                    if(velos>velosipedi) odbieni2++;
                }
            }
            cout<<"Brojot na patnici od 1-va klasa koi ostanale bez mesto e: "<<odbieni1<<endl;
            cout<<"Brojot na patnici od 2-ra klasa koi ostanale bez mesto e: "<<odbieni2<<endl;

        };
	
};


int main()
{
	Patnik p;
	char ime[100], destinacija[100];
	int n;
	bool velosiped;
	int klasa;
	int maxv;
	cin >> destinacija >> maxv;
	cin >> n;
	Voz v(destinacija, maxv);
	//cout<<v<<endl;
	for (int i = 0; i < n; i++){
		cin >> ime >> klasa >> velosiped;
		Patnik p(ime, klasa, velosiped);
		//cout<<p<<endl;
		v += p;
	}
	cout << v;
	v.patniciNemaMesto();

	return 0;
}
