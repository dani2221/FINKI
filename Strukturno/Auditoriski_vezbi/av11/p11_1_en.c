#include <stdio.h>

int is_letter(char c) {
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
}

int is_vowel(char c) {
    c = tolower(c);
    switch (c) {
    case 'a':
    case 'e':
    case 'i':
    case 'o':
    case 'u':
        return 1;
    default:
        return 0;
    }
}

int main() {
    char c;
    int consonants = 0, vowels = 0;
    FILE *dat;
    // Opening file for reading
    if ((dat = fopen("text.txt", "r")) == NULL) {
        printf("The file `text.txt` can not be opened.\n");
        return -1;
    }
    // Reading char by char until EndOfFile (EOF)
    while ((c = fgetc(dat)) != EOF) {
        if (is_letter(c)) {
            if (is_vowel(c))
                vowels++;
            else
                consonants++;
        }
    }
    fclose(dat);
    printf("Ratio vowels/consonants: %d/%d = %5.2f\n", vowels, consonants,
           (float) vowels / consonants);
    return 0;
}
