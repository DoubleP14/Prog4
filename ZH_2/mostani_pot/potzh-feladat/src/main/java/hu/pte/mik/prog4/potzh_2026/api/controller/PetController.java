package hu.pte.mik.prog4.potzh_2026.api.controller;

import hu.pte.mik.prog4.potzh_2026.entity.PetEntity;
// +++ Importáljuk a Service-t a Repository helyett +++
import hu.pte.mik.prog4.potzh_2026.service.PetService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PetController {

    // +++ Service példányosítása +++
    private final PetService petService = new PetService();

    @GET
    @Path("/{id}")
    public Response getPetById(@PathParam("id") Long id) {
        // +++ A Service-t hívjuk meg +++
        PetEntity pet = petService.findById(id);

        if (pet != null) {
            return Response.ok(pet).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Pet not found\"}")
                    .build();
        }
    }
}
