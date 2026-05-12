package hu.pte.mik.prog4.zh2.api.controller;

import hu.pte.mik.prog4.zh2.entity.CarEntity;
import hu.pte.mik.prog4.zh2.service.CarService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Path("/getcarkm")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

    private final CarService carService;

    // JAVÍTÁS: Szabályos üres konstruktor, ami példányosítja a Service-t
    public CarController() {
        this.carService = new CarService();
    }

    // JAVÍTÁS: POST kérés, hogy be tudja fogadni a JSON testet a klienstől
    @POST
    public Response getCarKm(CarEntity request) {
        LOGGER.info("Lekérdezés érkezett a következő rendszámra: " + request.getLicensePlate());

        try {
            // 1. SOAP webservice hívása a service rétegen keresztül
            long km = this.carService.getRemoteKm(request.getLicensePlate());

            // 2. JSON válasz összeállítása (egy Map-ből tökéletes {"km": 12345} formátumú JSON lesz)
            Map<String, Long> responseBody = new HashMap<>();
            responseBody.put("km", km);

            return Response.ok(responseBody).build();

        } catch (Exception e) {
            LOGGER.error("Hiba a kilométer lekérdezése közben", e);
            // 3. Hiba esetén is JSON-t adunk vissza, ahogy a feladat kéri
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Some error occurred!\"}")
                    .build();
        }
    }
}