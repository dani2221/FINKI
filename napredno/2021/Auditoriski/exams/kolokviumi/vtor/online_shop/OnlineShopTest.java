package mk.ukim.finki.exams.kolokviumi.vtor.online_shop;

//package mk.ukim.finki.vtor_kolokvium;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

enum COMPARATOR_TYPE {
    NEWEST_FIRST,
    OLDEST_FIRST,
    LOWEST_PRICE_FIRST,
    HIGHEST_PRICE_FIRST,
    MOST_SOLD_FIRST,
    LEAST_SOLD_FIRST
}

class ProductNotFoundException extends Exception {
    ProductNotFoundException(String message) {
        super(message);
    }
}

class Product {
    String id;
    String name;
    LocalDateTime createdAt;
    double price;
    int quantitySold = 0;

    public Product(String id, String name, LocalDateTime createdAt, double price) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", price=" + price +
                ", quantitySold=" + quantitySold +
                '}';
    }

    public double buy(int quantity) {
        this.quantitySold += quantity;
        return quantity * price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantitySold() {
        return quantitySold;
    }
}

class ProductComparatorFactory {
    static Comparator<Product> createComparator(COMPARATOR_TYPE comparatorType) {
        switch (comparatorType) {
            case HIGHEST_PRICE_FIRST:
                return Comparator.comparing(Product::getPrice).reversed();
            case LOWEST_PRICE_FIRST:
                return Comparator.comparing(Product::getPrice);
            case NEWEST_FIRST:
                return Comparator.comparing(Product::getCreatedAt).reversed();
            case OLDEST_FIRST:
                return Comparator.comparing(Product::getCreatedAt);
            case MOST_SOLD_FIRST:
                return Comparator.comparing(Product::getQuantitySold).reversed();
            default:
                return Comparator.comparing(Product::getQuantitySold);
        }
    }
}

class OnlineShop {
    Map<String, List<Product>> productsByCategory;
    Map<String, Product> productsById;

    OnlineShop() {
        productsByCategory = new HashMap<>();
        productsById = new HashMap<>();
    }

    void addProduct(String category, String id, String name, LocalDateTime createdAt, double price) {
        Product p = new Product(id, name, createdAt, price);
        productsById.put(id, p);
        productsByCategory.putIfAbsent(category, new ArrayList<>());
        productsByCategory.computeIfPresent(category, (k, v) -> {
            v.add(p);
            return v;
        });
    }

    double buyProduct(String id, int quantity) throws ProductNotFoundException {
        if (!productsById.containsKey(id))
            throw new ProductNotFoundException(String.format("Product with id %s does not exist in the online shop!", id));
        return productsById.get(id).buy(quantity);
    }

    List<List<Product>> listProducts(String category, COMPARATOR_TYPE comparatorType, int pageSize) {
        List<Product> products = (category == null) ? new ArrayList<>(productsById.values()) : productsByCategory.get(category);
        Comparator<Product> comparator = ProductComparatorFactory.createComparator(comparatorType);
        products.sort(comparator);
        List<List<Product>> result = new ArrayList<>();
        if (pageSize > products.size()) {
            result.add(products);
        } else {
            int ratio = (int) Math.ceil(products.size()*1.0 / pageSize);
            List<Integer> starts = IntStream.range(0, ratio).map(i -> i * pageSize).boxed().collect(Collectors.toList());
            starts.forEach(i -> result.add(products.subList(i, Math.min((i + pageSize), products.size()))));
        }
        return result;
    }

}

public class OnlineShopTest {

    public static void main(String[] args) {
        OnlineShop onlineShop = new OnlineShop();
        double totalAmount = 0.0;
        Scanner sc = new Scanner(System.in);
        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            String[] parts = line.split("\\s+");
            if (parts[0].equalsIgnoreCase("addproduct")) {
                String category = parts[1];
                String id = parts[2];
                String name = parts[3];
                LocalDateTime createdAt = LocalDateTime.parse(parts[4]);
                double price = Double.parseDouble(parts[5]);
                onlineShop.addProduct(category, id, name, createdAt, price);
            } else if (parts[0].equalsIgnoreCase("buyproduct")) {
                String id = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                try {
                    totalAmount += onlineShop.buyProduct(id, quantity);
                } catch (ProductNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                String category = parts[1];
                if (category.equalsIgnoreCase("null"))
                    category=null;
                String comparatorString = parts[2];
                int pageSize = Integer.parseInt(parts[3]);
                COMPARATOR_TYPE comparatorType = COMPARATOR_TYPE.valueOf(comparatorString);
                printPages(onlineShop.listProducts(category, comparatorType, pageSize));
            }
        }
        System.out.println("Total revenue of the online shop is: " + totalAmount);

    }

    private static void printPages(List<List<Product>> listProducts) {
        for (int i = 0; i < listProducts.size(); i++) {
            System.out.println("PAGE " + (i + 1));
            listProducts.get(i).forEach(System.out::println);
        }
    }
}
