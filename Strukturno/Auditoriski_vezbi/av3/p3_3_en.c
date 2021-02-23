#include <stdio.h>

int main() {
    int x = 1, y = 2, z = 3, r;
    r = (x < y || y < z++);
    printf("r = %d, z = %d\n", r, z);

    r = (x > y && y < z++);
    printf("r = %d, z = %d\n", r, z);

    return 0;
}
