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

void sort(Ekipa *p, int n) {
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            if (strcmp(p[i].getIme(), p[j].getIme()) > 0) {
                Ekipa pom = p[i];
                p[i] = p[j];
                p[j] = pom;
            }
        }

    }

}

int main() {

    int n;
    cin >> n;

    //pokazvac kon dinamicko alocirano pole od objekt od Ekipa
    Ekipa *prvenstvo = new Ekipa[n];

    char ime[20], grad[20], stadion[30];

    for (int i = 0; i < n; i++)  {
        cin >> ime >> grad;
        cin.getline(stadion, 29);
        prvenstvo[i] = Ekipa(ime, grad, stadion);
    }
    sort(prvenstvo, n);
    cout << "Ekipite od prvenstvoto se:\n";
    for (int i = 0; i < n; i++)  {
        cout << i + 1 << " " << prvenstvo[i].getIme() << " (" << prvenstvo[i].getGrad() << ", " << prvenstvo[i].getStadion() << ")" << endl;
    }

    delete [] prvenstvo;

    return 0;
}
