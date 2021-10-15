package mk.ukim.finki.aud9_kolokviumski_1.movies;

import java.util.*;

public class MoviesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int printN = scanner.nextInt();
        scanner.nextLine();
        MoviesCollection moviesCollection = new MoviesCollection();
        Set<String> genres = fillCollection(scanner, moviesCollection);
        System.out.println("=== PRINT BY GENRE ===");
        for (String genre : genres) {
            System.out.println("GENRE: " + genre);
            moviesCollection.printByGenre(genre);
        }
        System.out.println("=== TOP N BY RATING ===");
        printList(moviesCollection.getTopRatedN(printN));

        System.out.println("=== COUNT BY DIRECTOR ===");
        printMap(moviesCollection.getCountByDirector());
    }

    static void printMap(Map<String,Integer> countByDirector) {
        countByDirector.entrySet().stream()
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }

    static void printList(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    static TreeSet<String> fillCollection(Scanner scanner, MoviesCollection collection) {
        TreeSet<String> categories = new TreeSet<String>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            Movie movie = new Movie(parts[0], parts[1], parts[2], Float.parseFloat(parts[3]));
            collection.addMovie(movie);
            categories.add(parts[2]);
        }
        return categories;
    }
}
