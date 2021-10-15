package mk.ukim.finki.aud9_kolokviumski_1.movies;

import java.util.Comparator;

public class MovieRatingComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        int x = Float.compare(o1.getRating(), o2.getRating());
        if(x == 0)
            return o1.getTitle().compareTo(o2.getTitle());
        return x;
    }
}
