package hu.pte.mik.prog4.potpotzh.api.controller;

import hu.pte.mik.prog4.potpotzh.entity.CompanyEntity;
import hu.pte.mik.prog4.potpotzh.service.CompanyService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/company")
public class CompanyController {

    private final CompanyService companyService = new CompanyService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCompanies() {
        List<CompanyEntity> companies = companyService.findAll();
        return Response.ok(companies).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompanyById(@PathParam("id") Long id) {
        CompanyEntity company = companyService.findById(id);
        if (company != null) {
            return Response.ok(company).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Cég nem található\"}").build();
    }

    @GET
    @Path("/{id}/sold-products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSoldProducts(@PathParam("id") Long id) {
        Long soldProducts = companyService.getSoldProductsFromWS(id);
        String jsonResponse = "{\"companyId\":" + id + ", \"soldProducts\":" + soldProducts + "}";
        return Response.ok(jsonResponse).build();
    }
}