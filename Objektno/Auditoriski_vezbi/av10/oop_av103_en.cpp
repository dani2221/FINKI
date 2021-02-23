#include <iostream>
#include <cstring>
using namespace std;

// exception class
class NegativeValueException {
private:
    char text[50];
public:
    NegativeValueException(char *text)
    {
        strcpy(this->text, text);
    }
    void print() { cout << text; }
};

class Discount {
public:
    static float euro;
    static float dollar;
    virtual float discount_price() = 0;
    virtual float price() = 0;
    virtual void print_rule() = 0;
};

float Discount::euro = 61.7;
float Discount::dollar = 44.5;

class Product {
protected:
    char name[100];
    float price;
public:
    Product(const char *name = "", const float price = 0) {
        strcpy(this->name, name);
        this->price = price;
    }
    float getPrice() {
        return price;
    }
    void print() {
        cout << "Product{ name=" << name << ", price=" << price << "}" << endl;
    }
    void changePrice(float price) {
        if (price < 0) throw NegativeValueException("You entered a negative price for the exception!\n");
        this->price = price;
    }
};

class Cosmetics : public Product, public Discount {
private:
    int weight;
public:
    Cosmetics(const char *name = "", const float price = 0,
              const int weight = 0) : Product(name, price) {
        this->weight = weight;
    }
    float discount_price() {
        if (getPrice() / Discount::dollar > 20)
            return 0.86 * getPrice();
        if (getPrice() / Discount::euro > 5)
            return 0.88 * getPrice();
        return getPrice();
    }
    float price() {
        return getPrice();
    }
    void print_rule() {
        cout << "All cosmetic products with price > 5 euro have discount of 12%, while those with price > 20 dollars have discount 14%" << endl;
    }
};

class FoodProduct : public Product, public Discount {
private:
    float callories;
public:
    FoodProduct(const char *name = "", const float price = 0,
                const float callories = 0) : Product(name, price) {
        this->callories = callories;
    }

    float discount_price() {
        return getPrice();
    }

    float price() {
        return getPrice();
    }

    void print_rule() {
        cout << "No discount on food" << endl;
    }
};

class Drinks : public Product, public Discount {
private:
    char brand[100];
    bool alcoholic;
public:
    Drinks(const char *name = "", const float price = 0,
           const char *brand = "", const bool alcoholic = false) : Product(name, price) {
        strcpy(this->brand, brand);
        this->alcoholic = alcoholic;
    }
    float discount_price() {
        if (this->alcoholic && (getPrice() / Discount::euro > 20))
            return 0.95 * getPrice();
        if (!this->alcoholic && (strcmp(this->brand, "Coca-Cola") == 0))
            return 0.90 * getPrice();
        return getPrice();
    }
    float price() { return getPrice(); }
    void print_rule() {
        cout << "All alcohol drinks with price > 20 euros have discount of 5%, while alcohol free Coca-Cola have discount of 10% " << endl;
    }
};

float total_discount(Discount **d, int n) {
    float discount = 0;
    for (int i = 0; i < n; ++i) {
        discount += d[i]->discount_price();
        cout << "Original price: " << d[i]->price() << endl;
        cout << "Discounted: " << d[i]->discount_price() << endl;
        d[i]->print_rule();
    }
    return discount;
}

int main() {
    int n = 0;
    float newPrice;
    Discount **d = new Discount*[10];
    d[n++] = new FoodProduct("leb", 30);
    d[n++] = new Drinks("viski", 1350, "Jack Daniel's", true);
    d[n++] = new FoodProduct("sirenje", 390, 105);
    d[n++] = new Drinks("votka", 850, "Finlandia", true);
    d[n++] = new Cosmetics("krema", 720, 100);
    d[n++] = new Drinks("sok", 50, "Coca-Cola", false);
    d[n++] = new Cosmetics("parfem", 3500, 50);

    cout << "Total price of all products is: " << total_discount(d, n) << endl;

    cout << "Changing the price of the cosmetic products" << endl;
    for (int i = 0; i < n; ++i) {
        Cosmetics* c = dynamic_cast<Cosmetics *>(d[i]);
        if (c != 0) {
            c->print();
            cin >> newPrice;
            try {
                c->changePrice(newPrice);
            }
            catch (NegativeValueException i) {
                i.print();
            }
        }
    }

    for (int i = 0; i < n; ++i) {
        delete d[i];
    }
    delete[] d;

    return 0;
}
