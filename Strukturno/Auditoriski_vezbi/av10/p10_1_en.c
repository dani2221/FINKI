#include <stdio.h>
#define MAX 100
int count_char(char *str, char c) {
    int total = 0;
    while (*str != '\0') {
        total += (*str == c);
        str++;
    }
    return total;
}
int main() {
    char s[MAX], c;
    gets(s);
    c = getchar();
    printf("%d\n", count_char(s, c));
    return 0;
}
