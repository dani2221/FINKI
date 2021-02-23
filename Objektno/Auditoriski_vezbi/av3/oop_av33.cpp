#include <iostream>
#include <cstring>
using namespace std;

class EMail {
private:
    enum {
        AddrLen = 100, SubLen = 200, BodyLen = 1000
    };
    char to[AddrLen];
    char from[AddrLen];
    char subject[SubLen];
    char body[BodyLen];
public:

    EMail(char *_to, char *_from, char *_subject, char *_body) {
        strncpy(to, _to, AddrLen - 1);
        strncpy(from, _from, AddrLen - 1);
        strncpy(subject, _subject, SubLen - 1);
        strncpy(body, _body, BodyLen - 1);
        to[AddrLen - 1] = subject[SubLen - 1] = 0;
        from[AddrLen - 1] = subject[SubLen - 1] = body[BodyLen - 1] = 0;
    }
    ~EMail() {
    }
    void setTo(char const *n) {
        strncpy(to, n, AddrLen - 1);
        to[AddrLen - 1] = 0;
    }
    void setFrom(char const *n) {
        strncpy(from, n, AddrLen - 1);
        from[AddrLen - 1] = 0;
    }
    void setSubject(char const *n) {
        strncpy(subject, n, SubLen - 1);
        subject[SubLen - 1] = 0;
    }
    void setBody(char const *n) {
        strncpy(body, n, BodyLen - 1);
        body[BodyLen - 1] = 0;
    }
    const char* getTo() { return to;    }
    const char* getFrom() { return from; }
    const char* getSubject() { return subject; }
    const char* getBody() { return body; }
    void print() {
        cout << "To: " << to << endl;
        cout << "From: " << from << endl;
        cout << "Subject: " << subject << endl;
        cout << "Body: " << body << endl;
    }
};

bool checkEmail(char* address);

int main() {
    char to[100], from[100], subject[200], body[1000];
    cout << "To: " << endl;
    cin >> to;
    if (checkEmail(to)) {
        cout << "From: " << endl;
        cin >> from;
        cout << "Subject: " << endl;
        cin >> subject;
        cout << "Body: " << endl;
        cin >> body;
        EMail poraka(to, from, subject, body);
        cout << "Sent:" << endl;
        poraka.print();
    } else {
        cout << "Invalid email address!" << endl;
    }
    return 0;
}

bool checkEmail(char *address) {
    int count = 0;
    while (*address != 0)
        if ('@' == *address++)
            count++;
    return (1 == count);
}
