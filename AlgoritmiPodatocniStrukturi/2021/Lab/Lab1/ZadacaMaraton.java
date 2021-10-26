import java.util.Arrays;
import java.util.Scanner;

public class ZadacaMaraton {
//    Да се дефинира интерфејс IMaraton со методи:
//
//    Atleticar najdobroVreme() - го враќа победникот на маратонот
//    int atleticariOd(String s) - го враќа бројот на атлетичари со земја на потекло s.
//    Да се дефинира класа Atleticar. За секој атлетичар се чуваат податоци за име String, пол String, возраст int и време на истрчување изразено во секунди double и земја на потекло String. За оваа класа треба да се на располагање следните методи:
//
//    конструктори (default и со параметри)
//    set и get методи
//    toString() - формат: име / возраст / земја на потекло / време на истрчување (сите параметри одделени со празно место)
//    Да се дефинира класа Maraton што го имплементира интерфејсот IMaraton. За секој маратонот се чуваат податоци за местото на одржување String, година int, низа од атлетичари Atleticar[]. За оваа класа да се имплементираат:
//
//    конструктори (default и со параметри)
//    set и get методи
//    toString() - место на одржување / година / атлетичарите учесници на маратонот (сите параметри одделени со нов ред)
//    najdobroVreme()
//    atleticariOd(String s)

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        Atleticar[] atleticari = new Atleticar[n];

        String ime;
        String pol;
        int vozrast;
        double vreme;
        String zemja;

        input.nextLine();

        for(int i=0;i<n;i++)
        {
            ime = input.nextLine();
            pol = input.nextLine();
            vozrast = input.nextInt();
            vreme = input.nextDouble();
            input.nextLine();
            zemja = input.nextLine();
            atleticari[i]=new Atleticar(ime,pol,vozrast,vreme,zemja);
        }

        String mesto;
        int godina;
        String zemjaP;
        mesto = input.nextLine();
        godina = input.nextInt();
        input.nextLine();

        Maraton m1 = new Maraton(mesto, godina, atleticari);
        System.out.print(m1.toString());

        zemjaP = input.nextLine();
        System.out.println("Prvo mesto: " + m1.najdobroVreme().toString());
        System.out.println("\nIma vkupno " + m1.atleticariOd(zemjaP) + " atleticar/i od " + zemjaP);
    }
}

class Atleticar {
    private String ime;
    private String pol;
    private int vozrast;
    private double vreme;
    private String poteklo;

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public void setVozrast(int vozrast) {
        this.vozrast = vozrast;
    }

    public void setVreme(double vreme) {
        this.vreme = vreme;
    }

    public void setPoteklo(String poteklo) {
        this.poteklo = poteklo;
    }

    public String getIme() {
        return ime;
    }

    public String getPol() {
        return pol;
    }

    public int getVozrast() {
        return vozrast;
    }

    public double getVreme() {
        return vreme;
    }

    public String getPoteklo() {
        return poteklo;
    }

    public Atleticar(String ime, String pol, int vozrast, double vreme, String poteklo) {
        this.ime = ime;
        this.pol = pol;
        this.vozrast = vozrast;
        this.vreme = vreme;
        this.poteklo = poteklo;
    }

    @Override
    public String toString() {
        return ime + "\n"+ vozrast  + "\n" + poteklo + "\n" + vreme;
    }

    public Atleticar() {
    }
}
interface IMaraton {
    Atleticar najdobroVreme();
    int atleticariOd(String s);
}
class Maraton implements IMaraton {

    private String mesto;
    private int godina;
    private Atleticar[] atleticari;

    @Override
    public String toString() {
        String str = mesto + "\n" + godina+ "\n";
        for(Atleticar at : atleticari) {
            str+=at.toString() +"\n";
        }
        return str;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public Atleticar[] getAtleticari() {
        return atleticari;
    }

    public void setAtleticari(Atleticar[] atleticari) {
        this.atleticari = atleticari;
    }

    public Maraton(String mesto, int godina, Atleticar[] atleticari) {
        this.mesto = mesto;
        this.godina = godina;
        this.atleticari = atleticari;
    }

    public Maraton() {
    }

    @Override
    public Atleticar najdobroVreme() {
        Atleticar najdobar = atleticari[0];
        for(Atleticar at : atleticari) {
            if(at.getVreme()<najdobar.getVreme()) najdobar = at;
        }
        return najdobar;
    }

    @Override
    public int atleticariOd(String s) {
        int zemja = 0;
        for(Atleticar at : atleticari) {
            if(at.getPoteklo().compareTo(s)==0) zemja++;
        }
        return zemja;
    }
}