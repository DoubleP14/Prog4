package hu.pte.mik.prog4.potzh.service;

import hu.pte.mik.prog4.potzh.entity.MovieEntity;
import hu.pte.mik.prog4.potzh.repository.MovieRepository;
import hu.pte.mik.prog4.potzh.ws.MovieDataRequest;
import hu.pte.mik.prog4.potzh.ws.MovieDataResponse;
import hu.pte.mik.prog4.potzh.ws.MovieDataService;
import hu.pte.mik.prog4.potzh.ws.SoapMovieDataServiceService; // <- Ez a generált fájlok közül jön!

import java.util.List;

public class MovieService {

    private final MovieRepository movieRepository = new MovieRepository();

    // --- REPOSITORY METÓDUSOK BECSOMAGOLÁSA ---

    public List<MovieEntity> findAllMovies() {
        return movieRepository.findAll();
    }

    public MovieEntity findMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public MovieEntity createMovie(MovieEntity movie) {
        return movieRepository.create(movie);
    }

    public MovieEntity updateMovie(MovieEntity movie) {
        return movieRepository.update(movie);
    }

    // --- SOAP WEB SERVICE KLIENS HÍVÁSA ---

    public Double getImdbRatingForMovie(Long movieId) {
        try {
            // 1. Kapcsolódunk a SOAP szerverhez a generált klienssel
            SoapMovieDataServiceService service = new SoapMovieDataServiceService();
            MovieDataService port = service.getSoapMovieDataServicePort();

            // 2. Összeállítjuk a kérést
            MovieDataRequest request = new MovieDataRequest();
            request.setMovieId(String.valueOf(movieId));

            // 3. Elküldjük a kérést és várjuk a választ
            MovieDataResponse response = port.getMovieData(request);

            return response.getImdbRating();

        } catch (Exception e) {
            System.err.println("Hiba a SOAP hívás során: " + e.getMessage());
            return null; // Vagy valamilyen alapértelmezett érték, ha nem megy a szerver
        }
    }
}