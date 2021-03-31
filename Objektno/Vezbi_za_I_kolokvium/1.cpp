// Да се дефинира класата Pesna во која се чуваат информации за (5 поени):
// име на песна (динамички алоцирано поле од знаци)
// времетраење на песната во минути
// тип на песна кој може да биде: поп, рап или рок (енумерација tip)
// Да се обезбедат сите потребни методи за класата за правилно функционирање на програмата.(10 поени)
// Дополнително за оваа класа да се дефинира методот:
// pecati() - функција во која ќе се печатат одделени со цртичка(-):
// името на песната во наводници и времетраењето на песната со постфикс min. (5 поени)
// Да се креира класата CD во која се чуваат информации за (5 поени):
// низа од песни снимени на CD-то (Поле од најмногу 10 објекти од класата Pesna )
// број на песни снимени на CD-то
// максимално времетраење на песните на CD-то (во минути)
// Да се обезбедат сите потребни методи за класата за правилно функционирање на програмата.(5 поени)
// Дополнително за оваа класа да се дефинираат методите:
// dodadiPesna (Pesna p) - со кој се додава песната p на CDто, 
// но само ако има доволно преостанато место
// (земајќи го предвид максималното времетраење на песните на
// CDто и времетраењата на песните на CD-то)
// и ако има помалку од 10 песни на CD-то. (10 поени)
// pecatiPesniPoTip(tip t) – со кој се печатат информациите за сите песни од 
// тип t (поп, рап или рок). (10 поени)
// Комплетна функционалност (5 поени).

#include <iostream>
#include <cstring>
using namespace std;

enum tip {pop,rap,rok};
class Pesna{
    private:
        char *ime;
        int vreme;
        tip tipPesna;
    public:
        Pesna(){
            // default
        }
        Pesna(char *ime, int vreme, tip tipPesna){
            this->ime = new char[strlen(ime)+1];
            strcpy(this->ime,ime);
            this->vreme=vreme;
            this->tipPesna=tipPesna;
        }
        int getVreme(){return this->vreme;}
        tip getTip(){return this->tipPesna;}
        void pecati(){
            cout<<"&quot;"<<this->ime<<"&quot;"<<"-"<<this->vreme<<"min";
        }
};
class CD{
    private:
        Pesna pesni[10];
        int brojPesni;
        int maksVreme;
    public:
        CD(){
            // default
        }
        CD(Pesna pesni[10],int brojPesni,int maksVreme){
            for(int i=0;i>brojPesni;i++){
                this->pesni[i] = pesni[i];
            }
            this->brojPesni=brojPesni;
            this->maksVreme=maksVreme;
        }
        CD(int maksVreme){
            this->brojPesni=0;
            this->maksVreme=maksVreme;
        }
        void dodadiPesna(Pesna p){
            int vkupnoVreme = 0;
            for(int i =0;i<this->brojPesni;i++){
                vkupnoVreme+=this->pesni[i].getVreme();
            }
            if(this->brojPesni<10 && vkupnoVreme+p.getVreme()<this->maksVreme){
                this->pesni[brojPesni] = p;
                this->brojPesni++;
            }
        }
        void pecatiPesniPoTip(tip t){
            for(int i=0;i<this->brojPesni;i++){
                if(this->pesni[i].getTip()==t) this->pesni[i].pecati();
            }
        }
        Pesna getPesna(int id){
            return this->pesni[id];
        }
        int getBroj(){
            return this->brojPesni;
        }
};


int main() {
	// se testira zadacata modularno
    int testCase;
    cin >> testCase;
	
	int n, minuti, kojtip;
	char ime[50];
	
	if(testCase == 1) {
        cout << "===== Testiranje na klasata Pesna ======";
        cin >> ime;
        cin >> minuti;
        cin >> kojtip; //se vnesuva 0 za POP,1 za RAP i 2 za ROK
        Pesna p(ime,minuti,(tip)kojtip);
		p.pecati();
    } else if(testCase == 2) {
        cout << "===== Testiranje na klasata CD ======";
		CD omileno(20);
		cin>>n;
			for (int i=0;i<n;i++){
				cin >> ime;
				cin >> minuti;
				cin >> kojtip; //se vnesuva 0 za POP,1 za RAP i 2 za ROK
				Pesna p(ime,minuti,(tip)kojtip);
				omileno.dodadiPesna(p);
			}
        	for (int i=0; i<n; i++)
				(omileno.getPesna(i)).pecati();
	}
    else if(testCase == 3) {
        cout << "===== Testiranje na metodot dodadiPesna() od klasata CD ======";
		CD omileno(20);
		cin>>n;
			for (int i=0;i<n;i++){
				cin >> ime;
				cin >> minuti;
				cin >> kojtip; //se vnesuva 0 za POP,1 za RAP i 2 za ROK
				Pesna p(ime,minuti,(tip)kojtip);
				omileno.dodadiPesna(p);
			}
        	for (int i=0; i<omileno.getBroj(); i++)
				(omileno.getPesna(i)).pecati();
    }
    else if(testCase == 4) {
        cout << "===== Testiranje na metodot pecatiPesniPoTip() od klasata CD ======";
		CD omileno(20);
		cin>>n;
			for (int i=0;i<n;i++){
				cin >> ime;
				cin >> minuti;
				cin >> kojtip; //se vnesuva 0 za POP,1 za RAP i 2 za ROK
				Pesna p(ime,minuti,(tip)kojtip);
				omileno.dodadiPesna(p);
			}
        cin>>kojtip;
        omileno.pecatiPesniPoTip((tip)kojtip);
        	
    }
    else if(testCase == 5) {
        cout << "===== Testiranje na metodot pecatiPesniPoTip() od klasata CD ======";
		CD omileno(20);
		cin>>n;
			for (int i=0;i<n;i++){
				cin >> ime;
				cin >> minuti;
				cin >> kojtip; //se vnesuva 0 za POP,1 za RAP i 2 za ROK
				Pesna p(ime,minuti,(tip)kojtip);
				omileno.dodadiPesna(p);
			}
        cin>>kojtip;
        omileno.pecatiPesniPoTip((tip)kojtip);
        	
    }
    
return 0;
}