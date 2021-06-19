#include<iostream>
#include<cstring>

using namespace std;

class InvalidDateException{
    private:
        int den;
        int mesec;
        int godina;
    public:
        InvalidDateException(int den, int mesec, int godina){
            this->den=den;
            this->mesec=mesec;
            this->godina=godina;
        }
        void message(){
            cout<<"Invalid Date "<<den<<"/"<<mesec<<"/"<<godina<<endl;
        }
};
class NotSupportedCurrencyException{
    private:
        char *text;
    public:
        NotSupportedCurrencyException(char *text){
            this->text=new char[strlen(text)];
            strcpy(this->text,text);
        }
        void message(){
            cout<<text<<" is not a supported currency"<<endl;
        }
};

class Transakcija{
    protected:
        int den;
        int mesec;
        int godina;
        double iznos;
        static double EUR;
        static double USD;
    public:
        Transakcija(int den, int mesec, int godina, double iznos){
            if(den<1 || den>31 ) throw InvalidDateException(den,mesec,godina);
            if(mesec<1 || mesec>12 ) throw InvalidDateException(den,mesec,godina);
            this->den=den;
            this->mesec=mesec;
            this->godina=godina;
            this->iznos=iznos;
        }
        virtual double voDenari() = 0;
        virtual double voEvra() = 0;
        virtual double voDolari() = 0;
        virtual void pecati() = 0;
        static double getEUR(){
            return EUR;
        }
        static double getUSD(){
            return USD;
        }
        static void setEUR(double eur){
            Transakcija::EUR = eur;
        }
        static void setUSD(double usd){
            Transakcija::USD = usd;
        }
};
double Transakcija::EUR = 61;
double Transakcija::USD = 50;

class DenarskaTransakcija : public Transakcija{
    public:
        DenarskaTransakcija(int den, int mesec, int godina, double iznos): Transakcija(den,mesec,godina,iznos){

        }
        void pecati(){
            cout<<den<<"/"<<mesec<<"/"<<godina<<" "<<iznos<<" MKD"<<endl;
        }
        double voDenari(){
            return iznos;
        }
        double voEvra(){
            return iznos/EUR;
        }
        double voDolari(){
            return iznos/USD;
        }
};
class DeviznaTransakcija : public Transakcija{
    private:
        char valuta[4];
    public:
        DeviznaTransakcija(int den, int mesec, int godina, double iznos,char* valuta): Transakcija(den,mesec,godina,iznos){
            if(strcmp(valuta,"USD") && strcmp(valuta,"EUR")) throw NotSupportedCurrencyException(valuta);
            strcpy(this->valuta,valuta);
        }
        void pecati(){
            cout<<den<<"/"<<mesec<<"/"<<godina<<" "<<iznos<<" "<<valuta<<endl;
        }
        double voDenari(){
            if(!strcmp(this->valuta,"EUR")) return iznos*EUR;
            else return iznos*USD;
        }
        double voEvra(){
            if(!strcmp(this->valuta,"EUR")) return iznos;
            else return iznos*USD/EUR;
        }
        double voDolari(){
            if(!strcmp(this->valuta,"EUR")) return iznos*EUR/USD;
            else return iznos;
        }
};

class Smetka{
    private:
        Transakcija **niza;
        int num;
        char brojSmetka[15];
        double saldo;
    public:
        Smetka(char brojSmetka[15],double saldo){
            strcpy(this->brojSmetka,brojSmetka);
            this->saldo=saldo;
            this->num=0;
        }
        ~Smetka(){
            delete [] niza;
        }
        void izvestajVoDenari(){
            cout<<"Korisnikot so smetka: "<<brojSmetka<<" ima momentalno saldo od "<<saldo<<" MKD"<<endl;
        }
        void izvestajVoEvra(){
            cout<<"Korisnikot so smetka: "<<brojSmetka<<" ima momentalno saldo od "<<saldo/Transakcija::getEUR()<<" EUR"<<endl;
        }
        void izvestajVoDolari(){
            cout<<"Korisnikot so smetka: "<<brojSmetka<<" ima momentalno saldo od "<<saldo/Transakcija::getUSD()<<" USD"<<endl;
        }
        void pecatiTransakcii(){
            for(int i=0;i<num;i++){
                niza[i]->pecati();
            }
        }
        Smetka &operator+=(Transakcija *t){
            Transakcija **temp = new Transakcija*[num+1];
            for(int i=0;i<num;i++){
                temp[i]=niza[i];
            }
            temp[num]=t;
            if(num!=0) delete [] niza;
            niza = new Transakcija*[++num];
            niza=temp;
        }

};


int main () {
	
	Smetka s ("300047024112789",1500);
	
	int n, den, mesec, godina, tip;
	double iznos;
	char valuta [3];
	
	cin>>n;
    cout<<"===VNESUVANJE NA TRANSAKCIITE I SPRAVUVANJE SO ISKLUCOCI==="<<endl;
	for (int i=0;i<n;i++){
		cin>>tip>>den>>mesec>>godina>>iznos;		
		if (tip==2){
			cin>>valuta;
            try{
                Transakcija * t = new DeviznaTransakcija(den,mesec,godina,iznos,valuta);
                s+=t;
            }catch(InvalidDateException e){
                e.message();
            }catch(NotSupportedCurrencyException p){
                p.message();
            }
            //delete t;
		}
		else {
            try{
                Transakcija * t = new DenarskaTransakcija(den,mesec,godina,iznos);
                s+=t;
            }catch(InvalidDateException e){
                e.message();
            }
            //delete t;
		}
			
	}
    cout<<"===PECHATENJE NA SITE TRANSAKCII==="<<endl;
    s.pecatiTransakcii();
    cout<<"===IZVESHTAJ ZA SOSTOJBATA NA SMETKATA VO DENARI==="<<endl;
    s.izvestajVoDenari();
    cout<<"===IZVESHTAJ ZA SOSTOJBATA NA SMETKATA VO EVRA==="<<endl;
    s.izvestajVoEvra();
    cout<<"===IZVESHTAJ ZA SOSTOJBATA NA SMETKATA VO DOLARI==="<<endl;
    s.izvestajVoDolari();
    
    cout<<"\n===PROMENA NA KURSOT NA EVROTO I DOLAROT===\n"<<endl;
    
        
    double newEUR, newUSD;
    cin>>newEUR>>newUSD;
    Transakcija::setEUR(newEUR);
    Transakcija::setUSD(newUSD);
    cout<<"===IZVESHTAJ ZA SOSTOJBATA NA SMETKATA VO DENARI==="<<endl;
    s.izvestajVoDenari();
    cout<<"===IZVESHTAJ ZA SOSTOJBATA NA SMETKATA VO EVRA==="<<endl;
    s.izvestajVoEvra();
    cout<<"===IZVESHTAJ ZA SOSTOJBATA NA SMETKATA VO DOLARI==="<<endl;
    s.izvestajVoDolari();
    
	
	
	return 0;
}