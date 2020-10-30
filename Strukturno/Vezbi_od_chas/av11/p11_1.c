#include <stdio.h>

int e_bukva(char c) {
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
}

int e_samoglaska(char c) {
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
    int soglaski = 0, samoglaski = 0;
    FILE *dat;
    // Otvoranje na datoteka za chitanje
    if ((dat = fopen("text.txt", "r")) == NULL) {
        printf("Datotekata text.txt ne mozhe da se otvori.\n");
        return -1;
    }
    // Chitanje znak po znak se' dodeka ne se prochita EndOfFile (EOF)
    while ((c = fgetc(dat)) != EOF) {
        if (e_bukva(c)) {
            if (e_samoglaska(c))
                samoglaski++;
            else
                soglaski++;
        }
    }
    fclose(dat);
    printf("Odnos samoglaski/soglaski: %d/%d = %5.2f\n", samoglaski, soglaski,
           (float) samoglaski / soglaski);
    return 0;
}
