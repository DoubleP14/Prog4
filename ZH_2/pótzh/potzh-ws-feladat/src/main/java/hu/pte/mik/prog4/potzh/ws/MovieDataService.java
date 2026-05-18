package hu.pte.mik.prog4.potzh.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface MovieDataService {

    @WebMethod
    MovieDataResponse getMovieData(MovieDataRequest request);

}