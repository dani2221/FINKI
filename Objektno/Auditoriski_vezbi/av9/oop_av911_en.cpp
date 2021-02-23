#include <iostream>
using namespace std;

class Vehicle {
public:
    Vehicle() {
        cout << "Vehicle Constructor" << endl;
    }
    virtual ~Vehicle() {
        cout << "Vehicle Destructor" << endl;
    }
    virtual void accelerate() const {
        cout << "Vehicle Accelerating" << endl;
    }
    void setAcceleration(double a) {
        acceleration = a;
    }
    double getAcceleration() const {
        return acceleration;
    }
protected:
    double acceleration;
};

class Car : virtual public Vehicle {
public:
    Car() {
        cout << "Car Constructor" << endl;
    }
    virtual void accelerate() const {
        cout << "Car Accelerating" << endl;
    }
    virtual void drive() const {
        cout << "Car Driving" << endl;
    }
    virtual ~Car() {
        cout << "Car Destructor" << endl;
    }
};

class Jet : virtual public Vehicle {
public:
    Jet() {
        cout << "Jet Constructor" << endl;
    }
    virtual ~Jet() {
        cout << "Jet Destructor" << endl;
    }
    virtual void fly() const {
        cout << "Jet flying" << endl;
    }
};
class JetCar : public Car, public Jet {
public:
    JetCar() {
        cout << "JetCar Constructor" << endl;
    }
    virtual ~JetCar() {
        cout << "JetCar Destructor" << endl;
    }
    virtual void drive() const {
        cout << "JetCar driving" << endl;
    }
    virtual void fly() const {
        cout << "JetCar flying" << endl;
    }
};

void analyzeCarPerformance(Car *testVehicle) {
    testVehicle->drive();
    // the function drive() can be called with pointer of the base and pointer to the
    // derived class. This function is defined in both classes
}
void analyzeJetPerformance(Jet *testVehicle) {
    testVehicle->fly();
    //fly() is defined in the base and in the derived class (Јет and JetCar)
}
int main() {
    Car myCar;
    Jet myJet;
    JetCar myJetCar;
    cout << endl << endl;
    cout << "Car testing in progress" << endl;
    analyzeCarPerformance(&myCar);
    analyzeCarPerformance(&myJetCar);
    cout << "Jet testing in progress" << endl;
    analyzeJetPerformance(&myJet);
    analyzeJetPerformance(&myJetCar);
    cout << endl << endl;
    return 0;
}
