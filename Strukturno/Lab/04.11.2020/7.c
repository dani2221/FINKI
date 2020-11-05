// Од стандарден влез се чита еден природен број n,
// по што се читаат n цели броеви. Да се најде најголемата разлика помеѓу два броја,
// од вака внесените броеви.



#include <stdio.h>

int main(){
    int n,maks=0,min=100;
    scanf("%d",&n);
    for(int i=0;i<n;i++){
        int a;
        scanf("%d",&a);
        if(a>maks) maks = a;
        if(a<min) min = a;
    }
    printf("Najgolema razlika: %d",maks);
    return 0;
}