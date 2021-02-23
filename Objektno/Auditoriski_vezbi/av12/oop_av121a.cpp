#include <iostream>
using namespace std;
class Osnovna {
public:
    Osnovna() { cout << "Konstruiram objekt od Osnovna\n";}
    // ова е деструктор:
    ~Osnovna() { cout << "Unishtuvam objekt od Osnovna\n";}
};
class Izvedena : public Osnovna
{
public:
    Izvedena() { cout << "Konstruiram objekt od Izvedena\n"; }
    ~Izvedena() { cout << "Unishtuvam objekt od Izvedena\n"; }
};
int main() {
    Osnovna *osnovnaPok = new Izvedena();
    delete osnovnaPok;
}
