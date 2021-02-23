#include<iostream>
#include<cstring>
using namespace std;

class Datum {
private:
    int den, mesec, godina;
public:
    Datum(int den = 0, int mesec = 0, int godina = 0) {
        this->godina = godina;
        this->mesec = mesec;
        this->den = den;
    }
    Datum(const Datum &d) {
        godina = d.godina;
        mesec = d.mesec;
        den = d.den;
    }
    ~Datum() {}

    int getDen() { return den; }
    int getMesec() { return mesec; }
    int getGodina() { return godina; }
};

class Vraboten {
private:
    char ime[20];
    int plata;
    Datum dataRagjanje;
public:
    Vraboten() {}
    Vraboten(char *ime, int plata, const Datum &data) {
        strcpy(this->ime, ime);
        this->plata = plata;
        dataRagjanje = data;
    }
    ~Vraboten() {}

    int getPlata() {
        return plata;
    }
    Datum getDataRagjanje() {
        return dataRagjanje;
    }
    void print() {
        cout << "Ime: " << ime << endl;
        cout << "Plata: " << plata << endl;
        cout << "Datum na ragjanje: " << dataRagjanje.getDen() << "." << dataRagjanje.getMesec() << "." << dataRagjanje.getGodina() << endl;
    }
};
//go vrakja vraboteniot so najgolema plata od nizata v
Vraboten najgolemPlata(Vraboten v[], int n) {
    int max = v[0].getPlata();
    int ind = 0;
    for (int i = 1; i < n; i++) {
        if (v[i].getPlata() > max) {
            max = v[i].getPlata();
            ind = i;
        }
    }
    return v[ind];
}

//0 - isti se, 1 - datumot d1 e po datumot d2, 2 datumot d2 e po datumot d1
int sporedba(Datum d1, Datum d2) {
    if (d1.getGodina() > d2.getGodina()) return 1;
    else if (d1.getGodina() < d2.getGodina()) return 2;
    else if (d1.getMesec() > d2.getMesec()) return 1;
    else if (d1.getMesec() < d2.getMesec()) return 2;
    else if (d1.getDen() > d2.getDen()) return 1;
    else if (d1.getDen() < d2.getDen()) return 2;
    else return 0;
}
//go vrakja najmladiot vraboten od nizata v
Vraboten najmlad(Vraboten v[], int n) {
    Datum data(v[0].getDataRagjanje());
    int ind = 0;
    for (int i = 1; i < n; i++) {
        if (sporedba(v[i].getDataRagjanje(), data) == 1) {
            data = v[i].getDataRagjanje();
            ind = i;
        }
    }
    return v[ind];
}
int main() {
    Datum d1(1, 1, 1980);
    Datum d2(1, 2, 1983);
    Datum d3(11, 12, 1984);

    Vraboten v[3];
    Vraboten v1("Marjan", 40000, d1);
    Vraboten v2("Stefan", 30000, d2);
    Vraboten v3("Marko", 20000, d3);
    v[0] = v1;   v[1] = v2;   v[2] = v3;

    cout << "Najmlad vraboten: " << endl;
    najmlad(v, 3).print();

    cout << "Vraboten so najgolema plata: " << endl;
    najgolemPlata(v, 3).print();

    return 0;
}
