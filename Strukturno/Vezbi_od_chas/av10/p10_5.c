#include <stdio.h>
#include <string.h>
#define MAX 100
int e_palindrom(char *str) {
    int i, n = strlen(str);
    for (i = 0; i < n / 2; i++)
        if (*(str + i) != *(str + n - 1 - i))
            return 0;;
    return 1;
}
// REKURZIVNO
int e_pal(char *str, int start, int end) {
    if (start >= end) return 1;
    if (str[start] == str[end])
        return e_pal(str, start + 1, end - 1);
    return 0;
}

int main() {
    char s[MAX];
    gets(s);
    printf("%s ", s);
    if (e_pal(s, 0, strlen(s) - 1))
        printf("e palindrom.");
    else
        printf("NE e palindrom.");
    return 0;
}
