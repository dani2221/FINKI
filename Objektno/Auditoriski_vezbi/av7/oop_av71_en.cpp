#include <iostream>
#include <cstring>

using namespace std;

class TennisPlayer {
private:
    enum {STRMAX = 50};
    char name[STRMAX];
    char surname[STRMAX];
    bool playsInLeague;

public:
    TennisPlayer(const char* fn, const char* sn, bool pl);

    void print() const;

    bool PlaysInLeague();

    void setPlaysInLeague(bool pl);

    ~TennisPlayer();

    friend ostream& operator<<(ostream &o, const TennisPlayer &tp);
};

ostream& operator<<(ostream &o, const TennisPlayer &tp) {
    o << "-- Tennis Player --\n"
      << tp.name << " "
      << tp.surname << " - " << tp.playsInLeague << endl;
    return o;
}

TennisPlayer::TennisPlayer(const char* fn = "", const char* sn = "", bool pl = false) {
    cout << "Constructor:" << endl;
    strncpy(name, fn, STRMAX - 1);
    name[STRMAX - 1] = '\0';
    strncpy(surname, sn, STRMAX - 1);
    surname[STRMAX - 1] = '\0';
    playsInLeague = pl;
}

void TennisPlayer::print() const {
    cout << surname << ", " << name;
}

void TennisPlayer::setPlaysInLeague(bool pl) {
    this->playsInLeague = pl;
}

bool TennisPlayer::PlaysInLeague() {
    return this->playsInLeague;
}

TennisPlayer::~TennisPlayer() {
    cout << "Destructor TennisPlayer for: " << this->name << " " << this->surname << endl;
}

class RankedTennisPlayer : public TennisPlayer {
private:
    unsigned int rank;
public:
    RankedTennisPlayer(const char* n, const char* sn, bool pl = false, int r = 0)
        : TennisPlayer(n, sn, pl)
    {
        cout << "Constructor RankedTennisPlayer" << endl;
        this->rank = r;
    }

    RankedTennisPlayer(const TennisPlayer& t, unsigned int r = 0) : TennisPlayer(t) {
        this->rank = r;
    }

    ~RankedTennisPlayer() {
        cout << "Destructor RankedTennisPlayer\n" << endl;
    }

    friend ostream& operator<<(ostream& out, const RankedTennisPlayer &tp) {
        cout << "-- RANKED TENNIS PLAYER --\n";
        out << TennisPlayer (tp);
        out << "Rank: " << tp.rank << endl;
        return out;
    }
};

int main() {
    TennisPlayer rf("Roger", "Federer");
    TennisPlayer ng("Novak", "Djokovikj");
    cout << rf;
    cout << ng;
    //TennisPlayer t;
    RankedTennisPlayer rn("Rafael", "Nadal", true, 2750);
    cout << rn;
    TennisPlayer tp = rn;
    cout << tp;
    //RankedTennisPlayer copy(tp);
    RankedTennisPlayer copy(ng, 3320);
    cout << copy;
    return 0;
}


