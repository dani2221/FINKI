package mk.ukim.finki.aud8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Hashmap
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Name {
    String name;
    int counter;

    public Name(String name, int counter) {
        this.name = name;
        this.counter = counter;
    }

    public static Name createName (String line) {
        String [] parts = line.split("\\s+");
        return new Name (parts[0], Integer.parseInt(parts[1]));
    }
}

public class Names {

    public static Map<String, Integer> createMapFromFile (String filename) {
        Map<String, Integer> result = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            br.lines().map(line -> Name.createName(line))
                    .forEach(name -> {
                        result.put(name.name, name.counter);
                    });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {

        Map<String, Integer> boys = createMapFromFile("/Users/stefanandonov/Documents/NP2020/src/mk/ukim/finki/aud7/boysnames.txt");
        Map<String, Integer> girls = createMapFromFile("/Users/stefanandonov/Documents/NP2020/src/mk/ukim/finki/aud7/girlsnames.txt");

        Set<String> bothBoysAndGirls = new HashSet<>();

        for (String maleName : boys.keySet()) {
            if (girls.containsKey(maleName))
                System.out.println(maleName + "-->" + (boys.get(maleName) + girls.get(maleName)));
        }





    }

}
