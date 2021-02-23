#include <iostream>
#include <cstring>
using namespace std;

class DebitAccount {
protected:
  char name[100];
  long number;
  double balance;
public:
  DebitAccount(const char *name = "----", const long number = 0,
              const double balance = 0.0) {
    strncpy(this->name, name, 99);
    this->name[99] = 0;
    this->number = number;
    this->balance = balance;
  }
  void showInfo() {
    cout << name << '\n'
         << "\t Bank No: "<< number << '\n'
         << "\t Balance: "<< getBalance() << '\n';
  }
  
  void deposit(double amount) {
    if (amount >=0 ){
      balance += amount;
    }
    else {
      cout<< "You can not add negative amount to your balance!"<<endl;
    }
  }
  
  void withdraw(double amount) {
    if (amount < 0) {
      cout<< "You can note withdraw negative amount from your account!"<<endl; 
      return;
    }

    if (amount <= balance) {
      balance -= amount; 

    } else {
      cout << "You can not withdraw more money than you have on your account.\n"
           << "Please upgrade your debut account to credit account!"<<endl;
    }
  }

  double getBalance() {
    return this->balance;
  }

  ~DebitAccount() {}
};

class CreditAccount : public DebitAccount {
private:
  double limit;
  double interest; // % percent
  double minus;
public:
  CreditAccount(const char *name="----", const long number=0,
                const double balance=0, const double limit=1000,
                const double interest=0.05, const double minus=0):
    DebitAccount(name, number, balance) {

    this->limit = limit;
    this->interest = interest;
    this->minus = minus;
  }

  void withdraw(double amount) {
    int balance = getBalance();

    if (amount <= balance){
      DebitAccount::withdraw(amount);
    }

    else if (amount <= balance + limit - minus) {
      double advance = amount - balance;
      this->minus +=advance * (1.0 + interest);

      cout<<"Minus: " << advance <<"\n"
          << "Minus with interest: " << advance*interest<<endl;

      deposit(advance);
      DebitAccount::withdraw(amount);
      
    } else {
      cout << "The bank is not giving you that much money..." << endl;
      this->showInfo();
    }
  }

  void showInfo() {
    DebitAccount::showInfo();
    cout<<"\t Limit: "<<this->limit << "\n"
        <<"\t In minus: " << this->minus << "\n"
        <<"\t Interest: " << this->interest << "%\n";
  }

  
  double getInterest() {
    return this->interest;;
  }
};

int main() {
  DebitAccount d("Pero Perovski", 6, 100000);
  CreditAccount ca("Mitko Mitkovski", 10, 5000, 1000);
  d.showInfo();
  d.deposit(50000);
  d.withdraw(600000);
  d.showInfo();
  ca.showInfo();
  ca.deposit(500);
  ca.showInfo();
  ca.withdraw(6200);
  ca.showInfo();
  return 0;
}
