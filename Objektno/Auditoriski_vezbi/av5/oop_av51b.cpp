#include<iostream>
#include<cstring>
using namespace std;

class Ekipa {
private:
    char ime[20];
    char grad[20];
    char stadion[30];
public:
    Ekipa(char *ime = "", char *grad = "", char *stadion = "") {
        strcpy(this->ime, ime);
        strcpy(this->grad, grad);
        strcpy(this->stadion, stadion);
    }
    Ekipa(const Ekipa &e) {
        strcpy(ime, e.ime);
        strcpy(grad, e.grad);
        strcpy(stadion, e.stadion);
    }
    //get funkciite kako konstantni funkcii
    //vo niv ne se menuvaat podatocite od klasata
    const char *getIme() const {
        return ime;
    }
    const char *getGrad() const {
        return grad;
    }
    const char *getStadion() const {
        return stadion;
    }
    void setIme(char *ime) {
        strcpy(this->ime, ime);
    }
    ~Ekipa() {}
};

class Natprevar {
private:
    Ekipa *domakin, *gostin;
    int goloviDomakin, goloviGostin;

public:
    Natprevar(const Ekipa &d, const Ekipa &g, int gDom, int gGost) {
        domakin = new Ekipa(d);
        gostin = new Ekipa(g);
        goloviDomakin = gDom;
        goloviGostin = gGost;
    }
    Natprevar(const Natprevar& n) {
        domakin = new Ekipa(*n.domakin);
        gostin = new Ekipa(*n.gostin);

        goloviDomakin = n.goloviDomakin;
        goloviGostin = n.goloviGostin;
    }
    //vrakja pokazuvac kon prvata ekipag
    Ekipa* getDomakin() {
        return domakin;
    }
    //vrakja konstanten pokazuvac
    const Ekipa* getGostin() {
        return gostin;
    }
    int getGoloviDomakin() {
        return goloviDomakin;
    }
    int getGoloviGostin() {
        return goloviGostin;
    }
    ~Natprevar() {
        cout << "\nVo destruktor" << endl;
        delete domakin;
        delete gostin;
    }
    //friend funkcija
    friend bool isTip(Natprevar n, char tip);
};

bool  isTip(Natprevar n, char tip) {
    if (n.goloviDomakin == n.goloviGostin && tip == 'X') return true;
    else if (n.goloviDomakin > n.goloviGostin && tip == '1') return true;
    else if (n.goloviDomakin < n.goloviGostin && tip == '2') return true;
    else return false;
}

int main() {

    Ekipa e1("Real Madrid", "Madrid", "Santiago Bernabeu");
    Ekipa e2("FC Barcelona", "Barcelona", "Camp Nou");

    Natprevar first(e1, e2, 1, 3);

    cout << "Vnesi tip za natprevarot: ";
    cout << first.getDomakin()->getIme(); //getIme - const funkcija
    cout << "-";
    cout << first.getGostin()->getIme();
    cout << endl;


    char tip; //1, 2 ili X
    cin >> tip;

    if (isTip(first, tip)) cout << "Tipot e pogoden";
    else cout << "Tipot ne e pogoden";

    first.getDomakin()->setIme("RLM"); //mozno e
    //first.getGostin().setIme("BAR"); //ne e mozno:getGostin vrakja konstanten pokazuvac

    cout << "\nNatprevarot beshe megju: ";
    cout << first.getDomakin()->getIme();
    cout << "-";
    cout << first.getGostin()->getIme();

    return 0;
}
