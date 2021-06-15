// Дадена е класа Kurs во која се чуваат информации за име на курс (низа од знаци) и број на кредити (цел број).

// Дадена е класа Student што содржи инфомрации за: индекс на студентот (цел број), низа од оценки на студентот (динамички алоцирана низа на оценките кои претставуваат броеви од 5 до 10) и број на оценки.

// Дадена е класа Predavach што содржи инфомрации за: име на предавачот (динамички алоцирана низа од знаци), листа од курсеви кои ги предава предавачот (низа од објекти од класата Kurs) и број на курсеви (цел број).

// Да се креира класа Demonstrator, со која се претставуваат студентите држат лабораториските вежби на некои курсеви. Објектите од оваа класа треба да содржат инфомрации за: индекс на студентот, оценки на студентот, број на оценки, име на демонстраторот, листа од курсеви, број на курсеви чии лабораторисски вежби ги држи студентот и број на часови во неделата кога студентот држи лабораториски вежби (цел број). (5 поени)

// За секој студент да се овозможат следните функции:

// getBodovi() - која враќа цел број кој го претставува број на бодови за даден студент. Студентите кои не се демонстратори имаат бодови кои го претставуваат процентот на преодни оценки на студентот. (На пример студент со оценки: 5 6 7 ќе има 66 бодови (цел дел од 66.666...) затоа што во 66% од оценките има оценка поголема од 5 ). Кај секој демонстратор на овие бодовите од оценките се додаваат бодовите од лабораториските вежби: (20*C)/N, каде N e бројот на курсеви кои ги држи, C бројот на часови во неделата кога студентот држи лабораториски вежби. Во случај кога некој демонстратор не држи ниту еден курс се фрла исклучокот NoCourseException. Справување со исклучокот треба да реализира онаму каде што е потребно и притоа да се испечати соодветна порака за грешка "Demonstratorot so indeks XXXX ne drzi laboratoriski vezbi", каде XXXX е индексот на демонстраторот. (15 поени)

// pecati()- во која се печати само индексот на студентот ако студентот не е демонстратор, а во случај кога студентот е демонстратор во продолжение се печатат информации за курсевите чии лабораториски вежби ги држи демонстраторот. (10 поени)

// Форматот за печатење е:

// Indeks: ime (Kurs1 Krediti1 ECTS, Kurs2 Krediti2 ECTS,...)
// Да се имплементираат следните глобални функции:

// Student& vratiNajdobroRangiran(Student ** studenti, int n ) што враќа референца кон студентот кој има најмногу бодови од листата на дадените n студенти (studenti). Да забележиме дека оние демонстратори кои не држат лабораториски вежби ќе земеме дека имаат 0 бодови. Да забележиме и дека во примерите секогаш има точно еден студент кој има најголем број на бодови. (15 поени)
// void pecatiDemonstratoriKurs (char* kurs, Student** studenti, int n) - која од дадена листа на студенти, ќе ги испечати само оние кои држат лабораториски вежби на курсот kurs. (10 поени)
// Комплетна функционалност на програмата. (5 поени)

// Да забележиме дека веќе постоечките класи Kurs, Student и Predavach може да се дополнуваат и менуваат. Погледнете ги дадените класи. Во нив покрај конструкторите дадени се и други функциите кои можат да се користат.

#include<iostream>
#include<string.h>
using namespace std;

class NoCourseException
{
private:
    int indeks;
public:
    NoCourseException(int indeks)
    {
        this->indeks=indeks;
    }
    void message()
    {
        cout<<"Demonstratorot so indeks "<<indeks<<" ne drzi laboratoriski vezbi"<<endl;
    }
};
class Kurs{
private:
    char ime[20];
    int krediti;
public:
    Kurs (char *ime,int krediti){
        strcpy(this->ime,ime);
        this->krediti=krediti;
    }
    Kurs (){
        strcpy(this->ime,"");
        krediti=0;
    }
    bool operator==(const char *ime) const{
        return strcmp(this->ime,ime)==0;
    }
    char const * getIme()const{
        return ime;
    }
    void pecati ()const{cout<<ime<<" "<<krediti<<"ECTS";}
};
 
