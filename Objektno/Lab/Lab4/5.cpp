// Да се дефинира класа Pica за која ќе се чуваат податоци за:
// име на пицата (низа од 15 знаци)
// цена (цел број)
// состојки (динамички алоцирана низа од знаци)
// намалување на цената во проценти (цел број) 
// - ако пицата не е на промоција намалувањето има вредност нула, во спротивно, 
// вредност поголема од нула и не поголема од 100.
// За потребите на оваа класа да се креираат потребните конструктори 
// и да се напише соодветен деструктор. Дополнително за оваа класа да се дефинира функцијата:
// pecati() - функција во која ќе се печатат податоците за пицата во следниот формат:
// име - состојки, цена.
// istiSe(Pica p) - функција за споредба на две пици според состојките :
// Да се дефинира класа Picerija во која се чуваат:
// име (низа од максимум 15 знаци)
// динмички алоцирана низа од објекти од класата Pica
// број на пици (цел број)
// За потребите на оваа класа да се дефинираат потребните конструктори 
// и да се напише соодветен деструктор. Да се имплементираат и следниве методи:
// dodadi (Pica P) - за додавање нова пица во пицеријата, 
// но само ако не постои иста во низата (нема пица со исти состојки со пицата што треба да се додаде).
// void piciNaPromocija() - се печатат сите пици од пицеријата што се на промоција во формат:
// име - состојки, цена, цена со попуст.

#include <iostream>
#include <cstring>

using namespace std;

class Pica{
    private:
        char ime[15];
        int cena;
        char *sostojki;
        int namaluvanje;
    public:
        Pica(){
            this->cena = 0;
            this->namaluvanje = 0;
        }
        Pica(char ime[], int cena = 0, char *sostojki = "", int namaluvanje=0){
            strcpy(this->ime,ime);
            this->sostojki = new char[strlen(sostojki)+1];
            strcpy(this->sostojki,sostojki);
            this->namaluvanje = namaluvanje;
            this->cena=cena;
        }
        char *getSostojki(){return sostojki;}
        void pecati(){
            cout<<ime<<" - "<<sostojki<<", "<<cena<<" "<<cena*((float)(100-namaluvanje)/100)<<endl;
        }
        bool istiSe(Pica pica){
            return !strcmp(this->sostojki,pica.sostojki);
        }
        bool isPromocija(){
            return namaluvanje!=0;
        }
        ~Pica(){
            delete [] sostojki;
        }
};

class Picerija{
    private:
        char ime[15];
        Pica *pici;
        int broj;
    public:
        Picerija(char ime[15]){
            strcpy(this->ime,ime);
            this->broj=0;
        }
        Picerija(char ime[15],Pica *p, int broj){
            strcpy(this->ime,ime);
            this->pici = new Pica[broj];
            for(int i=0;i<broj;i++){
               pici[i]=p[i];
            }
            this->broj=broj;
        }
        void setIme(char ime[15]){
            strcpy(this->ime,ime);
        }
        char* getIme(){
            return ime;
        }
        void dodadi(Pica p){
            for(int i=0;i<broj;i++){
                if(pici[i].istiSe(p)) return;
            }
            Pica *n = new Pica[broj+1];
            for(int i=0;i<broj;i++){
                n[i] = pici[i];
            }
            n[broj]=p;
            pici = n; 
            broj++;
        }
        void piciNaPromocija(){
            for(int i=0;i<broj;i++){
                if(pici[i].isPromocija()) pici[i].pecati();
            }
        }
        ~Picerija(){
            delete [] pici;
        }

};

int main () {

    int n;
    char ime[15];
    cin >> ime;
    cin >> n;

    Picerija p1(ime);
    for(int i = 0; i < n; i++){
        char imp[100];
        cin.get();
        cin.getline(imp,100);
        int cena;
        cin >> cena;
        char sostojki[100];
        cin.get();
        cin.getline(sostojki,100);
        int popust;
        cin >> popust;
        Pica p(imp,cena,sostojki,popust);
        p1.dodadi(p);
    }

    Picerija p2 = p1;
    cin >> ime;
    p2.setIme(ime);
    char imp[100];
    cin.get();
    cin.getline(imp,100);
    int cena;
    cin >> cena;
    char sostojki[100];
    cin.get();
    cin.getline(sostojki,100);
    int popust;
    cin >> popust;
    Pica p(imp,cena,sostojki,popust);
    p2.dodadi(p);

    cout<<p1.getIme()<<endl;
    cout<<"Pici na promocija:"<<endl;
    p1.piciNaPromocija();

    cout<<p2.getIme()<<endl;
    cout<<"Pici na promocija:"<<endl;
    p2.piciNaPromocija();

	return 0;
}
