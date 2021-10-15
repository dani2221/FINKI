package mk.ukim.finki.aud5;//package mk.ukim.finki.aud5;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//class Box<T> {
//    List<T> elements;
//    static Random RANDOM = new Random();
//
//    public Box () {
//        elements = new ArrayList<>();
//    }
//
//    public void addElement (T element) {
//        elements.add(element);
//    }
//
//    public boolean isEmpty () {
//        return elements.size()==0;
//    }
//
//    public T drawElement () {
//        if (isEmpty())
//            return null;
//
////        int idx = RANDOM.nextInt(elements.size());
////        T element = elements.get(idx);
////        elements.remove(idx);
////        return element;
//
//        return elements.remove(RANDOM.nextInt(elements.size()));
//    }
//}
//
//public class GenericBoxTest {
//
//    public static void main(String[] args) {
//        Box<Integer> box = new Box<>();
//        for (int i=0;i<100;i++)
//            box.addElement(i);
//
//        for (int i=0;i<102;i++) {
//            System.out.println(box.drawElement());
//        }
//    }
//}
