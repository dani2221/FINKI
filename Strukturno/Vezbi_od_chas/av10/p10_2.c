#include <stdio.h>
#define MAX 100

int length(char *s) {
    int i, len = 0;
    for (i = 0; s[i] != '\0'; i++)
        len++;
    return len;
}

int length_r(char *s) {
    if (*s == '\0')
        return 0;
    return 1 + length_r(s + 1);
}

int main() {
    char s[MAX];
    gets(s);
    printf("Dolzhina: %d i %d\n", length(s), length_r(s));
    return 0;
}
