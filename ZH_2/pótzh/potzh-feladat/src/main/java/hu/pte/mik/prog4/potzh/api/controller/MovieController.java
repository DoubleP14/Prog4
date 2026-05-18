package hu.pte.mik.prog4.potzh.api.controller;

import hu.pte.mik.prog4.potzh.entity.MovieEntity;
import hu.pte.mik.prog4.potzh.service.MovieService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/movie")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovieController {

    private final MovieService movieService;

    public MovieController() {
        this.movieService = new MovieService();
    }

    @POST
    @Path("/save")
    public Response save(MovieEntity movieEntity) {
        // JAVÍTVA: A Service-ben createMovie a metódus neve
        MovieEntity result = movieService.createMovie(movieEntity);

        if (result != null) {
            return Response.status(Response.Status.OK).entity(result).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\": \"Sikertelen mentés, a film nem hozható létre.\"}").build();
        }
    }

    @GET
    public Response findAll() {
        // JAVÍTVA: A Service-ben findAllMovies a metódus neve
        List<MovieEntity> result = movieService.findAllMovies();

        if (result == null || result.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\": \"Nincs egyetlen film sem az adatbázisban.\"}").build();
        }

        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Path("/find/{id}")
    public Response findById(@PathParam("id") Long id) {
        // JAVÍTVA: A Service-ben findMovieById a metódus neve
        MovieEntity result = movieService.findMovieById(id);

        if (result != null) {
            return Response.status(Response.Status.OK).entity(result).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\": \"A keresett film nem található ezzel az ID-val.\"}").build();
        }
    }

    @GET
    @Path("/imdb/{id}")
    public Response getMovieRating(@PathParam("id") Long id) { // JAVÍTVA: Long-ot várunk a Service miatt
        // JAVÍTVA: A Service-ben getImdbRatingForMovie a metódus neve
        Double result = movieService.getImdbRatingForMovie(id);

        if (result != null) {
            return Response.status(Response.Status.OK).entity(result).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\": \"Nem található IMDb értékelés a megadott filmhez.\"}").build();
        }
    }
}