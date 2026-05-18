package hu.pte.mik.prog4.zh2_2026.api.controller;

import hu.pte.mik.prog4.zh2_2026.entity.CarEntity;
import hu.pte.mik.prog4.zh2_2026.service.CarService;
import hu.pte.mik.prog4.zh2_2026.ws.service.CarResponse;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cars")
public class CarController {

    private final CarService carService = new CarService();

    // Végpont a SOAP webservice hívásához és az extra adatok lekéréséhez
    @GET
    @Path("/{id}/extra")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarExtraData(@PathParam("id") String id) {
        CarResponse extraData = this.carService.getCarData(id);
        if (extraData != null) {
            return Response.ok(extraData).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Hiba történt!").build();
    }

    // Új autó létrehozása (POST)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCar(CarEntity car) {
        CarEntity savedCar = this.carService.save(car);
        return Response.status(Response.Status.CREATED).entity(savedCar).build();
    }

    // Autó frissítése / update (PUT)
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCar(CarEntity car) {
        CarEntity savedCar = this.carService.save(car);
        return Response.ok(savedCar).build();
    }
}