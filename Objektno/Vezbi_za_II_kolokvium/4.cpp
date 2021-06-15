// Да се креира апстрактна класа Pizza за опишување пици. (5 поени) За секоја пица се чуваат следните информации:

// име (низа од максимум 20 знаци)
// состојки (низа од максимум 100 знаци)
// основна цена (реален број)
// Од оваа класа да се изведат класите FlatPizza и FoldedPizza за опишување на рамни и преклопени пици соодветно (5 поени).

// За секоја рамна пица дополнително се чува големина (enum - една од три можности: мала, голема и фамилијарна). За секоја преклопена пица дополнително се чува информација дали тестото е од бело брашно или не (boolean).

// За секоја пица треба да се обезбеди метод за пресметување на нејзината продажна цена:

// цената на рамната пица се пресметува така што основната цена се зголемува за 10%, 30% и 50% за мала, голема и фамилијарна пица соодветно.
// цената на преклопената пица се пресметува така што основната цена се зголемува за 10% ако е тестото е од бело брашно, а во спротивно за 30%. (10 поени)
// Да се преоптоварат следните оператори:

// оператор << - за печатење сите податоци за пиците во следниов формат:

// За рамна пица:[име]: [состојки], [големина] - [продажната цена на пицата]. 
// За преклопена пица: [име]: [состојки], [wf - ако е со бело брашно / nwf - ако не е со бело брашно] - [продажната цена на пицата]
// (5 поени)

// оператор < - за споредување на пиците од каков било вид според нивната продажна цена. (5 поени)
// Да се дефинира глобална функција еxpensivePizza што на влез прима низа од покажувачи кон објекти од класата Pizza и нивниот број, а како резултат ги печати информациите за пицата со највисока продажна цена. Ако има повеќе пици со иста највисока цена, се печати првата. (10 поени)

// Да се дефинираат потребните конструктори и методи во класите за правилно функционирање на програмата. (5 поени)

#include <cstring>
#include <iostream>
using namespace std;

// Your Code goes here

class Pizza{
    protected:
        char name[20];
        char ingredients[100];
        double startPrice;
    public:
        Pizza(char name[20],char ingredients[20],double price){
            strcpy(this->name,name);
            strcpy(this->ingredients,ingredients);
            this->startPrice=price;
        }
        virtual double price() = 0;
        bool operator<(Pizza &p){
            return this->price()<p.price();
        }
};
enum Size{mala,golema,familijarna};
class FlatPizza: public Pizza{
    private:
        Size golemina;
    public:
        FlatPizza(char name[20],char ingredients[20],double price,Size golemina) : Pizza(name,ingredients,price){
            this->golemina=golemina;
        }
        FlatPizza(char name[20],char ingredients[20],double price):Pizza(name,ingredients,price){
            golemina=mala;
        }
        double price(){
            if(golemina==mala) return startPrice*1.1;
            if(golemina==golema) return startPrice*1.5;
            return startPrice*1.3;
        }
        friend ostream &operator<<(ostream &os, FlatPizza &p){
            os<<p.name<<": "<<p.ingredients<<", ";
            if(p.golemina==mala) os<<"small - ";
            if(p.golemina==golema) os<<"big - ";
            if(p.golemina==familijarna) os<<"family - ";
            os<<p.price()<<endl;
            return os;
        }

};
class FoldedPizza: public Pizza{
    private:
        bool isWhiteDough;
    public:
        FoldedPizza(char name[20],char ingredients[20],double price,bool isWhiteDough) : Pizza(name,ingredients,price){
            this->isWhiteDough=isWhiteDough;
        }
        FoldedPizza(char name[20],char ingredients[20],double price):Pizza(name,ingredients,price){
            isWhiteDough=true;
        }
        double price(){
            return isWhiteDough?startPrice*1.1:startPrice*1.3;
        }
        friend ostream &operator<<(ostream &os, FoldedPizza &p){
            os<<p.name<<": "<<p.ingredients<<", ";
            if(p.isWhiteDough) os<<"wf";
            else os<<"nwf";
            os<<" - "<<p.price()<<endl;
            return os;
        }
        void setWhiteFlour(bool cond){
            this->isWhiteDough=cond;
        }
};

void expensivePizza(Pizza **pizzas, int n){
    Pizza *mostExpensive = pizzas[0];
    for(int i=0;i<n;i++){
        if(*mostExpensive<*pizzas[i]){
            mostExpensive=pizzas[i];
        }
    }
    FlatPizza *f = dynamic_cast<FlatPizza*>(mostExpensive);
    if(f != 0){
        cout<<*f;
    }
    else{
        FoldedPizza *fp = dynamic_cast<FoldedPizza*>(mostExpensive);
        cout<<*fp;
    }
}

