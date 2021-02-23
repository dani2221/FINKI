
#ifndef TENISPLAYER_H /* Header Guard */

#define TENISPLAYER_H

class TenisPlayer {
private:
  enum {STRMAX = 50};
  char name[STRMAX];
  char surname[STRMAX];
  bool playsInLeague;

public:
  TenisPlayer(const char* fn, const char* sn, bool pl);

  void Print() const;

  bool PlaysInLeague();

  void setPlaysInLeague(bool pl);

/*    TenisPlayer& operator=(const TenisPlayer &tp) {
        cout << "operator= TenisPlayer" << endl;
        if(this == &tp) return *this;
        strcpy(name, tp.name);
        playInLeague = tp.playInLeague;
        return *this;
    }*/

    friend ostream& operator<<(ostream& out, const TenisPlayer &tp) {
        cout << "<< TENIS PLAYER" << endl;
        out << tp.name;
        if(tp.playsInLeague) {
            out << " PLAY IN LEAGUE";
        }
        out << endl;
        return out;
    }

  ~TenisPlayer();
};

#endif /* End Header Guard */
