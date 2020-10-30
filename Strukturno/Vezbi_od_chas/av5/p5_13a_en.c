#include <stdio.h>
int main() {
    int grade, sum = 0, num_grades = 0, failed = 0;
    while (scanf("%d", &grade) == 1) {
        while (grade < 5 || grade > 10) {
            printf("Enter grade from 5 to 10: ");
            if (scanf("%d", &grade) != 1) break;
        }
        /* counting */
        if (grade >= 6 && grade <= 10) {
            sum += grade;
            num_grades++;
        }
        else /*if (grade == 5) */
            failed++;
    }
    if (num_grades == 0)
        printf("No passed exams");
    else
        printf("Average is %4.2f\n", (float)sum / num_grades);
    if (failed != 0)
        printf("Failed on %d exams\n", failed);
    else
        printf("No failed exams\n");
    return 0;
}
