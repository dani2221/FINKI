// NBAPlayer
// Да се дефинира класа NBAPlayer за која ќе се чуваат:
// динамички алоцирана низа од карактери за името на играчот
// низа од максимум 40 карактери за тимот во кој играчот моментално настапува
// просечен број на поени на играчот оваа сезона (double)
// просечен број на асистенции на играчот оваа сезона (double)
// просечен број на скокови на играчот оваа сезона (double)
// За потребите на класата да се дефинираат:
// default конструктор и конструктор со аргументи
// copy constructor и оператор =
// деструктор
// метод rating() кој го враќа рејтингот на кошаркарот кој се пресметува како:
// 45% од поените + 30% од асистенциите + 25% од скоковите
// метод print() кој го печати играчот во следниот формат:
// Име - тим
// Points: поени
// Assists: асистенции
// Rebounds: скокови
// Rating: рејтинг
// AllStarPlayer
// Од претходната класа NBAPlayer да се изведе класата AllStarPlayer за која дополнително ќе се чуваат и:
// просечен број на поени на играчот од All Star натпреварите (double)
// просечен број на асистенции на играчот од All Star натпреварите (double)
// просечен број на скокови на играчот од All Star натпреварите (double)
// За потребите на класата да се дефинираат:
// default конструктор
// конструктор кој прима објект од NBAPlayer и плус додатните информации (погледни main)
// конструктор кој ги прима сите аргументи (погледни main)
// copy constructor, оператор =, деструктор
// метод allStarRating() кој го враќа рејтингот на кошаркарот од All Star натпреварите и кој се пресметува како:
// 30% од поените + 40% од асистенциите + 30% од скоковите
// Да се препокријат методите:
// rating() кој го враќа просекот од обичниот рејтинг на кошаркарот и неговиот All Star рејтинг
// print() кој покрај основните информации за кошаркарот печати и:
// All Star Rating: рејтингот од All Star натпреварите
// New Rating: просечниот рејтинг

#include<iostream>
#include<cstring>
using namespace std;
class NBAPlayer
{
protected:
    char *ime;
    char tim[40];
    double poeni;
    double asistencii;
    double skokovi;
 
    void copy(const NBAPlayer &p)
    {
        ime=new char[strlen(p.ime)+1];
        strcpy(ime,p.ime);
        strcpy(tim,p.tim);
        poeni=p.poeni;
        asistencii=p.asistencii;
        skokovi=p.skokovi;
    }
public:
    NBAPlayer()
    {
        ime=new char[0];
        strcpy(tim,"");
        poeni=0;
        asistencii=0;
        skokovi=0;
    }
    NBAPlayer(const char *ime,const char *tim,double poeni,double asistencii,double skokovi)
    {
        this->ime=new char[strlen(ime)+1];
        strcpy(this->ime,ime);
        strcpy(this->tim,tim);
        this->poeni=poeni;
        this->asistencii=asistencii;
        this->skokovi=skokovi;
    }
    NBAPlayer(const NBAPlayer &p)
    {
        copy(p);
    }
    NBAPlayer&operator=(const NBAPlayer &p)
    {
        if(this!=&p)
        {
            delete[]ime;
            copy(p);
        }
        return *this;
    }
    ~NBAPlayer()
    {
        delete[]ime;
    }
    double rating()
    {
        return((poeni*0.45) + (asistencii*0.3) + (skokovi*0.25));
    }
    void print()
    {
        cout<<ime<<" - "<<tim<<endl;
        cout<<"Points: "<<poeni<<endl;
        cout<<"Assists: "<<asistencii<<endl;
        cout<<"Rebounds: "<<skokovi<<endl;
        cout<<"Rating: "<<rating()<<endl;
    }
};
class AllStarPlayer : public NBAPlayer
{
private:
    double poeniAllStar;
    double asistenciiAllStar;
    double skokoviAllStar;
 
