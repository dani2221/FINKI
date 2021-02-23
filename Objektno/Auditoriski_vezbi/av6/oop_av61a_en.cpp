#include <iostream>
using namespace std;

class Array {
private:
    int *x;
    int size;
    int capacity;
public:
    Array(const int capacity = 5) {
        x = new int[capacity];
        size = 0;
        this->capacity = capacity;
    }

    // copy constructor
    Array(const Array &a) {
        size = a.size;
        capacity = a.capacity;
        x = new int[capacity];
        for (int i = 0; i < size; ++i) {
            x[i] = a.x[i];
        }
    }

    // assignment operator =
    Array& operator=(const Array &a) {
        if (this == &a) return *this;
        size = a.size;
        capacity = a.capacity;
        delete [] x;
        x = new int[capacity];
        for (int i = 0; i < size; ++i) {
            x[i] = a.x[i];
        }
        return *this;
    }

    // destructor
    ~Array() {
        delete [] x;
    }
    int getSize() {
        return size;
    }

    int getCapacity() {
        return capacity;
    }

    const int *getX() {
        return x;
    }

    Array& operator+=(int n) {
        if (capacity == size) {
            int *y = new int[2 * capacity];
            for (int i = 0; i < size; ++i) {
                y[i] = x[i];
            }
            delete [] x;
            x = y;
            capacity = capacity * 2;
        }
        x[size] = n;
        size++;
        return *this;
    }

    Array& operator-=(int n) {
        int newSize = 0;
        for (int i = 0, j = 0; i < size; ++i)
            if (x[i] != n) {
                x[j++] = x[i];
                newSize++;
            }
        size = newSize;
        return *this;
    }
  //friend ostream & operator<<(ostream &o, Array &a);
};

ostream& operator<<(ostream &o, Array &a) {
    for (int i = 0; i < a.getSize(); ++i) {
        o << a.getX()[i] << " ";
    }
    for (int i = a.getSize(); i < a.getCapacity(); ++i) {
        o << "- ";
    }
    o << endl;
    return o;
}
int main() {

    Array a;
    a += 6;
    a += 4;
    a += 3;
    a += 2;
    a += 1;

    Array b(a);

    b -= 2;
    b -= 3;

    cout << " a: " << a;
    cout << " b: " << b;

    return 0;
}
