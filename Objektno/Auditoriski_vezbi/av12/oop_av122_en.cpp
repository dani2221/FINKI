#include <iostream>
#include <cstring>
using namespace std;

class InvalidTeamName : exception {
    char msg[100];
public:
    InvalidTeamName(const char* name) {
        strcpy(msg, name);
    }
    const char* what() {
        return msg;
    }
};

class Goal {
private:
    char* name;
    int minute;
    char team[50];
    void copy(const Goal &g) {
        name = new char[strlen(g.name)];
        strcpy(name, g.name);
        minute = g.minute;
        strcpy(team, g.team);
    }
public:
    Goal() {
        name = NULL;
    }
    Goal(const char* n, int m, const char* t) {
        name = new char[strlen(n)];
        strcpy(name, n);
        minute = m;
        strcpy(team, t);
    }

    Goal(const Goal &g) {
        copy(g);
    }

    Goal& operator=(const Goal& g) {
        if(&g == this) return *this;
        delete [] name;
        copy(g);
        return *this;
    }

    friend ostream& operator<<(ostream& out, const Goal& g) {
        out << g.minute << " " << g.name;
        return out;
    }

    Goal operator++(int) {
        Goal g = *this;
        ++minute;
        return g;
    }

    Goal& operator--() {
        --minute;
        return *this;
    }

    char* getTeam() {
        return team;
    }
};

class Game {
private:
    Goal* goals;
    char teamHome[50];
    char teamGuest[50];
    int n;
    void copy(const Game& game) {
      goals = new Goal[game.n];
      n = game.n;
      for(int i = 0; i < n; ++i) {
        goals[i] = game.goals[i];
      }
      strcpy(teamHome, game.teamHome);
      strcpy(teamGuest, game.teamGuest);
    }
public:
    Game(const char* t1, const char* t2) {
        goals = NULL;
        n = 0;
        strcpy(teamHome, t1);
        strcpy(teamGuest, t2);
    }

    Game(const Game& game) {
        copy(game);
    }

    Game& operator=(const Game& game) {
        if(this == &game) return *this;
        delete [] goals;
        copy(game);
        return *this;
    }

    Game& operator+=(Goal &g) {
        if(strcmp(g.getTeam(), teamHome) != 0&&strcmp(g.getTeam(), teamGuest)) {
            throw InvalidTeamName(g.getTeam());
        }
        Goal* temp = goals;
        goals = new Goal[n + 1];
        for(int i = 0; i < n; ++i) {
            goals[i] = temp[i];
        }
        delete [] temp;
        goals[n] = g;
        ++n;
        return *this;
    }

    friend ostream& operator<<(ostream &out, const Game &n) {
        out << n.teamHome << " - " << n.teamGuest << endl;
        for(int i = 0; i < n.n; ++i) {
            cout << n.goals[i] << endl;
        }
        return out;
    }
};

int main() {
    char team1[50];
    char team2[50];
    cin >> team1;
    cin >> team2;
    Game n(team1, team2);
    int x;
    cin >> x;
    char player[100];
    int m;
    for(int i = 0; i < x; ++i) {
        cin >> player;
        cin >> m;
        cin >> team1;
        Goal g(player, m, team1);
        try {
    		  n += g;
        } catch(InvalidTeamName &e) {
            cout << "Invalid team name: " << e.what() << endl;
        }
    }
    cout << n << endl;
    return 0;
}
