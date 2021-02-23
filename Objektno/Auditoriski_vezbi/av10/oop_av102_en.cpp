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

class Register {
private:
    double  cashBalance;
    double  cardBalance;
    int day, month, year;

public:

    Register(double  cashBalance, int day, int month, int year) {
        this->cashBalance = cashBalance;
        this->cardBalance = 0;
        this->day = day;
        this->month = month;
        this->year = year;
    }

    void pay(double amount) {
        this->cashBalance += amount;
    }

    void pay(double amount, Card &c) {
        this->cardBalance += c.pay(amount);
    }

    void show() {
        cout << "Day: \t" << day << endl;
        cout << "Month: \t" << month << endl;
        cout << "Year: " << year << endl;
        cout << "Balance: " << this->balance() << endl;
        cout << endl;
    }

    double  balance() {
        return this->cardBalance + this->cashBalance;
    }
};

int main() {
    Register daily(10000, 22, 4, 2014);
    Card *k;
    daily.show();

    cout << "Paying in cash!" << endl;
    daily.pay(5000);
    daily.show();

    k = new Master(10000.00, "1234567890123456", 54000.00);
    cout << "Paying with card!" << endl;
    daily.pay(10000.00, *k);
    daily.show();

    k = new Maestro("password", "1234567890123456", 54000.00);
    cout << "Paying with card!" << endl;
    daily.pay(10000, *k);
    daily.show();

    Master::setPopust2(0.07);
    k = new Master(10000, "4567891234567890", 3000);
    cout << "Paying with card!" << endl;
    daily.pay(10000, *k);
    daily.show();
    return 0;
}

