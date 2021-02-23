#include <stdio.h>

int main() {
    float price;
    printf("Enter the product price: ");
    scanf("%f", &price);
    printf("The total price of the product is: %.2f\n", price * 1.18);
    return 0;
}
