#include <stdio.h>

int main() {
    char c;
    printf("Vnesete golema bukva: ");
    scanf("%c", &c);
    printf("%c malo se pishuva: '%c'\n", c, c + ('a' - 'A'));
    return 0;
}

