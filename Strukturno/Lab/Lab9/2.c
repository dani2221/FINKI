// Од датотека "input.txt" да се вчита квадратна матрица,
// така што прво ќе се прочита број n што означува колку редови и колони има матрица, 
// а потоа да се вчитаат сите n x n елементи од матрицата.
// Во датотеката "output.txt" да се запише матрицата така што ќе се запишат 
// само елементите над главната дијагонала, 
// но ќе имаат вредност колку сумата на елементите на главната дијагонала. 
// Печатењето да се прави со три места за бројот за елементите над главната дијагонала, 
// а за останатите да се испечатат три празни места, 
// со цел да се добие посакуваниот излез како во тест примерите.

#include <stdio.h>
#include <string.h>

void writeToFile() {
    FILE *f = fopen("input.txt", "w");
    char c;
    while((c = getchar()) != '#') {
        fputc(c, f);
    }
    fclose(f);
}

void printFile() {

    FILE *f=fopen("output.txt","r");
    char line[100];
    while(!feof(f)){
        fgets(line,100,f);
        if (feof(f))
            break;
        printf("%s",line);
    }
    fclose(f);
}

int main() {
    writeToFile();
    
    FILE *in;
    FILE *out;
    if(!(in=fopen("input.txt","r")) || !(out=fopen("output.txt","w"))){
        printf("problem so chitanje files");
        return -1;
    }

    int size, suma=0;
    fscanf(in,"%d",&size);
    int matrica[size][size];

    for(int i=0; i<size;i++){
        for(int j=0;j<size;j++){
            fscanf(in,"%d",&matrica[i][j]);
            if(i==j) suma+=matrica[i][j];
        }
    }
    for(int i=0; i<size;i++){
        for(int j=0;j<size;j++){
            if(i<j) fprintf(out,"%03d ",suma);
            else fprintf(out,"    ");
        }
        fprintf(out,"\n");
    }
    fclose(in);
    fclose(out);
    
    printFile();
    return 0;
}