#include <iostream>
using namespace std;
// tag::class[]
class Cuboid {
private:
	int a, b, c;
public:
	// Default constructor
	Cuboid(){
		cout << "Default constructor" << endl;
	}
	// Constructor with arguments
	Cuboid(int aa, int bb, int cc) {
		a = aa;
		b = bb;
		c = cc;
		cout << "Constructor" << endl;
	}
	~Cuboid() {	cout << "Destructor" << endl; }
	// Method for computing area
	int area() {
		return 2 * (a * b + a * c + b * c);
	}
	// Method for computing volume
	int volume() {
		return a * b * c;
	}
};
// end::class[]
int main() {
  // tag::instance[]
	Cuboid c(10, 15, 20);
	// end::instance[]
	cout << "Area is: " << c.area() << endl;
	cout << "Volumenot e: " << c.volume() << endl;
	return 0;
}
