#include <iostream>
#include <string.h>
#define MAX 100
using namespace std;

class Student
{
private:
    char *name;
    float average;
    int academicYear;
public:
    Student(const char* n = "", float a = 0, int ay = 0) {
        name = new char[strlen(n) + 1];
        strcpy(name, n);
        average = a;
        academicYear = ay;
    }

    Student(const Student& u) {
        name = new char[strlen(u.name) ];
        strcpy(name , u.name);
        average = u.average;
        academicYear = u.academicYear;
    }

    ~Student() {
        delete [] name;
    }

    Student& operator=(const Student& u) {
        if (this != &u) {
            delete [] name;
            name = new char[strlen(u.name)];
            strcpy(name, u.name);
            average = u.average;
            academicYear = u.academicYear;
        }
        return *this;
    }
    Student& operator++() { // prefix operator
        academicYear++;
        return *this;
    }
    Student operator++(int) { // postfix
        Student u(*this);
        academicYear++;
        return u;
    }
    float getAverage() {
        return average;
    }
    friend ostream& operator<<(ostream& o, const Student& u) {
        return o << "Name: " << u.name << ", academicYear: " << u.academicYear << ", average: " << u.average << endl;
    }
    friend bool operator>(const Student& s1, const Student& s2);
};

bool operator>(const Student& s1, const Student& s2) {
    return s1.average > s2.average;
}

class Group
{
private:
    Student* students;
    int count;
    void copy(const Group &g) {
        this -> count = g.count;
        this -> students = new Student[count];
        for (int i = 0; i < count; i ++)
            students[i] = g.students[i];
    }
public:
    Group(Student* s = 0, int c = 0) {
        count = c;
        students = new Student [count];
        for (int i = 0; i < count; i ++)
            students[i] = s[i];
    }

    Group(const Group &g) {
        copy(g);
    }

    ~Group() {
        delete [] students;
    }
    Group& operator+=(Student s) {
        Student* tmp = new Student[count + 1];
        for (int i = 0; i < count; i++)
            tmp[i] = students[i];
        tmp [count ++] = s;
        delete [] students;
        students = tmp;
        return *this;
    }

    Group& operator++() {
        for (int i = 0; i < count; i++)
            students[i]++;
        return *this;
    }
    Group operator++(int) {
        Group g(*this);
        for (int i = 0; i < count; i++)
            students[i]++;
        return g;
    }

    friend ostream& operator<<(ostream& o, const Group& p) {
        for (int i = 0; i < p.count; i ++)
            o << p.students[i];
        return o;
    }

    void reward() {
        for (int i = 0; i < count; i++)
            if (students[i].getAverage() > 9.0)
                cout << students[i];
    }

    void highestAverage() {
        Student tmpU = students[0];
        for (int i = 0; i < count; i++)
            if (students[i] > tmpU)
                tmpU = students[i];
        cout << "Highest average in the group:" << tmpU.getAverage() << endl;
    }
};

int main() {
    Student s1("Martina Martinovska", 9.5, 3);
    Student s2("Darko Darkoski", 7.3, 2);
    Student s3("Angela Angelovska", 10, 3);

    Group group;
    group += s1;
    group += s2;
    group += s3;

    cout << group;
    cout << "Reward:" << endl;
    group.reward();
    cout << endl;
    group.highestAverage();
    cout << endl;

    s2++;
    cout << group;
    cout << endl;
    group++;
    cout << group;

    return 0;
}

