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
};

void sort(Team *teams, int n) {
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            if (strcmp(teams[i].getName(), teams[j].getName()) > 0) {
                Team temp = teams[i];
                teams[i] = teams[j];
                teams[j] = temp;
            }
        }

    }

}

int main() {

    int n;
    cin >> n;

    // pointer to a dynamically allocated array of teams
    Team *league = new Team[n];

    char name[20], city[20], stadium[30];

    for (int i = 0; i < n; i++)  {
        cin >> name >> city;
        cin.getline(stadium, 29);
        league[i] = Team(name, city, stadium);
    }
    sort(league, n);
    cout << "League teams:\n";
    for (int i = 0; i < n; i++)  {
        cout << i + 1 << " " << league[i].getName() << " (" << league[i].getCity() << ", " << league[i].getStadium() << ")" << endl;
    }

    delete [] league;

    return 0;
}
