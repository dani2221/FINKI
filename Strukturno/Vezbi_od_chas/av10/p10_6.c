#include <stdio.h>
#include <string.h>
#define MAX 100

int e_validna_lozinka(char *str) {
    int bukvi = 0, cifri = 0, spec = 0;
    for (; *str; str++) {
        if (isalpha(*str))
            bukvi++;
        else if (isdigit(*str))
            cifri++;
        else
            spec++;
    }
    return (bukvi > 0 && cifri > 0 && spec > 0);
}

int main() {
    char s[MAX];
    gets(s);
    printf("%s ", s);
    if (e_validna_lozinka(s))
        printf("e validna lozinka.");
    else
        printf("NE e validna lozinka.");
    return 0;
}
