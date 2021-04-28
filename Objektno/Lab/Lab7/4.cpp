/* Да се дефинира класа Vozilo која ќе содржи информација за неговата маса (децимален број), ширина и висина (цели броеви).

Од оваа класа да се изведе класата Автомобил во која како дополнителна информација се чува информацијата за бројот на врати (цел број).

Од класата возило да се изведе и класата Автобус во која се чуваат информации и за бројот на патници кои може да ги пренесува.

Од класата возило да се изведе класата Камион во која се чуваат информации и за максималната маса која може да се товари на него (децимална вредност).

За сите класи да се креираат погодни контруктори, како и set и get функции.

Да се дефинира класа ParkingPlac за која се чува динамичко алоцирано поле од покажувачи кон Vozilo, како и бројот на елементи во полето. Во оваа класа да се дефинираат:

конструктор
деструктор
операторот += за додавање на ново возило (аргументот е покажувач кон Vozilo)
функција float presmetajVkupnaMasa() со која се пресметува вкупната маса на сите возила во паркинг плацот
функција int brojVozilaPoshirokiOd(int l) со која се пресметува бројот на возила кои се пошироки од дадената вредност
функција void pecati() со која се печати: Brojot na avtomobili e X, brojot na avtobusi e Y i brojot na kamioni e Z.
функција int pogolemaNosivostOd(Vozilo& v) во која се враќа бројот на сите камиони кои имаат носивост поголема од масата на возилото предадено како аргумент.
Да се дефинира виртуелна функција int vratiDnevnaCena() во класата Vozilo и истата да се преоптовари во сите изведени класи. За секој автомобил со помалку од 5 врати дневната цена е 100, а инаку е 130 денари. За секој камион цената се пресметува со формулата: (masa+nosivost)*0.02. За секој автобус цената е 5 денари по лице кое може да се пренесува.

Во класата ParkingPlac да се додаде следната функција: - функција int vratiDnevnaZarabotka() со која се враќа дневната заработка од сите возила на паркингот.
*/
#include<iostream>
#include<cmath>
#include<cstring>
using namespace std;
class Vozilo
{
protected:
    double masa;
    int sirina;
    int visina;
public:
    Vozilo(double masa=0,int sirina=0,int visina=0)
    {
        this->masa=masa;
        this->sirina=sirina;
        this->visina=visina;
    }
    virtual int vratiDnevnaCena() = 0;
    double getMasa()
    {
        return masa;
    }
    int getSirina()
    {
        return sirina;
    }
    int getVisina()
    {
        return visina;
    }
};
class Avtomobil : public Vozilo
{
private:
    int brojNaVrati;
public:
    Avtomobil(double masa=0,int sirina=0,int visina=0,int brojNaVrati=0) : Vozilo(masa,sirina,visina)
    {
        this->brojNaVrati=brojNaVrati;
    }
    int vratiDnevnaCena()
    {
        if(brojNaVrati<5)
            return 100;
        return 130;
    }
};
class Avtobus : public Vozilo
{
private:
    int brojNaPatnici;
public:
    Avtobus(double masa=0,int sirina=0,int visina=0,int brojNaPatnici=0) : Vozilo(masa,sirina,visina)
    {
        this->brojNaPatnici=brojNaPatnici;
    }
    int vratiDnevnaCena()
    {
        return brojNaPatnici*5;
    }
};
class Kamion : public Vozilo
{
private:
    double maxMasa;
public:
    Kamion(double masa=0,int sirina=0,int visina=0,double maxMasa=0) : Vozilo(masa,sirina,visina)
    {
        this->maxMasa=maxMasa;
    }
    int vratiDnevnaCena()
    {
        return (masa+maxMasa)*0.02;
    }
    double getMaxMasa()
    {
        return maxMasa;
    }
};
class ParkingPlac
{
private:
    Vozilo **niza;
    int broj;

