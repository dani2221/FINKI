import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//        Пицерија
//
//        Треба да се развие систем за електронска нарачка од пицерија. Менито на пицеријата се состои од следново:
//        Pizza:
//
//        Standard: 10$
//        Pepperoni: 12$
//        Vegetarian: 8$
//
//        Extra
//        Ketchup 3$
//        Coke 5$
//        За да го претставите менито, секоја ставка треба да имплементира interface Item која опишува една ставка од менито и ги дефинира следниве методи:
//
//        getPrice():int - ја дава цената за конкретната ставка
//        Следно, дефинирајте две класи ExtraItem и PizzaItem за да може да правите разлика меѓу пици и останатите работи во нарачката. И двете класи треба да имаат еден конструктор кој прима еден String аргумент.
//
//        ExtraItem(String type) - валидни вредности за type се { "Coke", "Ketchup" }
//        PizzaItem(String type) - валидни вредности за type се { Standard , Pepperoni , Vegetarian }
//        Ако за type се проследи некоја невалидна вредност (која ја нема на менито) треба да се фрли исклучок InvalidExtraTypeException, односно InvalidPizzaTypeException.
//
//        Последно имплементирајте ја класата Order. Таа треба да ги нуди следните функционалности:
//
//        Order() - креира нова празна нарачка
//        addItem(Item item, int count) - соодветната ставка се додава во нарачката (count означува колку примероци сакаме од дадената ставка). Aко count е поголем од 10 се фрла исклучок ItemOutOfStockException(item). Доколку во нарачката веќе ја има соодветната ставка Item тогаш истата се заменува со нова. Следниот код резултира со нарачка со една стандардна пица:
//
//        Order order = new Order(); order.addItem(new PizzaItem("Standard"), 2); order.addItem(new PizzaItem("Standard"), 1);
//
//        getPrice():int - ја враќа вкупната цена на нарачката
//
//        displayOrder() - ја печати содржината на нарачката со соодветни редни броеви пред секоја ставка, името, количината и збирна сума на ставката, како и вкупна сума за целата нарачка. За редниот број се резервирани 3 места порамнети во десно, за имињата на ставките се резервирани 15 места со порамнување од лево, за кардиналноста две места порамнети во десно и за цената на една ставка 5 места порамнети во десно. За "Total:" се резервирани 22 места со порамнување од лево и за вкупната цена 5 места порамнети во десно. Пример:
//        1.Standard       x 2   20$
//        2.Vegetarian     x 1    8$
//        3.Coke           x 3   15$
//        Total:                   43$
//        Редоследот по кој се печатат ставките е оној по кој тие се внесувани во нарачката. Доколку некоја ставка се внесе повторно нејзиното место не се менува.
//
//        removeItem(int idx) - се отстранува нарачката со даден индекс (сите нарачки со поголеми индекси се поместуваат во лево). Доколку не постои нарачка со таков индекс треба да се фрли исклучок ArrayIndexOutOfBоundsException(idx)
//        lock() - ја заклучува нарачката. За да може нарачката да се заклучи треба истата да има барем една ставка, во спротивно фрлете исклучок EmptyOrderException.
//        Откако ќе се заклучи нарачката треба веќе да не може да се менува со методите removeItem, addItem. Повикот на овие методи резултира со исклучок од типот OrderLockedException.


