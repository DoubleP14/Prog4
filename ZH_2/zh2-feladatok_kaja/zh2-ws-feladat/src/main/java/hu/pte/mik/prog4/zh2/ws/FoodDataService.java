package hu.pte.mik.prog4.zh2.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

// JAVÍTVA: Hozzáadtuk a targetNamespace-t, hogy megegyezzen az implementációval!
@WebService(targetNamespace = "hu.pte.mik.prog4.zh2.ws.soap")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface FoodDataService {
    @WebMethod
    FoodDataResponse getFoodData(FoodDataRequest request);
}