    void copy(const ParkingPlac &p)
    {
        broj=p.broj;
        niza=new Vozilo*[broj];
        for(int i=0;i<broj;i++)
        {
            niza[i]=p.niza[i];
        }
    }
public:
    ParkingPlac()
    {
        niza=new Vozilo*[0];
        broj=0;
    }
    ~ParkingPlac()
    {
        delete[]niza;
    }
    ParkingPlac(const ParkingPlac &p)
    {
        copy(p);
    }
    ParkingPlac&operator=(const ParkingPlac &p)
    {
        if(this!=&p)
        {
            delete[]niza;
            copy(p);
        }
        return *this;
    }
    ParkingPlac&operator+=(Vozilo *v)
    {
        Vozilo **temp=new Vozilo*[broj+1];
        for(int i=0;i<broj;i++)
        {
            temp[i]=niza[i];
        }
        temp[broj++]=v;
        delete[]niza;
        niza=temp;
        return *this;
    }
    double presmetajVkupnaMasa()
    {
        double vkupno=0;
        for(int i=0;i<broj;i++)
        {
            vkupno+=niza[i]->getMasa();
        }
        return vkupno;
    }
    int brojVozilaPoshirokiOd(int n)
    {
        int vkupno=0;
        for(int i=0;i<broj;i++)
        {
            if(niza[i]->getSirina() > n)
                vkupno++;
        }
        return vkupno;
    }
    void pecati()
    {
        int brojAvtomobili=0,brojKamioni=0,brojAvtobus=0;
        for(int i=0;i<broj;i++)
        {
            Avtomobil *a=dynamic_cast<Avtomobil*>(niza[i]);
            if(a!=0)
            {
                brojAvtomobili++;
            }
            Avtobus *as=dynamic_cast<Avtobus*>(niza[i]);
            if(as!=0)
            {
                brojAvtobus++;
            }
            Kamion *k=dynamic_cast<Kamion*>(niza[i]);
            if(k!=0)
            {
                brojKamioni++;
            }
        }
        cout<<"Brojot na avtomobili e "<<brojAvtomobili<<", brojot na avtobusi e "<<brojAvtobus<<" i brojot na kamioni e "<<brojKamioni<<"."<<endl;
    }
    int pogolemaNosivostOd(Vozilo &v)
    {
        int count=0;
        for(int i=0;i<broj;i++)
        {
            Kamion *k=dynamic_cast<Kamion*>(niza[i]);
            if(k != 0)
            {
                if(k->getMaxMasa() > v.getMasa())
                    count++;
            }
        }
        return count;
    }
    int vratiDnevnaZarabotka()
    {
        int vkupno=0;
        for(int i=0;i<broj;i++)
        {
            vkupno+=niza[i]->vratiDnevnaCena();
        }
        return vkupno;
    }
};
int main ()
{
ParkingPlac p;

int n;
cin>>n;
int shirina,visina, broj;
float masa,nosivost;
for (int i=0;i<n;i++){
    int type;
    cin>>type;
    if(type==1){
        cin>>masa>>shirina>>visina>>broj;
        Avtomobil *a=new Avtomobil(masa,shirina,visina,broj);
        p+=a;
    }
    if(type==2){
        cin>>masa>>shirina>>visina>>broj;
        p+=new Avtobus(masa,shirina,visina,broj);
    }
    if(type==3){
        cin>>masa>>shirina>>visina>>nosivost;
        p+=new Kamion(masa,shirina,visina,nosivost);
    }
}

p.pecati();

cout<<"\nZarabotkata e "<<p.vratiDnevnaZarabotka()<<endl;
cout<<"Vkupnata masa e "<<p.presmetajVkupnaMasa()<<endl;
cout<<"Brojot poshiroki od 5 e "<<p.brojVozilaPoshirokiOd(5)<<endl;
Avtomobil a(1200,4,3,5);
cout<<"Brojot na kamioni so nosivost pogolema od avtomobilot e "<<p.pogolemaNosivostOd(a)<<endl;
}
