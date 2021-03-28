// Да се дефинира класата Film во која се чуваат информации за:
// име на филмот (динамички алоцирано поле од знаци)
// мемориската зафатнина на филмот изразена во MB
// жанр на филмот кој може да биде: акција, комедија или драма (енумерација zanr)
// Да се обезбедат сите потребни методи за класата за правилно функционирање на програмата.
// Дополнително за оваа класа да се дефинира функцијата:
// pecati() - функција во која ќе се печатат одделени со цртичка(-): 
// мемориска зафатнина на филмот со постфикс MB и името на филмот во наводници.
// Да се креира класата DVD во која се чуваат информации за :
// низа од филмови снимени на DVD-то (Поле од најмногу 5 објекти од класата Film )
// број на филмови снимени на DVD-то
// меморискиот капацитет на DVD-то (во MB)
// Да се обезбедат сите потребни методи за класата за правилно функционирање на програмата.
// Дополнително за оваа класа да се дефинираат методите:
// dodadiFilm (Film f) - 
// со кој се додава филмот f на DVDто, 
// но само ако има доволно преостанато место 
// (земајќи го предвид меморискиот капацитет на DVD-то и мемориската зафатнина на снимените филмови)
//  и притоа да има помалку од 5 филмови на DVD-то.
// pecatiFilmoviDrugZanr(zanr z) – 
// со кој се печатат информациите за сите филмови кои НЕ се од жанрот 
// zanr (акција, комедија или драма).
// (дополнително барање):float procentNaMemorijaOdZanr(zanr z)- 
// со кој се пресметува процентот на меморијата која ја зафаќаат филмовите од жанр z на DVD-то.


#include <iostream>
#include <cstring>

using namespace std;


enum zanr{akcija, komedija, drama};

class Film{
    private:
        char *ime;
        int golemina;
        zanr z;
    public:
        Film(char *ime="\0", int golemina=0, zanr z=akcija){
            this->ime = new char [strlen(ime)+1];
            strcpy(this->ime,ime);
            this->golemina=golemina;
            this->z=z;
        }
        void pecati(){
            cout<<golemina<<"MB-\""<<ime<<"\""<<endl;
        }
        int getGolemina(){
            return golemina;
        }
        zanr getZanr(){
            return z;
        }
};
class DVD{
    private:
        Film filmovi[5];
        int brojFilmovi;
        int memorija;
    public:
        DVD(Film filmovi[], int brojFilmovi, int memorija){
            for(int i=0;i<brojFilmovi;i++){
                this->filmovi[i]=filmovi[i];
            }
            this->brojFilmovi=brojFilmovi;
            this->memorija = memorija;
        }
        DVD(int memorija){
            this->brojFilmovi=0;
            this->memorija = memorija;
        }
        Film getFilm(int i){
            return filmovi[i];
        }
        void dodadiFilm(Film film){
            int vkupnaMemorija = 0;
            for(int i=0;i<brojFilmovi;i++){
                vkupnaMemorija+=filmovi[i].getGolemina();
            }
            if(brojFilmovi>4 || vkupnaMemorija+film.getGolemina()>memorija) return;
            filmovi[brojFilmovi] = film;
            brojFilmovi++;
        }
        void pecatiFilmoviDrugZanr(zanr z){
            for(int i=0;i<brojFilmovi;i++){
                if(filmovi[i].getZanr()!=z)filmovi[i].pecati();
            }
        }
        float procentNaMemorijaOdZanr(zanr z){
            int zanrMemorija=0, vkupnaMemorija=0;
            for(int i=0;i<brojFilmovi;i++){
                if(filmovi[i].getZanr()==z)  zanrMemorija+=filmovi[i].getGolemina();
                vkupnaMemorija+=filmovi[i].getGolemina();
            }
            return ((float)zanrMemorija/vkupnaMemorija)*100;
        }
        int getBroj(){
            return brojFilmovi;
        }
};  

int main() {
    // se testira zadacata modularno
    int testCase;
    cin >> testCase;

    int n, memorija, kojzanr;
    char ime[50];

    if (testCase == 1) {
        cout << "===== Testiranje na klasata Film ======" << endl;
        cin >> ime;
        cin >> memorija;
        cin >> kojzanr; //se vnesuva 0 za AKCIJA,1 za KOMEDIJA i 2 za DRAMA
        Film f(ime, memorija, (zanr) kojzanr);
        f.pecati();
    } else if (testCase == 2) {
        cout << "===== Testiranje na klasata DVD ======" << endl;
        DVD omileno(50);
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> memorija;
            cin >> kojzanr; //se vnesuva 0 za AKCIJA,1 za KOMEDIJA i 2 za DRAMA
            Film f(ime, memorija, (zanr) kojzanr);
            omileno.dodadiFilm(f);
        }
        for (int i = 0; i < n; i++)
            (omileno.getFilm(i)).pecati();
    } else if (testCase == 3) {
        cout << "===== Testiranje na metodot dodadiFilm() od klasata DVD ======" << endl;
        DVD omileno(50);
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> memorija;
            cin >> kojzanr; //se vnesuva 0 za AKCIJA,1 za KOMEDIJA i 2 za DRAMA
            Film f(ime, memorija, (zanr) kojzanr);
            omileno.dodadiFilm(f);
        }
        for (int i = 0; i < omileno.getBroj(); i++)
            (omileno.getFilm(i)).pecati();
    } else if (testCase == 4) {
        cout << "===== Testiranje na metodot pecatiFilmoviDrugZanr() od klasata DVD ======" << endl;
        DVD omileno(50);
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> memorija;
            cin >> kojzanr; //se vnesuva 0 za AKCIJA,1 za KOMEDIJA i 2 za DRAMA
            Film f(ime, memorija, (zanr) kojzanr);
            omileno.dodadiFilm(f);
        }
        cin >> kojzanr;
        omileno.pecatiFilmoviDrugZanr((zanr) kojzanr);

    } else if (testCase == 5) {
        cout << "===== Testiranje na metodot pecatiFilmoviDrugZanr() od klasata DVD ======" << endl;
        DVD omileno(50);
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> memorija;
            cin >> kojzanr; //se vnesuva 0 za AKCIJA,1 za KOMEDIJA i 2 za DRAMA
            Film f(ime, memorija, (zanr) kojzanr);
            omileno.dodadiFilm(f);
        }
        cin >> kojzanr;
        omileno.pecatiFilmoviDrugZanr((zanr) kojzanr);

    } else if (testCase == 6){
		cout<<"===== Testiranje na metodot procentNaMemorijaOdZanr() od klasata DVD =====" <<endl;
		DVD omileno(40);
		cin >> n;
		for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> memorija;
            cin >> kojzanr; //se vnesuva 0 za AKCIJA,1 za KOMEDIJA i 2 za DRAMA
            Film f(ime, memorija, (zanr) kojzanr);
            omileno.dodadiFilm(f);
        }
        cin >> kojzanr;
        cout<<"Procent na filmovi od dadeniot zanr iznesuva: "<<omileno.procentNaMemorijaOdZanr((zanr) kojzanr)<<"%\n";
		
	}

    return 0;
}