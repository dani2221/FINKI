// Потребно е да се имплементираат класи File (датотека)
// и Folder (директориум) за работа со едноставен датотечен систем.
// Во класата File треба да се чуваат следниве податоци:
// Име на датотеката (динамички алоцирана низа од знаци)
// Екстензија на датотеката (енумерација со можни вредности: txt, pdf, exe)
// Име на сопственикот на датотеката (динамички алоцирана низа од знаци)
// Големина на датотеката (цел број на мегабајти)
// Дополнително, во класата потребно е да се напишат и:
// Конструктор без параметри
// Конструктор со параметри
// Конструктор за копирање
// Деструктор
// Преоптоварување на операторот =
// Метод за печатење на информациите за една датотека 
// (видете го излезот од тест примерите за структурата на печатење) - print()
// Метод за проверка на еднаквост помеѓу две датотеки со потпис 
// bool equals(const File & that) кој ќе враќа true ако датотеките имаат исто име, 
// екстензија и сопственик
// Метод за споредба на типот помеѓу две датотеки со потпис 
// bool equalsType(const File & that) кој ќе враќа true ако датотеките се од ист тип,
//  т.е. имаат исто име и екстензија
// Во класата Folder треба да се чуваат следниве податоци:
// Име на директориумот (динамички алоцирана низа од знаци)
// Број на датотеки во него (на почеток директориумот е празен)
// Динамички алоцирана низа од датотеки, објекти од класата File
// Дополнително, во класата потребно е да се напишат и:
// Конструктор со потпис Folder(const char* name)
// Деструктор
// Метод за печатење на информациите за еден директориум 
// (видете го излезот од тест примерите за структурата на печатење) - print()
// Метод за бришење на датотека од директориумот со потпис 
// void remove(const File & file) кој ќе ја избрише првата датотека од директориумот 
// која е еднаква според equals методот
// Метод за додавање на датотека во директориумот со потпис 
// void add(const File & file) кој ќе додава датотеката во директориумот

#include<iostream>
#include<cstring>

using namespace std;

enum Extension {pdf, txt, exe};

class File{
    private:
        char *ime;
        Extension ekstenzija;
        char *sopstvenik;
        int golemina;
    public:
        File(){
            this->ime = new char[0];
            this->sopstvenik= new char[0];
            this->golemina=0;
        }
        File(char *ime, char *sops, int golemina, Extension ekx){
            this->ime = new char[strlen(ime)+1];
            this->sopstvenik= new char[strlen(sops)+1];
            strcpy(this->ime,ime);
            strcpy(this->sopstvenik,sops);
            this->golemina=golemina;
            this->ekstenzija=ekx;
        }
        File(const File &f){
            this->ime = new char[strlen(f.ime)+1];
            this->sopstvenik = new char[strlen(f.sopstvenik)+1];
            strcpy(this->ime,f.ime);
            strcpy(this->sopstvenik,f.sopstvenik);
            this->golemina=f.golemina;
            this->ekstenzija=f.ekstenzija;
        }
        ~File(){
            delete[]ime;
            delete[]sopstvenik;
        }
        File& operator=(const File &f){
            if(this==&f) return *this;
            delete[]ime;
            delete[]sopstvenik;
            this->ime = new char[strlen(f.ime)+1];
            this->sopstvenik = new char[strlen(f.sopstvenik)+1];
            strcpy(this->ime,f.ime);
            strcpy(this->sopstvenik,f.sopstvenik);
            this->golemina=f.golemina;
            this->ekstenzija=f.ekstenzija;
            return *this;
        }
        void print(){
            char ext[4];
            if(this->ekstenzija==0) strcpy(ext,"pdf");
            if(this->ekstenzija==1) strcpy(ext,"txt");
            if(this->ekstenzija==2) strcpy(ext,"exe");
            cout<<"File name: "<<this->ime<<"."<<ext<<endl;
            cout<<"File owner: "<<this->sopstvenik<<endl;
            cout<<"File size: "<<this->golemina<<endl;
        }
        bool equals(const File &that){
            return this->ekstenzija==that.ekstenzija 
            && !strcmp(this->ime,that.ime)
            && !strcmp(this->sopstvenik,that.sopstvenik);
        }
        bool equalsType(const File & that){
            return this->ekstenzija==that.ekstenzija && !strcmp(this->ime,that.ime);
        }
};

