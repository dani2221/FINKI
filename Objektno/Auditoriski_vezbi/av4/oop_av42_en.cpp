#include <iostream>
#include <cstring>
using namespace std;

class Team {
private:
    char name[100];
    int yearFounded;
    char city[50];
public:
    Team() {
    }
    Team(const char* n, int yf, const char*c) {
        strcpy(name, n);
        yearFounded = yf;
        strcpy(city, c);
    }

    bool equalTeam(Team& team) {
        return strcmp(team.name, name) == 0;
    }

    void print() {
        cout << name << " " << yearFounded << " " << city << endl;
    }
};

class Game {
private:
    Team host;
    Team guest;
    int goalsHost;
    int goalsGuest;
public:
    Game(Team& host, Team& guest, int gh, int gg) {
        this->host = host;
        this->guest = guest;
        goalsHost = gh;
        goalsGuest = gg;
    }

    bool equalsGuest(Game& game) {
        return host.equalTeam(game.guest);
    }

    bool equalHost(Game& game) {
        return guest.equalTeam(game.host);
    }

    Team winner(Game game) {
        int goals1 = goalsHost + game.goalsGuest;
        int goals2 = goalsGuest + game.goalsHost;
        if(goals1 > goals2) {
            return host;
        } else {
            return guest;
        }
    }
};

Team winner(Game& game1, Game& game2) {
    if(game1.equalHost(game2) && game1.equalsGuest(game2)) {
        return game1.winner(game2);
    } else {
        return Team("Dummy", 0, "");
    }
}

int main() {
    Team t1("Barcelona", 1900, "Barcelona");
    Team t2("Real Madrid", 1890, "Madrid");
    Team t3("Elche", 1950, "Elche");

    Game g1(t1, t2, 5, 0);
    Game g2(t2, t1, 2, 2);
    Game g3(t1, t3, 6, 2);

    Team w = winner(g1, g3);
    w.print();
    return 0;
}
