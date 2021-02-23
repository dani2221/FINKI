#include <stdio.h>
#include <stdlib.h>
int is_vowel(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
}
int main() {
    int row = 0, total = 0;
    FILE *dat; char c;
    if ((dat = fopen("SP_example.txt", "r")) == NULL) {
        printf("The file `SP_example.txt` can not be opened");
        exit(-1);
    }
    int vowels = 0;
    while ((c = fgetc(dat)) != EOF) {
        if(is_vowel(tolower(c))) {
            ++vowels;
            ++total;
        }
        if (c == '\n') {
            if (vowels > 10) {
                row++;
            }
            vowels = 0;
        }
    }
    printf("Total of %d rows has more than 10 vowels\n", row);
    printf("The file has total %d vowels.\n", total);
    return 0;
}
