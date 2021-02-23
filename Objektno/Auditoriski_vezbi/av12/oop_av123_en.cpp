#include <iostream>
#include <cstring>
#include <cstdlib>
using namespace std;

char randomDigit() {
    return '0' + rand() % 10;
}

char randomChar() {
    return 'A' + rand() % 26;
}

class Ticket {
protected:
    char id[50];
    int len;
public:
    virtual bool validate() const = 0;

    friend ostream& operator<<(ostream& out, const Ticket &t) {
        for(int i = 0; i < t.len; ++i) {
            out << t.id[i];
        }
        return out;
    }

    bool operator==(const Ticket& t) {
        if(len != t.len) return false;
        for(int i = 0; i < t.len; ++i) {
            if(id[i] != t.id[i]) return false;
        }
        return true;
    }
};

class DigitTicket : public Ticket {
public:
    DigitTicket(int n) {
        len = n;
        for(int i = 0; i < n; ++i) {
            id[i] = randomDigit();
        }
    }

    bool validate() const {
        int sum = 0;
        for(int i = 0; i < len; ++i) {
            sum += id[i] - '0';
        }
        return sum % 7 == 0;
    }

};

class CharTicket : public Ticket {
public:
    CharTicket(int n) {
        len = n;
        for(int i = 0; i < n; ++i) {
            id[i] = randomChar();
        }
    }

    bool validate() const {
        int sum = 0;
        for(int i = 0; i < len; ++i) {
            sum += id[i];
        }
        return sum % 3 == 0;
    }

};

int valid(Ticket** t, int n) {
    int valid = 0;
    for(int i = 0; i < n; ++i) {
        if(t[i]->validate()){
            ++valid;
        }
    }
    return valid;
}

int unique(Ticket **t, int n) {
    int duplicates = 0;
    for(int i = 0; i < n - 1; ++i) {
        for(int j = i + 1; j < n; ++j) {
            if(*t[i] == *t[j]&&i != j) {
                ++duplicates;
            }
        }
    }
    return n - duplicates;
}

int main() {
    int seed;
    cin >> seed;
    srand (seed);
    int n;
    cin >> n;
    Ticket **t = new Ticket*[n];
    cout << "===== ALL TICKETS (" << n << ") =====" << endl;
    for(int i = 0; i < n; ++i) {
        int x;
        cin >> x;
        if(i % 2 == 0) {
        	t[i] = new DigitTicket(x);
        } else {
            t[i] = new CharTicket(x);
        }
        cout << *t[i] << endl;
    }
    cout << "===== VALID =====" << endl;
    cout << valid(t, n) << endl;

    cout << "===== UNIQUE =====" << endl;
    cout << unique(t, n) << endl;

    for(int i = 0; i < n; ++i) {
		delete t[i];
    }
    delete [] t;
    return 0;
}
