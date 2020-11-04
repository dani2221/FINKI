// Од стандарден влез се чита еден природен број n,
// по што се читаат n цели броеви. Да се најде најголемата разлика помеѓу два броја,
// од вака внесените броеви.


// neznam kako bez nizi???

#include <stdio.h>

int main(){
    int n;
    scanf("%d",&n);
    int niza[n];
    for(int i=0;i<n;i++){
        int a;
        scanf("%d",&a);
        niza[i]=a;
    }
    int maksRazlika = 0;
    for(int i=0;i<n-1;i++){
        for(int j=1;j<n;j++){
            if(niza[i]-niza[j]>maksRazlika) maksRazlika = niza[i]-niza[j];
            if(niza[j]-niza[i]>maksRazlika) maksRazlika = niza[j]-niza[i];
        }
    }
    printf("Najgolema razlika: %d",maksRazlika);
    return 0;
}