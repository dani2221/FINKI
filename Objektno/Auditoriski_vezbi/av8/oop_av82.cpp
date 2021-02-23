#include<iostream>
#include<cstring>
using namespace std;

class HotelskaRezervacija{
protected:
  int denovi;
  int broj_lica;
  char ime[50];
  char prezime[50];

public:
  HotelskaRezervacija(char *ime, char *prezime, int denovi, int broj_lica){
    strcpy(this->ime,ime);
    strcpy(this->prezime, prezime);
    this->denovi=denovi;
    this->broj_lica=broj_lica;
 }

  virtual int vratiCena() {
    return denovi * broj_lica * 25;
  }
  virtual int vratiCena(int uplata) {
    if (uplata >= vratiCena())
      return uplata - vratiCena();
    else {
      cout<<"Za vashata rezervacija treba da naplatite "<<vratiCena()<<endl;
      return -1;
    }
  }
};


class PolupansionskaHotelskaRezervacija: public HotelskaRezervacija{
public:
  PolupansionskaHotelskaRezervacija(char *ime, char *prezime, int denovi, int
                                    broj_lica) : HotelskaRezervacija(ime,prezime,denovi,broj_lica){}

  //препокривање на vratiCena(int uplata)
  int vratiCena(int uplata){
    int cena= HotelskaRezervacija::vratiCena() + broj_lica * 5; // пристап до protected податокот broj_lica
    if (uplata >= cena)
      return uplata - cena;
    else {
      cout<<"Za vashata rezervacija treba da naplatite "<<cena<<endl;
      return -1;
    }
  }
};

class Hotel{
private:
  char ime[50];
  int saldo;
public:
  Hotel(char *ime) {
    strcpy(this->ime, ime);
    saldo = 0;
  }
  // референца кон основната класа може да референцира објекти и кон изведените класи
  int uplatiZaRezervacija(HotelskaRezervacija &hr, int uplata) {
    int kusur=hr.vratiCena(uplata); //полиморфизам
    // која дефиниција на vratiCena ќе се повика?
    // важно: vrtiCena е виртуелна функција
    if (kusur != -1)
      saldo += uplata - kusur;
    return kusur;
  }
};

int main() {
  Hotel h("Bristol");
  HotelskaRezervacija *hr1=new HotelskaRezervacija("Petko","Petkovski",5,5);
  int cena = h.uplatiZaRezervacija(*hr1,1000);
  if (cena!=-1)
    cout<<"Kusur : "<<cena<<endl;
  PolupansionskaHotelskaRezervacija *hr2=
    new PolupansionskaHotelskaRezervacija("Risto","Ristovski",5,5);
  cena=h.uplatiZaRezervacija(*hr2,1000);
  if (cena!=-1)
    cout<<"Kusur : "<<cena<<endl;
  //покажувач кон основна класа покажува кон објект од изведена
  HotelskaRezervacija *hr3=new PolupansionskaHotelskaRezervacija("Ana","Anovska",4,2);
  cena=h.uplatiZaRezervacija(*hr3,100);
  if (cena!=-1)
    cout<<"Kusur : "<<cena<<endl;
  PolupansionskaHotelskaRezervacija hr4("Tome","Tomovski",5,3);
  cena=h.uplatiZaRezervacija(hr4,1000);
  if (cena!=-1)
    cout<<"Kusur : "<<cena<<endl;
}
