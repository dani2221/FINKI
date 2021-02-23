#include <iostream>
using namespace std;

template <typename T>
class MyComplex {
private:
    T real, imag;
public:
    MyComplex<T> (T real = 0, T imag = 0) : real(real), imag(imag) { }

    MyComplex<T> & operator+= (const MyComplex<T> & rhs) {
        real += rhs.real;
        imag += rhs.imag;
        return *this;
    }

    MyComplex<T> & operator+= (T value) {
        real += value;
        return *this;
    }

    bool operator== (const MyComplex<T> & rhs) {
        return (real == rhs.real && imag == rhs.imag);
    }

    bool operator!= (const MyComplex<T> & rhs) {
        return !(*this == rhs);
    }

    MyComplex<T>  operator++() {
        ++real;
        return *this;
    }

    MyComplex<T> operator++ (int dummy) {
        MyComplex<T>  saved(*this);
        ++real;
        return saved;
    }

    friend ostream & operator<< (ostream & out, const MyComplex<T> & c) {
        out << '(' << c.real << ',' << c.imag << ')';
        return out;
    }

    friend istream & operator>> (istream & in, MyComplex<T> & c) {
        T inReal, inImag;
        char inChar;
        bool validInput = false;
        in >> inChar;
        if (inChar == '(')
        {
            in >> inReal >> inChar;
            if (inChar == ',')
            {
                in >> inImag >> inChar;
                if (inChar == ')')
                {
                    c = MyComplex<T>(inReal, inImag);
                    validInput = true;
                }
            }
        }
if (!validInput) cout << “Vnesete go brojot vo format: (real,   imag)” << endl;
        return in;
    }

    friend  MyComplex<T> operator+ (const MyComplex<T> & lhs, const MyComplex<T> & rhs) {
        MyComplex<T> result(lhs);
        result += rhs;
        return result;
    }

    friend  MyComplex<T> operator+ (const MyComplex<T> & lhs, T value) {
        MyComplex<T> result(lhs);
        result += value;
        return result;
    }

    friend const MyComplex<T> operator+ (T value, const MyComplex<T> & rhs) {
        return rhs + value;
    }
};

int main() {
    MyComplex<double> c1(3.1, 4.2);
    cout << c1 << endl; // (3.10,4.20)
    MyComplex<double> c2(3.1);
    cout << c2 << endl; // (3.10,0.00)
    MyComplex<double> c3 = c1 + c2;
    cout << c3 << endl; // (6.20,4.20)
    c3 = c1 + 2.1;
    cout << c3 << endl; // (5.20,4.20)
    c3 = 2.2 + c1;
    cout << c3 << endl; // (5.30,4.20)
    c3 += c1;
    cout << c3 << endl; // (8.40,8.40)
    c3 += 2.3;
    cout << c3 << endl; // (10.70,8.40)
    cout << ++c3 << endl; // (11.70,8.40)
    cout << c3++ << endl; // (11.70,8.40)
    cout << c3 << endl; // (12.70,8.40)
    MyComplex<int> c5;
    cout << "Enter complex number in format (real, imag): ";
    cin >> c5;
    return 0;
}
