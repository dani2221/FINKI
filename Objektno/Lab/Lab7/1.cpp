/* Да се дефинира апстрактна класа Employee којашто ќе содржи име на вработениот, години и работно искуство во години (integer). Да се дефинираат чисти виртуелни функции plata() и bonus() (double).

Од класата Employee да се изведе класа SalaryEmployee која покрај основните информации содржи и информација за основната плата. Бонусот на овие работници се пресметува како процент од основната плата, а процентот е бројот на години со работно искуство. На пример ако работеле 10 години, бонусот е 10 проценти од основната плата. Вкупната плата се пресметува како основната плата плус бонусот.

Од класата Employee исто така да се изведе класа HourlyEmployee која покрај основните информации содржи информација и за вкупниот број на часови кои ги одработил работникот и платата по час. Вкупната плата се пресметува како бројот на часови помножен со платата по час плус бонусот, додека бонусот се пресметува на следниот начин: За секој час над 320-тиот се добива 50 проценти од платата по час.

Од класата Employee на крај се изведува класата Freelancer која покрај основните информации содржи и број на проекти на кои работел вработениет и низа со суми кои ги добил за тие проекти (double). По направени 5 проекти, за секој нареден вработените добиваат бонус од 1000 денари. Вкупната плата на овој тип на вработени изнесува вкупната сума добиена од сите проекти плус бонусот.

Да се преоптовари операторот == кој ќе прима два објекти од класата Employee и ќе ги споредува според тоа дали имаат ист број на години и дали добиваат ист бонус.

Да се дефинира класа Company која ќе содржи информации за името на компанијата, бројот на вработени, и динамична низа од покажувачи од класата Employee или Employee **. За потребите на оваа класа треба да се дефинира конструктор кој прима само еден аргумент - името на компанијата, да се преоптовари операторот += и да се дефинираат следните методи:

double vkupnaPlata() - метод којшто ја враќа вкупната плата на сите вработени во компанијата
double filtriranaPlata(Employee * emp) - метод којшто ја враќа платата само на работниците кои се еднакви со дадениот вработен (според оператор ==)
void pecatiRabotnici() - метод којшто печати по колку вработени има од секој тип на работници во компанијата, а форматот на печатење можете да го видите од тест примерите
Hint: За потребите на последниот метод можете да искористите статички членови и статички функции во секоја од класата, dynamic_cast, виртуелна функција која ќе ја преоптоварите во секоја од класите или нешто друго по ваша желба. */
#include<iostream>
#include<cstring>
#include<cmath>
using namespace std;
class Employee
{
protected:
    char ime[50];
    int godini;
    int rabotnoIskustvo;
public:
    Employee(const char *ime="",int godini=0,int rabotnoIskustvo=0)
    {
        strcpy(this->ime,ime);
        this->godini=godini;
        this->rabotnoIskustvo=rabotnoIskustvo;
    }
    virtual double plata() = 0;
    virtual double bonus() = 0;
    bool operator==(Employee &e)
    {
        return (godini == e.godini && bonus() == e.bonus());
    }
};
class FreeLancer : public Employee
{
private:
    int brojNaProekti;
    double proekti[15];
public:
    FreeLancer(const char *ime="",int godini=0,int rabotnoIskustvo=0,int brojNaProekti=0,double *proekti=0) : Employee(ime,godini,rabotnoIskustvo)
    {
        this->brojNaProekti=brojNaProekti;
        for(int i=0;i<brojNaProekti;i++)
        {
            this->proekti[i]=proekti[i];
        }
    }
    double bonus()
    {
        if(brojNaProekti>5)
            return (brojNaProekti-5)*1000;
        return 0;
    }
    double plata()
    {
        double total=0;
        for(int i=0;i<brojNaProekti;i++)
        {
            total+=proekti[i];
        }
        return total+bonus();
    }
};
class SalaryEmployee : public Employee
{
private:
    int osnovnaPlata;
public:
    SalaryEmployee(const char *ime="",int godini=0,int rabotnoIskustvo=0,int osnovnaPlata=0) : Employee(ime,godini,rabotnoIskustvo)
    {
        this->osnovnaPlata=osnovnaPlata;
    }
    double bonus()
    {
        return (rabotnoIskustvo*osnovnaPlata)/100;
    }
    double plata()
    {
        return osnovnaPlata+bonus();
    }
};
class HourlyEmployee : public Employee
{
private:
    int vkupniCasovi;
    int plataPoCas;
public:
    HourlyEmployee(const char *ime="",int godini=0,int rabotnoIskustvo=0,int vkupniCasovi=0,int plataPoCas=0) : Employee(ime,godini,rabotnoIskustvo)
    {
        this->vkupniCasovi=vkupniCasovi;
        this->plataPoCas=plataPoCas;
    }
    double bonus()
    {
        if(vkupniCasovi > 320)
        {
            return (vkupniCasovi-320)*0.5*plataPoCas;
        }
        return 0;
    }
    double plata()
    {
        return vkupniCasovi*plataPoCas + bonus();
    }
};

