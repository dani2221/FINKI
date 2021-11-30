package MojDDV2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MojDDVTest {

    public static void main(String[] args) {

        MojDDV mojDDV = new MojDDV();

        System.out.println("===READING RECORDS FROM INPUT STREAM===");
        mojDDV.readRecords(System.in);

        System.out.println("===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===");
        mojDDV.printTaxReturns(System.out);

        System.out.println("===PRINTING SUMMARY STATISTICS FOR TAX RETURNS TO OUTPUT STREAM===");
        mojDDV.printStatistics(System.out);

    }
}

class MojDDV {
    private List<Smetka> smetkiList;
    public static double povratok = 0.15;

    public void readRecords(InputStream in) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(in));
        smetkiList = bf.lines().map(line -> {
            try {
                return new Smetka(line);
            } catch (AmountNotAllowedException e) {
                System.out.println(e.getMessage());
                return new Smetka();
            }
        }).collect(Collectors.toList());
    }

    public void printTaxReturns(PrintStream out) {
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
        smetkiList.stream().filter(x -> x.getId()!=-1).forEach(x -> pw.println(x.toString()));
        pw.flush();
    }

    public void printStatistics(PrintStream out) {
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));

        double max = smetkiList.stream().filter(x -> x.getId()!=-1).mapToDouble(x -> x.taxReturn()).max().getAsDouble();
        double min = smetkiList.stream().filter(x -> x.getId()!=-1).mapToDouble(x -> x.taxReturn()).min().getAsDouble();
        double sum = smetkiList.stream().filter(x -> x.getId()!=-1).mapToDouble(x -> x.taxReturn()).sum();
        int count = smetkiList.stream().filter(x -> x.getId()!=-1).toArray().length;

        pw.println(String.format("min:\t%5.3f\nmax:\t%5.3f\nsum:\t%5.3f\ncount:\t%-5d\navg:\t%5.3f\n",min,max,sum,count,sum/count));
        pw.close();
    }
}

class Smetka {
    private int id;
    private List<Produkt> produktList;

    public Smetka() {
        id = -1;
    }

    public int getId() {
        return id;
    }

    public Smetka(String line) throws AmountNotAllowedException {
        id = Integer.parseInt(line.split("\\s+")[0]);
        produktList = new ArrayList<Produkt>();
        Arrays.stream(line.split("\\s+")).skip(1).forEach(x -> {
            try {
                int price = Integer.parseInt(x);
                produktList.add(new Produkt(price));
            }catch(NumberFormatException nf){
                Type t = Type.A;
                if(x.equals("B")) t = Type.B;
                if(x.equals("V")) t = Type.V;
                produktList.get(produktList.size()-1).setType(t);
            }
        });
        if(sum()>30000) throw new AmountNotAllowedException(sum());
    }

    public int sum() {
        return produktList.stream().mapToInt(x -> x.getPrice()).sum();
    }

    @Override
    public String toString() {
        return String.format("%10d\t%10d\t%10.5f",id,sum(),taxReturn());
    }

    public double taxReturn() {
        return produktList.stream().mapToDouble(x -> x.calculateReturn()).sum();
    }
}

enum Type{A,B,V}

class Produkt {
    private int price;
    private Type type;
    private double danok;

    public void setType(Type type) {
        this.type = type;
        if(type == Type.A) danok = 0.18;
        if(type == Type.B) danok = 0.05;
        if(type == Type.V) danok = 0;
    }

    public Produkt(int price) {
        this.price = price;
    }

    public double getDanok() {
        return danok;
    }

    public int getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }

    public double calculateReturn(){
        return getDanok()*getPrice()*MojDDV.povratok;
    }
}

class AmountNotAllowedException extends Exception{
    public AmountNotAllowedException(int sum) {
        super(String.format("Receipt with amount %d is not allowed to be scanned",sum));
    }
}