// За програмски јазик C.
// Да се надополни програмата со следните барања:
// да се креира структура на точка во тродимензионален простор и да се напише функција 
// која ќе го пресметува растојанието помеѓу две такви точки.
// да се напише функција која како аргумент прима три точки во дводимензионален простор 
// и ќе проверува дали тие точки лежат на иста права.

#include <stdio.h>
#include <math.h>

typedef struct t2d
{
    float x,y;
} tocka2D;

typedef struct t3d
{
    float x,y,z;
} tocka3D;

float rastojanie(tocka2D t1, tocka2D t2){
    return (float) sqrt((t1.x-t2.x)*(t1.x-t2.x)+(t1.y-t2.y)*(t1.y-t2.y));
}
float rastojanie3D(tocka3D t1, tocka3D t2){
    return (float) sqrt((t1.x-t2.x)*(t1.x-t2.x)+(t1.y-t2.y)*(t1.y-t2.y)+(t1.z-t2.z)*(t1.z-t2.z));
}

int ista_prava(tocka2D t1, tocka2D t2, tocka2D t3){
    float k1 = (t2.y-t1.y)/(t2.x-t1.x);
    float k2 = (t3.y-t1.y)/(t3.x-t1.x);
    if(k1==k2) return 1;
    return 0;
}




int main() {
    float x1, y1, x2, y2;
    scanf("%f %f", &x1, &y1);
	scanf("%f %f", &x2, &y2);
	tocka2D t1 = { x1, y1 };
	tocka2D t2 = { x2, y2 };
	printf("%.2f\n", rastojanie(t1, t2));
    float z1, z2;
    scanf("%f %f", &z1, &z2);
    tocka3D t3 = {x1, y1, z1};
    tocka3D t4 = {x2, y2, z2};
    printf("%.2f\n", rastojanie3D(t3, t4));
    tocka2D t5 = {z1, z2};
    printf("%d\n", ista_prava(t1, t2, t5));
	return 0;
}
