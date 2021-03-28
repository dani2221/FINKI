// Фирмата FINKI Cloud има потреба од решение за управување со 
// image датотеките од различните оперативни системи 
// кои ги користат при сетирање на нови сервери. 
// За таа цел потребно е да се дефинираат следниве класи:
// OperativenSistem
// Во класата OperativenSistem треба да се чуваат следниве податоци:
// име на оперативниот систем (динамички алоцирана низа од знаци)
// верзија (float)
// тип (енумерација со можни вредности LINUX, UNIX, WINDOWS)
// големина (во GB) (float)
// Дополнително, во рамките на класата потребно е да се дефинира:
// конструктори со и без параметри
// copy constructor
// destructor
// преоптоварување на операторот =
// метод за печатење на информациите - void pecati() 
// (видете го излезот од тест примерите за структурата)
// метод за проверка на еднаквост помеѓу два оперативни системи 
// (два оперативни системи се еднакви ако имаат исто име, верзија, тип и големина)
// - bool ednakviSe(const OperativenSistem &os)
// метод за споредба на верзии помеѓу два оперативни система 
// - int sporediVerzija(const OperativenSistem &os), кој ќе враќа 0 доколку верзиите се исти,
// -1 доколку верзијата на оперативниот систем од аргументот е поголема и 1 во преостанатиот случај.
// метод за проверка дали два оперативни системи се од иста фамилија. 
// Два оперативни системи се од иста фамилија доколку имаат исто име и се од ист тип.
// bool istaFamilija(const OperativenSistem &sporedba)
// Repozitorium
// Во класата Repozitorium треба да се чуваат следниве податоци:
// име на репозиториумот (низа од 20 знака)
// динамички алоцирана низа од OperativenSistem
// број на оперативни системи кои моментално се наоѓаат во репозиториумот (int)
// Дополнително, во рамките на класата потребно е да се дефинира:
// конструктор Repozitorium(const char *ime)
// деструктор
// метод void pecatiOperativniSistemi() 
// кој ќе ги печати сите оперативни системи кои се дел од репозиториумот
// метод void izbrishi(const OperativenSistem &operativenSistem) 
// кој ќе го избрише оперативниот систем проследен како аргумент,
//  доколку тој се наоѓа во репозиториумот. 
//  Да се користи методот ednakviSe за споредба на два оперативни система.
// метод за додавање на оперативен систем (void dodadi(const OperativenSistem &nov))
//  кој ќе го има следново однесување:
// Доколку веќе постои оперативен систем од иста фамилија 
// (да се користи методот за проверка на фамилија) кој е постар од проследениот оперативен систем,
//  тогаш постоечкиот оперативен систем се заменува со новиот.
// Во секој друг случај, проследениот оперативен систем се додава како дополнителен во репозиториумот.

#include <iostream>
#include <cstring>
using namespace std;

enum Tip{LINUX, UNIX, WINDOWS};

class OperativenSistem{
    private:
        char* ime;
        float verzija;
        Tip t;
        float golemina;
    public:
        OperativenSistem(){
            this->verzija=0.0;
            this->golemina=0.0;
        }
        OperativenSistem(char* ime,float verzija,Tip t,float golemina){
            this->verzija=verzija;
            this->golemina=golemina;
            this->t=t;
            this->ime=new char[strlen(ime)+1];
            strcpy(this->ime,ime);
        }
        OperativenSistem(const OperativenSistem &sistem){
            this->verzija=sistem.verzija;
            this->golemina=sistem.golemina;
            this->t=sistem.t;
            this->ime=new char[strlen(sistem.ime)+1];
            strcpy(this->ime,sistem.ime);
        }
        ~OperativenSistem(){
            delete [] ime;
        }
        OperativenSistem &operator=(const OperativenSistem &sistem){
            if(this==&sistem) return *this;
            this->verzija=sistem.verzija;
            this->golemina=sistem.golemina;
            this->t=sistem.t;
            this->ime=new char[strlen(sistem.ime)+1];
            strcpy(this->ime,sistem.ime);
            return *this;
        }
        void pecati(){
            cout<<"Ime: "<<ime<<" Verzija: "<<verzija<<" Tip: "<<t<<" Golemina:"<<golemina<<"GB"<<endl;
        }
        bool ednakviSe(const OperativenSistem &os){
            return !strcmp(this->ime,os.ime) && verzija==os.verzija && t==os.t && golemina==os.golemina;
        }
        int sporediVerzija(const OperativenSistem &os){
            if(verzija==os.verzija) return 0;
            if(os.verzija>verzija) return -1;
            return 1;
        }
        bool istaFamilija(const OperativenSistem &sporedba){
            return !strcmp(ime,sporedba.ime) && t==sporedba.t;
        }
};

class Repozitorium{
    private:
        char ime[20];
        OperativenSistem* sistemi;
        int broj;
    public:
        Repozitorium(const char *ime){
            strcpy(this->ime,ime);
            broj=0;
        }
        ~Repozitorium(){
            delete [] sistemi;
        }
        void pecatiOperativniSistemi(){
            cout<<"Repozitorium: "<<ime<<endl;
            for(int i=0;i<broj;i++){
                sistemi[i].pecati();
            }
        }
        void izbrishi(const OperativenSistem &operativenSistem){
            int deleteNum=broj;
            for(int i=0;i<broj;i++){
                if(sistemi[i].ednakviSe(operativenSistem)) deleteNum = i;
            }
            if(deleteNum==broj) return;
            if(deleteNum==broj-1) broj--;
            else{
                for(int i=deleteNum;i<broj;i++){
                    sistemi[i]=sistemi[i+1];
                }
                broj--;
            }

        }
        void dodadi(const OperativenSistem &nov){
            for(int i=0;i<broj;i++){
                if(sistemi[i].istaFamilija(nov) && sistemi[i].sporediVerzija(nov)==-1){
                    sistemi[i] = nov;
                    return;
                }
            }
            OperativenSistem *novi = new OperativenSistem[broj+1];
            for(int i=0;i<broj;i++){
                novi[i]=sistemi[i];
            }
            novi[broj]=nov;
            sistemi=novi;
            broj++;
        }
};

int main() {
    char repoName[20];
    cin>>repoName;
    Repozitorium repozitorium=Repozitorium(repoName);
    int brojOperativniSistemi = 0;
    cin>>brojOperativniSistemi;
    char ime[20];
    float verzija;
    int tip;
    float golemina;
    for (int i = 0; i<brojOperativniSistemi; i++){
        cin>>ime;
        cin>>verzija;
        cin>>tip;
        cin>>golemina;
        OperativenSistem os = OperativenSistem(ime, verzija, (Tip)tip, golemina);
        repozitorium.dodadi(os);
    }

    repozitorium.pecatiOperativniSistemi();
     cin>>ime;
    cin>>verzija;
    cin>>tip;
    cin>>golemina;
    OperativenSistem os = OperativenSistem(ime, verzija, (Tip)tip, golemina);
    cout<<"=====Brishenje na operativen sistem====="<<endl;
    repozitorium.izbrishi(os);
    repozitorium.pecatiOperativniSistemi();
    return 0;
}