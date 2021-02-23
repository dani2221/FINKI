#include<iostream>
#include<string.h>
using namespace std;
class Book {
protected:
    char isbn[20];
    char title[50];
    char author[30];
    float price;
public:
    Book(const char* isbn = "", const char* title = "", const char* author = "", float price = 0) {
        strncpy(this->isbn, isbn, 19);
        this->isbn[19] = 0;
        strncpy(this->title, title, 49);
        this->title[49] = 0;
        strncpy(this->author, author, 29);
        this->author[29] = 0;
        this->price = price;
    }
    void setISBN(char *isbn) {
        strncpy(this->isbn, isbn, 19);
        this->isbn[19] = 0;
    }
    virtual float bookPrice() = 0;
    char* getISBN() { return isbn; }
    friend ostream& operator<< (ostream& o, Book& b) {
        o << b.isbn << ": " << b.title << ", " << b.author << " " << b.bookPrice() << endl;
        return o;
    }
    virtual ~Book() {}
};

float Book::bookPrice() {
    return price;
}

bool operator>(Book& b1, Book& b2) {
    return (b1.bookPrice() > b2.bookPrice());
}

class OnlineBook : public Book {
private:
    char* url;
    int size;
public:
    OnlineBook(const char* isbn = "", const char* title = "", const char* author = "", float price = 0, const char* url = "", int size = 0): Book(isbn, title, author, price) {
        this->url = new char[strlen(url) + 1];
        strcpy(this->url, url);
        this->size = size;
    }
    OnlineBook(OnlineBook& ob) {
        strcpy(isbn, ob.isbn);
        strcpy(title, ob.title);
        strcpy(author, ob.author);
        price = ob.price;
        url = new char[strlen(ob.url) + 1];
        strcpy(url, ob.url);
        size = ob.size;
    }
    OnlineBook& operator=(OnlineBook& ob) {
        if (this != &ob) {
            strcpy(isbn, ob.isbn);
            strcpy(title, ob.title);
            strcpy(author, ob.author);
            price = ob.price;
            delete[] url;
            url = new char[strlen(ob.url) + 1];
            strcpy(url, ob.url);
            size = ob.size;
        }
        return *this;
    }
    ~OnlineBook() {
        delete[] url;
    }
    float bookPrice() {
        if (size > 20)
            return Book::bookPrice() * 1.2;
        return Book::bookPrice();
    }
};

class PrintBook : public Book {
private:
    float weight;
    bool inStock;
public:
    PrintBook(const char* isbn = "", const char* title = "", const char* author = "", float price = 0, float weight = 0, bool inStock = false): Book(isbn, title, author, price) {
        this->weight = weight;
        this->inStock = inStock;
    }
    float bookPrice() {
        if (weight > 0.7)
            return Book::bookPrice() * 1.15;
        return Book::bookPrice();
    }
};

void mostExpensiveBook(Book** books, int n) {
    int obNo = 0;
    int pbNo = 0;
    for (int i = 0; i < n; i++)
    {
        OnlineBook* ob = dynamic_cast<OnlineBook*>(books[i]);
        if (ob != 0)
            obNo++;
        PrintBook* pb = dynamic_cast<PrintBook*>(books[i]);
        if (pb != 0)
            pbNo++;
    }
    cout << "FINKI-Education" << endl;
    cout << "Total number of online books: " << obNo << endl;
    cout << "Total number of print books: " << pbNo << endl;
    Book* max = books[0];
    for (int i = 1; i < n; i++)
        if (*books[i] > *max)
            max = books[i];
    cout << "The most expensive book is: " << endl;
    cout << *max;
}
