package Canvas1;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Shapes1Test {

    public static void main(String[] args) throws IOException {
        ShapesApplication shapesApplication = new ShapesApplication();

        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        System.out.println(shapesApplication.readCanvases(System.in));
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);

    }
}
class ShapesApplication{

    private List<Canvas> canvases;
    public ShapesApplication() {
    }

    public int readCanvases(InputStream in) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(in));
        canvases =  bf.lines().map(x -> new Canvas(x)).collect(Collectors.toList());
        int count = 0;
        for(Canvas c : canvases) count+=c.count();
        bf.close();
        return count;
    }

    public void printLargestCanvasTo(OutputStream out){
        PrintWriter pw = new PrintWriter(new PrintStream(out));
        pw.println(max().toString());
        pw.close();
    }

    public Canvas max(){
        return canvases.stream().max(Comparator.naturalOrder()).get();
    }
}

class Canvas implements Comparable<Canvas>{
    private String id;
    private List<Integer> sizes;

    public Canvas(String line) {
        String[] contents = line.split("\\s+");
        id = contents[0];
        sizes = new ArrayList<Integer>();
        for(int i=1;i<contents.length;i++){
            sizes.add(Integer.parseInt(contents[i]));
        }
    }
    public int count(){
        return sizes.size();
    }
    public int sum(){
        int sum = 0;
        for(int size : sizes) sum+=size;
        return sum*4;
    }

    @Override
    public int compareTo(Canvas o) {
        return Integer.compare(sum(),o.sum());
    }

    @Override
    public String toString() {
        return String.format("%s %d %d",id,count(),sum());
    }
}