class Company
{
private:
    char ime[50];
    int brojNaVraboteni;
    Employee **niza;

public:
    Company(const char *ime="")
    {
        strcpy(this->ime,ime);
        brojNaVraboteni=0;
        niza=new Employee*[0];
    }
    ~Company()
    {
        delete[]niza;
    }
    Company&operator+=(Employee *e)
    {
        Employee **temp=new Employee*[brojNaVraboteni+1];
        for(int i=0;i<brojNaVraboteni;i++)
        {
            temp[i]=niza[i];
        }
        temp[brojNaVraboteni++]=e;
        delete[]niza;
        niza=temp;
        return *this;
    }
    double vkupnaPlata()
    {
        double total=0;
        for(int i=0;i<brojNaVraboteni;i++)
        {
            total+=niza[i]->plata();
        }
        return total;
    }
    double filtriranaPlata(Employee *emp)
    {
        double total=0;
        for(int i=0;i<brojNaVraboteni;i++)
        {
            if(*niza[i] == *emp)
            {
                total+=niza[i]->plata();
            }
        }
        return total;
    }
    void pecatiRabotnici()
    {
        int countSalary=0,countHourly=0,countFree=0;
        cout<<"Vo kompanijata "<<ime<< " rabotat:"<<endl;
        for(int i=0;i<brojNaVraboteni;i++)
        {
            SalaryEmployee *se = dynamic_cast<SalaryEmployee *>(niza[i]);
            if(se != 0)
            {
                countSalary++;
                continue;
            }
            HourlyEmployee *he = dynamic_cast<HourlyEmployee *>(niza[i]);
            if(he != 0)
            {
                countHourly++;
                continue;
            }
            FreeLancer *fl=dynamic_cast<FreeLancer*>(niza[i]);
            if(fl != 0)
            {
                countFree++;
                continue;
            }

        }
        cout<<"Salary employees: "<<countSalary<<endl;
        cout<<"Hourly employees: "<<countHourly<<endl;
        cout<<"Freelancers: "<<countFree<<endl;
    }
};
int main ()
{
char name[50];
cin >> name;
Company c(name);

int n;
cin >> n;

char employeeName[50];
int age;
int experience;
int type;

for (int i=0; i <n; ++i) {
  cin >> type;
  cin >> employeeName >> age >> experience;

  if (type == 1) {
    int basicSalary;
    cin >> basicSalary;
    c += new SalaryEmployee(employeeName, age, experience, basicSalary);
  }

  else if (type == 2) {
    int hoursWorked;
    int hourlyPay;
    cin >> hoursWorked >> hourlyPay;
    c += new HourlyEmployee(employeeName, age, experience, hoursWorked, hourlyPay);
  }

  else {
    int numProjects;
    cin >> numProjects;
    double projects[10];
    for (int i=0; i < numProjects; ++i) {
      cin >> projects[i];
    }
    c += new FreeLancer(employeeName, age, experience, numProjects, projects);
  }
}

c.pecatiRabotnici();
cout << "Vkupnata plata e: " << c.vkupnaPlata() << endl;
Employee * emp = new HourlyEmployee("Petre_Petrov",31,6,340,80);
cout << "Filtriranata plata e: " << c.filtriranaPlata(emp);

delete emp;
}
