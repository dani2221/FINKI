#include <stdio.h> 
#include <math.h>
int main() 
{
    int faktori = 1, faktorb = 1;
    double pi = 1, clen = 1;
    /* vo ciklusot se presmetuva pi/2 */
    do
    {
        faktori += 2;
        clen *= (double)faktorb / faktori;
        faktorb++;
        pi += clen;
    } while (clen > 1e-6); 
    

/*    do
        pi += clen*= (double)faktorb++ / (faktori+=2);
    while (clen > 1e-6); */
        
    pi *= 2;
    printf("pi (priblizno) = %10.8lf \t pi = %10.8lf\n", pi, M_PI);

    return 0;
}