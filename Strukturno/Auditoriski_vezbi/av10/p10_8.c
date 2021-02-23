#include <stdio.h>
#include <string.h>
#define MAX 100

void trim(char *s) {
    char *d = s;
    while (isspace(*s++))
        ;
    s--;
    while (*d++ = *s++)
        ;
    d--;
    while (isspace(*--d))
        *d = 0;
}
int main() {
    char s[MAX];
    gets(s);
    printf("[%s] -> ", s);
    trim(s);
    printf("[%s]", s);
    return 0;
}
