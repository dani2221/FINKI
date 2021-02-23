#include <iostream>
#include <cstring>
using namespace std;
class WebServer; //deklaracija na klasata WebServer

class WebPage {
private :
    char  url [100];
    char* sodrzina;
public :
    WebPage ( char* url = "", char* sodrzina = "" ) {
        strcpy(this -> url, url);
        this -> sodrzina = new char [strlen(sodrzina) + 1];
        strcpy(this -> sodrzina, sodrzina);
    }

    WebPage (const WebPage& wp) {
        strcpy(this->url , wp.url );
        this-> sodrzina = new char [strlen (wp.sodrzina) + 1];
        strcpy(this->sodrzina, wp.sodrzina );
    }

    ~WebPage () {
        delete [] sodrzina ;
    }
    bool daliIsti(WebPage& wp) {
        return strcmp(url, wp.url) == 0;
    }

    WebPage& operator= (WebPage& wp) {
        if (this != &wp) {
            strcpy (this -> url , wp.url);
            delete [] sodrzina ;
            this -> sodrzina = new char [strlen(wp.sodrzina) + 1];
            strcpy(this -> sodrzina, wp.sodrzina);
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
    WebServer (const char * ime = "", int count = 0, WebPage *wp = 0) {
        strcpy(this ->ime, ime);
        this-> count = count ;
        this->wp = new WebPage [count];
        for (int i = 0; i < count ; i++)
            this->wp[i] = wp[i];
    }

    WebServer(const WebServer &ws) {
        strcpy (this->ime, ws.ime );
        this -> count = ws.count ;
        this -> wp = new WebPage[count];
        for (int i = 0; i < count ; i++)
            this -> wp[i] = ws.wp[i];
    }

    WebServer& operator= (const WebServer &ws) {
        if (this != &ws) {
            strcpy (this ->ime, ws.ime );
            this -> count = ws.count ;
            delete [] this -> wp;
            this -> wp = new WebPage[count];
            for (int i = 0; i < count; i++)
                this ->wp[i] = ws.wp[i];
        }
        return *this ;
    }

    ~ WebServer () {
        delete [] wp;
    }

    WebServer& addPage(WebPage webPage) {
        WebPage * tmp = new WebPage [count + 1]; //alociraj nova memorija
        //so kapacitet za eden povekje od prethodno
        for (int i = 0; i < count; i++)
            tmp [i] = wp[i];

        tmp [count++] = webPage ; //vmetni ja novata veb strana (webPage)
        delete [] wp;
        wp = tmp;
        return *this ;
    }

    WebServer& deletePage(WebPage webPage) {
        int newCount = 0;
        for (int i = 0; i < count; i++) {
            if (!wp[i].daliIsti(webPage)) {
                newCount++;
            }
        }
        //po brishenjeto kje ima newCount elementi
        WebPage* tmp = new WebPage[newCount];
        newCount = 0;
        for (int i = 0; i < count; i++) {
            if (!wp[i].daliIsti(webPage)) {
                tmp[newCount++] = wp[i];
            }
        }
        delete [] wp;
        wp = tmp;
        count = newCount ;
        return *this ;
    }

    void listPages () {
        cout << "Number: " << count << endl;
        for (int i = 0; i < count; i++)
            cout << wp[i].sodrzina << "- " << wp[i].url << endl ; //direkten pristap do sodrzina i url
    }
};

int main () {
    WebPage wp1 ("http://www.google.com", "The search engine");
    WebPage wp2 ("http://www.finki.ukim.mk", "FINKI");
    WebPage wp3 ("http://www.time.mk", "Site vesti");

    WebServer ws(" Server ");

    ws.addPage(wp1) ;
    ws.addPage(wp2);
    ws.addPage(wp3) ;

    ws.listPages ();

    cout << "\nAfter delete: \n";
    ws.deletePage(wp3);

    ws.listPages ();

    return 0;
}
