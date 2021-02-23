#include<iostream>
#include<cstring>
using namespace std;

class Kartichka
{
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

    void uplati(double suma) {
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
        } else {
            double  suma = cena * (1 - popust2);
            return Kartichka::isplati(suma, limit);
        }
    }
};
const double Master::popust1 = 0.03; //pri inicijalizacija ne se pisuva klucniot zbor static
double Master::popust2 = 0.1;

class Kasa {
private:
    double  sumaVoKasa;
    double  sumaOdKartichka;
    int den, mesec, godina;

public:

    Kasa(double  sumaVoKasa, int den, int mesec, int godina) {
        this->sumaVoKasa = sumaVoKasa;
        this->sumaOdKartichka = 0;
        this->den = den;
        this->mesec = mesec;
        this->godina = godina;
    }

    void kasaPrimi(double smetka) {
        this->sumaVoKasa += smetka;
    }

    void kasaPrimi(double smetka, Kartichka &k) {
        this->sumaOdKartichka += k.isplati(smetka);
    }

    void prikaziKasa() {
        cout << "Den: \t" << den << endl;
        cout << "Mesec: \t" << mesec << endl;
        cout << "Godina: " << godina << endl;
        cout << "Prihod-vkupno: " << this->vratiPrihod() << endl;
        cout << endl;
    }

    double  vratiPrihod() {
        return this->sumaOdKartichka + this->sumaVoKasa;
    }
};

int main() {
    Kasa deneshna(10000, 22, 4, 2014);
    Kartichka *k;
    deneshna.prikaziKasa();

    cout << "Primam vo gotovo!" << endl;
    deneshna.kasaPrimi(5000);
    deneshna.prikaziKasa();

    k = new Master(10000.00, "1234567890123456", 54000.00);
    cout << "Primam so kartichka!" << endl;
    deneshna.kasaPrimi(10000.00, *k);
    deneshna.prikaziKasa();

    k = new Maestro("lozinka", "1234567890123456", 54000.00);
    cout << "Primam so kartichka!" << endl;
    deneshna.kasaPrimi(10000, *k);
    deneshna.prikaziKasa();

    Master::setPopust2(0.07);
    k = new Master(10000, "4567891234567890", 3000);
    cout << "Primam so kartichka!" << endl;
    deneshna.kasaPrimi(10000, *k);
    deneshna.prikaziKasa();
    return 0;
}

