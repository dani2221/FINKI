/*Да се дефинира апстрактна класа User за која ќе се чуваат:

username (char[50])
password (char[50])
email (char[50])
Класата треба да содржи еден чист виртуелен метод double popularity().

Од оваа класа да се изведат две класи и тоа FacebookUser и TwitterUser.

За класата FacebookUser уште се чуваат и:

број на пријатели
број на лајкови и
број на коментари
Популарноста се пресметува така што се собираат бројот на пријатели, бројот на лајкови и бројот на коментари, но притоа бројот на лајкови се множи со коефициент на вредност на лајкот кој е ист за секој корисник и изнесува 0.1, а исто така и бројот на коментари се множи со ист таков коефициент кој е 0.5.

За класата TwitterUser уште се чуваат и:

број на следачи и
број на твитови
Популарноста се пресметува така што се собираат бројот на следачи и бројот на твитови, но притоа бројот на твитови се множи со коефициент на вредност на твитот кој е ист за секој корисник и изнесува 0.5.

Да се креира класа SocialNetwork која ќе содржи:

динамичка низа од покажувачи од класата User
број на тековни корисници и
максимален број на корисници кој може да го содржи мрежата и кој е ист за сите мрежи и иницијално е поставен на 5
Да се преоптовари операторот += со кој ќе се додава нов корисник.

Да се дефинира метод avgPopularity() кој ќе ја враќа просечната популарност на корисниците во мрежата.

Исто така да се овозможи промена на максималната големина на низата преку методот changeMaximumSize(int number).

Потребно е да се справите со следните исклучоци:

Доколку лозинката на корисникот не содржи барем 1 голема буква, 1 мала буква и 1 број да се фрли исклучок од класа InvalidPassword така што како параметар ќе се прати пораката Password is too weak.
Доколку емаилот на корисникот не содржи точно еднаш @ да се фрли исклучок од класа InvalidEmail така што како параметар ќе се прати пораката Mail is not valid.
Доколку проба да се додаде корисник во социјалната мрежа, а веќе максималниот број на корисници е пополнет да се фрли исклучок од класа MaximumSizeLimit така што како параметар ќе се прати максималниот број на корисници во мрежата.
Сите класи кои се справуваат со исклучоци треба да го имаат имплементирано методот void message() така што за првите две класи ќе ја печати пораката којашто е испратена како параметар, а за последната класа ќе печати You can't add more than N users, каде што N е параметарот кој е пратен. Исто така со try-catch блокови справете се со исклучоците на соодветните места во main(), каде што во catch ќе го повикате методот message() од соодветниот исклучок.*/
#include<iostream>
#include<cmath>
#include<cstring>
using namespace std;
class InvalidPassword
{
private:
    char text[50];
public:
    InvalidPassword(const char *text="")
    {
        strcpy(this->text,text);
    }
    void message()
    {
        cout<<text<<endl;
    }
};
class InvalidEmail
{
private:
    char text[50];
public:
    InvalidEmail(const char *text="")
    {
        strcpy(this->text,text);
    }
    void message()
    {
        cout<<text<<endl;
    }
};
class MaximumSizeLimit
{
private:
    int n;
public:
    MaximumSizeLimit(int n=0)
    {
        this->n=n;
    }
    void message()
    {
        cout<<"You can't add more than "<<n<<" users."<<endl;
    }
};
class User
{
protected:
    char username[50];
    char password[50];
    char email[50];
public:
    User(const char *username="",const char *password="",const char *email="")
    {
        strcpy(this->username,username);
        setPassword(password);
        setEmail(email);
    }
    virtual double popularity() = 0;
    void setPassword(const char *password="")
    {
        int malaBukva=0,golemaBukva=0,broj=0;
        for(int i=0;i<strlen(password);i++)
        {
            if(password[i]>='A' && password[i]<='Z')
            {
                golemaBukva++;
            }
            else if(password[i]>='a' && password[i]<='z')
            {
                malaBukva++;
            }
            else if(password[i]>='0' && password[i]<='9')
            {
                broj++;
            }
        }
        if(!malaBukva || !golemaBukva || !broj)
        {
            throw InvalidPassword("Password is too weak.");
        }
        strcpy(this->password,password);
    }
    void setEmail(const char *email="")
    {
        int brojac=0;
        for(int i=0;i<strlen(email);i++)
        {
            if(email[i] == '@')
            {
                brojac++;
            }
        }
        if(brojac == 1)
        {
            strcpy(this->email,email);
        }
        else
        {
            throw InvalidEmail("Mail is not valid.");
        }
    }

};
class FacebookUser : public User
{
private:
    int brojPrijateli;
    int brojLajkovi;
    int brojKomentari;
public:
    FacebookUser(const char *username="",const char *password="",const char *email="",int brojPrijateli=0,int brojLajkovi=0,int brojKomentari=0)
                                                                                                                : User(username,password,email)
    {
        this->brojPrijateli = brojPrijateli;
        this->brojLajkovi = brojLajkovi;
        this->brojKomentari = brojKomentari;
    }
    double popularity()
    {
        return brojPrijateli + (brojLajkovi * 0.1) + (brojKomentari * 0.5);
    }
};
class TwitterUser : public User
{
private:
    int brojSledaci;
    int brojTvitovi;
public:
    TwitterUser(const char *username="",const char *password="",const char *email="",int brojSledaci=0,int brojTvitovi=0) : User(username,password,email)
    {
        this->brojSledaci = brojSledaci;
        this->brojTvitovi = brojTvitovi;
    }
    double popularity()
    {
        return brojSledaci + (brojTvitovi*0.5);
    }
};
class SocialNetwork
{
private:
    User **niza;
    int broj;
    static int maksBroj;

