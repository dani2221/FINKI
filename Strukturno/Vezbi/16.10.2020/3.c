//da se najde kvadratot od plostina i perimetar na raznostran trijagolnik

#include <stdio.h>

int main(){
    float a=10,b=20,c=30;
    float l = a+b+c;
    float s = l/2.0;
    float p = s*(s-a)*(s-b)*(s-c);
    printf("perimetarot e %f, a kvadratot od plostinata e %f",l,p);
    return 0;
}