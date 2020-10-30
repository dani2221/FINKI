#include <stdio.h>
#include <string.h>
#define MAX 100

int main() {
    char s[MAX], dest[MAX];
    int position, length;
    gets(s);
    scanf("%d %d", &position, &length);
    if (position <= strlen(s)) {
        strncpy(dest, s + position - 1, length);
        dest[length] = '\0';
        printf("Result: ");
        puts(dest);
    } else
        printf("Invalid input, the read string has only %d characters.\n", strlen(s));
    return 0;
}
