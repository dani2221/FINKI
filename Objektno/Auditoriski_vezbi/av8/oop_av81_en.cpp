#include <iostream>
#include <cstring>

using namespace std;
class Publication {
private:
    char name [100];
protected:
    int year;
    char* getName() {
        return name;
    }

public:
    int getYear() { return year;}

    void print () {
        cout << "Publication: " << name << " - " << year << endl;
    }

    Publication( char *name, int year ) {
        strcpy(this->name, name);
        this->year = year;
    }
};

// public inheritance

class Book: public Publication {
private:
    int number_of_pages;
public:
    Book(char *name, int year, int number_of_pages): Publication(name, year) {
        this->number_of_pages = number_of_pages;
    }
    void printBookYear() {
        cout << year; // access to protected resource year
    }
    void printBookName () {
        // access to getName(), name can be accessed because it's private
        cout << getName();
    }
    void numberOfPages() {
        cout << number_of_pages;
    }
};

// protected inheritance

class Newspaper: protected Publication {
private:
    int number;
public:
    Newspaper(char* name, int year, int number): Publication(name, year) {
        this->number = number;
    }
    void printYearNewspaper () {
        cout << getYear(); // access to public getYear(), that in this class has protected access
    }
    void printNameNewspaper() {
        cout << getName(); // access to getName(), name that can not be accessed because it is private
    }
    void printNumber() {
        cout << number;
    }
};

// private inheritance

class DailyNewspaper: private Newspaper {
private:
    int day;
    int month;
public:
    DailyNewspaper(char *name, int day, int month, int year,
                 int number): Newspaper(name, year, number) {
        this->day = day;
        this->month = month;
    }
    using Newspaper::print; // function print from Publication becomes public for DailyNewspaper
    using Newspaper::printNumber; // function printNumber from Publication becomes public for DailyNewspaper
};

int main() {
    Publication p("Tabernakul", 1992);
    p.print(); // public - function
    Book *k = new Book("ProsvetnoDelo", 1900, 123);
    k->print(); //print is public in Book
    k->printBookYear(); // public - function
    // cout<<k->getName(); // error! protected - function
    Newspaper *s = new Newspaper("Tea", 2013 , 30);
    // s->print(); //error! protected - function
    // cout<<s->getYear(); // error! protected - function
    s->printNameNewspaper(); // public - function
    DailyNewspaper d("Vest", 2, 3, 2014, 25);
    d.print(); //public-function
    // d.printNameNewspaper(); // error! private - function
    // cout<<d.getName(); // error! private – function
}
