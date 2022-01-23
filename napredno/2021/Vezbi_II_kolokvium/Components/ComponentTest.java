package Components;

import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class ComponentTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		Window window = new Window(name);
		Component prev = null;
		while (true) {
            try {
				int what = scanner.nextInt();
				scanner.nextLine();
				if (what == 0) {
					int position = scanner.nextInt();
					window.addComponent(position, prev);
				} else if (what == 1) {
					String color = scanner.nextLine();
					int weight = scanner.nextInt();
					Component component = new Component(color, weight);
					prev = component;
				} else if (what == 2) {
					String color = scanner.nextLine();
					int weight = scanner.nextInt();
					Component component = new Component(color, weight);
					prev.addComponent(component);
                    prev = component;
				} else if (what == 3) {
					String color = scanner.nextLine();
					int weight = scanner.nextInt();
					Component component = new Component(color, weight);
					prev.addComponent(component);
				} else if(what == 4) {
                	break;
                }
                
            } catch (InvalidPositionException e) {
				System.out.println(e.getMessage());
			}
            scanner.nextLine();			
		}
		
        System.out.println("=== ORIGINAL WINDOW ===");
		System.out.println(window);
		int weight = scanner.nextInt();
		scanner.nextLine();
		String color = scanner.nextLine();
		window.changeColor(weight, color);
        System.out.println(String.format("=== CHANGED COLOR (%d, %s) ===", weight, color));
		System.out.println(window);
		int pos1 = scanner.nextInt();
		int pos2 = scanner.nextInt();
        System.out.println(String.format("=== SWITCHED COMPONENTS %d <-> %d ===", pos1, pos2));
		window.swichComponents(pos1, pos2);
		System.out.println(window);
	}
}

// вашиот код овде
class Component implements Cloneable{
	private String color;
	private int weight;
	private TreeSet<Component> children;

	public Component(String color, int weight) {
		this.color = color;
		this.weight = weight;
		children = new TreeSet<Component>(Comparator.comparing(Component::getWeight).thenComparing(Component::getColor));
	}

	public String getColor() {
		return color;
	}

	public int getWeight() {
		return weight;
	}

	public TreeSet<Component> getChildren() {
		return children;
	}

	public void addComponent(Component component) {
		children.add(component);
	}
	public void changeColor(int weight, String color){
		if(this.weight<weight) this.color = color;
		children.forEach(x -> x.changeColor(weight,color));
	}

	@Override
	public Object clone() {
		Component comp = new Component(color,weight);
		this.children.forEach(x -> comp.addComponent(x));
		return comp;
	}

	public String toString(String prefix) {
		StringBuilder str = new StringBuilder();
		str.append(prefix+weight+":"+color);
		children.forEach(x -> str.append("\n"+x.toString(prefix+"---")));
		return str.toString();
	}
}

class Window {
	private String name;
	private TreeMap<Integer,Component> components;

	public Window(String name) {
		this.name = name;
		components = new TreeMap<Integer, Component>();
	}

	public void addComponent(int position, Component prev) throws InvalidPositionException {
		if(components.get(position)!=null) throw new InvalidPositionException(position);
		components.put(position,prev);
	}

	public void changeColor(int weight, String color) {
		components.values().forEach(x -> x.changeColor(weight,color));
	}

	public void swichComponents(int pos1, int pos2) {
		Component temp = (Component) components.get(pos1).clone();
		components.compute(pos1,(k,v)-> (Component) components.get(pos2).clone());
		components.compute(pos2,(k,v)->temp);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("WINDOW "+name);
		components.forEach((k,v) -> str.append("\n"+k+":"+v.toString("")));
		return str.toString()+"\n";
	}
}
class InvalidPositionException extends Exception{
	public InvalidPositionException(Integer position) {
		super("Invalid position "+position+", alredy taken!");
	}
}