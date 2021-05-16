/*Да се креира класа Transakcija во која што ќе се чуваат информации за:

датумот на реализирање на банкарската трансакција:
ден (int)
месец (int)
година (int)
паричниот износ кој се однесува на трансакцијата (позитивен или негативен, тип double)
моменталната вредност на еврото во денари (static double EUR), иницијално поставен на 61
моменталната вредност на доларот во денари (static double USD), иницијално поставен на 50
За класата да се имплемтнираат соодветните конструктори, како и да се дефинираат следните четири чисто виртуелни методи:

double voDenari()
double voEvra()
double voDolari()
void pecati()
Трансакциите можат да бидат денарски и девизни (DenarskaTransakcija и DeviznaTransakcija). За девизните трансакции се чува дополнителна информација за валутата на трансакцијата (низа од три знаци). Дозволени валути за девизните транскации се USD и EUR.

За двете изведени класи да се напишат соодветните конструктори, деструктори и да се препокријат потребните методи.

Да се дефинира класа Smetka во која што ќе се чуваат информации за:

извршените трансакции (динамички алоцирана низа од покажувачи кон класата Transakcija)
број на извршените трансакции (int)
број на сметката (низа од 15 знаци)
почетно салдо во денари (double)
За класата Smetka да се имплементираат:

потребен конструктор (со два аргументи, видете во main), деструктор
оператор за додавање на нова трансакција во низата од трансакции +=
void izvestajVoDenari() - функција што печати информации за сметката во форматот:

Korisnikot so smetka: [број на сметката] ima momentalno saldo od [салдо на сметката пресметано во денари] MKD

void izvestajVoEvra() - функција што печати информации за сметката во форматот:

Korisnikot so smetka: [број на сметката] ima momentalno saldo od [салдо на сметката пресметано во евра] EUR

void izvestajVoDolari() - функција што печати информации за сметката во форматот:

Korisnikot so smetka: [број на сметката] ima momentalno saldo od [салдо на сметката пресметано во долари] УСД

void pecatiTransakcii() - функција што ги печати сите внесени трансакции
Да се креираат класи за следните исклучоци:

InvalidDateException којшто се фрла доколку при креирање на трансакција не се испочитувани правилата 1<=ден<=31 и 1<=месец<=12
NotSupportedCurrencyException којшто се фрла доколку при креирање на девизна трансакција се внесува вредност за валута што не е дозволена
Овие исклучоци да се фрлат и да се фатат таму каде што е потребно. Истите при фаќање треба да печатат пораки од следниот формат:

Invalid Date 32/12/2018
GBP is not a supported currency */
#include<iostream>
#include<cstring>
#include<cmath>
using namespace std;
class InvalidDateException
{
private:
    int den;
    int mesec;
    int godina;
public:
    InvalidDateException(int den,int mesec,int godina)
    {
        this->den=den;
        this->mesec=mesec;
        this->godina=godina;
    }
    void message()
    {
            cout<<"Invalid Date "<<den<<"/"<<mesec<<"/"<<godina<<endl;
    }

};
class NotSuportedCurrencyException
{
private:
    char valuta[4];
public:
    NotSuportedCurrencyException(const char *valuta)
    {
        strcpy(this->valuta,valuta);
    }
    void message()
    {

            cout<<valuta<<" is not a supported currency"<<endl;
    }
};
class Transakcija
{
protected:
    int den;
    int mesec;
    int godina;
    double iznos;
    static double EUR;
    static double USD;
public:
    Transakcija(int den=0,int mesec=0,int godina=0,double iznos=0)
    {
        if(den<1 || den > 31)
        {
            throw InvalidDateException(den,mesec,godina);
        }
        else if(mesec< 1 || mesec > 12)
        {
            throw InvalidDateException(den,mesec,godina);
        }
        this->den=den;
        this->mesec=mesec;
        this->godina=godina;
        this->iznos=iznos;
    }
    virtual double voDenari() = 0;
    virtual double voEvra() = 0;
    virtual double voDolari() = 0;
    virtual void pecati() = 0;

    static void setEUR(double number)
    {
        EUR=number;
    }
    static void setUSD(double number)
    {
        USD=number;
    }
    static  double getEUR()
    {
        return Transakcija::EUR;
    }
    static  double getUSD()
    {
        return Transakcija::USD;
    }
};
double Transakcija::EUR = 61;
double Transakcija::USD = 50;

class DenarskaTransakcija : public Transakcija
{
public:
    DenarskaTransakcija(int den=0,int mesec=0,int godina=0,double iznos=0) : Transakcija(den,mesec,godina,iznos)
    {

    }
    double voDenari()
    {
        return iznos;
    }
    double voEvra()
    {
        return iznos/Transakcija::EUR;
    }
    double voDolari()
    {
        return iznos/Transakcija::USD;
    }
    void pecati()
    {
        cout<<den<<"/"<<mesec<<"/"<<godina<<" "<<iznos<<" MKD"<<endl;
    }

};
class DeviznaTransakcija : public Transakcija
{
private:
    char valuta[4];
public:
    DeviznaTransakcija(int den=0,int mesec=0,int godina=0,double iznos=0,const char *valuta="") : Transakcija(den,mesec,godina,iznos)
    {
        if(strcmp(valuta,"EUR") != 0 && strcmp(valuta,"USD") != 0)
        {
            throw NotSuportedCurrencyException(valuta);
        }
        strcpy(this->valuta,valuta);
    }
    double voDenari()
    {
        if(strcmp(valuta,"EUR") == 0)
        {
            return 1.0*iznos*Transakcija::EUR;
        }
        else
        {
            return 1.0*iznos*Transakcija::USD;
        }
    }
    double voEvra()
    {
        if(strcmp(valuta,"EUR") == 0)
        {
            return 1.0*iznos;
        }
        else
        {
            return (iznos*Transakcija::USD*1.0)/Transakcija::EUR;
        }
    }
    double voDolari()
    {
        if(strcmp(valuta,"USD") == 0)
        {
            return 1.0*iznos;
        }
        else
        {
            return(1.0*iznos*Transakcija::EUR)/Transakcija::USD;
        }
    }
    void pecati()
    {
        cout<<den<<"/"<<mesec<<"/"<<godina<<" "<<iznos<<" "<<valuta<<endl;
    }

};
class Smetka
{
private:
    Transakcija **niza;
    int broj;
    char brojSmetka [15];
    double pocetnoSaldo;

