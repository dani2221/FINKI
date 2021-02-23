#include <iostream>
using namespace std;

template <typename T>
void mySwap(T &a, T &b) {
    T temp;
    temp = a;
    a = b;
    b = temp;
}

template <typename T>
void mySwap(T a[], T b[], int size) {
    T temp;
    for (int i = 0; i < size; ++i)
    {
        temp = a[i];
        a[i] = b[i];
        b[i] = temp;
    }
}

template <typename T>
void print(const T * const array, int size) {
    cout << "(";
    for (int i = 0; i < size; ++i)
    {
        cout << array[i];
        if (i < size - 1)
            cout << ",";
    }
    cout << ")" << endl;
}

int main() {
    const int SIZE = 3;
    int i1 = 1, i2 = 2;
    mySwap(i1, i2);
    cout << "i1 is " << i1 << ", i2 is " << i2 << endl;
    int ar1[] = {1, 2, 3};
    int ar2[] = {4, 5, 6};
    mySwap(ar1, ar2, SIZE);
    print(ar1, SIZE);
    print(ar2, SIZE);
}
