#include<iostream>
#include<cstring>
#include<cmath>
using namespace std;

class Momche {
private:
    int godini;
    char ime[20];
    char prezime[20];
public:
    Momche(int godini = 0, char *ime = "", char *prezime = "") {
        this->godini = godini;
        strcpy(this->ime, ime);
        strcpy(this->prezime, prezime);
    }
    Momche(const Momche &m) {
        godini = m.godini;
        strcpy(ime, m.ime);
        strcpy(prezime, m.prezime);
    }
    ~Momche() {}
    int getGodini() {
        return godini;
    }
    void print() {
        cout << "Momche: " << ime << " " << prezime << " " << godini;
    }
};

class Devojche {
private:
    int godini;
    char ime[20];
    char prezime[20];
public:
    Devojche(int godini = 0, char *ime = "", char *prezime = "") {
        this->godini = godini;
        strcpy(this->ime, ime);
        strcpy(this->prezime, prezime);
    }
    Devojche(const Devojche &d) {
        godini = d.godini;
        strcpy(ime, d.ime);
        strcpy(prezime, d.prezime);
    }
    ~Devojche() {}
    int getGodini() {
        return godini;
    }
    void print() {
        cout << "Devojche: " << ime << " " << prezime << " " << godini;
    }
};

class Sredba {

private:
    Momche momche;
    Devojche devojche;
public:
    Sredba(const Momche m, const Devojche d) {
        momche = m;
        devojche = d;
    }
    ~Sredba() {}

    void print() {
        cout << "Sredba: ";
        momche.print();
        devojche.print();
    }
    void daliSiOdgovaraat() {
        if (abs(momche.getGodini() - devojche.getGodini()) < 5)
            cout << "Si odgovaraat" << endl;
        else
            cout << "Ne si odgovaraat" << endl;

    }
};

int main() {
    int godini;
    char ime[20], prezime[20];

    cout << "Informacii za momche: " << endl;
    cout << "Ime: ";
    cin >> ime;
    cout << "Prezime: ";
    cin >> prezime;
    cout << "Godini: ";
    cin >> godini;
    Momche m(godini, ime, prezime);
    Momche momche(m); //eksplicitno povikuvanje na copy konstruktor za momche

    cout << "Informacii za Devojche: " << endl;
    cout << "Ime: ";
    cin >> ime;
    cout << "Prezime: ";
    cin >> prezime;
    cout << "Godini: ";
    cin >> godini;
    Devojche d(godini, ime, prezime);
    Devojche devojche = d; //eksplicitno povikuvanje na copy konstruktor za devojche

    Sredba s(momche, devojche);//implicitno povikuvanje na copy konstruktor za momche i devojche
    s.print();
    s.daliSiOdgovaraat();
    return 0;
}
