// Од стандарден влез се чита еден природен број n,
// по што се читаат n цели броеви. Да се најде најголемата разлика помеѓу два броја,
// од вака внесените броеви.


// ne raboti
// kako bez nizi?

#include <stdio.h>

int main(){
    int n,pred,maksRazlika=0;
    scanf("%d %d",&n,&pred);
    for(int i=1;i<n;i++){
        int a;
        scanf("%d",&a);
        if(a-pred>maksRazlika) maksRazlika = a-pred;
        if(pred-a>maksRazlika) maksRazlika = pred-a;
        pred = a;
    }
    printf("Najgolema razlika: %d",maksRazlika);
}