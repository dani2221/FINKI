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

    // asignment operator =
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

    void print () {
        for (int i = 0; i < size; ++i) {
            cout << x[i] << " ";
        }
        for (int i = size; i < capacity; ++i) {
            cout << "- ";
        }

        cout << endl;
    }
    void change(int n, int m) {
        for (int i = 0; i < size; ++i) {
            if (x[i] == n) x[i] = m;
        }
    }

    void deleteAll(int n) {
        int newSize = 0;
        for (int i = 0, j = 0; i < size; ++i)
            if (x[i] != n) {
                x[j++] = x[i];
                newSize++;
            }
        size = newSize;
    }

    void add(int n) {
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
    }
};

int main() {
    Array a;
    a.add(6);
    a.add(4);
    a.add(3);
    a.add(2);
    a.add(1);

    Array b(a);
    Array c;
    c = a;

    b.add(2);
    b.change(2, 6);
    c.deleteAll(6);

    cout << " a: ";
    a.print();
    cout << " b: ";
    b.print();
    cout << " c: ";
    c.print();
    return 0;
}
