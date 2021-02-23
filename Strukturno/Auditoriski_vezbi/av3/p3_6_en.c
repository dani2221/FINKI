#include <stdio.h>

int main() {
    float price, interest, amount;
    int installments;
    printf("Enter the product price: ");
    scanf("%f", &price);
    printf("Enter number of installments: ");
    scanf("%d", &installments);
    printf("Enter interest rate: ");
    scanf("%f", &interest);
    float total = price * (1 + interest / 100);
    printf("Installment amount: %.3f\n", total / installments);
    printf("Total payed amount: %.3f\n", total);
    return 0;
}
