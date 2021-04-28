/* Потребно е да конструирате абстракна класа Shape со само еден параметар:

страна (int)
Конструктори:

Shape()
Shape(int a)
И виртуелните методи:

double plostina()
void pecati()
int getType()
Од таа класа вие треба да конструирате 3 изведени класи:

Square
Circle
Triangle
Изведените класи немаат дополнителни парамтери, туку ја наследуваат страната од Shape

Конструктори:

Конструкторот на изведените класи ќе зема еден аругмент, страната на фигурата.

При пишување на конструкторот на изведените класи да се искористи констукторот на родител класата

Методи:

Секоја од класите си има своја формула за plostina() соодветна за нивната геометриска фигура

Формула за плоштина на квадрат а * a
Формула за плоштина на круг 3.14 * a * a
Формула за плоштина на триаголник (sqrt(3)/4) * side * side
За pecati() методот упатете се кон outputot за тест случајите.

getType() методот треба да враќа:

1 за Square
2 за Circle
3 за Triangle
Поени: 5

--

Дефинирајте го методот void checkNumTypes(Shape** niza, int n) така што ќе испечати во три реда колку квадрати, кругови и триаголници има во низата.

Поени: 2

--

Покрај тоа треба да ја допишете Main класата така што ќе алоцирате динамичка низа од покажувачи кон класата Shape.

Потоа кај секој покажувач од низата зависно од дадениот input да алоцирате објект од една од трите изведени класи.

Input:

Прво се добива n - големината на динамичката низа. Потоа n пати се добиваат пар цели броеви t i a, каде што t е типот на објектот и а е страната на објектот.

Пример:

3

1 3 //квадрат со страна со должина 3

2 2 //круг со страна со должина 2

3 1 //триаголник со страна со должина 1
*/
#include<iostream>
#include<cstring>
#include<cmath>
using namespace std;
class Shape
{
protected:
    int strana;
public:
    Shape()
    {
        strana=0;
    }
    Shape(int strana)
    {
        this->strana=strana;
    }
    virtual double plostina() = 0;
    virtual void pecati() = 0;
    virtual int getType() = 0;
};
class Square : public Shape
{
public:
    Square(int strana) : Shape(strana)
    {
    }
    double plostina()
    {
        return strana*strana;
    }
    void pecati()
    {
        cout<<"Kvadrat so plostina = "<<plostina()<<endl;
    }
    int getType()
    {
        return 1;
    }
};
class Circle : public Shape
{
public:
    Circle(int strana) : Shape(strana)
    {

    }
    double plostina()
    {
        return 3.14*strana*strana;
    }
    void pecati()
    {
        cout<<"Krug so plostina = "<<plostina()<<endl;
    }
    int getType()
    {
        return 2;
    }
};
class Triangle : public Shape
{
public:
    Triangle(int strana=0) : Shape(strana)
    {

    }
    double plostina()
    {
        return (sqrt(3)/4) * strana * strana;
    }
    void pecati()
    {
        cout<<"Triagolnik so plostina = "<<plostina()<<endl;
    }
    int getType()
    {
        return 3;
    }
};
void checkNumTypes(Shape **niza,int n)
{
    int countTriangle=0,countCircle=0,countSquare=0;
    for(int i=0;i<n;i++)
    {
        if(niza[i]->getType() == 1)
        {
            countSquare++;
        }
        else if(niza[i]->getType() == 2)
        {
            countCircle++;
        }
        else if(niza[i]->getType() == 3)
        {
            countTriangle++;
        }
    }
    cout<<"Broj na kvadrati vo nizata = "<<countSquare<<endl;
    cout<<"Broj na krugovi vo nizata = "<<countCircle<<endl;
    cout<<"Broj na triagolnici vo nizata = "<<countTriangle<<endl;
}
int main ()
{
    int n,t,a;
    cin>>n;
    Shape **niza=new Shape*[n];
    for(int i=0;i<n;i++)
    {
        cin>>t>>a;
        if(t == 1)
        {
            niza[i]=new Square(a);
        }
        else if(t == 2)
        {
            niza[i]=new Circle(a);
        }
        else if(t == 3)
        {
            niza[i]=new Triangle(a);
        }
    }
    for(int i=0;i<n;i++)
    {
        niza[i]->pecati();
    }
    checkNumTypes(niza,n);
    delete[]niza;
}