class Student{
private:
    int *ocenki;
    int brojOcenki;
 
protected:
    int indeks;
 
public:
    Student(int indeks,int *ocenki, int brojOcenki){
        this->indeks=indeks;
        this->brojOcenki=brojOcenki;
        this->ocenki=new int[brojOcenki];
        for (int i=0;i<brojOcenki;i++) this->ocenki[i]=ocenki[i];
    }
    Student(const Student &k){
        this->indeks=k.indeks;
        this->brojOcenki=k.brojOcenki;
        this->ocenki=new int[k.brojOcenki];
        for (int i=0;i<k.brojOcenki;i++) this->ocenki[i]=k.ocenki[i];
    }
    Student operator=(const Student &k){
        if (&k==this) return *this;
        this->indeks=k.indeks;
        this->brojOcenki=k.brojOcenki;
        delete [] ocenki;
        this->ocenki=new int[k.brojOcenki];
        for (int i=0;i<k.brojOcenki;i++) this->ocenki[i]=k.ocenki[i];
        return *this;
    }
 
    ~Student(){delete [] ocenki;}
 
   virtual int getBodovi()
   {
       int brojac=0;
       for(int i=0;i<brojOcenki;i++)
       {
           if(ocenki[i] > 5)
           {
               brojac++;
           }
       }
       return (brojac*100.0)/brojOcenki;
   }
   virtual void pecati()
   {
       cout<<indeks;
   }
 
};
 
class Predavach{
private:
    Kurs kursevi[10];
    int brojKursevi;
 
protected:
    char *imeIPrezime;
 
public:
    Predavach(const char *imeIPrezime,Kurs *kursevi,int brojKursevi){
        this->brojKursevi=brojKursevi;
        for (int i=0;i<brojKursevi;i++) this->kursevi[i]=kursevi[i];
        this->imeIPrezime=new char[strlen(imeIPrezime)+1];
        strcpy(this->imeIPrezime,imeIPrezime);
    }
    Predavach(const Predavach &p){
        this->brojKursevi=p.brojKursevi;
        for (int i=0;i<p.brojKursevi;i++) this->kursevi[i]=p.kursevi[i];
        this->imeIPrezime=new char[strlen(p.imeIPrezime)+1];
        strcpy(this->imeIPrezime,p.imeIPrezime);
    }
    Predavach operator=(const Predavach &p){
        if (this==&p) return *this;
        this->brojKursevi=p.brojKursevi;
        for (int i=0;i<p.brojKursevi;i++) this->kursevi[i]=p.kursevi[i];
        this->imeIPrezime=new char[strlen(p.imeIPrezime)+1];
        delete [] imeIPrezime;
        strcpy(this->imeIPrezime,p.imeIPrezime);
        return *this;
    }
    ~Predavach(){delete [] imeIPrezime;}
 
    int getBrojKursevi()const {return brojKursevi;}
 
    char * const getImeIPrezime()const {return imeIPrezime;}
 
    Kurs operator[](int i) const {
       if (i<brojKursevi&&i>=0)
           return kursevi[i];
       else return Kurs();
    }
     virtual void pecati() const  {
         cout<<imeIPrezime<<" (";
         for (int i=0;i<brojKursevi;i++){
             kursevi[i].pecati();
             if (i<brojKursevi-1) cout<<", ";  else cout<<")";
        }
    }
};
 
 
//mesto za vashiot kod
class Demonstrator : public  Student, public  Predavach
{
private:
    int brojCasovi;
public:
    Demonstrator(int indeks=0,int *ocenki=0,int brojOcenki = 0,const char *ime="",Kurs *kursevi=0,int brojKursevi=0, int brojCasovi =0) : Predavach(ime,kursevi,brojKursevi), Student(indeks,ocenki,brojOcenki)
    {
        this->brojCasovi=brojCasovi;
    }
    int getBodovi()
    {
 
            if(getBrojKursevi() == 0)
            {
                throw NoCourseException(indeks);
            }
 
        return (Student::getBodovi() + (20*brojCasovi)/getBrojKursevi());
    }
    void pecati()
    {
        Student::pecati();
        cout<<": ";
        Predavach::pecati();
    }
};
 
