#include <stdio.h>

void cipher(char *word, int x) {
    if(*word == 0) return;
    if(isalpha(*word)) {
        char first = 'a';
        if(isupper(*word))
            first = 'A';
        *word = first + (*word + x -first) % 26;
    }
    cipher(++word, x);
}

int main() {
    int n, x;
    scanf("%d %d\n", &n, &x);
    int i;
    char word[80];
    for(i = 0; i < n; ++i) {
        gets(word);
        cipher(word, x);
        printf("%s\n", word);
    }
    return 0;
}
