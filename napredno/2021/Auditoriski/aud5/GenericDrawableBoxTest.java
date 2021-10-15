package mk.ukim.finki.aud5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

interface Drawable {
    void draw();
}

class Box<T extends Drawable> {
    List<T> elements;
    static Random RANDOM = new Random();

    public Box () {
        elements = new ArrayList<>();
    }

    public void addElement (T element) {
        elements.add(element);
    }

    public boolean isEmpty () {
        return elements.size()==0;
    }

    public void drawElement () {
        if (isEmpty())
            return;

        T element = elements.remove(RANDOM.nextInt(elements.size()));
        element.draw();
    }
}


public class GenericDrawableBoxTest {

    static Random RANDOM = new Random();

    public static void main(String[] args) {
        Box<Drawable> box = new Box<>();
        for (int i=0;i<100;i++) {
            box.addElement(() -> System.out.println(RANDOM.nextInt(50)));
        }

        for (int i=0;i<50;i++) {
            box.drawElement();
        }
    }
}
