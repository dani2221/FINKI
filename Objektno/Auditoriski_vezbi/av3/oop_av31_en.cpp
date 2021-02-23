#include <iostream>
#include <cmath>
using namespace std;

class Triangle {
private:
    int a, b, c;
public:
    // Constructor
    Triangle(int x, int y, int z) {
        a = x;
        b = y;
        c = z;
    }
    // Destructor
    ~Triangle() {
    }

    int permeter() {
        return a + b + c;
    }

    float area() {
        float s = (a + b + c) / 2;
        return sqrt(s * (s - a) * (s - b) * (s - c));
    }
};
int main() {
    int a, b, c;
    cin >> a >> b >> c;
    Triangle t(a, b, c);
    cout << "Area: " << t.area() << endl;
    cout << "Permeter: " << t.permeter() << endl;
    return 0;
}
