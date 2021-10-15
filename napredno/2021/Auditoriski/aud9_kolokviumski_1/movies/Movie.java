package mk.ukim.finki.aud9_kolokviumski_1.movies;

public class Movie {
    private String title;
    private String director;
    private String genre;
    private float rating;

    public Movie(String title, String director, String genre, float rating) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %s) %.2f", title, director, genre, rating);
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public float getRating() {
        return rating;
    }
}
