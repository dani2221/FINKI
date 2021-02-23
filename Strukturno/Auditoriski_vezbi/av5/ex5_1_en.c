#include <stdio.h>
int main() {
    char answer;
    printf("Is SP an easy course? (Y/N): ");
    //answer=getchar();
    scanf("%c", &answer);

    switch (answer) {
        case 'Y':
        case 'y': printf("I think so too!\n");
            break;
        case 'N':
        case 'n': printf("Really?\n");
            break;
        default:
            printf("Is this Yes or No?\n");
    }

    return 0;
}
