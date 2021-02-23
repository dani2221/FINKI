#include<iostream>
#include<string.h>
using namespace std;
class Exception {
public:
    void print() {
        cout << "Ne moze da se vnese dadeniot trud" << endl;
    }
};
class Trud {
private:
    char tip;
    int god;
public:
    Trud(const char tip = 'C', int god = 0) {
        this->tip = toupper(tip);
        this->god = god;
    }
    int getGod() {
        return god;
    }
    char getTip() {
        return tip;
    }
    friend istream& operator>>(istream& in, Trud &t) {
        in >> t.tip >> t.god;
        return in;
    }
};
class Student {
private:
    char ime[30];
    int indeks;
    int god;
    int oceni[50];
    int n;
public:
    Student() {}
    Student(const char* ime, int indeks, int god, int *oceni, int n) {
        strcpy(this->ime, ime);
        this->indeks = indeks;
        this->god = god;
        this->n = n;
        for (int i = 0; i < n; i++)
            this->oceni[i] = oceni[i];
    }
    int getGod() {
        return god;
    }
    int getIndeks() {
        return indeks;
    }
    virtual float rang() {
        int suma = 0;
        for (int i = 0; i < n; i++)
            suma += oceni[i];
        return (float)suma / n;
    }
    friend ostream& operator<< (ostream& o, Student& st)
    {
        o << st.indeks << " " << st.ime << " " << st.god << " " << st.rang() << endl;
        return o;
    }
    virtual ~Student() {}
};
class PhDStudent : public Student {
private:
    Trud *t;
    int nt;
    static int conf;
    static int journal;
public:
    PhDStudent(const char* ime, int indeks, int god, int *oceni, int n, Trud* t, int nt) : Student(ime, indeks, god, oceni, n) {
        //this->nt = nt;
        this->t = new Trud[100];
        int ok = 0;
        for (int i = 0; i < nt; i++) {
            try {
                if (this->getGod() > t[i].getGod()) throw Exception();
                this->t[ok] = t[i];
                ok++;
            }
            catch (Exception e) {
                e.print();
                //this->nt--;
            }
        }
        this->nt = ok
    }
    float rang() {
        int suma = 0;
        for (int i = 0; i < nt; i++) {
            if (t[i].getTip() == 'C')
                suma += conf;
            else
                suma += journal;
        }
        return Student::rang() + suma;
    }
    static void setConf(int c) {
        PhDStudent::conf = c;
    }
    static void setJournal(int j) {
        PhDStudent::journal = j;
    }
    void operator+=(Trud &tr) {
        if (this->getGod() > tr.getGod()) throw Exception();
        this->t[nt] = tr;
        this->nt++;
    }
    ~PhDStudent() {
        delete[] t;
    }
};
int PhDStudent::conf = 1;
int PhDStudent::journal = 3;
