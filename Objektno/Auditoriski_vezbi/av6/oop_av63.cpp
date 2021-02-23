#include <iostream>
#include <string.h>
#define MAX 100
using namespace std;
class Ucenik
{
private:
    char *ime;
    float prosek;
    int godina;
public:
    Ucenik (const char* ii = "", float pp = 0, int gg = 0)
    {
        ime = new char[strlen(ii) + 1];
        strcpy (ime, ii);
        prosek = pp;
        godina = gg;
    }
    Ucenik (const Ucenik& u)
    {
        ime = new char[strlen(u.ime) ];
        strcpy (ime , u.ime);
        prosek = u.prosek;
        godina = u.godina;
    }
    ~Ucenik() {
        delete [] ime;
    }

    Ucenik& operator= (const Ucenik& u)
    {
        if (this != &u)
        {
            delete  []  ime;
            ime = new char[strlen(u.ime)] ;
            strcpy (ime, u.ime) ;
            prosek = u.prosek ;
            godina = u.godina ;
        }
        return *this ;
    }
    Ucenik& operator++() { //prefiksen operator
        godina++ ;
        return *this ;
    }
    Ucenik operator++(int) { //postfiksen operator
        Ucenik u(*this) ;
        godina++ ;
        return u;
    }
    float getProsek() {
        return prosek;
    }
    // globalna funkcija za preoptovaruvanje na operatorot <<
    // ovaa funkcija e prijatelska na klasata Ucenik
    friend ostream& operator<< (ostream& o, const Ucenik& u)
    {
        return o << "Ime:" << u.ime << ", godina:" << u.godina << ",prosek:" << u.prosek << endl;
    }
    friend bool operator> (const Ucenik& u1, const Ucenik& u2);
};

//globalna funkcija za preoptovaruvanje na operatorot >
bool operator> (const Ucenik& u1, const Ucenik& u2)
{
    return (u1.prosek > u2.prosek);
}

class Paralelka
{
private:
    Ucenik* spisok;
    int vkupno;
public:
    Paralelka (Ucenik* s = 0, int v = 0)
    {
        vkupno = v;
        spisok = new Ucenik [vkupno];
        for (int i = 0; i < vkupno ; i ++)
            spisok[i] = s[i];
    }

    Paralelka (const Paralelka &p)
    {
        this -> vkupno = p.vkupno;
        this -> spisok = new Ucenik[vkupno];
        for (int i = 0; i < vkupno; i ++)
            spisok[i] = p.spisok[i];
    }

    ~Paralelka() {
        delete [] spisok;
    }
    Paralelka& operator+= (Ucenik u) {
        Ucenik* tmp = new Ucenik[vkupno + 1];
        for (int i = 0; i < vkupno; i++)
            tmp[i] = spisok[i];
        tmp [vkupno ++] = u;
        delete [] spisok;
        spisok = tmp;
        return * this ;
    }

    Paralelka& operator++() {
        for (int i = 0; i < vkupno; i++)
            spisok[i]++;
        return *this;
    }
    Paralelka operator++(int) {
        Paralelka p(*this);
        for (int i = 0; i < vkupno; i++)
            spisok[i]++;
        return p;
    }

    friend ostream& operator<< (ostream& o, const Paralelka& p)
    {
        for (int i = 0; i < p.vkupno; i ++)
            o << p.spisok[i];
        return o;
    }

    void nagradi()
    {
        for (int i = 0; i < vkupno; i++)
            if (spisok[i].getProsek() == 10.0)
                cout << spisok[i];
    }

    void najvisokProsek()
    {
        Ucenik tmpU = spisok[0];
        for (int i = 0; i < vkupno; i++)
            if ( spisok[i] > tmpU)
                tmpU = spisok[i];
        cout << "Najvisok prosek vo paralelkata:" << tmpU.getProsek() << endl;
    }
};

int main ()
{
    Ucenik u1("Martina Martinovska", 9.5, 3);
    Ucenik u2("Darko Darkoski", 7.3, 2);
    Ucenik u3("Angela Angelovska", 10, 3);

    Paralelka p;
    p += u1;
    p += u2;
    p += u3;

    cout << p;
    cout << "Nagradeni:" << endl;
    p.nagradi();
    cout << endl;
    p.najvisokProsek();
    cout << endl;

    u2++;
    cout << p;
    cout << endl;
    p++;
    cout << p;

    return 0;
}

