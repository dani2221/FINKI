#include <iostream>
#include <cstring> // string.h math.h -> cmath
using namespace std;

struct student {
    char firstName[50];
    char lastName[50];
    int number;
    int totalPoints;
    void print() {
        cout << firstName << "\t";
        cout << lastName << "\t";
        cout << number << "\t";
        cout << totalPoints << "\n";
    }
};


void normalize(char *name, bool allUppercase = false) {
    *name = toupper(*name);
    ++name;
    while(*name) {
        if(allUppercase) {
            *name = toupper(*name);
        } else {
            *name = tolower(*name);
        }
        ++name;
    }
}

/*void normalize(char *name) {
    normalize(name, false);
}
*/
void read(student &s) {
    cin >> s.firstName;
    cin >> s.lastName;
    normalize(s.firstName);
    normalize(s.lastName);
    cin >> s.number;
    int a, b, c, d;
    cin >> a >> b >> c >> d;
    s.totalPoints = a + b + c + d;
}



void sort(student s[], int n) {
    for(int i = 0; i < n; ++i) {
        for(int j = 0; j < n - 1; ++j) {
            if(s[j].totalPoints < s[j + 1].totalPoints) {
                student tmp = s[j];
                s[j] = s[j + 1];
                s[j + 1] = tmp;
            }
        }
    }
}

int main() {
    student s[100];
    int n;
    cin >> n;
    for(int i = 0; i < n; ++i) {
        read(s[i]);
    }
    sort(s, n);
    cout << "======== ORDERED ========" << endl;
    //read(s);
    for(int i = 0; i < n; ++i) {
        s[i].print();
        //print(s[i]);
    }

    return 0;
}
