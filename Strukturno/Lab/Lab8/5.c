// Да се напише функција letterFrequency (char * text, char letter) 
// што за даден letter ќе испечати информации која е релативната фреквенција
// на појавување на таа буква во текстуалната низа text (како мала и како голема буква).
// Текстот е ограничен на максимум 1000 карактери.
// Релативната фреквенција на буква се пресметува 
// како количник од бројот на појавувања на таа буква и вкупниот број на букви во текстот.

#include <stdio.h>
#include <string.h>
#include <ctype.h>

void letterFrequency (char * text, char letter){
    int brojac_mala = 0;
    int brojac_golema = 0;
    int length = strlen(text);
    for(int i=0;i<length;i++){
        if(*(text+i)==tolower(letter)) brojac_mala++;
        if(*(text+i)==toupper(letter)) brojac_golema++;
    }
    //bidejki fgets funkcijata stava \n znak na kraj treba da se odzeme toj od goleminata
    length--;
    printf("%c -> %.3lf%%\n",tolower(letter),((double)brojac_mala/length)*100);
    printf("%c -> %.3lf%%\n",toupper(letter),((double)brojac_golema/length)*100);
} 

int main(){
    char *text;
    char c;
    
    fgets(text,1024,stdin);
    scanf("%c",&c);
    letterFrequency(text,c);
    return 0;
}