public class PizzaOrderTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (k == 0) { //test Item
            try {
                String type = jin.next();
                String name = jin.next();
                Item item = null;
                if (type.equals("Pizza")) item = new PizzaItem(name);
                else item = new ExtraItem(name);
                System.out.println(item.getPrice());
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
        }
        if (k == 1) { // test simple order
            Order order = new Order();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            jin.next();
            System.out.println(order.getPrice());
            order.displayOrder();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            System.out.println(order.getPrice());
            order.displayOrder();
        }
        if (k == 2) { // test order with removing
            Order order = new Order();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            jin.next();
            System.out.println(order.getPrice());
            order.displayOrder();
            while (jin.hasNextInt()) {
                try {
                    int idx = jin.nextInt();
                    order.removeItem(idx);
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            System.out.println(order.getPrice());
            order.displayOrder();
        }
        if (k == 3) { //test locking & exceptions
            Order order = new Order();
            try {
                order.lock();
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.addItem(new ExtraItem("Coke"), 1);
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.lock();
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.removeItem(0);
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
        }
    }

}

interface Item{
    int getPrice();
    String getType();
}

class InvalidPizzaTypeException extends Exception{
    public InvalidPizzaTypeException() {
        super("Invalid pizza type");
    }
}
class InvalidExtraTypeException extends Exception{
    public InvalidExtraTypeException() {
        super("Invalid extra type");
    }
}

class PizzaItem implements Item{
    private String type;
    private int price;

    public PizzaItem(String type) throws InvalidPizzaTypeException {
        if(type.equals("Standard")) price = 10;
        else if(type.equals("Pepperoni")) price = 12;
        else if(type.equals("Vegetarian")) price = 8;
        else throw new InvalidPizzaTypeException();
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getPrice() {
        return price;
    }


}

class ExtraItem implements Item{
    private String type;
    private int price;

    public ExtraItem(String type) throws InvalidExtraTypeException {
        if(type.equals("Ketchup")) price = 3;
        else if(type.equals("Coke")) price = 5;
        else throw new InvalidExtraTypeException();
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getPrice() {
        return price;
    }
}

class ItemOutOfStockException extends Exception{
    public ItemOutOfStockException(Item item) {
        super(item.toString());
    }
}
class Order{
    private List<Item> items;
    private boolean locked = false;

    public Order() {
        this.items = new ArrayList<Item>();
    }

    public void addItem(Item item, int count) throws ItemOutOfStockException, OrderLockedException {
        if(locked) throw new OrderLockedException();
        if(count>10) throw new ItemOutOfStockException(item);
        int firstApp = -1;
        for(int i=0; i<items.size();i++){
            if(items.get(i).getType().equals(item.getType())){
                firstApp = i;
                break;
            }
        }
        items = items.stream().filter(x -> !x.getType().equals(item.getType())).collect(Collectors.toList());
        for(int i=0; i<count; i++) {
            if (firstApp == -1) {
                items.add(item);
            } else {
                items.add(firstApp, item);
            }
        }
    }

    public int getPrice(){
        int price = 0;
        for(Item i: items) price+=i.getPrice();
        return price;
    }

    public void displayOrder(){
        int i = 1;
        for(List<Item> item : categorize()){
            int price = item.size()*item.get(0).getPrice();
            System.out.printf("%3s.%-15sx%2s%5s$\n",i++,item.get(0).getType(),item.size(),price);
        }
        System.out.printf("%-22s%5s$\n","Total:",this.getPrice());
    }

    private List<List<Item>> categorize(){
        List<List<Item>> categorized = new ArrayList<List<Item>>();
        for(Item i : items) {
            boolean found = false;
            for (List<Item> cat : categorized) {
                if (cat.get(0).getType().equals(i.getType())) {
                    cat.add(i);
                    found = true;
                }
            }
            if (!found) {
                List<Item> newList = new ArrayList<Item>();
                newList.add(i);
                categorized.add(newList);
            }
        }
        return categorized;
    }

    public void removeItem(int idx) throws OrderLockedException, ArrayIndexOutOfBoundsException {
        if(locked) throw new OrderLockedException();
        if(idx<0 || idx>items.size()-1) throw new ArrayIndexOutOfBoundsException();
        String type = items.get(idx).getType();
        items = items.stream().filter(x -> !x.getType().equals(type)).collect(Collectors.toList());
    }

    public void lock() throws EmptyOrder {
        if(items.size()>0) this.locked = true;
        else throw new EmptyOrder();
    }


}

class EmptyOrder extends Exception{
    public EmptyOrder() {
        super();
    }
}
class OrderLockedException extends Exception{
    public OrderLockedException() {
        super();
    }
}