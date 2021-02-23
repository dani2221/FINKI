#include <iostream>
using namespace std;
class Base {
public:
    Base() { cout << "Constructor of Base\n";}
    // this is the destructor
    virtual ~Base() { cout << "Destructor of Base\n";}
};

class Derived : public Base
{
public:
    Derived() { cout << "Constructor of Derived\n"; }
    ~Derived() { cout << "Destructor of Derived\n"; }
};

int main() {
    Base *basePointer = new Derived();
    delete basePointer;
}
