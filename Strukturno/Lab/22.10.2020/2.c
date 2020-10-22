//da se presmeta BMI od masa i visina
// BMI = masa/(visina*visina)


#include <stdio.h>

int main(){
    float masa, visina;
    scanf("%f%f",&masa,&visina);

    visina/=100; //od cm vo m

    printf("%.2f",masa/(visina*visina));

    return 0;
}