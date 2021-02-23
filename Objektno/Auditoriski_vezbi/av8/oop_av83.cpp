#include<iostream>
#include<cmath>
using namespace std;
class GeomTelo{
protected:
  double visina;
public:
  GeomTelo (int visina = 0) {
    this->visina=visina;
  }
  virtual void pecati() { //виртуелна функција
    cout << visina;
  }
  virtual double getVolumen() = 0; //чисто виртуелна функција
  double getVisina() const {
    return visina;
  }
};

class Cilinder: public GeomTelo{
private:
  double radius;
public:
  // конструктор
  Cilinder(double radius, double visina):GeomTelo(visina){
    this->radius = radius;
  }
  //препокривање на функцијата pecati()
  void pecati(){
    cout << "Cilinder so visina ";
    GeomTelo::pecati();
    cout << " i so radius na osnovata " << radius << endl;
  }
  //препокривање на функцијата getVolumen()
  double getVolumen(){
    return M_PI * radius * radius * getVisina();
  }
  double getRadius(){
    return radius;
  }
};

class Konus: public GeomTelo {
private:
  double radius;
public:
  // контсруктор
  Konus(double radius, double visina):GeomTelo(visina){
    this->radius=radius;
  }
  //препокривање на функцијата pecati()
  void pecati(){
    cout << "Konus so visina ";
    GeomTelo::pecati();
    cout << " i so radius na osnovata " << radius << endl;
  }
  //препокривање на функциајта getVolumen()
  double getVolumen(){
    return M_PI*radius*radius*getVisina()/3.0;
  }
  double getRadius(){
    return radius;
  }
};


class Kvadar: public GeomTelo{
private:
  double a,b;
public:
  //конструктор
  Kvadar(double a, double b, double visina):GeomTelo(visina){
    this->a=a;
    this->a=b;
  }
  //препокривање на функцијата pecati()
  void pecati(){
    cout << "Kvadar so visina ";
    GeomTelo::pecati();
    cout << "i so osnova " << this->a << " i " << this->b <<endl;
  }
  //препокривање на функцијата getVolumen()
  double getVolumen(){
    return a * b * getVisina();
  }
};
void teloSoNajgolemVolumen(GeomTelo *niza[], int n );
double getRadius (GeomTelo *g);
int main(){
  GeomTelo** mnozestvoTela; //динамички алоцирано поле од покажувачи кон GeomTelo
  int n;
  cin>>n; //број на тела во множеството
  mnozestvoTela = new GeomTelo*[n]; //се алоцира меморија за полето од покажувачи
  for (int i = 0 ; i < n ; i++){
    int r,a,b,h,type;
    cout<<"Kakvo telo: 1-cilinder 2-konus 3-kvadar "<<endl;
    cin >> type;
    if (type==1) { //ако корисникот внесува цилиндер
      cin >> r >> h; mnozestvoTela[i]= new Cilinder(r,h); }
    if (type==2) { //ако корисникот внесува конус
      cin >> r >> h; mnozestvoTela[i]= new Konus(r,h); }
    if (type==3) { //ако корисникот внесува квадар
      cin >> a >> b >> h; mnozestvoTela[i]= new Kvadar(a,b,h); }
  }
    //барање 1
    teloSoNajgolemVolumen(mnozestvoTela,n);
    //барање 2
    int brojac=0;
    for (int i = 0 ; i < n ; i++)
      if (getRadius(mnozestvoTela[i]) == -1)
        brojac++;
    cout<<"Brojot na tela koi nemaat osnova krug e "<<brojac;
}
void teloSoNajgolemVolumen(GeomTelo *niza[], int n)
{
  int maxVolumen=0;
  int maxIndex=0;
  for (int i = 0 ; i < n ; i++)
    {
      if (niza[i]->getVolumen() > maxVolumen){
        //се повикува виртуелната функција getVolumen()
        maxVolumen=niza[i]->getVolumen();
        maxIndex=i;
      }
    }
  cout<<"Teloto so najgolem Volumen e:";
  niza[maxIndex]->pecati(); //се повикува виртуелната функција pecati()
}
double getRadius(GeomTelo *g){
  //претворање за време на извршување
  Cilinder* c = dynamic_cast<Cilinder *>(g);
  if (c != 0){ //ако може да се изведе претворањето
    return c->getRadius();
  }
  //претворање за време на извршување
  Konus* k = dynamic_cast<Konus *>(g);
  if (k!= 0 ){ //ако може да се изведе претворањето
    return k->getRadius();
  }
  return -1; // ако g не покажува кон цилиндер, ни кон конус врати -1
}
