#include<iostream>
#include<cstring>
using namespace std;

class HotelReservation {
protected:
    int days;
    int persons;
    char name[50];
    char surname[50];

public:
    HotelReservation(char *name, char *surname, int days, int persons) {
        strcpy(this->name, name);
        strcpy(this->surname, surname);
        this->days = days;
        this->persons = persons;
    }

    virtual int getPrice() {
        return days * persons * 25;
    }
    virtual int getPrice(int payment) {
        if (payment >= getPrice())
            return payment - getPrice();
        else {
            cout << "You should pay " << getPrice() << endl;
            return -1;
        }
    }
};


class BBHotelReservation: public HotelReservation {
public:
    BBHotelReservation(char *name, char *surname, int days, int
                                   persons) : HotelReservation(name, surname, days, persons) {}

    //overriding getPrice(int payment)
    int getPrice(int payment) {
        int price = HotelReservation::getPrice() + persons * 5; // пристап до protected податокот persons
        if (payment >= price)
            return payment - price;
        else {
            cout << "You should pay: " << price << endl;
            return -1;
        }
    }
};

class Hotel {
private:
    char name[50];
    int balance;
public:
    Hotel(char *name) {
        strcpy(this->name, name);
        balance = 0;
    }
    // reference of the base class that can reference objects from the derived classes
    int payForReservation(HotelReservation &hr, int payment) {
        int change = hr.getPrice(payment); // polymorphism
        // what definition of getPrice is going to be called?
        // important: getPrice() is virtual function
        if (change != -1)
            balance += payment - change;
        return change;
    }
};

int main() {
    Hotel h("Bristol");
    HotelReservation *hr1 = new HotelReservation("Petko", "Petkovski", 5, 5);
    int price = h.payForReservation(*hr1, 1000);
    if (price != -1)
        cout << "Change : " << price << endl;
    BBHotelReservation *hr2 =
        new BBHotelReservation("Risto", "Ristovski", 5, 5);
    price = h.payForReservation(*hr2, 1000);
    if (price != -1)
        cout << "Change : " << price << endl;
    // pointer to the base class pointing to object of derived
    HotelReservation *hr3 = new BBHotelReservation("Ana", "Anovska", 4, 2);
    price = h.payForReservation(*hr3, 100);
    if (price != -1)
        cout << "Change : " << price << endl;
    BBHotelReservation hr4("Tome", "Tomovski", 5, 3);
    price = h.payForReservation(hr4, 1000);
    if (price != -1)
        cout << "Change : " << price << endl;
}
