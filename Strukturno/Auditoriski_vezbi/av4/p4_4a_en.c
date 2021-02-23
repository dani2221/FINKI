#include <stdio.h>

int main () {
    int points, grade = 0;
    printf("Enter points: \n");
    scanf("%d", &points);
    if (points >= 0 && points <= 50) grade = 5;
    else if (points > 50 && points <= 60) grade = 6;
    else if (points > 60 && points <= 70) grade = 7;
    else if (points > 70 && points <= 80) grade = 8;
    else if (points > 80 && points <= 90) grade = 9;
    else if (points > 90 && points <= 100) grade = 10;
    else printf("Invalid value for points!\n");
    printf("Grade %d\n", grade);
    return 0;
}
