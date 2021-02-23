#include <stdio.h>
int main() {
    char character;
    printf("Enter character: ");
    character = getchar(); /* scanf("%c", &character); */

    switch (character) {
        case 'a': case 'A':
        case 'e': case 'E':
        case 'i': case 'I':
        case 'o': case 'O':
        case 'u': case 'U':
            printf("Vowel is entered: %c\n", character);
            break;
        case '0': case '1': case '2': case '3': case '4':
        case '5': case '6': case '7': case '8': case '9':
            printf("Digit is entered: %c\n", character);
            break;
        default:
            printf("Character is entered: %c\n", character);
    }

    return 0;
}


