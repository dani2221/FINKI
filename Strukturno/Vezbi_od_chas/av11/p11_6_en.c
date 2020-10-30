#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
int main() {
    char c;
    int occurrences = 0;
    FILE *file;
    if ((file = fopen("dat.txt", "r")) == NULL) {
        printf("The file `dat.txt` can not be opened!\n");
        exit(-1);
    }
    char word[50];
    printf("Enter word for searching:");
    gets(word);
    int i = 0, counter = 0;
    while ((c = fgetc(file)) != EOF) {
        if (isdigit(c)) {
            if (c != word[i++]) {
                if (counter == strlen(word)) {
                    occurrences++;
                }
                counter = 0;
                i = 0;
            } else {
                counter++;
            }
        } else {
            if (counter == strlen(word)) {
                occurrences++;
            }
            counter = 0;
            i = 0;
        }
    }
    printf("The word `%s` occurs %d times in the file\n", word, occurrences);
    return 0;
}
