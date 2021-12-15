package UniqueNames;

import java.util.*;

public class NamesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Names names = new Names();
        for (int i = 0; i < n; ++i) {
          String name = scanner.nextLine();
          names.addName(name);
        }
        n = scanner.nextInt();
        System.out.printf("===== PRINT NAMES APPEARING AT LEAST %d TIMES =====\n", n);
        names.printN(n);
        System.out.println("===== FIND NAME =====");
        int len = scanner.nextInt();
        int index = scanner.nextInt();
        System.out.println(names.findName(len, index));
        scanner.close();

    }
}

// vashiot kod ovde
class Names{
    private TreeMap<String,Integer> names;

    public Names() {
        names = new TreeMap<String, Integer>();
    }

    public void addName(String name) {
        names.compute(name,(k, v) -> (v == null) ? 1 : v+1);
    }

    public void printN(int n) {
        names.forEach((k,v) ->{
            if(v>=n) System.out.printf("%s (%d) %d\n",k,v,getUniqueLetters(k));
        });
    }

    private int getUniqueLetters(String name){
        Set<Character> set = new HashSet<Character>();
        for (char c: name.toLowerCase().toCharArray()) {
            set.add(c);
        }
        return set.size();
    }

    public String findName(int len, int index) {
        TreeMap<String, Integer> copy = new TreeMap<String, Integer>();
        names.forEach((k,v) ->{
            if(k.length()<len) copy.put(k,v);
        });
        int i = index%copy.keySet().size();
        int en = 0;
        for (Map.Entry<String, Integer> entry : copy.entrySet()) {
            if(i==en){
                return entry.getKey();
            }
            en++;
        }
        return null;
    }
}