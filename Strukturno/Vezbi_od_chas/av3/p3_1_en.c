#include <stdio.h>

int main() {
    char ch;
    int rez;
    printf("Enter char: ");
    scanf("%c", &ch);
    rez = (ch >= 'a') && (ch <= 'z');
    printf("%d\n", rez);
    return 0;
}
