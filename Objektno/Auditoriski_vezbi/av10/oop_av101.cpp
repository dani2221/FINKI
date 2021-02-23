#include<iostream>
#include<cstring>
using namespace std;

class Kartichka {
private:
    char id[16];
    double saldo;

public:
    Kartichka(char* id = "", double  saldo = 0) {
        this->saldo = saldo;
        strcpy(this->id, id);
    }
    void prikaziSaldo() {
        cout << id << ": " << saldo << endl;
    }

    void uplati(double  suma) {
        this->saldo += suma;
    }

    virtual double isplati(double suma) {
        if (this->saldo > suma) {
            this->saldo -= suma;
            return suma;
        }
        else return 0;
    }

protected:
    virtual double isplati(double suma, double limit) {
        if (this->saldo + limit > suma) {
            this->saldo -= suma;
            return suma;
        }
        else return 0;
    }
};

class Maestro : public Kartichka {
private:
    char lozinka[10];
    const static double popust; //static clen na klasa

public:
    Maestro(char* lozinka = "", char* id = "", double  saldo = 0) : Kartichka(id, saldo) {
        strcpy(this->lozinka, lozinka);
    }

    static double  getPopust() { //static funkcija koja raboti so static clen
        return popust;
    }

    double isplati(double  cena) {
        double  suma = cena * (1 - popust); // non-static funkcii moze da gi koristat
        // static podatocnite elementi
        return Kartichka::isplati(suma);
    }
};
const double Maestro::popust = 0.05; // inicijaliziranje na static clen

class Master : public Kartichka {
private:
    double  limit;
    const static double popust1;  // fiksen popust
    static double popust2;       // popust koj sto moze da se promeni

public:
    Master(double  limit = 0, char* id = "", double  saldo = 0) : Kartichka(id, saldo) {
        this->limit = limit;
    }

    static double getPopust1() {
        return popust1;
    }

    static double getPopust2() {
        return popust2;
    }

    static void setPopust2(double  popust2) {
        Master::popust2 = popust2;
    }
    /* ne smeeme da go napravime ova:
    static void setPopust1 (double  popust1) {
        Master::popust1 = popust1;
    }
    //poradi toa sto stanuva zbor za konstanta
    */
    double isplati(double  cena) {
        if (this->limit < 6000) {
            double  suma = cena * (1 - popust1);
            return Kartichka::isplati(suma, limit);
        }
        else {
            double  suma = cena * (1 - popust2);
            return Kartichka::isplati(suma, limit);
        }
    }
};
const double Master::popust1 = 0.03; //pri inicijalizacija ne se pisuva klucniot zbor static
double Master::popust2 = 0.1;
