#include <iostream>
#include <cstring>

#include "TenisPlayer.hpp"

using namespace std;


TenisPlayer::TenisPlayer(const char* fn="", const char* sn="", bool pl = false) {
  cout << "constructor fortuneteller" << endl;
  strncpy(name, fn, STRMAX-1);
  name[STRMAX-1] = '\0';
  strncpy(surname, sn, STRMAX-1);
  surname[STRMAX-1] = '\0';
  playsInLeague = pl;
}

void TenisPlayer::Print() const {
  cout << surname << ", " << name;
}

void TenisPlayer::setPlaysInLeague(bool pl){
  this->playsInLeague = pl;
}

bool TenisPlayer::PlaysInLeague(){
  return this->playsInLeague;
}

TenisPlayer::~TenisPlayer() {
  cout << "destructor TenisPlayer" << endl;
}

/*

class RankedTenisPlayer : public TenisPlayer {
private:
    int rank;
public:
    RankedTenisPlayer(const char* n, bool pl = false, int r = 0)
        : TenisPlayer(n, pl)
    {
        cout << "constructor RankedTenisPlayer" << endl;
        rank = r;
        playInLeague = true;
    }

    ~RankedTenisPlayer() {
        cout << "destructor RankedTenisPlayer" << endl;
    }

    friend ostream& operator<<(ostream& out, const RankedTenisPlayer &tp) {
        cout << "<< RANKED TENIS PLAYER" << endl;
        out << (TenisPlayer)tp;
        out << "Rank: " << tp.rank << endl;
        return out;
    }
};

*/

