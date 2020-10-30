#include <stdio.h>

int main () {
	float x, y;
	printf("Vnesi koordinati \n");
	scanf("%f %f", &x, &y);
	if (x > 0 && y > 0)
		printf("I kvadrant.\n");
	if (x > 0 && y < 0)
		printf("IV kvadrant.\n");
	if (x < 0 && y > 0)
		printf("II kvadrant.\n");
	if (x < 0 && y < 0)
		printf("III kvadrant.\n");
	return 0;
}
