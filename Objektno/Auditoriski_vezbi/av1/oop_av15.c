#include<stdio.h>

typedef struct city {
	char name[30];
	long population;
} city;

typedef struct president {
	char name[20];
	char party[20];
} pres;

typedef struct country {
	char name[30];
	pres president;
	long population;
	city capital;
} country;

int main() {
	country d[20];
	int n, i, maxi, max;
	scanf("%d", &n);
	for (i = 0; i < n; ++i) {
		scanf("%s", &d[i].name);
		printf("president:\n");
		scanf("%s", &d[i].president.name);
		scanf("%s", &d[i].president.party);
		scanf("%d", &d[i].population);
		scanf("%s", &d[i].capital.name);
		scanf("%d", &d[i].capital.population);
	}
	maxi = 0;
	max = d[maxi].capital.population;
	for (i = 0; i < n; ++i)
		if (d[i].capital.population > max) {
			max = d[i].capital.population;
			maxi = i;
		}
	printf(
			"Name of the president of the country with the largest capital is: %s\n",
			d[maxi].president.name);
	return 0;
}
