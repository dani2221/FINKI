package mk.ukim.finki.aud8;

import java.util.*;

class DuplicateNumberException extends Exception {
    String number;

    public DuplicateNumberException(String number) {
        super(String.format("Duplicate number: %s", number));
    }
}

class Contact implements Comparable<Contact> {
    String name;
    String number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("%s %s", name, number);
    }

    @Override
    public int compareTo(Contact o) {
        int res = this.name.compareTo(o.name);
        return res == 0 ? this.number.compareTo(o.number) : res;
    }

    List<String> getSubNumbers () {
        List<String> results = new ArrayList<>();
        for (int i=3;i<=this.number.length();i++) { //for the length of the subnumber
            for (int j=0; j<=number.length()-i;j++) { //for the begining of the subnumber
                results.add(number.substring(j, j+i));
            }
        }
        return results;
    }
}

class PhoneBook {
    Map<String, String> namesByPhoneNumber;
    Map<String, Set<Contact>> contactsBySubnumber;
    Map<String, Set<Contact>> contactsByName;

    PhoneBook() {
        namesByPhoneNumber = new HashMap<>();
        contactsBySubnumber = new HashMap<>();
        contactsByName = new HashMap<>();
    }

    public void addContact(String name, String number) throws DuplicateNumberException {
        if (namesByPhoneNumber.containsKey(number))
            throw new DuplicateNumberException(number);

        Contact c = new Contact(name, number);

        namesByPhoneNumber.put(number, name);

        contactsByName.putIfAbsent(name, new TreeSet<>());
        contactsByName.get(name).add(c);

        for (String subNumber : c.getSubNumbers()) {
            contactsBySubnumber.putIfAbsent(subNumber, new TreeSet<>());
            contactsBySubnumber.get(subNumber).add(c);
        }


    }

    public void contactsByNumber(String number) {
        if (!contactsBySubnumber.containsKey(number)) {
            System.out.println("NOT FOUND");
        } else {
            contactsBySubnumber.get(number)
                    .forEach(c -> System.out.println(c));
        }
    }

    public void contactsByName(String name) {
        if (!contactsByName.containsKey(name)) {
            System.out.println("NOT FOUND");
        } else {
            contactsByName.get(name)
                    .forEach(c -> System.out.println(c));
        }
    }
}

public class PhoneBookTest {

    public static void main(String[] args) {
//        Contact c = new Contact("stefan", "123456789");
//        System.out.println(c.getSubNumbers());
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");

            try {
                phoneBook.addContact(parts[0], parts[1]);
            } catch (DuplicateNumberException e) {
                System.out.println(e.getMessage());
            }

        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
            String[] parts = line.split(":");
            if (parts[0].equals("NUM")) {
                phoneBook.contactsByNumber(parts[1]);
            } else {
                phoneBook.contactsByName(parts[1]);
            }
        }
    }

}

// Вашиот код овде

