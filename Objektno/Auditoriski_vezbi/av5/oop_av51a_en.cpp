#include<iostream>
#include<cstring>
using namespace std;

class Team {
private:
    char name[20];
    char city[20];
    char stadium[30];
public:
    Team(char *name = "", char *city = "", char *stadium = "") {
        strcpy(this->name, name);
        strcpy(this->city, city);
        strcpy(this->stadium, stadium);
    }
    Team(const Team &e) {
        strcpy(name, e.name);
        strcpy(city, e.city);
        strcpy(stadium, e.stadium);
    }
    const char *getName() {
        return name;
    }
    const char *getCity() {
        return city;
    }
    const char *getStadium() {
        return stadium;
    }
    void setName(char *name) {
        strcpy(this->name, name);
    }
    ~Team() {}
};

int main() {

    Team *e1 = new Team("Real Madrid", "Madrid", "Santiago Bernabeu");
    Team *e2 = new Team(*e1);

    cout << "Teams are: ";
    cout << e1->getName();
    cout << "-";
    cout << e2->getName();

    //e1->getName()->setName("Barselona"); // error
    e1->setName("Barselona");

    cout << "\nAfter the change teams are: ";
    cout << e1->getName();
    cout << "-";
    cout << e2->getName();

    delete e1;
    delete e2;

    return 0;
}