    void copy(const SocialNetwork &sn)
    {
        broj=sn.broj;
        niza = new User*[broj];
        for(int i=0;i<broj;i++)
        {
            niza[i]=sn.niza[i];
        }
    }
public:

    SocialNetwork()
    {
        broj=0;
        niza = new User*[broj];
    }
    SocialNetwork(const SocialNetwork &sn)
    {
        copy(sn);
    }
    SocialNetwork&operator=(const SocialNetwork &sn)
    {
        if(this!=&sn)
        {
            delete[]niza;
            copy(sn);
        }
        return *this;
    }
    SocialNetwork & operator += (User *user)
    {
        if(broj == maksBroj)
        {
            throw (MaximumSizeLimit(maksBroj));
        }
        User **temp = new User*[broj+1];
        for(int i=0;i<broj;i++)
        {
            temp[i]=niza[i];
        }
        temp[broj++]=user;
        delete[]niza;
        niza=temp;
        return *this;
    }
    double avgPopularity()
    {
        double total=0;
        for(int i=0;i<broj;i++)
        {
            total+=niza[i]->popularity();
        }
        return total/broj;
    }
    static void changeMaximumSize(int number)
    {
        SocialNetwork::maksBroj=number;
    }
};
int SocialNetwork::maksBroj = 5;

int main ()
{
  SocialNetwork network = SocialNetwork();
    int n;
    cin >> n;
    char username[50];
    char password[50];
    char email[50];
    int userType;
    for (int i=0; i < n; ++i) {
      cin >> username;
      cin >> password;
      cin >> email;
      cin >> userType;
      if (userType == 1) {
        int friends;
        int likes;
        int comments;
        cin >> friends >> likes >> comments;

        // TODO: Try-catch
        try{
        User * u = new FacebookUser(username,password,email,friends,likes,comments);
        network += u;
        }
        catch (InvalidPassword &ip)
        {
            ip.message();
        }
        catch (InvalidEmail &ie)
        {
            ie.message();
        }
        catch (MaximumSizeLimit &sl)
        {
            sl.message();
        }

      }
      else {
        int followers;
        int tweets;
        cin >> followers >> tweets;

        // TODO: Try-catch
        try{
        User * u= new TwitterUser(username,password,email,followers,tweets);
        network += u;
        }
                catch (InvalidPassword &ip)
        {
            ip.message();
        }
        catch (InvalidEmail &ie)
        {
            ie.message();
        }
        catch (MaximumSizeLimit &sl)
        {
            sl.message();
        }

      }

    }
    network.changeMaximumSize(6);
    cin >> username;
    cin >> password;
    cin >> email;
    int followers;
    int tweets;
    cin >> followers >> tweets;

    // TODO: Try-catch
    try{
        User * u= new TwitterUser(username,password,email,followers,tweets);
        network += u;
    }
        catch (InvalidPassword &ip){
            ip.message();
        }
        catch (InvalidEmail &ie)
        {
            ie.message();
        }
        catch (MaximumSizeLimit &sl)
        {
            sl.message();
        }

    cout << network.avgPopularity();
}
