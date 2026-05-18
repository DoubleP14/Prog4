package hu.pte.mik.prog4.potzh.ws;

public class MovieDataResponse {
    private String movieId;
    private Double imdbRating;

    public MovieDataResponse() {}

    public MovieDataResponse(String movieId, Double imdbRating) {
        this.movieId = movieId;
        this.imdbRating = imdbRating;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }
}