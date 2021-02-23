#include <stdio.h>
#define MAX 100
int count_char(char *str, char c) {
    int vkupno = 0;
    while (*str != '\0') {
        vkupno += (*str == c);
        str++;
    }
    return vkupno;
}
int main() {
    char s[MAX], c;
    gets(s);
    c = getchar();
    printf("%d\n", count_char(s, c));
    return 0;
}