class Folder{
    private:
        char *ime;
        int broj;
        File *files;
    public:
        Folder(){
            this->ime = new char[0];
            this->broj = 0;
            this->files = new File[0];
        }
        Folder(const char* name){
            this->ime = new char[strlen(name)+1];
            strcpy(this->ime,name);
            this->broj = 0;
            this->files = new File[0];
        }
        ~Folder(){
            delete[]ime;
            delete[]files;
        }
        void print(){
            cout<<"Folder name: "<<this->ime<<endl;
            for(int i=0;i<this->broj;i++){
                this->files[i].print();
            }
        }
        void remove(const File &file){
            int removeNum = this->broj;
            for(int i=0;i<this->broj;i++){
                if(this->files[i].equals(file)){
                    removeNum = i;
                    break;
                }
            }
            if(removeNum==this->broj) return;
            this->broj--;
            for(int i=removeNum;i<this->broj;i++){
                this->files[i]=this->files[i+1];
            }
        }
        void add(const File & file){
            File *temp = new File[this->broj+1];
            for(int i=0;i<this->broj;i++){
                temp[i] = this->files[i];
            }
            temp[this->broj++] = file;
            delete[]files;
            this->files=temp;
        }

};


int main() {
	char fileName[20];
	char fileOwner[20];
	int ext;
	int fileSize;

	int testCase;
	cin >> testCase;
	if (testCase == 1) {
		cout << "======= FILE CONSTRUCTORS AND = OPERATOR =======" << endl;
		cin >> fileName;
		cin >> fileOwner;
		cin >> fileSize;
		cin >> ext;

		File created = File(fileName, fileOwner, fileSize, (Extension) ext);
		File copied = File(created);
		File assigned = created;

		cout << "======= CREATED =======" << endl;
		created.print();
		cout << endl;
        cout << "======= COPIED =======" << endl;
		copied.print();
		cout << endl;
        cout << "======= ASSIGNED =======" << endl;
		assigned.print();
	}
	else if (testCase == 2) {
		cout << "======= FILE EQUALS AND EQUALS TYPE =======" << endl;
		cin >> fileName;
		cin >> fileOwner;
		cin >> fileSize;
		cin >> ext;

		File first(fileName, fileOwner, fileSize, (Extension) ext);
		first.print();

		cin >> fileName;
		cin >> fileOwner;
		cin >> fileSize;
		cin >> ext;	

		File second(fileName, fileOwner, fileSize, (Extension) ext);
		second.print();

		cin >> fileName;
		cin >> fileOwner;
		cin >> fileSize;
		cin >> ext;

		File third(fileName, fileOwner, fileSize, (Extension) ext);
		third.print();

		bool equals = first.equals(second);
		cout << "FIRST EQUALS SECOND: ";
		if (equals)
			cout << "TRUE" << endl;
		else
			cout << "FALSE" << endl;

		equals = first.equals(third);
		cout << "FIRST EQUALS THIRD: ";
		if (equals)
			cout << "TRUE" << endl;
		else
			cout << "FALSE" << endl;

		bool equalsType = first.equalsType(second);
		cout << "FIRST EQUALS TYPE SECOND: ";
		if (equalsType)
			cout << "TRUE" << endl;
		else
			cout << "FALSE" << endl;

		equalsType = second.equals(third);
		cout << "SECOND EQUALS TYPE THIRD: ";
		if (equalsType)
			cout << "TRUE" << endl;
		else
			cout << "FALSE" << endl;

	}
	else if (testCase == 3) {
		cout << "======= FOLDER CONSTRUCTOR =======" << endl;
		cin >> fileName;
		Folder folder(fileName);
		folder.print();

	}
	else if (testCase == 4) {
		cout << "======= ADD FILE IN FOLDER =======" << endl;
		char name[20];
		cin >> name;
		Folder folder(name);

		int iter;
		cin >> iter;

		while (iter > 0) {
			cin >> fileName;
			cin >> fileOwner;
			cin >> fileSize;
			cin >> ext;

			File file(fileName, fileOwner, fileSize, (Extension) ext);
			folder.add(file);
			iter--;
		}
		folder.print();
	}
	else {
		cout << "======= REMOVE FILE FROM FOLDER =======" << endl;
		char name[20];
		cin >> name;
		Folder folder(name);

		int iter;
		cin >> iter;

		while (iter > 0) {
			cin >> fileName;
			cin >> fileOwner;
			cin >> fileSize;
			cin >> ext;

			File file(fileName, fileOwner, fileSize, (Extension) ext);
			folder.add(file);
			iter--;
		}
		cin >> fileName;
		cin >> fileOwner;
		cin >> fileSize;
		cin >> ext;

		File file(fileName, fileOwner, fileSize, (Extension) ext);
		folder.remove(file);
		folder.print();
	}
	return 0;
}