/* Во една игротека има 2 типа играчки: топки и коцки. Коцките и топките се опишани со параметри како што се:

боја (char *)
густина (int).
Дополнително за топките се знае и радиусот (int), додека за коцките целосните димензии (висина, ширина и длабочина – int).

За секоја од класите треба да се дефинираат методи getMasa() и getVolumen(). Масата на играчката се пресметува како производ од волуменот и густината на играчката. За PI користете ја вредноста 3.14.

Во функцијата main да се декларира променлива kupche што претставува динамички алоцирана низа кон Igrachka. Во зависност од првиот влезен параметар се внесуваат објекти од класите Topka или Kocka (1 - се внесува топка, 2 - се внесува коцка).

Од тастатура да се внесат податоци за коцката на Петра Kocka petra. Во главната функција во да се отпечатат:

Да се отпечати DA ако вкупната маса на сите играчки е поголема од масата на играчката на Петра, а NE во спротивно.
Разликата по апсолутна вредност на волуменот на играчката со максимален волумен во купчето и волуменот на коцката на Петра. Форматот е:

Razlikata e: {razlika}
Задачата да се реши со тоа што класите Kocka и Topka ќе наследуваат од класите Forma и Igrachka.

Дополнителни барања:

Во класата Igrachka да се додаде уште една чисто виртуелна функција float getPlostina(). Истата да се имплементира во класите Kocka и Topka
Во главната функција, дополнително да се испечати и: Разликата по апсолутна вредност на плоштината на играчката со минимална плоштина во купчето и плоштината на коцката на Петра во истиот формат како и второто барање погоре. */
#include<iostream>
#include<cstring>
#include<cmath>
using namespace std;
class Igracka
{
public:
    virtual float getVolumen() = 0;
    virtual float getMasa() = 0;
    virtual float getPlostina() = 0;
};
class Forma
{
protected:
    char boja[100];
    int gustina;
public:
    Forma(const char *boja, int gustina)
    {
        strcpy(this->boja,boja);
        this->gustina=gustina;
    }
};
class Kocka : public Igracka, public Forma
{
private:
    int visina;
    int sirina;
    int dlabocina;
public:
    Kocka(const char *boja="", int gustina=0, int visina=0, int sirina=0, int dlabocina=0) : Forma(boja,gustina)
    {
        this->visina=visina;
        this->sirina=sirina;
        this->dlabocina=dlabocina;
    }
    float getVolumen()
    {
        return visina*sirina*dlabocina;
    }
    float getMasa()
    {
        return gustina*getVolumen();
    }
    float getPlostina()
    {
        return (visina*sirina + visina*dlabocina + dlabocina*sirina)*2;
    }

};
class Topka : public Igracka, public Forma
{
protected:
    int radius;
public:
    Topka(const char *boja="", int gustina=0, int radius=0) : Forma(boja,gustina)
    {
        this->radius=radius;
    }
    float getVolumen()
    {
        return (4/3.0)*(3.14)*radius*radius*radius;
    }
    float getMasa()
    {
        return gustina*getVolumen();
    }
    float getPlostina()
    {
        return radius*radius*3.14*4;
    }
};
int main ()
{
    int sirina,dlabocina,visina,radius,gustina;
    char boja[100];
    int n;
    cin>>n;
    Igracka **kupche = new Igracka*[n];
    int testCase;
    int vkupnaMasa=0;
    for(int i=0;i<n;i++)
    {
        cin>>testCase;
        if(testCase == 1)
        {
            cin>>boja>>gustina>>radius;
            kupche[i]=new Topka(boja,gustina,radius);
            vkupnaMasa+=kupche[i]->getMasa();
        }
        else if(testCase == 2)
        {
            cin>>boja>>gustina>>visina>>sirina>>dlabocina;
            kupche[i]= new Kocka(boja,gustina,visina,sirina,dlabocina);
            vkupnaMasa+=kupche[i]->getMasa();
        }
    }
    cin>>boja>>gustina>>visina>>sirina>>dlabocina;
    Kocka petra(boja,gustina,visina,sirina,dlabocina);
    if(vkupnaMasa > petra.getMasa())
    {
        cout<<"DA"<<endl;
    }
    else
    {
        cout<<"NE"<<endl;
    }
    float maxVolumen=kupche[0]->getVolumen();
    float minPlostina=kupche[0]->getPlostina();
    float volumenPetra=petra.getVolumen();
    float plostinaPetra=petra.getPlostina();
    for(int i=1;i<n;i++)
    {
        if(maxVolumen < kupche[i]->getVolumen())
        {
            maxVolumen=kupche[i]->getVolumen();
        }
        if(minPlostina > kupche[i]->getPlostina())
        {
            minPlostina=kupche[i]->getPlostina();
        }
    }
    cout<<"Razlikata e: "<<fabs(maxVolumen-volumenPetra)<<endl;
    cout<<"Razlikata e: "<<fabs(minPlostina-plostinaPetra);
}
