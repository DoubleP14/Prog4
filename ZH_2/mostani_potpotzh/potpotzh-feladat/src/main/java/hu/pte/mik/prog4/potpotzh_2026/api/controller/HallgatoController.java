package hu.pte.mik.prog4.potpotzh_2026.api.controller;

import hu.pte.mik.prog4.potpotzh_2026.entity.Hallgato;
import hu.pte.mik.prog4.potpotzh_2026.service.HallgatoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/hallgatok")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HallgatoController {

    private final HallgatoService service = new HallgatoService();

    @GET
    public List<Hallgato> getAll() {
        return service.getAllHallgatok();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") int id) {
        Hallgato h = service.getHallgatoById(id);
        if (h == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(h).build();
    }

    @POST
    public Response create(Hallgato hallgato) {
        service.createHallgato(hallgato);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        service.deleteHallgato(id);
        return Response.noContent().build();
    }
}
