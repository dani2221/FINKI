#include <stdio.h>
int main() {
    int value, choice;
    printf("Enter initial value: ");
    scanf("%d", &value);

    do {
        do {
            printf("Menu:\n");
            printf("1 - increase\n");
            printf("2 - decrease\n");
            printf("3 - double\n");
            printf("0 - END\n");
            printf("choice : ");
            scanf("%d", &choice);
        } while ((choice < 0) || (choice > 3));
		switch (choice) {
			case 1: value++; break;
			case 2: value--; break;
			case 3: value *= 2; break;
			case 0: printf("End\n");            break;
			default: printf("Invalid choice!\n"); break;
			}
			printf("value = %d\n", value);
	  }
	  while (choice != 0);
  return 0;
}
