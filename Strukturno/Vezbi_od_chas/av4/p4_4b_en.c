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
        printf("Grade %d\n", grade);
    }
    return 0;
}
