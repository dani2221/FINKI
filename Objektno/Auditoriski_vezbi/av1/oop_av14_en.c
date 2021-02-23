#include <stdio.h>
#include <string.h>

struct student {
    char first_name[15];
    char last_name[20];
    int number;
    int points;
};

void norm(char *s) {
    // First letter uppercase, others lowercase
    *s = toupper(*s);
    while (*(++s) != '\0')
        *s = tolower(*s);
}

void sort(struct student a[], int n) {
    int i, j;
    struct student s;
    for (i = 0; i < n; i++)
        for (j = 0; j < n - i - 1; j++)
            if (a[j].points < a[j + 1].points) {
                s = a[j];
                a[j] = a[j + 1];
                a[j + 1] = s;
            }
}

int main() {
    struct student st[50];
    int i, n;
    scanf("%d", &n);
    for (i = 0; i < n; ++i) {       
        scanf("%s", &st[i].first_name);     
        scanf("%s", &st[i].last_name);     
        scanf("%d", &st[i].number);     
        int j, zadaca;
        st[i].points = 0;
        for(j = 0; j < 4; j++) {
            scanf("%d", &zadaca);
            st[i].points += zadaca;
        }
        norm(st[i].first_name);
        norm(st[i].last_name);
    }
    sort(st, n);
    for (i = 0; i < n; i++) {
        printf("%d. %s %s\t%d\t%d\n", i + 1, st[i].first_name, st[i].last_name, st[i].number, st[i].points);
    }
    return 0;
}
