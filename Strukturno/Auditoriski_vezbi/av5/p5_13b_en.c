#include <stdio.h>
int main() {
    int grade, sum = 0, num_grades = 0, failed = 0;
    while (scanf("%d", &grade) == 1) {
        switch (grade) {
            case 6:
            case 7:
            case 8:
            case 9:
            case 10: sum += grade;
                     num_grades++; break;
            case 5:  failed++; break;
            default: printf("Enter grade from 5 to 10: "); break;
        }
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