Student & vratiNajdobroRangiran(Student **studenti, int n)
{
    Student *temp=studenti[0];
    for(int i=0;i<n;i++)
    {
        try{
        if(studenti[i]->getBodovi() > temp->getBodovi())
            {
            temp=studenti[i];
            }
        }
        catch(NoCourseException &n)
        {
            n.message();
        }
    }
    return *temp;
}
void pecatiDemonstratoriKurs(char *kurs,Student **studenti,int n)
{
    for(int i=0;i<n;i++)
    {
        Demonstrator *temp = dynamic_cast<Demonstrator*>(studenti[i]);
        if(temp != 0 )
        {
            for(int j=0;j<temp->getBrojKursevi();j++)
            {
                if((*temp)[j] == kurs)
                {
                    temp->pecati();
                    cout<<endl;
                    break;
                }
            }
        }
    }
}
int main(){
 
Kurs kursevi[10];
int indeks,brojKursevi, ocenki[20],ocenka,brojOcenki,tip,brojCasovi,krediti;
char ime[20],imeIPrezime[50];
 
cin>>tip;
 
if (tip==1) //test class Demonstrator
{
    cout<<"-----TEST Demonstrator-----"<<endl;
    cin>>indeks>>brojOcenki;
    for (int i=0;i<brojOcenki;i++){
         cin>>ocenka;
         ocenki[i]=ocenka;
    }
    cin>>imeIPrezime>>brojKursevi;
    for (int i=0;i<brojKursevi;i++){
         cin>>ime>>krediti;
         kursevi[i]=Kurs(ime,krediti);
    }
    cin>>brojCasovi;
 
Demonstrator d(indeks,ocenki,brojOcenki,imeIPrezime,kursevi,brojKursevi,brojCasovi);
cout<<"Objekt od klasata Demonstrator e kreiran";
 
} else if (tip==2) //funkcija pecati vo Student
{
    cout<<"-----TEST pecati-----"<<endl;
    cin>>indeks>>brojOcenki;
    for (int i=0;i<brojOcenki;i++){
         cin>>ocenka;
         ocenki[i]=ocenka;
    }
 
Student s(indeks,ocenki,brojOcenki);
s.pecati();
 
} else if (tip==3) //funkcija getVkupnaOcenka vo Student
{
    cout<<"-----TEST getVkupnaOcenka-----"<<endl;
    cin>>indeks>>brojOcenki;
    for (int i=0;i<brojOcenki;i++){
         cin>>ocenka;
         ocenki[i]=ocenka;
    }
Student s(indeks,ocenki,brojOcenki);
cout<<"Broj na bodovi: "<<s.getBodovi()<<endl;
 
} else if (tip==4) //funkcija getVkupnaOcenka vo Demonstrator
{
  cout<<"-----TEST getVkupnaOcenka-----"<<endl;
  cin>>indeks>>brojOcenki;
    for (int i=0;i<brojOcenki;i++){
         cin>>ocenka;
         ocenki[i]=ocenka;
    }
    cin>>imeIPrezime>>brojKursevi;
    for (int i=0;i<brojKursevi;i++){
         cin>>ime>>krediti;
         kursevi[i]=Kurs(ime,krediti);
    }
    cin>>brojCasovi;
 
Demonstrator d(indeks,ocenki,brojOcenki,imeIPrezime,kursevi,brojKursevi,brojCasovi);
cout<<"Broj na bodovi: "<<d.getBodovi()<<endl;
 
} else if (tip==5) //funkcija pecati vo Demonstrator
{
 cout<<"-----TEST pecati -----"<<endl;
 cin>>indeks>>brojOcenki;
    for (int i=0;i<brojOcenki;i++){
         cin>>ocenka;
         ocenki[i]=ocenka;
    }
    cin>>imeIPrezime>>brojKursevi;
    for (int i=0;i<brojKursevi;i++){
         cin>>ime>>krediti;
         kursevi[i]=Kurs(ime,krediti);
    }
    cin>>brojCasovi;
 
Demonstrator d(indeks,ocenki,brojOcenki,imeIPrezime,kursevi,brojKursevi,brojCasovi);
d.pecati();
 
} else if (tip==6) //site klasi
{
    cout<<"-----TEST Student i Demonstrator-----"<<endl;
 cin>>indeks>>brojOcenki;
    for (int i=0;i<brojOcenki;i++){
         cin>>ocenka;
         ocenki[i]=ocenka;
    }
    cin>>imeIPrezime>>brojKursevi;
    for (int i=0;i<brojKursevi;i++){
         cin>>ime>>krediti;
         kursevi[i]=Kurs(ime,krediti);
    }
    cin>>brojCasovi;
 
Student *s=new Demonstrator(indeks,ocenki,brojOcenki,imeIPrezime,kursevi,brojKursevi,brojCasovi);
s->pecati();
cout<<"\nBroj na bodovi: "<<s->getBodovi()<<endl;
delete s;
 
 
} else if (tip==7) //funkcija vratiNajdobroRangiran
{
    cout<<"-----TEST vratiNajdobroRangiran-----"<<endl;
int k, opt;
cin>>k;
Student **studenti=new Student*[k];
for (int j=0;j<k;j++){
   cin>>opt; //1 Student 2 Demonstrator
   cin>>indeks>>brojOcenki;
   for (int i=0;i<brojOcenki;i++)
      {
         cin>>ocenka;
         ocenki[i]=ocenka;
      }
   if (opt==1){
        studenti[j]=new Student(indeks,ocenki,brojOcenki);
   }else{
       cin>>imeIPrezime>>brojKursevi;
        for (int i=0;i<brojKursevi;i++){
         cin>>ime>>krediti;
         kursevi[i]=Kurs(ime,krediti);
        }
        cin>>brojCasovi;
        studenti[j]=new Demonstrator(indeks,ocenki,brojOcenki,imeIPrezime,kursevi,brojKursevi,brojCasovi);
   }
}
 
Student& najdobar=vratiNajdobroRangiran(studenti,k);
cout<<"Maksimalniot broj na bodovi e:"<<najdobar.getBodovi();
cout<<"\nNajdobro rangiran:";
najdobar.pecati();
 
for (int j=0;j<k;j++) delete studenti[j];
 delete [] studenti;
} else if (tip==8) //funkcija pecatiDemonstratoriKurs
{
cout<<"-----TEST pecatiDemonstratoriKurs-----"<<endl;
int k, opt;
cin>>k;
Student **studenti=new Student*[k];
for (int j=0;j<k;j++){
   cin>>opt; //1 Student 2 Demonstrator
   cin>>indeks>>brojOcenki;
   for (int i=0;i<brojOcenki;i++)
      {
         cin>>ocenka;
         ocenki[i]=ocenka;
      }
   if (opt==1){
        studenti[j]=new Student(indeks,ocenki,brojOcenki);
   }else{
   cin>>imeIPrezime>>brojKursevi;
    for (int i=0;i<brojKursevi;i++)
      {
         cin>>ime>>krediti;
         kursevi[i]=Kurs(ime,krediti);
      }
      cin>>brojCasovi;
      studenti[j]=new Demonstrator(indeks,ocenki,brojOcenki,imeIPrezime,kursevi,brojKursevi,brojCasovi);
   }
}
char kurs[20];
cin>>kurs;
cout<<"Demonstratori na "<<kurs<<" se:"<<endl;
pecatiDemonstratoriKurs (kurs,studenti,k);
for (int j=0;j<k;j++) delete studenti[j];
delete [] studenti;
 
}
 
 
return 0;
}
 
 