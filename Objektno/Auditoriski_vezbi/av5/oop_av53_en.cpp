#include <iostream>
#include <cstring>
using namespace std;

class WebPage {
private :
    char  url [100];
    char* contents;
public :
    WebPage(char* url = "", char* contents = "") {
        strcpy(this->url, url);
        this->contents = new char[strlen(contents) + 1];
        strcpy(this->contents, contents);
    }

    WebPage(const WebPage& wp) {
        strcpy(this->url , wp.url );
        this-> contents = new char [strlen(wp.contents) + 1];
        strcpy(this->contents, wp.contents );
    }

    ~WebPage() {
        delete [] contents ;
    }
    bool equal(WebPage& wp) {
        return strcmp(url, wp.url) == 0;
    }

    WebPage& operator=(WebPage& wp) {
        if(this != &wp) {
            strcpy(this->url , wp.url);
            delete [] contents ;
            this->contents = new char [strlen(wp.contents) + 1];
            strcpy(this->contents, wp.contents);
        }
        return *this ;
    }

    friend class WebServer; //prijatelska klasa

};

class WebServer {

private:
    char ime [30];
    int count ;
    WebPage* wp;

public:
    WebServer(const char * ime = "", int count = 0, WebPage *wp = 0) {
        strcpy(this ->ime, ime);
        this-> count = count ;
        this->wp = new WebPage [count];
        for(int i = 0; i < count ; i++)
            this->wp[i] = wp[i];
    }

    WebServer(const WebServer &ws) {
        strcpy(this->ime, ws.ime );
        this->count = ws.count ;
        this->wp = new WebPage[count];
        for(int i = 0; i < count ; i++)
            this->wp[i] = ws.wp[i];
    }

    WebServer& operator=(const WebServer &ws) {
        if(this != &ws) {
            strcpy(this ->ime, ws.ime );
            this->count = ws.count ;
            delete [] this->wp;
            this->wp = new WebPage[count];
            for(int i = 0; i < count; i++)
                this ->wp[i] = ws.wp[i];
        }
        return *this ;
    }

    ~ WebServer() {
        delete [] wp;
    }

    WebServer& addPage(WebPage webPage) {
        WebPage * tmp = new WebPage [count + 1]; // allocate new array
        // with increased capacity for +1
        // copy the contents of the current array
        for(int i = 0; i < count; i++)
            tmp [i] = wp[i];

        tmp [count++] = webPage ; // enter the new webpage
        delete [] wp; // delete the old array
        wp = tmp; // move the pointer to the new array
        return *this ;
    }

    WebServer& deletePage(WebPage webPage) {
        int newCount = 0;
        for(int i = 0; i < count; i++) {
            if(!wp[i].equal(webPage)) {
                newCount++;
            }
        }
        // after the deletion there will be newCount elements
        WebPage* tmp = new WebPage[newCount];
        newCount = 0;
        for(int i = 0; i < count; i++) {
            if(!wp[i].equal(webPage)) {
                tmp[newCount++] = wp[i];
            }
        }
        delete [] wp;
        wp = tmp;
        count = newCount ;
        return *this ;
    }

    void listPages() {
        cout << "Number: " << count << endl;
        for(int i = 0; i < count; i++)
            cout << wp[i].contents << "- " << wp[i].url << endl ; // direct access of contents and url
    }
};

int main() {
    WebPage wp1("http://www.google.com", "The search engine");
    WebPage wp2("http://www.finki.ukim.mk", "FINKI");
    WebPage wp3("http://www.time.mk", "Site vesti");

    WebServer ws("Server");

    ws.addPage(wp1) ;
    ws.addPage(wp2);
    ws.addPage(wp3) ;

    ws.listPages();

    cout << "\nAfter delete: \n";
    ws.deletePage(wp3);

    ws.listPages();

    return 0;
}
