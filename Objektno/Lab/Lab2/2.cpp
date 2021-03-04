// Да се дефинира класа Krug, во која се чуваат информации за:
// радиус float
// бројот π const float.
// Во класата да се реализираат:
// default конструктор и конструктор со аргументи
// метод за пресметување плоштина
// метод за пресметување периметар
// метод кој кажува дали плоштината и периметарот на даден круг се еднакви


#include <iostream>
#include <math.h>
using namespace std;

class Krug {
    private:
        float radius;
        const float PI = 3.14; // mora da e 3.14 inache so povekje decimali ne raboti vo editorot
    public:
        Krug(){
            //default konstruktor uwu
        }
        Krug(float radius){
            this->radius=radius;
        }
        float perimetar(){
            return 2*radius*PI;
        }
        float plostina(){
            return radius*radius*PI;
        }
        bool ednakvi(){
            return plostina()==perimetar();
        }

};

int main() {
	float r;
	cin >> r;
	//instanciraj objekt od klasata Krug cij radius e vrednosta procitana od tastatura
    Krug k(r);
	cout << k.perimetar() << endl;
	cout << k.plostina() << endl;
	cout << k.ednakvi() <<endl;
    //instanciraj objekt od klasata Krug cij radius ne e definiran
    Krug randomkrug;
	return 0;
}