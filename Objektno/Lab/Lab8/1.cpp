#include <iostream>

using namespace std;

class Shape {
public:
    virtual void draw() {
        cout << "Drawing a shape.\n";
    }
};

class Rectangle: public Shape{
    public:
        void draw(){
            cout<<"Drawing a rectangle.\n";
        }
};

int main() {
    Shape* shape;

    // тука инстанцирајте објект од класата Shape и покажувачот shape нека покажува кон него
    Shape sh;
    shape = &sh;
    
    shape->draw();

    // сега инстанцирајте објект од класата Rectangle и покажувачот shape нека покажува кон него
    Rectangle rec;
    shape = &rec;
    shape->draw();
    // повторно повикајте ја функцијата draw() на shape

    return 0;
}