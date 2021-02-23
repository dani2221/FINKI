#include <stdio.h>
#include <math.h>

typedef struct complex_number {
    float real;
    float imag;
} comp;

comp add(comp a, comp b) {
    comp c = a;
    c.real += b.real;
    c.imag += b.imag;
    return c;
}

comp subtract(comp *pok1, comp *pok2) {
    comp c = *pok1;
    c.real -= (*pok2).real;
    c.imag -= (*pok2).imag;
    return c;
}

void multiply(comp a, comp b, comp *c) {
    c->real = a.real * b.real - a.imag * b.imag;
    c->imag = a.real * b.imag + a.imag * b.real;
}

void print(comp *pok) {
    printf("%.2f", pok->real);
    if (pok->imag >= 0)
        printf("+j%.2f\n", pok->imag);
    else
        printf("-j%.2f\n", fabs(pok->imag));
}

int main() {
    comp a, b, c;
    scanf("%f %f", &a.real, &a.imag);   
    scanf("%f %f", &b.real, &b.imag);   
    print(&a);
    print(&b);
    printf("a + b\n");
    c = add(a, b);
    print(&c);
    printf("a - b\n");
    c = subtract(&a, &b);
    print(&c);
    printf("a * b\n");
    multiply(a, b, &c);
    print(&c);
    return 0;
}