    void copy(const AllStarPlayer &ap)
    {
        poeniAllStar=ap.poeniAllStar;
        asistenciiAllStar=ap.asistenciiAllStar;
        skokoviAllStar=ap.skokoviAllStar;
    }
public:
    AllStarPlayer() : NBAPlayer()
    {
        poeniAllStar=0;
        asistenciiAllStar=0;
        skokoviAllStar=0;
    }
    AllStarPlayer(const NBAPlayer &p,double poeniAllStar,double asistenciiAllStar,double skokoviAllStar) : NBAPlayer(p)
    {
        this->poeniAllStar=poeniAllStar;
        this->asistenciiAllStar=asistenciiAllStar;
        this->skokoviAllStar=skokoviAllStar;
    }
    AllStarPlayer(const char *ime,const char*tim,double poeni,double asistencii,double skokovi,
                  double poeniAllStar,double asistenciiAllStar,double skokoviAllStar) : NBAPlayer(ime,tim,poeni,asistencii,skokovi)
                  {
                        this->poeniAllStar=poeniAllStar;
                        this->asistenciiAllStar=asistenciiAllStar;
                        this->skokoviAllStar=skokoviAllStar;
                  }
    AllStarPlayer(const AllStarPlayer &ap) : NBAPlayer(ap)
    {
        copy(ap);
    }
    AllStarPlayer&operator=(const AllStarPlayer &ap)
    {
        if(this!=&ap)
        {
            delete[]ime;
            NBAPlayer::copy(ap);
            copy(ap);
        }
        return *this;
    }
    double allStarRating()
    {
        return ((poeniAllStar*0.3)+(asistenciiAllStar*0.4)+(skokoviAllStar*0.3));
    }
    double rating()
    {
        return(NBAPlayer::rating() + allStarRating())/2;
    }
    void print()
    {
        NBAPlayer::print();
        cout<<"All Star Rating: "<<allStarRating()<<endl;
        cout<<"New Rating: "<<rating()<<endl;
    }
 
};
int main ()
{
  char name[50];
  char team[40];
  double points;
  double assists;
  double rebounds;
  double allStarPoints;
  double allStarAssists;
  double allStarRebounds;
 
  NBAPlayer * players = new NBAPlayer[5];
  AllStarPlayer * asPlayers = new AllStarPlayer[5];
  int n;
  cin >> n;
 
  if (n == 1) {
 
    cout << "NBA PLAYERS:" << endl;
    cout << "=====================================" << endl;
    for (int i = 0; i < 5; ++i) {
      cin >> name >> team >> points >> assists >> rebounds;
      players[i] = NBAPlayer(name,team,points,assists,rebounds);
      players[i].print();
    }
  }
  else if (n == 2) {
 
    for (int i=0; i < 5; ++i){
      cin >> name >> team >> points >> assists >> rebounds;
      cin >> allStarPoints >> allStarAssists >> allStarRebounds;
      players[i] = NBAPlayer(name,team,points,assists,rebounds);
      asPlayers[i] = AllStarPlayer(players[i],allStarPoints,allStarAssists,allStarRebounds);
    }
 
    cout << "NBA PLAYERS:" << endl;
    cout << "=====================================" << endl;
    for (int i=0; i < 5; ++i)
      players[i].print();
 
    cout << "ALL STAR PLAYERS:" << endl;
    cout << "=====================================" << endl;
    for (int i=0; i < 5; ++i)
      asPlayers[i].print();
 
    }
    else if (n == 3) {
 
      for (int i=0; i < 5; ++i){
        cin >> name >> team >> points >> assists >> rebounds;
        cin >> allStarPoints >> allStarAssists >> allStarRebounds;
        asPlayers[i] = AllStarPlayer(name, team, points, assists, rebounds,
                                     allStarPoints,allStarAssists,allStarRebounds);
      }
      cout << "ALL STAR PLAYERS:" << endl;
      cout << "=====================================" << endl;
      for (int i=0; i < 5; ++i)
        asPlayers[i].print();
 
    }
 
    delete [] players;
    delete [] asPlayers;
}