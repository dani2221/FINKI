#include <iostream>
#include <cstring>
using namespace std;

struct city {
    char name[100];
    int population;
};

struct country {
    char name[100];
    char president[100];
    city capital;
    int population;
    void print() {
        cout << name << "\t" << president << "\t";
        cout << capital.name << "\t";
        cout << capital.population << "\t";
        cout << population << endl;
    }
};

void print(country &c) {
    cout << c.name << "\t" << c.president << "\t";
    cout << c.capital.name << "\t";
    cout << c.capital.population << "\t";
    cout << c.population << endl;
}

void read(int n, country c[]) {
    for (int i = 0; i < n; ++i) {
        cin >> c[i].name;
        cin >> c[i].president;
        cin >> c[i].capital.name;
        cin >> c[i].capital.population;
        cin >> c[i].population;
    }
}

int sum(int a = 0, int b = 5) {
    return a + b;
}

void maxCapitalPopulation(int n, country c[]) {
    country max = c[0];
    for (int i = 1; i < n; ++i) {
        if (c[i].capital.population > max.capital.population) {
            max = c[i];
        }
    }

    cout << max.president << endl;
}

int main() {
    country countries[100];
    country &first = countries[0];
    first = countries[1];
    int n;
    cin >> n;
    read(n, countries);

    for (int i = 0; i < n; ++i) {
        //print(countries[i]);
        countries[i].print();
    }

    maxCapitalPopulation(n, countries);
/*    cout << sum(3, 10) << endl;
    cout << sum(10) << endl;
    cout << sum() << endl;*/


    return 0;
}
