package hu.pte.mik.prog4.potzh.ws;

public class MovieDataRequest {
    private String movieId;

    public MovieDataRequest() {}

    public MovieDataRequest(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}