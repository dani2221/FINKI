#include<iostream>
#include<cstring>
using namespace std;

class Ekipa {
private:
    int godina;
    char ime[20];
    char grad[20];
public:
    Ekipa(int godina = 0, char *ime = "", char *grad = "") {
        this->godina = godina;
        strcpy(this->ime, ime);
        strcpy(this->grad, grad);
    }
    Ekipa(const Ekipa &e) {
        godina = e.godina;
        strcpy(ime, e.ime);
        strcpy(grad, e.grad);
    }
    const char *getIme() {
        return ime;
    }
    ~Ekipa() {}
};

class Natprevar {
private:
    Ekipa domakin, gostin;
    int goloviDomakin, goloviGostin;
public:
    Natprevar(const Ekipa &d, const Ekipa &g, int gDom, int gGost) {
        domakin = d;
        gostin = g;
        goloviDomakin = gDom;
        goloviGostin = gGost;
    }
    Ekipa getDomakin() {
        return domakin;
    }
    Ekipa getGostin() {
        return gostin;
    }
    int getGoloviDomakin() {
        return goloviDomakin;
    }
    int getGoloviGostin() {
        return goloviGostin;
    }
    ~Natprevar() {}
};

bool revans(Natprevar n1, Natprevar n2) {
    if (!(strcmp(n1.getDomakin().getIme(), n2.getGostin().getIme()) == 0 && strcmp(n1.getGostin().getIme(), n2.getDomakin().getIme()) == 0)) {
        return false;
    }
    return true;
}

Ekipa duel(Natprevar n1, Natprevar n2) {
    if (revans(n1, n2)) {
        int eGolovi1 = n1.getGoloviDomakin() + n2.getGoloviGostin();
        int eGolovi2 = n2.getGoloviDomakin() + n1.getGoloviGostin();
        if (eGolovi1 > eGolovi2) return n1.getDomakin();
        else if (eGolovi1 < eGolovi2) return n1.getGostin();
        else if (n1.getGoloviGostin() > n2.getGoloviGostin()) return n1.getGostin();
        else if (n1.getGoloviGostin() < n2.getGoloviGostin()) return n1.getDomakin();
        else return 0;
    }
    else {
        cout << "Ne se sofpagjaat." << endl;
        return 0;
    }
}

int main() {

    Ekipa e1(1880, "Real Madrid", "Madrid");
    Ekipa e2(1880, "FC Barcelona", "Barcelona");

    Natprevar n1(e1, e2, 1, 2);
    Natprevar n2(e2, e1, 1, 0);

    cout << duel(n1, n2).getIme();

    return 0;
}
