package mk.ukim.finki.aud6_kolokviumski_1;

import java.io.*;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class AmountNotAllowedException extends Exception {

    int sumOfItems;

    public AmountNotAllowedException(int sumOfItems) {
        this.sumOfItems = sumOfItems;
    }

    @Override
    public String getMessage() {
        return String.format("Receipt with amount %d is not allowed to be scanned", sumOfItems);
    }
}

abstract class Item {
    int price;

    public Item(int price) {
        this.price = price;
    }

    public abstract double getTax();

    public double getTaxReturn() {
        return getTax() * 0.15;
    }

    public static Item createItem (char type, int price) {
        switch (type) {
            case 'A': return new ItemA(price);
            case 'B': return new ItemB(price);
            default: return new ItemV(price);
        }
    }
}

class ItemA extends Item {
    public ItemA(int price) {
        super(price);
    }

    @Override
    public double getTax() {
        return price * 0.18;
    }
}

class ItemB extends Item {

    public ItemB(int price) {
        super(price);
    }

    @Override
    public double getTax() {
        return price * 0.05;
    }
}

class ItemV extends Item {

    public ItemV(int price) {
        super(price);
    }

    @Override
    public double getTax() {
        return 0;
    }
}

class Receipt {
    String id;
    List<Item> itemsList;

    public Receipt(String id, List<Item> itemsList) {
        this.id = id;
        this.itemsList = itemsList;
    }

    public int itemsPriceSum() {
//        int sum = 0;
//        for (Item i : itemsList) {
//            sum+=i.price;
//        }
//        return sum;
        return itemsList.stream()
                .mapToInt(i -> i.price)
                .sum();
    }

    public double taxReturnsSum() {
        return itemsList.stream()
                .mapToDouble(i -> i.getTaxReturn())
                .sum();
    }

    public static Receipt createReceipt(String line) throws AmountNotAllowedException {
        String[] parts = line.split("\\s+");
        String id = parts[0];
        List<Item> items = new ArrayList<>();
        for (int i = 1; i < parts.length; i += 2) {
            Integer price = Integer.parseInt(parts[i]);
            char type = parts[i + 1].charAt(0);
            items.add(Item.createItem(type,price));
        }
        Receipt receipt = new Receipt(id, items);
        if (receipt.itemsPriceSum() > 30000)
            throw new AmountNotAllowedException(receipt.itemsPriceSum());
        return receipt;
    }

    @Override
    public String toString() {
        //70876 20538 53.42
        return String.format("%10s\t%10d\t%10.5f", id, itemsPriceSum(), taxReturnsSum());
    }
}

class MojDDV {
    List<Receipt> receipts;

    MojDDV() {
        receipts = new ArrayList<>();
    }


    public void readRecords(InputStream in) throws IOException {
        //0 1 2 3 4 5 6
        //70876 1585 B 1585 V 247 V 1391 B 1391 V 1649 B 1649 V 548 B 548 V 640 B 640 V 1309 B 1309 V 1486 V 2093 V 106 V 2001 V 361 V
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
//        String line;
//        while ((line = br.readLine()) != null) {
//            try {
//                receipts.add(Receipt.createReceipt(line));
//            } catch (AmountNotAllowedException e) {
//                System.out.println(e.getMessage());
//            }
//        }

        receipts = br.lines().map(line -> {
            try {
                return Receipt.createReceipt(line);
            } catch (AmountNotAllowedException e) {
                System.out.println(e.getMessage());
                return null;
            }
        })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public void printTaxReturns(OutputStream out) {
        PrintWriter pw = new PrintWriter(out);
        for (Receipt r : receipts)
            pw.println(r.toString());
        pw.flush();
    }

    public void printStatistics(OutputStream out) {
        PrintWriter pw = new PrintWriter(out);
        DoubleSummaryStatistics dss = receipts.stream()
                .mapToDouble(r -> r.taxReturnsSum())
                .summaryStatistics();

        pw.printf("min:\t%5.3f\nmax:\t%5.3f\nsum:\t%5.3f\ncount:\t%5d\navg:\t%5.3f\n",
                dss.getMin(),
                dss.getMax(),
                dss.getSum(),
                dss.getCount(),
                dss.getAverage());

        pw.flush();
    }
}

public class MojDDVTest {

    public static void main(String[] args) {

        MojDDV mojDDV = new MojDDV();

        System.out.println("===READING RECORDS FROM INPUT STREAM===");
        try {
            mojDDV.readRecords(System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===");
        mojDDV.printTaxReturns(System.out);

        System.out.println("===PRINTING SUMMARY STATISTICS FOR TAX RETURNS TO OUTPUT STREAM===");
        mojDDV.printStatistics(System.out);

    }
}