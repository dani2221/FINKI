#include<iostream>
#include<cmath>
using namespace std;
class Shape {
protected:
    double height;
public:
    Shape (int height = 0) {
        this->height = height;
    }
    virtual void print() { //virtual function
        cout << height;
    }
    virtual double getVolume() = 0; //pure virtual function
    double getHeight() const {
        return height;
    }
};

class Cylinder: public Shape {
private:
    double radius;
public:
    //  constructor
    Cylinder(double radius, double height): Shape(height) {
        this->radius = radius;
    }
    // overriding function print()
    void print() {
        cout << "Cylinder with height ";
        Shape::print();
        cout << " and radius of tha basis" << radius << endl;
    }
    // overriding function getVolume()
    double getVolume() {
        return M_PI * radius * radius * getHeight();
    }
    double getRadius() {
        return radius;
    }
};

class Cone: public Shape {
private:
    double radius;
public:
    // constructor
    Cone(double radius, double height): Shape(height) {
        this->radius = radius;
    }
    // overriding function print()
    void print() {
        cout << "Cone with height ";
        Shape::print();
        cout << " and with radius of basis " << radius << endl;
    }
    // overriding function getVolume()
    double getVolume() {
        return M_PI * radius * radius * getHeight() / 3.0;
    }
    double getRadius() {
        return radius;
    }
};


class Cuboid: public Shape {
private:
    double a, b;
public:
    // constructor
    Cuboid(double a, double b, double height): Shape(height) {
        this->a = a;
        this->a = b;
    }
    // overriding function print()
    void print() {
        cout << "Cuboid with height ";
        Shape::print();
        cout << "and with basis " << this->a << " i " << this->b << endl;
    }
    // overriding function getVolume()
    double getVolume() {
        return a * b * getHeight();
    }
};
void maxVolume(Shape *shapes[], int n );
double getRadius (Shape *s);

int main() {
    Shape** shapes; // dynamically allocated array of pointers of Shape
    int n;
    cin >> n; // number of elements in the array
    shapes = new Shape*[n]; // allocate the pointer array
    for (int i = 0 ; i < n ; i++) {
        int r, a, b, h, type;
        cout << "Shape: 1-cylinder 2-cone 3-cuboid" << endl;
        cin >> type;
        if (type == 1) { // for Cylinder
            cin >> r >> h; shapes[i] = new Cylinder(r, h);
        } else if (type == 2) { // for Cone
            cin >> r >> h; shapes[i] = new Cone(r, h);
        } else if (type == 3) { // for Cuboid
            cin >> a >> b >> h; shapes[i] = new Cuboid(a, b, h);
        }
    }
    // 1.
    maxVolume(shapes, n);
    // 2.
    int counter = 0;
    for (int i = 0 ; i < n ; i++)
        if (getRadius(shapes[i]) == -1)
            counter++;
    cout << "Number of shapes with base circle is " << counter;
}
void maxVolume(Shape *shapes[], int n)
{
    int max = 0;
    int maxIndex = 0;
    for (int i = 0 ; i < n ; i++)
    {
        if (shapes[i]->getVolume() > max) {
            // call virtual function getVolume()
            max = shapes[i]->getVolume();
            maxIndex = i;
        }
    }
    cout << "Shape with max volume is:";
    shapes[maxIndex]->print(); // virtual call of function print()
}
double getRadius(Shape *g) {
    // runtime cast
    Cylinder* c = dynamic_cast<Cylinder *>(g);
    if (c != 0) { // if cast fails
        return c->getRadius();
    }
    // runtime cast
    Cone* k = dynamic_cast<Cone *>(g);
    if (k != 0 ) { // if cast fails
        return k->getRadius();
    }
    return -1; // if g is not a pointer to Cylinder or Cone return -1
}
