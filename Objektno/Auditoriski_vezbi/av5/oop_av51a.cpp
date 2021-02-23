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
    const char *getIme() {
        return ime;
    }
    const char *getGrad() {
        return grad;
    }
    const char *getStadion() {
        return stadion;
    }
    void setIme(char *ime) {
        strcpy(this->ime, ime);
    }
    ~Ekipa() {}
};

int main() {

    Ekipa *e1 = new Ekipa("Real Madrid", "Madrid", "Santiago Bernabeu");
    Ekipa *e2 = new Ekipa(*e1);

    cout << "Ekipite se e: ";
    cout << e1->getIme();
    cout << "-";
    cout << e2->getIme();

    //e1->getIme()->setIme("Barselona"); //GRESHKA
    e1->setIme("Barselona");

    cout << "\nPo promenata ekipite se: ";
    cout << e1->getIme();
    cout << "-";
    cout << e2->getIme();

    delete e1;
    delete e2;

    return 0;
}
