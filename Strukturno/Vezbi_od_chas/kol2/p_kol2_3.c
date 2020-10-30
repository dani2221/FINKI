#include <stdio.h>
#include <ctype.h>
#include <string.h>

int main() {
    FILE *f = fopen("dat.txt", "r");
    int br = 0, l, flag = 0, start = 0, end = 0, m_start = 0, m_end = 0;
    int i = 0;
    char c;
    char word[100];
    char linija[100], max_linija[100];
    int max = 0;

    while(fgets(linija,100,f)!=NULL) {
        l = strlen(linija);
        flag=0;
        	for(i=0;i<l;i++){
            	if(isdigit(linija[i])){
                	if(!flag){
                		start=i;
                		flag = 1;
                	}
            		end=i;
            	}
        	}
        if(start!=end){
            if(l >= max){
            	max = l;
        		strcpy(max_linija,linija);
                m_start = start;
                m_end = end;
            }
        }
    }
    strncpy(word, max_linija+m_start, m_end-m_start+1);
    word[m_end-m_start+1]='\0';
    printf("%s\n",word);

    fclose(f);
    return 0;
}
