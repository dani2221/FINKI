#include <iostream>
#include <cstring>
using namespace std;

enum gender {
    male,
    female,
    other
};

class Mate {
private:
    char name[100];
    int age;
    gender gen;
public:
    Mate() {}

    Mate(const char *n, int a, gender g) {
        strcpy(name, n);
        age = a;
        gen = g;
    }

    int getNameNumber() {
        int sum = 0;
        char *n = name;
        while(*n) {
            sum += *n;
            ++n;
        }
        return sum;
    }

    
};

class Date {
private:
    Mate m1;
    Mate m2;
public:

    Date(Mate& _m1, Mate& _m2) {
        m1 = _m1;
        m2 = _m2;
    }

    bool isSuccess() {
        return (m1.getNameNumber() + m2.getNameNumber()) % 2 == 0;
    }
};


int main() {
    Mate m1("Barby", 20, female);
    Mate m2("Ken", 25, male);
    Mate m3("Sam", 23, other);

    Date date(m1, m2);
    if(date.isSuccess()) {
        cout << "The date was success, lets make another one..." << endl;
    } else {
        cout << "This did not work out, good bye!" << endl;
    }

    return 0;

}