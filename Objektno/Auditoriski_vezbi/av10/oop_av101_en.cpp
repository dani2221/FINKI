#include<iostream>
#include<cstring>
using namespace std;

class Card {
private:
    char id[16];
    double balance;

public:
    Card(char* id = "", double  balance = 0) {
        this->balance = balance;
        strcpy(this->id, id);
    }
    void showBalance() {
        cout << id << ": " << balance << endl;
    }

    void deposit(double  amount) {
        this->balance += amount;
    }

    virtual double withdraw(double amount) {
        if (this->balance > amount) {
            this->balance -= amount;
            return amount;
        }
        else return 0;
    }

protected:
    virtual double withdraw(double amount, double limit) {
        if (this->balance + limit > amount) {
            this->balance -= amount;
            return amount;
        }
        else return 0;
    }
};

class Maestro : public Card {
private:
    char password[10];
    const static double discount; //static member of the class

public:
    Maestro(char* password = "", char* id = "", double  balance = 0) : Card(id, balance) {
        strcpy(this->password, password);
    }

    static double  getPopust() { //static function accessing a static member
        return discount;
    }

    double withdraw(double price) {
        double  amount = price * (1 - discount); // non-static functions can use static members
        // static data members
        return Card::withdraw(amount);
    }
};
const double Maestro::discount = 0.05; // initialization of static member

class Master : public Card {
private:
    double  limit;
    const static double discount1;  // fixed discount
    static double discount2;       // discount that can be changed

public:
    Master(double  limit = 0, char* id = "", double  balance = 0) : Card(id, balance) {
        this->limit = limit;
    }

    static double getDiscount1() {
        return discount1;
    }

    static double getDiscount2() {
        return discount2;
    }

    static void setDiscount2(double  discount2) {
        Master::discount2 = discount2;
    }

    double withdraw(double price) {
        if (this->limit < 6000) {
            double  amount = price * (1 - discount1);
            return Card::withdraw(amount, limit);
        }
        else {
            double  amount = price * (1 - discount2);
            return Card::withdraw(amount, limit);
        }
    }
};
const double Master::discount1 = 0.03; // on initialization you do not write the keyword static
double Master::discount2 = 0.1;
