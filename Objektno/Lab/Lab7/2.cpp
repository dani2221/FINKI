/* Да се дефинира апстрктна класа Number со следните чисто виртуелни методи:

double doubleValue() -ја враќа децималната вредност на даден број
int intValue()- ја враќа целобројната вредност на даден број
void print() - печати информации за бројот. (1 поен)
Од оваа класа да се изведат две класи:

Integer (во која што се чува еден број од тип int)
Double (во која што се чува еден број од тип double) (1 поен).
За двете изведени класи да се имплементираaт соодветен конструктори, како и да се препокријат методите од основната класа. (1 поени)

Да се преоптовари операторот == којшто ќе споредува два броеви според нивната вредност (објекти од класа Number) (1 поен)

Дополнително да се дефинира класа Numbers во која што ќе се чуваат:

динамички алоцирана низа од покажувачи кон објекти од класата Number
број на елементи во низата од покажувачи
За класата да се имплементира соодветен конструктор (default) , како и: (1 поени)

операторот += за додавање на броеви во низата од броеви (1 поен)
Бројот се додава ако и само ако сите броеви што се веќе додадени во низата се различни од него
функција void statistics() која што печати информации за броевите во низата: (2 поени)
Count of numbers: [број на броеви во низата

Sum of all numbers: [сума на сите броеви во низата]

Count of integer numbers: [број на цели броеви (Integer)]

Sum of integer numbers: [сума на сите цели броеви (Integer)]

Count of double numbers: [број на децимални броеви (Double)]

Sum of double numbers: [сума на сите децимални броеви (Double)]

функција void integersLessThan (Integer n) која што ги печати сите цели броеви помали од бројот n (1 поен)
функција void doublesBiggerThan (Double n) која што ги печати сите децимални броеви поголеми од бројот n (1 поен)
*/
#include<iostream>
#include<cstring>
using namespace std;
class Number
{
    public:
        virtual double doubleValue() = 0;
        virtual int intValue() = 0;
        virtual void print()= 0;
};
bool operator ==(Number *n1,Number &n2)
{
    if(n1->doubleValue() == n2.doubleValue() && n1->intValue() == n2.intValue())
        return true;
    else
        return false;
}
class Integer : public Number
{
private:
    int broj;
public:
    Integer(int broj)
    {
        this->broj=broj;
    }
    double doubleValue()
    {
        return (double)broj;
    }
    int intValue()
    {
        return broj;
    }
    void print()
    {
        cout<<"Integer: "<<broj<<endl;
    }
};
class Double : public Number
{
private:
    double broj;
public:
    Double(double broj)
    {
        this->broj=broj;
    }
    double doubleValue()
    {
        return broj;
    }
    int intValue()
    {
        return (int)broj;
    }
    void print()
    {
        cout<<"Double: "<<broj<<endl;
    }
};
class Numbers
{
private:
    Number **niza;
    int broj;

    void copy(const Numbers &n)
    {
        this->broj=n.broj;
        this->niza=new Number*[broj];
        for(int i=0;i<broj;i++)
        {
            niza[i]=n.niza[i];
        }
    }
public:
    Numbers()
    {
        broj=0;
        niza=new Number*[0];
    }
    Numbers(const Numbers &n)
    {
        copy(n);
    }
    Numbers&operator=(const Numbers &n)
    {
        if(this!=&n)
        {
            delete[]niza;
            copy(n);
        }
        return *this;
    }
    ~Numbers()
    {
        delete[]niza;
    }
    Numbers&operator+=(Number *num)
    {
        for(int i=0;i<broj;i++)
        {
            if(niza[i] == *num)
                return *this;
        }
        Number **temp=new Number*[broj+1];
        for(int i=0;i<broj;i++)
        {
            temp[i]=niza[i];
        }
        temp[broj++]=num;
        delete[]niza;
        niza=temp;
        return *this;
    }
    void statistics()
    {
        double suma=0;
        for(int i=0;i<broj;i++)
        {
            suma+=niza[i]->doubleValue();
        }
        int countOfInteger=0,countOfDouble=0,sumInt=0;
        double sumDouble=0;
        for(int i=0;i<broj;i++)
        {
            Integer *in=dynamic_cast<Integer *>(niza[i]);
            if(in != 0)
            {
                countOfInteger++;
                sumInt+=niza[i]->intValue();
            }
            Double *d=dynamic_cast<Double *>(niza[i]);
            if(d != 0)
            {
                countOfDouble++;
                sumDouble+=niza[i]->doubleValue();
            }
        }
        cout<<"Count of numbers: "<<this->broj<<endl;
        cout<<"Sum of all numbers: "<<suma<<endl;
        cout<<"Count of integer numbers: "<<countOfInteger<<endl;
        cout<<"Sum of integer numbers: "<<sumInt<<endl;
        cout<<"Count of double numbers: "<<countOfDouble<<endl;
        cout<<"Sum of double numbers: "<<sumDouble<<endl;
    }
    void integersLessThan(Integer n)
    {
        int count=0;
        int max=n.intValue();
        for(int i=0;i<broj;i++)
        {
            Integer *in=dynamic_cast<Integer *>(niza[i]);
            if(in)
            {
                if(niza[i]->intValue() < max)
                {
                    niza[i]->print();
                    count++;
                }
            }
        }
        if(count == 0)
            cout<<"None"<<endl;
    }
    void doublesBiggerThan(Double n)
    {
        int count=0;
        int min=n.doubleValue();
        for(int i=0;i<broj;i++)
        {
            Double *d=dynamic_cast<Double *>(niza[i]);
            if(d)
            {
                if(niza[i]->doubleValue()>min)
                {
                    niza[i]->print();
                    count++;
                }
            }
        }
        if(count == 0)
        {
            cout<<"None"<<endl;
        }
    }
};
int main ()
{
int n;
	cin>>n;
	Numbers numbers;
	for (int i=0;i<n;i++){
		int type;
		double number;
		cin>>type>>number;
		if (type==0){//Integer object
			Integer * integer = new Integer((int) number);
			numbers+=integer;
		}
		else {
			Double * doublee = new Double(number);
			numbers+=doublee;
		}
	}

	int lessThan;
	double biggerThan;

	cin>>lessThan;
	cin>>biggerThan;

	cout<<"STATISTICS FOR THE NUMBERS\n";
	numbers.statistics();
	cout<<"INTEGER NUMBERS LESS THAN "<<lessThan<<endl;
	numbers.integersLessThan(Integer(lessThan));
	cout<<"DOUBLE NUMBERS BIGGER THAN "<<biggerThan<<endl;
	numbers.doublesBiggerThan(Double(biggerThan));
}
