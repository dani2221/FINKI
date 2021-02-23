
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

  ~TenisPlayer();
};

#endif /* End Header Guard */
