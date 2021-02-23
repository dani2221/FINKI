#include <iostream>
using namespace std;

class Complex {
private:
    float real;
    float imag;
public:
    Complex(const float real = 0, const float imag = 0) {
        this->real = real;
        this->imag = imag;
    }
    Complex operator+(const Complex &c) {
        return Complex(real + c.real, imag + c.imag);
    }

    friend Complex operator-(const Complex &c1, const Complex &c2); // as global function

    Complex operator*(const Complex &c) {
        return Complex(real * c.real - imag * c.imag, imag * c.real - real * c.imag);
    }

    Complex operator/(const Complex &c) {
        float m = c.real * c.real + c.imag * c.imag;
        float r = (real * c.real - imag * c.imag) / m;
        return Complex(r, (real * c.real + imag * c.imag) / m);
    }

    Complex &operator+=(const Complex &c) {
        real += c.real;
        imag += c.imag;
        return *this;
    }

    Complex &operator-=(const Complex &c) {
        real -= c.real;
        imag -= c.imag;
        return *this;
    }
    Complex &operator*=(const Complex &c) {
        real = real * c.real - imag * c.imag;
        imag = imag * c.real - real * c.imag;
        return *this;
    }
    Complex &operator/=(const Complex &c) {
        *this = *this / c;
        return *this;
    }

    bool operator==(const Complex &c) {
        return real == c.real && imag == c.imag;
    }

    float getReal() const {
        return real;
    }
    float getImag() const {
        return imag;
    }

    Complex operator+(float n) {
        return Complex(real + n, imag);
    }
    friend Complex operator+(float n, Complex &c);

    friend ostream &operator<<(ostream &x, const Complex &c) {
        x << c.real;
        if (c.imag >= 0) {
            x << "+";
        }
        x << c.imag << "j";
        return x;
    }
};

Complex operator-(const Complex &c1, const Complex &c2) {
    return Complex(c1.real - c2.real, c1.imag - c2.imag);
}
Complex operator+(float n, Complex &c) {
    return Complex(c.real + n, c.imag);
}

int main() {
    Complex c1(2, -6);
    Complex c2(3, 5);
    Complex c = c1 + c2;
    cout << c1 << " + " << c2 << " = " << c << endl;
    c = c1 - c2;
    cout << c1 << " - " << c2 << " = " << c << endl;
    c = c1 * c2;
    cout << c1 << " * " << c2 << " = " << c << endl;
    c = c1 / c2;
    cout << c1 << " / " << c2 << " = " << c << endl;
    if (c == c1) {
        cout << "Numbers are equal" << endl;
    }

    c = c1 + 2;
    cout << c1 << " + " << 2 << " = " << c << endl;
    c = 2 + c1;
    cout << 2 << " + " << c1 << " = " << c << endl;

    return 0;
}
