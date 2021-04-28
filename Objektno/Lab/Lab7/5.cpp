/* Да се креира хиерархиjа на класи за репрезентациjа на жичани инструменти. За потребите на оваа хиерархиjа да се дефинира класа ZicanInstrument од коjа ќе бидат изведени двете класи Mandolina и Violina.

Во класата ZicanInstrument се чуваат податоци за:

името на инструментот (низа од 20 знаци)
броjот на жици
основната цена на инструментот.
За класата Mandolina дополнително се чува неjзината форма (низа од 20 знаци).

За класата Violina дополнително се чува неjзината големина (децимален броj).

За секоjа изведените класи треба да се дефинираат соодветните конструктори и следните методи:

cena() за пресметување на цената на инструментот
основната цена на мандолината се зголемува за 15% доколку таа има форма во Неаполитански стил (вредноста на променливата форма е “Neapolitan”)
основната цена на виолината се зголемува за 10% ако неjзината големина има вредност 1/4 (0.25), односно за 20% ако неjзината големина има вредност 1 (1.00)
проптоварување на операторот ==, коj ги споредува жичаните инструменти според броjот на жици што го имаат
преоптоварување на операторот << за печатење на сите податоци за жичаните инструменти.
Да се напише функциjа pecatiInstrumenti(ZicanInstrument &zi, ZicanInstrument **i, int n) коjа на влез прима жичан инструмент, низа од покажувачи кон жичани инструменти и броj на елементи во низата. Функциjата jа печати цената на сите жичани инструменти од низата кои имаат ист броj на жици како и инструментот проследен како прв аргумент на функциjата.*/
#include<iostream>
#include<cstring>
#include<cmath>
using namespace std;
class ZicanInstrument
{
protected:
    char ime[20];
    int brojNaZici;
    int osnovnaCena;
public:
    ZicanInstrument(const char *ime="",int brojNaZici=0,int osnovnaCena=0)
    {
        strcpy(this->ime,ime);
        this->brojNaZici=brojNaZici;
        this->osnovnaCena=osnovnaCena;
    }
    virtual double cena()=0;

    int getBroj()
    {
        return brojNaZici;
    }

    friend bool operator ==(ZicanInstrument *z1,ZicanInstrument &z2)
    {
        if(z1->getBroj() == z2.getBroj())
            return true;
        return false;
    }
    friend ostream &operator<<(ostream &out,ZicanInstrument *z)
    {
        out<<z->ime<<" "<<z->brojNaZici<<" "<<z->osnovnaCena<<endl;
        return out;
    }
};
class Mandolina : public ZicanInstrument
{
private:
    char forma[20];
public:
    Mandolina(const char *ime="",int brojNaZici=0,int osnovnaCena=0,const char *forma="") : ZicanInstrument(ime,brojNaZici,osnovnaCena)
    {
        strcpy(this->forma,forma);
    }
    double cena()
    {
        if(strcmp(forma,"Neapolitan") == 0)
            return (osnovnaCena*1.15);
        return osnovnaCena;
    }
    friend ostream & operator <<(ostream &out,Mandolina &m)
    {
        out<<m.ime<<" " <<m.brojNaZici<<" " <<m.cena()<<" " <<m.forma<<endl;
        return out;
    }
};
class Violina : public ZicanInstrument
{
private:
    double golemina;
public:
    Violina(const char *ime="",int brojNaZici=0,int osnovnaCena=0,double golemina=0) : ZicanInstrument(ime,brojNaZici,osnovnaCena)
    {
        this->golemina=golemina;
    }
    double cena()
    {
        if(golemina==0.25)
            return osnovnaCena*1.1;
        if(golemina == 1.0)
            return osnovnaCena*1.2;
        return osnovnaCena;
    }
    friend ostream & operator <<(ostream &out,Violina &v)
    {
         out<<v.ime<<" " <<v.brojNaZici<<" " <<v.cena()<<" " <<v.golemina<<endl;
        return out;
    }

};
void pecatiInstrumenti(ZicanInstrument &z1,ZicanInstrument **niza, int n)
{
    for(int i=0;i<n;i++)
    {
        if(niza[i]==z1)
        {
            Mandolina *m=dynamic_cast<Mandolina*>(niza[i]);
            if(m != 0)
            {
                cout<<m->cena()<<endl;
            }
            Violina *v=dynamic_cast<Violina*>(niza[i]);
            if(v != 0)
            {
                cout<<v->cena()<<endl;
            }
        }
    }
}
void pecati(ZicanInstrument **niza, int n)
{
    for(int i=0;i<n;i++)
    {
        cout<<niza[i];
    }
}
int main ()
{
char ime[20];
	int brojZici;
	float cena;
	char forma[20];
	cin >> ime >> brojZici >> cena >> forma;
	Mandolina m(ime, brojZici, cena, forma);
	int n;
	cin >> n;
	ZicanInstrument **zi = new ZicanInstrument*[2 * n];
	for(int i = 0; i < n; ++i) {
		cin >> ime >> brojZici >> cena >> forma;
		zi[i] = new Mandolina(ime, brojZici, cena, forma);
	}
	for(int i = 0; i < n; ++i) {
		float golemina;
		cin >> ime >> brojZici >> cena >> golemina;
		zi[n + i] = new Violina(ime, brojZici, cena, golemina);
	}
	pecatiInstrumenti(m, zi, 2 * n);
	for(int i = 0; i < 2 * n; ++i) {
		delete zi[i];
	}
	delete [] zi;
	return 0;
}