// Testing

int main() {
  int test_case;
  char name[20];
  char ingredients[100];
  float inPrice;
  Size size;
  bool whiteFlour;

  cin >> test_case;
  if (test_case == 1) {
    // Test Case FlatPizza - Constructor, operator <<, price
    cin.get();
    cin.getline(name,20);
    cin.getline(ingredients,100);
    cin >> inPrice;
    FlatPizza fp(name, ingredients, inPrice);
    cout << fp;
  } else if (test_case == 2) {
    // Test Case FlatPizza - Constructor, operator <<, price
    cin.get();
    cin.getline(name,20);
    cin.getline(ingredients,100);
    cin >> inPrice;
    int s;
    cin>>s;
    FlatPizza fp(name, ingredients, inPrice, (Size)s);
    cout << fp;
    
  } else if (test_case == 3) {
    // Test Case FoldedPizza - Constructor, operator <<, price
    cin.get();
    cin.getline(name,20);
    cin.getline(ingredients,100);
    cin >> inPrice;
    FoldedPizza fp(name, ingredients, inPrice);
    cout << fp;
  } else if (test_case == 4) {
    // Test Case FoldedPizza - Constructor, operator <<, price
    cin.get();
    cin.getline(name,20);
    cin.getline(ingredients,100);
    cin >> inPrice;
    FoldedPizza fp(name, ingredients, inPrice);
    fp.setWhiteFlour(false);
    cout << fp;

  } else if (test_case == 5) {
	// Test Cast - operator <, price
    int s;
    
    cin.get();
    cin.getline(name,20);
    cin.getline(ingredients,100);
    cin >> inPrice;
    cin>>s;
    FlatPizza *fp1 = new FlatPizza(name, ingredients, inPrice, (Size)s);
    cout << *fp1;
      
    cin.get();
    cin.getline(name,20);
    cin.getline(ingredients,100);
    cin >> inPrice;
    cin>>s;
    FlatPizza *fp2 = new FlatPizza(name, ingredients, inPrice, (Size)s);
    cout << *fp2;
      
    cin.get();
    cin.getline(name,20);
    cin.getline(ingredients,100);
    cin >> inPrice;
    FoldedPizza *fp3 = new FoldedPizza(name, ingredients, inPrice);
    cout << *fp3;
      
    cin.get();
    cin.getline(name,20);
    cin.getline(ingredients,100);
    cin >> inPrice;
    FoldedPizza *fp4 = new FoldedPizza(name, ingredients, inPrice);
    fp4->setWhiteFlour(false);
    cout << *fp4;
      
    cout<<"Lower price: "<<endl;
    if(*fp1<*fp2)
        cout<<fp1->price()<<endl;
    else cout<<fp2->price()<<endl;
        
    if(*fp1<*fp3)
        cout<<fp1->price()<<endl;
    else cout<<fp3->price()<<endl;    
        
    if(*fp4<*fp2)
        cout<<fp4->price()<<endl;
    else cout<<fp2->price()<<endl;
    
    if(*fp3<*fp4)
        cout<<fp3->price()<<endl;
    else cout<<fp4->price()<<endl;

  } else if (test_case == 6) {
	// Test Cast - expensivePizza
    int num_p;
    int pizza_type;

    cin >> num_p;
    Pizza **pi = new Pizza *[num_p];
    for (int j = 0; j < num_p; ++j) {

      cin >> pizza_type;
      if (pizza_type == 1) {
        cin.get();
        cin.getline(name,20);
          
        cin.getline(ingredients,100);
        cin >> inPrice;
        int s;
        cin>>s;
        FlatPizza *fp = new FlatPizza(name, ingredients, inPrice, (Size)s);
        cout << (*fp);
        pi[j] = fp;
      }
      if (pizza_type == 2) {

        cin.get();
        cin.getline(name,20);
        cin.getline(ingredients,100);
        cin >> inPrice;
        FoldedPizza *fp =
            new FoldedPizza (name, ingredients, inPrice);
        if(j%2)
          (*fp).setWhiteFlour(false);
        cout << (*fp);
        pi[j] = fp;

      }
    }

    cout << endl;
    cout << "The most expensive pizza:\n";
    expensivePizza(pi,num_p);


  }
  return 0;
}