    void copy(const Smetka &s)
    {
        broj=s.broj;
        niza=new Transakcija*[broj];
        for(int i=0;i<broj;i++)
        {
            niza[i]=s.niza[i];
        }
        strcpy(brojSmetka,s.brojSmetka);
        pocetnoSaldo=s.pocetnoSaldo;
    }
public:
    Smetka(const char *brojSmetka="",double pocetnoSaldo=0)
    {
        niza=new Transakcija*[0];
        broj=0;
        strcpy(this->brojSmetka,brojSmetka);
        this->pocetnoSaldo=pocetnoSaldo;
    }
    Smetka(const Smetka &s)
    {
        copy(s);
    }
    Smetka&operator=(const Smetka &s)
    {
        if(this!=&s)
        {
            delete[]niza;
            copy(s);
        }
        return *this;
    }
    ~Smetka()
    {
        delete[]niza;
    }
    Smetka&operator+=(Transakcija *t)
    {
        Transakcija **temp=new Transakcija*[broj+1];
        for(int i=0;i<broj;i++)
        {
            temp[i]=niza[i];
        }
        temp[broj++]=t;
        delete[]niza;
        niza=temp;
        return *this;
    }
    double vkupnoSaldo()
    {
        double vkupno=pocetnoSaldo;
        for(int i=0;i<broj;i++)
        {
            vkupno+=niza[i]->voDenari();
        }
        return vkupno;
    }
    void izvestajVoDenari()
    {
        double vkupno=vkupnoSaldo();
        cout<<"Korisnikot so smetka: "<<brojSmetka<<" ima momentalno saldo od "<<vkupno<<" MKD"<<endl;
    }
    void izvestajVoEvra()
    {
        double vkupno=vkupnoSaldo()/Transakcija::getEUR();
        cout<<"Korisnikot so smetka: "<<brojSmetka<<" ima momentalno saldo od "<<vkupno<<" EUR"<<endl;
    }
    void izvestajVoDolari()
    {
        double vkupno=vkupnoSaldo()/Transakcija::getUSD();
        cout<<"Korisnikot so smetka: "<<brojSmetka<<" ima momentalno saldo od "<<vkupno<<" USD"<<endl;
    }
    void pecatiTransakcii()
    {
        for(int i=0;i<broj;i++)
        {
            niza[i]->pecati();
        }
    }
};
int main ()
{
	Smetka s ("300047024112789",1500);

	int n, den, mesec, godina, tip;
	double iznos;
	char valuta [3];

	cin>>n;
    cout<<"===VNESUVANJE NA TRANSAKCIITE I SPRAVUVANJE SO ISKLUCOCI==="<<endl;
	for (int i=0;i<n;i++){
                cin>>tip>>den>>mesec>>godina>>iznos;
		if (tip==2){
			cin>>valuta;
			try{
			Transakcija * t = new DeviznaTransakcija(den,mesec,godina,iznos,valuta);
			s+=t;
			}
			catch(NotSuportedCurrencyException &n)
			{
			    n.message();
			}
			catch(InvalidDateException &d)
			{
			    d.message();
			}
            //delete t;
		}
		else {
                try{
                    Transakcija * t = new DenarskaTransakcija(den,mesec,godina,iznos);
                    s+=t;
                }
                catch(InvalidDateException &d)
                {
                    d.message();
                }
            //delete t;
		}

	}
    cout<<"===PECHATENJE NA SITE TRANSAKCII==="<<endl;
    s.pecatiTransakcii();
    cout<<"===IZVESHTAJ ZA SOSTOJBATA NA SMETKATA VO DENARI==="<<endl;
    s.izvestajVoDenari();
    cout<<"===IZVESHTAJ ZA SOSTOJBATA NA SMETKATA VO EVRA==="<<endl;
    s.izvestajVoEvra();
    cout<<"===IZVESHTAJ ZA SOSTOJBATA NA SMETKATA VO DOLARI==="<<endl;
    s.izvestajVoDolari();

    cout<<"\n===PROMENA NA KURSOT NA EVROTO I DOLAROT===\n"<<endl;


    double newEUR, newUSD;
    cin>>newEUR>>newUSD;
    Transakcija::setEUR(newEUR);
    Transakcija::setUSD(newUSD);
    cout<<"===IZVESHTAJ ZA SOSTOJBATA NA SMETKATA VO DENARI==="<<endl;
    s.izvestajVoDenari();
    cout<<"===IZVESHTAJ ZA SOSTOJBATA NA SMETKATA VO EVRA==="<<endl;
    s.izvestajVoEvra();
    cout<<"===IZVESHTAJ ZA SOSTOJBATA NA SMETKATA VO DOLARI==="<<endl;
    s.izvestajVoDolari();



	return 0;
}

