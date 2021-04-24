/* Да се дефинира класа Ekipa за коjа се чуваат следниве информации:

името на екипата (низа од наjмногу 15 знаци)
броj на порази
броj на победи
За оваа класа да се дефинира метод pecati() коjа ги печати податоците за екипаta. Од оваа класа да се изведe новa класa, FudbalskaEkipa.

За фудбалската екипа дополнително се чуваат информации за:

вкупниот броj на црвени картони
вкупниот броj жолти картони
броjот на нерешени натпревари
За фудбалската екипа да се преоптовари методот pecati(), така што покрај останатите информации, ќе се испечатат и бројот на нерешени резултати и вкупен број на поени во формат: Име на екипа, броj на победи, броj на порази, броj на нерешени натпревари и вкупен броj на поени (за победа фудбалската екипа добива 3 поени, додека за нерешен резултата, 1 поен); */
#include<iostream>
#include<cstring>
using namespace std;
class Ekipa
{
protected:
    char ime[15];
    int brojNaPorazi;
    int brojNaPobedi;
public:
    Ekipa(const char* ime="",int brojNaPobedi=0,int brojNaPorazi=0)
    {
        strcpy(this->ime,ime);
        this->brojNaPobedi=brojNaPobedi;
        this->brojNaPorazi=brojNaPorazi;
    }
    void pecati()
    {
        cout<<"Ime: "<<ime<<" Pobedi: "<<brojNaPobedi<<" Porazi: "<<brojNaPorazi;
    }
};
class FudbalskaEkipa : public Ekipa
{
private:
    int crven;
    int zut;
    int nereseni;
public:
    FudbalskaEkipa(char *ime, int brojNaPobedi, int brojNaPorazi,int crven, int zut, int nereseni) : Ekipa(ime,brojNaPobedi,brojNaPorazi)
    {
        this->crven=crven;
        this->zut=zut;
        this->nereseni=nereseni;
    }
    int vkupnoPoeni()
    {
        return((brojNaPobedi*3) + nereseni);
    }
    void pecati()
    {
        Ekipa::pecati();
        cout<<" Nereseni: "<<nereseni<<" Poeni: "<<vkupnoPoeni()<<endl;
    }
};
int main ()
{
	char ime[15];
	int pob,por,ck,zk,ner;
	cin>>ime>>pob>>por>>ck>>zk>>ner;
	FudbalskaEkipa f1(ime,pob,por,ck,zk,ner);
	f1.pecati();
	return 0;
}
