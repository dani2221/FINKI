// Да се дефинира класа Potpisuvac во која се чуваат информации за:
// име (низа од максимум 20 знаци)
// презиме (низа од максимум 20 знаци)
// ЕМБГ (низа од максимум 14 знаци)
// За класата да се дефинира copy конструктор, default конструктор и конструктор со аргументи.
// Да се дефинира класа Dogovor, во која се чуваат информации за:
// број на договор (int),
// категорија на договор (низа од 50 знаци),
// поле од 3 потпишувачи на договорот (објекти од класата Potpisuvac)
// Во класата да се додаде метод кој ќе проверува дали постојат два исти потпишувачи (имаат ист ЕМБГ).

#include <iostream>
#include <cstring>

using namespace std;

class Potpisuvac
{
	private:
        char ime[20];
        char prezime[20];
        char embg[14];
    public:
        Potpisuvac(){
            //default
        }
        Potpisuvac(char* ime, char* prezime,char* embg){
            strcpy(this->ime,ime);
            strcpy(this->prezime,prezime);
            strcpy(this->embg,embg);
        }
        Potpisuvac(const Potpisuvac &p){
            strcpy(this->ime,p.ime);
            strcpy(this->prezime,p.prezime);
            strcpy(this->embg,p.embg);
        }
        char* getEmbg(){
            return this->embg;
        }
};
class Dogovor
{
    private:
        int broj;
        char kategorija[50];
        Potpisuvac potpisuvaci[3];
    public:
        Dogovor(int broj, char* kategorija, Potpisuvac potpisuvaci[3]){
            this->broj=broj;
            strcpy(this->kategorija,kategorija);
            for(int i=0;i<3;i++){
                this->potpisuvaci[i]=potpisuvaci[i];
            }
        }
        bool proverka(){
            return strcmp(potpisuvaci[0].getEmbg(),potpisuvaci[1].getEmbg())==0
            || strcmp(potpisuvaci[1].getEmbg(),potpisuvaci[2].getEmbg())==0
            || strcmp(potpisuvaci[0].getEmbg(),potpisuvaci[2].getEmbg())==0;
        }
};


//ne smee da se menuva main funkcijata
int main()
{
    // potrebno e da se smeni od embg[13] vo embg[14].
    // so emgb[13] nema prostor za zavrshen znak i ne raboti zadachata
    char embg[14], ime[20], prezime[20], kategorija[20];
    int broj, n;
    cin >> n;
    for(int i = 0; i < n; i++){
    	cin >> embg >> ime >> prezime;
    	Potpisuvac p1(ime, prezime, embg);
    	cin >> embg >> ime >> prezime;
    	Potpisuvac p2(ime, prezime, embg);
    	cin >> embg >> ime >> prezime;
    	Potpisuvac p3(ime, prezime, embg);
    	cin >> broj >> kategorija;
    	Potpisuvac p[3];
    	p[0] = p1; p[1] = p2; p[2] = p3;
    	Dogovor d(broj, kategorija, p);
        cout << "Dogovor " << broj << ":" << endl; 
    	if(d.proverka() == true)
    	    cout << "Postojat potpishuvaci so ist EMBG" << endl;
    	else
    	    cout << "Ne postojat potpishuvaci so ist EMBG" << endl;
    }
    return 0;
}