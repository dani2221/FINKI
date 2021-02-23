#include <iostream>
using namespace std;

int main () {
    cout << "Enter your age: ";
    int myAge ;
    cin >> myAge ;
    cout << "Enter your friend's age: ";
    int friendsAge ;
    cin >> friendsAge ;
    if (myAge > friendsAge)
        cout << "You are older.\n";
    else if (myAge < friendsAge)
        cout << "Your friend is older.\n";
    else
        cout << "You and your friend are same age.\n";
    return 0;
}
