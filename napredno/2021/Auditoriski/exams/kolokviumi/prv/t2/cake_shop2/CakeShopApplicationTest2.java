package mk.ukim.finki.exams.kolokviumi.prv.t2.cake_shop2;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

enum Type {
    CAKE,
    PIE
}

class InvalidOrderException extends Exception {
    public InvalidOrderException(int orderId) {
        super(String.format("The order with id %d has less items than the minimum allowed.", orderId));
    }
}

abstract class Item {
    private String name;
    private int price;

    public Item(String name) {
        this.name = name;
        this.price = 0;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    abstract Type getType();
}

class Cake extends Item {

    public Cake(String name) {
        super(name);
    }

    @Override
    Type getType() {
        return Type.CAKE;
    }
}

class Pie extends Item {

    public Pie(String name) {
        super(name);
    }

    @Override
    Type getType() {
        return Type.PIE;
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 50;
    }
}

class Order implements Comparable<Order> {
    private int id;
    private List<Item> items;

    public Order(int id, List<Item> items) {
        this.id = id;
        this.items = items;
    }

    public static Order createOrder(String line, int minOrderItems) throws InvalidOrderException {
        String[] parts = line.split("\\s+");
        int orderId = Integer.parseInt(parts[0]);
        List<Item> items = new ArrayList<>();
        Arrays.stream(parts).skip(1)
                .forEach(part -> {
                    if (Character.isAlphabetic(part.charAt(0))) {
                        if (part.charAt(0) == 'C') items.add(new Cake(part));
                        else items.add(new Pie(part));
                    } else {
                        items.get(items.size() - 1).setPrice(Integer.parseInt(part));
                    }
                });
        if (items.size() < minOrderItems) throw new InvalidOrderException(orderId);
        return new Order(orderId, items);
    }

    public int totalOrderSum() {
        return this.items.stream().mapToInt(Item::getPrice).sum();
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public int compareTo(Order o) {
        return Integer.compare(this.totalOrderSum(), o.totalOrderSum());
    }

    private int totalPies() {
        return (int) this.items.stream().filter(item -> item.getType().equals(Type.PIE)).count();
    }

    private int totalCakes() {
        return (int) this.items.stream().filter(item -> item.getType().equals(Type.CAKE)).count();
    }

    @Override
    public String toString() {
        return this.id + " " +
                this.items.size() + " " +
                this.totalPies() + " " +
                this.totalCakes() + " " +
                this.totalOrderSum();
    }
}

class CakeShopApplication {
    private int minOrderItems;
    private List<Order> orders;

    public CakeShopApplication(int minOrderItems) {
        this.minOrderItems = minOrderItems;
        this.orders = new ArrayList<>();
    }

    public void readCakeOrders(InputStream inputStream) {
        this.orders = new BufferedReader(new InputStreamReader(inputStream))
                .lines().map(line -> {
                    try {
                        return Order.createOrder(line, minOrderItems);
                    } catch (InvalidOrderException exception) {
                        System.out.println(exception.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        this.orders.removeIf(order -> order.getItems().size() < minOrderItems);
    }

    public void printAllOrders(OutputStream outputStream) {
        PrintWriter printWriter = new PrintWriter(outputStream);
        this.orders.stream().sorted(Comparator.reverseOrder()).forEach(printWriter::println);
        printWriter.close();
    }
}

public class CakeShopApplicationTest2 {

    public static void main(String[] args) {
        CakeShopApplication cakeShopApplication = new CakeShopApplication(4);

        System.out.println("--- READING FROM INPUT STREAM ---");
        cakeShopApplication.readCakeOrders(System.in);

        System.out.println("--- PRINTING TO OUTPUT STREAM ---");
        cakeShopApplication.printAllOrders(System.out);
    }
}
