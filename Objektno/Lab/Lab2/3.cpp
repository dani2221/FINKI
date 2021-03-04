// Да се дефинира класа Film, во која ќе се чуваат информации за:
    // име низа од 100 знаци
    // режисер низа од 50 знаци
    // жанр низа од 50 знаци
    // година цел број
// Сите променливи треба да бидат приватни. Соодветно во рамките на класата да се дефинираат:
    // default конструктор и конструктор со аргументи
    // метод за печатење на информациите за филмот
// Дополнително да се реализира надворешна функција:
    // void pecati_po_godina(Film *f, int n, int godina) 
    // која ќе прима аргумент низа од филмови, вкупниот број на филмови и година, 
    // а треба да ги отпечати само филмовите кои се направени во дадената година.

#include <iostream>
#include <cstring>
using namespace std;

// vashiot kod ovde
class Film{
    private:
        char ime[100];
        char reziser[50];
        char zanr[50];
        int godina;
    public:
        Film(){
            //default konstruktor uwu
        }
        Film(char ime[100],char reziser[50], char zanr[50],int godina){
            strcpy(this->ime,ime);
            strcpy(this->reziser,reziser);
            strcpy(this->zanr,zanr);
            this->godina=godina;
        }
        void pecati(){
            cout<<"Ime: "<<ime
                <<"\nReziser: "<<reziser
                <<"\nZanr: "<<zanr
                <<"\nGodina: "<<godina<<endl;
        }
        int get_godina(){
            return godina;
        }
};
void pecati_po_godina(Film *f, int n, int godina){
    for(int i=0;i<n;i++){
        if((f+i)->get_godina()==godina) (f+i)->pecati();
    }
}

int main() {
 	int n;
 	cin >> n;
 	//da se inicijalizira niza od objekti od klasata Film
    Film *filmovi = new Film[n];
 	for(int i = 0; i < n; ++i) {
 		char ime[100];
 		char reziser[50];
 		char zanr[50];
 		int godina;
 		cin >> ime;
 		cin >> reziser;
 		cin >> zanr;
 		cin >> godina;
 		//da se kreira soodveten objekt
        Film film(ime,reziser,zanr,godina);
        filmovi[i]=film;
 	}
 	int godina;
 	cin >> godina;
 	//da se povika funkcijata pecati_po_godina
    pecati_po_godina(filmovi,n,godina);
 	return 0;
 }