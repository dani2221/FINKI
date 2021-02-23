#include <iostream>
#include <cstring>

using namespace std;
class Publikacija {
private:
  char naziv [100];
protected:
  int godina;
  char* getNaziv() {
    return naziv;
  }

public:
  int getGodina () { return godina;}
  void pecati () {
    cout << "Publikacija: "<< naziv << " - " << godina << endl;
 }
 Publikacija( char *naziv, int godina ) {
   strcpy(this->naziv, naziv);
   this->godina = godina;
 }
};

// public изведување
class Kniga: public Publikacija {
private:
  int broj_strani;
public:
  Kniga(char *naziv, int godina, int broj_strani):Publikacija(naziv, godina){
    this->broj_strani = broj_strani;
  }
  void pecatiGodinaKniga(){
    cout << godina; //пристап до protected променливата godina
  }
  void pecatiNazivKniga (){
    //пристап до getNaziv(), naziv не може да се пристапи затоа што е private
    cout << getNaziv();
  }
  void pecatiStrani (){
    cout << broj_strani;
  }
};

// protected изведување
class Vesnik: protected Publikacija{
private:
  int broj;
public:
  Vesnik(char* naziv, int godina, int broj):Publikacija(naziv, godina){
    this->broj=broj;
  }
  void pecatiGodinaVesnik (){
    cout << getGodina(); //пристап до public getGodina(), која во оваа класа има protected пристап
      }
  void pecatiNazivVesnik(){
    cout << getNaziv(); //пристап до getNaziv(), naziv не може да се пристапи затоа што е private
      }
  void pecatiBroj(){
    cout << broj;
  }
};

//private изведување
class DnevenVesnik: private Vesnik{
private:
  int den;
  int mesec;
public:
  DnevenVesnik(char *naziv, int den, int mesec, int godina,
               int broj):Vesnik(naziv, godina,broj){
    this->den=den;
    this->mesec=mesec;
  }
  using Vesnik::pecati; // функцијата pecati од Publikacija станува public за DnevenVesnik
  using Vesnik::pecatiBroj; // функцијата pecatiBroj од Publikacija станува public за DnevenVesnik
};

int main()
{
  Publikacija p("Tabernakul", 1992);
  p.pecati(); // public - функција
  Kniga *k = new Kniga("ProsvetnoDelo", 1900, 123);
  k->pecati(); //pecati e public во Kniga
  k->pecatiGodinaKniga(); // public - функција
  // cout<<k->getNaziv(); // грешка! protected - функција
  Vesnik *s = new Vesnik("Tea", 2013 ,30);
  // s->pecati(); //грешка! protected - функција
  // cout<<s->getGodina(); // грешка! protected - функција
  s->pecatiNazivVesnik(); // public - функција
  DnevenVesnik d("Vest",2,3,2014,25);
  d.pecati(); //public-функција
  // d.pecatiNazivVesnik(); // грешка! private - функција
  // cout<<d.getNaziv(); // грешка! private – функција
}
