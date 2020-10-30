#include <iostream>
#define PI 3.1415

int main() {
	float r;
	float P = 0, L = 0;
	cout << "Enter the radius of the circle: ";
	cin >> r;
	L = 2 * r * PI;
	P = r * r * PI;
	cout << "P = " << P << endl;
	cout << "L = " << L << endl;
	return 0;
}
