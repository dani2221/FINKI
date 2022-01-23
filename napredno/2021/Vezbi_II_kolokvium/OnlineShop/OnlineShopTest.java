package OnlineShop;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

enum COMPARATOR_TYPE {
    NEWEST_FIRST,
    OLDEST_FIRST,
    LOWEST_PRICE_FIRST,
    HIGHEST_PRICE_FIRST,
    MOST_SOLD_FIRST,
    LEAST_SOLD_FIRST
}

class ProductNotFoundException extends Exception {
    ProductNotFoundException(String id) {
        super("Product with id "+id+" does not exist in the online shop!");
    }
}

class ComparatorGenerator{
    public static Comparator<Product> generateComparator(COMPARATOR_TYPE type){
        if(type==COMPARATOR_TYPE.OLDEST_FIRST) return Comparator.comparing(Product::getCreatedAt);
        if(type==COMPARATOR_TYPE.NEWEST_FIRST) return generateComparator(COMPARATOR_TYPE.OLDEST_FIRST).reversed();
        if(type==COMPARATOR_TYPE.LOWEST_PRICE_FIRST) return Comparator.comparing(Product::getPrice);
        if(type==COMPARATOR_TYPE.HIGHEST_PRICE_FIRST) return generateComparator(COMPARATOR_TYPE.LOWEST_PRICE_FIRST).reversed();
        if(type==COMPARATOR_TYPE.LEAST_SOLD_FIRST) return Comparator.comparing(Product::getSold);
        if(type==COMPARATOR_TYPE.MOST_SOLD_FIRST) return generateComparator(COMPARATOR_TYPE.LEAST_SOLD_FIRST).reversed();
        return null;
    }
}


class Product {
    private String category;
    private String id;
    private String name;
    private LocalDateTime createdAt;
    private double price;
    private int sold;

    public String getCategory() {
        return category;
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

    public void incrementSold(int amount){
        this.sold+=amount;
    }

    public int getSold() {
        return sold;
    }

    public Product(String category, String id, String name, LocalDateTime createdAt, double price) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.price = price;
        sold = 0;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", price=" + price +
                ", quantitySold=" + sold +
                '}';
    }
}


class OnlineShop {
    private HashMap<String, Product> products;

    OnlineShop() {
        products = new HashMap<String, Product>();
    }

    void addProduct(String category, String id, String name, LocalDateTime createdAt, double price){
        products.put(id,new Product(category,id,name,createdAt,price));
    }

    double buyProduct(String id, int quantity) throws ProductNotFoundException{
        Product p = products.get(id);
        if(p==null) throw new ProductNotFoundException(id);
        p.incrementSold(quantity);
        return p.getPrice()*quantity;
    }

    List<List<Product>> listProducts(String category, COMPARATOR_TYPE comparatorType, int pageSize) {
        List<List<Product>> result = new ArrayList<List<Product>>();
        Stream<Product> stream = products.values().stream();
        if(category!=null) stream = stream.filter(x -> x.getCategory().equals(category));

        List<Product> categoryProducts = stream
                .sorted(ComparatorGenerator.generateComparator(comparatorType))
                .collect(Collectors.toList());
        for(int i=0;i<categoryProducts.size();i+=pageSize){
            result.add(categoryProducts.stream().skip(i).limit(pageSize).collect(Collectors.toList()));
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

