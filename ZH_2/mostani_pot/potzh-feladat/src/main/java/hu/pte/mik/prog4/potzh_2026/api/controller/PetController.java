package hu.pte.mik.prog4.potzh_2026.api.controller;

import hu.pte.mik.prog4.potzh_2026.entity.PetEntity;
import hu.pte.mik.prog4.potzh_2026.repository.PetRepository;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PetController {

    private final PetRepository petRepository = new PetRepository();

    @GET
    @Path("/{id}")
    public Response getPetById(@PathParam("id") Long id) {
        PetEntity pet = petRepository.findById(id);
        if (pet != null) {
            return Response.ok(pet).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Pet not found\"}")
                    .build();
        }
    }
}