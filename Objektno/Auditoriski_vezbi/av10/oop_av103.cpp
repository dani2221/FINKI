#include <iostream>
#include <cstring>
using namespace std;

//klasa za iskluchokot
class NegativeValueException{
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
	void changePrice(float price){
		if (price < 0) throw NegativeValueException("Vnesena e negativna vrednost za cena!\n");
		this->price = price;
	}
};

class Cosmetics : public Product, public Discount {
private:
	int weight;
public:
	Cosmetics(const char *name = "", const float price = 0,
		const int weight = 0) :Product(name, price){
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
	void print_rule(){
		cout << "Site kozmeticki proizvodi poskapi od 5 evra imaat popust od 12%, dodeka pak onie koi se poskapi od 20 dolari imaat popust 14%" << endl;
	}
};

class FoodProduct : public Product, public Discount{
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

	void print_rule(){
		cout << "Nema popust za proizvodite od tip na hrana" << endl;
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
	void print_rule(){
		cout << "Site alkoholni pijaloci poskapi od 20 evra imaat popust od 5 % , dodeka pak nealkoholnite od brendot Coca - Cola imaat popust od 10 % "<<endl;
	}
};

float total_discount(Discount **d, int n) {
	float discount = 0;
	for (int i = 0; i < n; ++i) {
		discount += d[i]->discount_price();
		cout << "Prvicna cena: " << d[i]->price() << endl;
		cout << "So popust: " << d[i]->discount_price() << endl;
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

	cout << "Vkupnata cena na site proizvodi e: " << total_discount(d, n) << endl;
	
	//se menuva cenata na site Kozmeticki proizvodi
	cout << "Promena na cenata na kozmetickite proizvodi " << endl;
	for (int i = 0; i < n; ++i) {
		Cosmetics* c = dynamic_cast<Cosmetics *>(d[i]);
		if (c != 0){
			c->print();
			cin >> newPrice;
			try{
				c->changePrice(newPrice);
			}
			catch (NegativeValueException i){
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