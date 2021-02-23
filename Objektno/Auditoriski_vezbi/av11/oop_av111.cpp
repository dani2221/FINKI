#include <iostream>
using namespace std;

template <typename T>
void mySwap(T &a, T &b) {
    T temp;
    temp = a;
    a = b;
    b = temp;
}

int main() {
    int i1 = 1, i2 = 2;
    mySwap(i1, i2); // се генерира mySwap(int &, int &)
    cout << "i1 is " << i1 << ", i2 is " << i2 << endl;
    char c1 = 'a', c2 = 'b';
    mySwap(c1, c2); // се генерира mySwap(char &, char &)
    cout << "c1 is " << c1 << ", c2 is " << c2 << endl;
    double d1 = 1.1, d2 = 2.2;
    mySwap(d1, d2); // се генерира mySwap(double &, double &)
    cout << "d1 is " << d1 << ", d2 is " << d2 << endl;
    mySwap(i1, d1); // грешка 'mySwap(int&, double&)'
    return 0;
}
