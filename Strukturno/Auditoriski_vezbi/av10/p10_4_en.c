#include <stdio.h>
#include <string.h>
#define MAX 100

int substring(char *s1, char *s2) {
    int i;
    int d1 = strlen(s1);
    int d2 = strlen(s2);
    if (d1 > d2)
        return 0;
    for (i = 0; i <= d2 - d1; i++)
        if (strncmp(s1, s2 + i, d1) == 0)
            return 1;
    return 0;
}
int main() {
    char s1[MAX], s2[MAX];
    gets(s1);
    gets(s2);
    if (substring(s1, s2))
        printf("%s is substring of %s\n", s1, s2);
    else
        printf("%s is NOT a substring of %s\n", s1, s2);
    return 0;
}
