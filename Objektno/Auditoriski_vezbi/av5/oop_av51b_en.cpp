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
    // get methods as const functions
    // they do not mutate the state of the class
    const char *getName() const {
        return name;
    }
    const char *getCity() const {
        return city;
    }
    const char *getStadium() const {
        return stadium;
    }
    void setName(char *name) {
        strcpy(this->name, name);
    }
    ~Team() {}
};

class Game {
private:
    Team *home, *away;
    int goalsHome, goalsAway;

public:
    Game(const Team &d, const Team &g, int gHome, int gAway) {
        home = new Team(d);
        away = new Team(g);
        goalsHome = gHome;
        goalsAway = gAway;
    }
    Game(const Game& n) {
        home = new Team(*n.home);
        away = new Team(*n.away);

        goalsHome = n.goalsHome;
        goalsAway = n.goalsAway;
    }
    // returns pointer of the home team
    Team* getHome() {
        return home;
    }
    // returns const pointer
    const Team* getAway() {
        return away;
    }
    int getGoalsHome() {
        return goalsHome;
    }
    int getGoalsAway() {
        return goalsAway;
    }
    ~Game() {
        cout << "\ndestructor" << endl;
        delete home;
        delete away;
    }
    // friend function
    friend bool isPick(Game n, char tip);
};

bool  isPick(Game n, char tip) {
    if (n.goalsHome == n.goalsAway && tip == 'X') return true;
    else if (n.goalsHome > n.goalsAway && tip == '1') return true;
    else if (n.goalsHome < n.goalsAway && tip == '2') return true;
    else return false;
}

int main() {

    Team e1("Real Madrid", "Madrid", "Santiago Bernabeu");
    Team e2("FC Barcelona", "Barcelona", "Camp Nou");

    Game first(e1, e2, 1, 3);

    cout << "Enter pick for the game: ";
    cout << first.getHome()->getName(); //getName - const function
    cout << "-";
    cout << first.getAway()->getName();
    cout << endl;


    char tip; //1, 2 ili X
    cin >> tip;

    if (isPick(first, tip)) cout << "You won!";
    else cout << "You lost!";

    first.getHome()->setName("RLM"); // possible
    //first.getAway().setName("BAR"); // not possible :getAway returns const pointer

    cout << "\nGame between: ";
    cout << first.getHome()->getName();
    cout << "-";
    cout << first.getAway()->getName();

    return 0;
}
