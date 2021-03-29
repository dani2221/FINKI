// Да се напише класа за матрица. Во класата се чуваат елементите од матрицата од тип float 
// (матрица со максимална димензија [10]x[10]) и големината на матрицата (број на редици и колони).
// За оваа класа да се преоптоварат следните оператори:
// оператор + за собирање матрица со број
// оператор - за одземање на матрици
// оператор * за множење на матрици
// операторот >> за внесување на елементите на матрицата
// операторот << за печатење на елементите на матрицата
// Во главната функција да се креираат објекти A, B и C со подразбирливиот конструктор на класата Matrica.
// Од стандарден влез да се прочитаат нивните вредности. 
// Да се отпечати вредноста на изразот A-(B*C)+2 на стандарден излез.
// Да се претпостави дека секогаш матриците ќе бидат квадратни со ист број на редици и колони.

#include <iostream>
#include <cstring>
using namespace std;

class Matrica{
    private:
        float matrica[10][10];
        int broj;
    public:
        Matrica(int num){
            this->broj=10;
            for(int i=0;i<broj;i++){
                for(int j=0;j<broj;j++){
                    this->matrica[i][j]=num;
                }
            }
        }
        Matrica(){
            this->broj=0;
        }
        Matrica &operator+(const Matrica &m){
            for(int i=0;i<broj;i++){
                for(int j=0;j<broj;j++){
                    this->matrica[i][j]+=m.matrica[i][j];
                }
            }
            return *this;
        }
        Matrica &operator-(const Matrica &m){
            for(int i=0;i<broj;i++){
                for(int j=0;j<broj;j++){
                    this->matrica[i][j]-=m.matrica[i][j];
                }
            }
            return *this;
        }
        Matrica &operator*(const Matrica &m){
            float temp[broj][broj];
            for(int i=0;i<broj;i++){
                for(int j=0;j<broj;j++){
                    int zbir = 0;
                    for(int k=0;k<broj;k++){
                        zbir+=(this->matrica[i][k]*m.matrica[k][j]);
                    }
                    temp[i][j]=zbir;
                }
            }
            for(int i=0;i<broj;i++){
                for(int j=0;j<broj;j++){
                    this->matrica[i][j]=temp[i][j];
                }
            }
            return *this;
        }
        friend istream &operator>>(istream &in, Matrica &m){
            int br;
            in>>br;
            in>>br;
            m.broj=br;
            float temp;
            for(int i=0;i<br;i++){
                for(int j=0;j<br;j++){
                    in>>temp;
                    m.matrica[i][j]=temp;
                }
            }
            return in;
        }
        friend ostream &operator<<(ostream &o, Matrica &m){
            for(int i=0;i<m.broj;i++){
                for(int j=0;j<m.broj;j++){
                    o<<m.matrica[i][j]<<" ";
                }
                o<<endl;
            }
            return o;
        }
};


int main()
{
    Matrica A,B,C;
    cin>>A>>B>>C;
    Matrica D=B*C;
    Matrica R=A-D+2;
    cout<<R;
}