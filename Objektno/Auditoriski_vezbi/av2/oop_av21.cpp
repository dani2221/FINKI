#include <iostream>
using namespace std;

int main () {
    cout << "Vnesi gi tvoite godini : ";
    int myAge ;
    cin >> myAge ;
    cout << "Vnesi gi godinite na prijatel : ";
    int friendsAge ;
    cin >> friendsAge ;
    if (myAge > friendsAge)
        cout << "Ti se postar.\n";
    else if (myAge < friendsAge)
        cout << "Prijatelot e postar.\n";
    else
        cout << "Ti i prijatelot imate godini.\n";
    return 0;
}
