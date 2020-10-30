#include <stdio.h>
#include <string.h>
#define MAX 100

int is_valid_password(char *str) {
    int letters = 0;
    int digits = 0;
    int spec = 0;
    for (; *str; str++) {
        if (isalpha(*str))
            letters++;
        else if (isdigit(*str))
            digits++;
        else
            spec++;
    }
    return (letters > 0 && digits > 0 && spec > 0);
}

int main() {
    char s[MAX];
    gets(s);
    printf("%s ", s);
    if (is_valid_password(s))
        printf("is a valid password.");
    else
        printf("is NOT a valid password.");
    return 0;
}
