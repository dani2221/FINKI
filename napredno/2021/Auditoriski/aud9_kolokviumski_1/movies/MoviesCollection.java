package mk.ukim.finki.aud9_kolokviumski_1.movies;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MoviesCollection {
    private List<Movie> movies;

    public MoviesCollection() {
        this.movies = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    public void printByGenre(String genre) {
        this.movies.stream()
                .filter(movie -> movie.getGenre().equalsIgnoreCase(genre))
                .sorted(new MovieTitleComparator())
                .forEach(System.out::println);
    }

    public List<Movie> getTopRatedN(int n) {
        if (n > this.movies.size()) return this.movies;
        return this.movies.stream()
                .sorted(new MovieRatingComparator())
                .limit(n)
                .collect(Collectors.toList());
    }

    public Map<String, Integer> getCountByDirector() {
        return this.movies.stream()
                .collect(Collectors.toMap(
                        Movie::getDirector,
                        movie -> 1,
                        (i1, i2) -> {
                            i1 += i2;
                            return i1;
                        },
                        TreeMap::new
                ));
    }
}
