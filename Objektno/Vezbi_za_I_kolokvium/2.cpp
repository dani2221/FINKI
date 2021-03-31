// ISTA OD LAB4-2

// Да се имплементира класа List во којашто ќе се чуваат информации за:
// броеви што се дел од листата (динамички алоцирана низа од цели броеви)
// бројот на броеви што се дел од листата
// За класата да се дефинираат следните методи:
// конструктор (со аргументи), copy-конструктор, деструктор, оператор =
// void pecati()метод што ќе ги печати информациите за листата во форматот: 
// [број на елементи во листата]: x1 x2 .. xn sum: [сума] average: [просек]
// int sum() метод што ја враќа сумата на елементите во листата
// double average() метод што ќе го враќа просекот на броевите во листата
// Дополнително, креирајте класата ListContainer, во која што ќе се чуваат информации за:
// низа од листи (динамички алоцирана низа од објекти од класата List)
// број на елементи во низата од листи (цел број)
// број на обиди за додавање на листа во контејнерот (цел број првично поставен на нула)
// За класата потребно е да ги дефинирате следните методи:
// конструктор (default), copy-конструктор, деструктор, оператор =
// void pecati() метод што ќе ги печати информациите за контејнерот во форматот: List number: 
// [реден број на листата] List info: [испечатени информации за листата со методот List::pecati()]
// \n sum: [сума] average: [просек]
// доколку контејнерот е празен се печати само порака The list is empty.
// void addNewList(List l) метод со којшто се додава листа во контејнерот
// Напомена: една листа се додава во контејнерот 
// ако и само ако има различна сума од сите листи што се веќе додадени во контејнерот
// int sum() метод што ја враќа сумата на сите елементи во сите листи
// double average() метод што го враќа просекот на сите елементи во сите листи во контејнерот

#include <iostream>
using namespace std;

class List{
    private:
        int *nums;    
        int count;
    public:
        List(int *nums, int count){
            this->nums = new int[count];
            for(int i=0;i<count;i++){
                this->nums[i]=nums[i];
            }
            this->count = count;
        }
        List(){
            this->count = 0;
            this->nums = new int[0];
        }
        List(const List &l){
            this->nums = new int[l.count];
            for(int i=0;i<l.count;i++){
                this->nums[i]=l.nums[i];
            }
            this->count = l.count;
        }
        ~List(){
            delete[]nums;
        }
        List& operator = (const List &l){
            if(this==&l) return *this;
            delete [] nums;
            this->nums = new int[l.count];
            for(int i=0;i<l.count;i++){
                this->nums[i]=l.nums[i];
            }
            this->count = l.count;
            return *this;
        }
        int sum(){
            int s = 0;
            for(int i=0;i<this->count;i++) s+=nums[i];
            return s;
        }
        double average(){
            int s = this->sum();
            double n = (double)this->count;
            return s/n;
        }
        void print(){
            cout<<this->count<<": ";
            for(int i=0;i<this->count;i++){
                cout<<this->nums[i]<<" ";
            }
            cout<<"sum: "<<this->sum()<<" average: "<<this->average()<<endl;
        }
        int getCount(){
            return this->count;
        }
};

class ListContainer{
    private:
        List *lists;
        int count;
        int tries;
    public:
        ListContainer(){
            this->count=0;
            this->tries=0;
            this->lists = new List[0];
        }
        ListContainer(const ListContainer &lc){
            this->lists = new List[lc.count];
            for(int i=0;i<lc.count;i++){
                this->lists[i]=lc.lists[i];
            }
            this->count=lc.count;
            this->tries=lc.tries;
        }
        ~ListContainer(){
            delete[]lists;
        }
        ListContainer& operator =(const ListContainer &lc){
            if(this==&lc) return *this;
            delete [] lists;
            this->lists = new List[lc.count];
            for(int i=0;i<lc.count;i++){
                this->lists[i]=lc.lists[i];
            }
            this->count=lc.count;
            this->tries=lc.tries;
            return *this;
        }
        void print(){
            if(this->count==0){
                cout<<"The list is empty"<<endl;
                return;
            }
            for(int i=0;i<this->count;i++){
                cout<<"List number: "<<i+1<<" List info: ";
                this->lists[i].print();
            }
            cout<<"Sum: "<<this->sum()<<" Average: "<<this->average()<<endl;
            cout<<"Successful attempts: "<<this->count<<" Failed attempts: "<<this->tries-this->count<<endl;
        }
        void addNewList(List l){
            this->tries+=1;
            for(int i=0;i<this->count;i++){
                if(this->lists[i].sum()==l.sum()) return;
            }
            List *lists = new List[this->count+1];
            for(int i=0;i<this->count;i++){
                lists[i] = this->lists[i];
            }
            lists[this->count++] = l;

            delete [] this->lists;
            this->lists = lists;
        }
        int sum(){
            int s=0;
            for(int i=0;i<this->count;i++) s+=this->lists[i].sum();
            return s;
        }
        double average(){
            double avgs = 0.0;
            int nums = 0;
            for(int i=0;i<this->count;i++){
                avgs+=this->lists[i].sum();
                nums+=this->lists[i].getCount();
            }
            return avgs/nums;
        }
};

int main() {
	
	ListContainer lc;
	int N;	
	cin>>N;	
    
	for (int i=0;i<N;i++) {
		int n;
		int niza[100];
		
		cin>>n;
       
		for (int j=0;j<n;j++){
			cin>>niza[j];
            
		}
       
		List l=List(niza,n);
	
		lc.addNewList(l);
	}	
	
    
    int testCase;
    cin>>testCase;
    
    if (testCase==1) {
        cout<<"Test case for operator ="<<endl;
        ListContainer lc1;
        lc1.print();
        cout<<lc1.sum()<<" "<<lc.sum()<<endl;
        lc1=lc;
        lc1.print();
        cout<<lc1.sum()<<" "<<lc.sum()<<endl;
        lc1.sum();
        lc1.average();
        
    }
    else {
        lc.print();
    }
}