#include <stdio.h>

int main () {
    int points, grade = 0;
    printf("Enter points: \n");
    scanf("%d", &points);
    if (points < 0 || points > 100)
        printf("Invalid value for points!\n");
    else {
        if (points > 90) grade = 10;
        else if (points > 80) grade = 9;
        else if (points > 70) grade = 8;
        else if (points > 60) grade = 7;
        else if (points > 50) grade = 6;
        else grade = 5;
        char sign = ' ';
        int pc = points % 10;
        if (grade != 5) {
            if (pc >= 1 && pc <= 3) sign = '-';
            else if (grade != 10 && (pc >= 8 || pc == 0))
                sign = '+';
        }
        printf("Grade %d%c\n", grade, sign);
    }
    return 0;
}
