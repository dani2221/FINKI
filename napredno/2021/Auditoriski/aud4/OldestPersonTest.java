package mk.ukim.finki.aud4;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Person implements Comparable<Person> {
    String name;
    Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person(String line) {
        String[] parts = line.split("\\s+");
        this.name = parts[0];
        this.age = Integer.parseInt(parts[1]);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return this.age.compareTo(o.age);
    }
}

public class OldestPersonTest {

    public static List<Person> getPeopleFrom(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        return br.lines()
                .map(line -> new Person(line))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        File file = new File("/Users/stefanandonov/Documents/GitWorkspace/NP2020/src/mk/ukim/finki/aud4/people");
        try {
            List<Person> people = getPeopleFrom(new FileInputStream(file));

//            Person max = people.get(0);
//            for (Person p : people)
//                if (p.compareTo(max)>0)
//                    max = p;
//

            Person max = people.stream().max(Comparator.naturalOrder()).get();

//            max = people.stream().reduce(
//                    new Person("Test person", 0),
//                    (left, right) -> left.compareTo(right) > 0 ? left : right
//            );

//            Collections.sort(people);
//            max = people.get(people.size()-1);


            System.out.println(max);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
