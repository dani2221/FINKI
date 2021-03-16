// Во оваа задача е потребно да уредите даден дом со маси. Креирајте класа Masa со следниве атрибути:
// должина (целобројна вредност)
// ширина (целобројна вредност)
// конструктор со и без параметри и метода pecati().
// Креирајте класа Soba која содржи:
// маса (објект од класата Маса)
// должина на собата (целобројна вредност)
// ширина на собата (целобројна вредност)
// конструктор со и без параметри и метода pecati() во која се повикува и pecati() за објектот Masa.
// Креирајте класа Kukja со атрибути:
// соба (објект од класата Soba)
// адреса (низа од 50 знаци), и соодветни методи.
// конструктор со и без параметри, деструктор и метода pecati() во која се повикува 
// и pecati() за објектот Soba.

#include <iostream>
#include<cstring>

using namespace std;

class Masa{
    private:
        int dolzhina;
        int shirina;
    public:
        Masa(){
            //default
        }
        Masa(int dolzhina,int shirina){
            this->dolzhina=dolzhina;
            this->shirina=shirina;
        }
        void pecati(){
            cout<<"Masa: "<<this->dolzhina<<" "<<this->shirina<<endl;
        }
};
class Soba{
    private:
        Masa masa;
        int dolzhina;
        int shirina;
    public:
        Soba(){
            //default
        }
        Soba(int dolzhina,int shirina,Masa masa){
            this->masa=masa;
            this->dolzhina=dolzhina;
            this->shirina=shirina;
        }
        void pecati(){
            cout<<"Soba: "<<this->dolzhina<<" "<<this->shirina<<" ";
            this->masa.pecati();
        }
};
class Kukja{
    private:
        Soba soba;
        char adresa[50];
    public:
        Kukja(){
            //default
        }
        Kukja(Soba soba,char *adresa){
            this->soba=soba;
            strcpy(this->adresa,adresa);
        }
        ~Kukja(){
            //destruktor
        }
        void pecati(){
            cout<<"Adresa: "<<this->adresa<<" ";
            this->soba.pecati();
        }
};


//ne smee da se menuva main funkcijata!
int main(){
    int n;
    cin>>n;
    for(int i=0;i<n;i++){
    	int masaSirina,masaDolzina;
        cin>>masaSirina;
        cin>>masaDolzina;
    	Masa m(masaSirina,masaDolzina);
    	int sobaSirina,sobaDolzina;
        cin>>sobaSirina;
        cin>>sobaDolzina;
    	Soba s(sobaSirina,sobaDolzina,m);
    	char adresa[30];
        cin>>adresa;
    	Kukja k(s,adresa);
    	k.pecati();
	}
    
    return 0;
}