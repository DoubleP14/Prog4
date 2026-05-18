package hu.pte.mik.prog4.potzh.ws;

import jakarta.jws.WebService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

// Itt kötjük össze az interfésszel!
@WebService(endpointInterface = "hu.pte.mik.prog4.potzh.ws.MovieDataService")
public class SoapMovieDataService implements MovieDataService {

    private final Map<String, Double> map = new HashMap<>();

    @Override
    public MovieDataResponse getMovieData(MovieDataRequest request) {
        System.out.println("getMovieData meghívva!");
        return new MovieDataResponse(
                request.getMovieId(),
                this.map.computeIfAbsent(request.getMovieId(), movieId ->
                        Math.round(ThreadLocalRandom.current().nextDouble(1, 10) * 10) / 10.0)
        );
    }
}