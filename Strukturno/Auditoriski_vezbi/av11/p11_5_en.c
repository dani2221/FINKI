#include <stdio.h>
#include <ctype.h>
#define SIZE 21

int has_more_than_2eq(char *w) {
    char *c;
    int equal;
    while (*w) {
        c = w + 1;
        equal = 1;
        while (*c) {
            if (tolower(*w) == tolower(*c))
                equal++;
            c++;
        }
        if (equal > 2)
            return 1;
        w++;
    }
    return 0;
}
int main() {
    char word[SIZE];
    FILE *f;
    int words_count = 0;
    if ((f = fopen("words.txt", "r")) == NULL) {
        printf("The file `words.txt` can not be opened.\n");
        return -1;
    }
    while (fgets(word, SIZE, f) != NULL) {
        if (has_more_than_2eq(word)) {
            puts(word);
            words_count++;
        }
    }
    printf("\nTotal %d words.\n", words_count);
    fclose(f);
    return 0;
}
