#include <stdio.h>
#define MAX 81

int main() {
    char row[MAX], *c;
    FILE *input, *output;
    if ((input = fopen("input.txt", "r")) == NULL) {
        printf("The file `%s` can not be opened.\n", "input.txt");
        return -1;
    }
    if ((output = fopen("output.txt", "w")) == NULL) {
        printf("The file `%s` can not be opened.\n", "output.txt");
        return -1;
    }

    while ((fgets(row, MAX, input)) != NULL) {
        int br = strlen(row);
        fprintf(output, "%d\n%s", br, row);
    }
    fclose(input);
    fclose(output);
    return 0;
}
