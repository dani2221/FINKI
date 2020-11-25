// Од тастатура се внесува цел број n, а потоа и n тројки природни броеви
// (должини на три отсечки). За секоја тројка должини треба да се испечати
// порака Moze ако со нив може да се конструира триаголник, во спротивно се
// печати Ne moze. На крај да се испечати редниот број на тројката броеви со која
// се конструира триаголникот со најголем периметар (се земаат предвид само
// тројките со кои може да се конструира триаголник), како и вредноста на
// најголемиот периметар. Ако постојат повеќе такви тројки броеви, да се
// испечати редниот број на последната. Се смета дека првата внесена тројка е со
// реден број еден, втората со реден број два итн.

#include <stdio.h>

int main(){
    int n,redenBroj=0,maks=0;
    scanf("%d",&n);
    for(int i=1; i<=n; i++){
        int a,b,c,perimetar;
        scanf("%d %d %d",&a,&b,&c);
        if(c>=a && c>=b){
            if(a+b>c){
                perimetar = a+b+c;
                printf("Moze");
            }else{
                printf("Ne moze");
                continue;
            }
        }
        else if(b>=c && b>=a){
            if(c+a>b){
                perimetar = a+b+c;
                printf("Moze");
            }else{
                printf("Ne moze");
                continue;
            }
        }
        else if(a>=c && a>=b){
            if(b+c>c){
                perimetar = a+b+c;
                printf("Moze");
            }else{
                printf("Ne moze");
                continue;
            }
        }
        if(perimetar>maks) {
            maks= perimetar;
            redenBroj = i;
        }
    }
    printf("Najgolem perimetar: %d,redenBroj: %d",maks,redenBroj);
    return 0;
}