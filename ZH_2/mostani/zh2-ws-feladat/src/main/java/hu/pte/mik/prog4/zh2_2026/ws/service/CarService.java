package hu.pte.mik.prog4.zh2_2026.ws.service;

import hu.pte.mik.prog4.zh2_2026.ws.entity.CarRequest;
import hu.pte.mik.prog4.zh2_2026.ws.entity.CarResponse;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface CarService {

    @WebMethod
    CarResponse getCarData(@WebParam(name = "request") CarRequest request);

}