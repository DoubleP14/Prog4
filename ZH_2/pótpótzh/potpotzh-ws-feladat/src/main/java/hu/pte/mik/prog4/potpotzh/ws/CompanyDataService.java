package hu.pte.mik.prog4.potpotzh.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

// Az interfész, ami definiálja a szolgáltatást
@WebService(name = "CompanyDataService") // A név itt CompanyDataService
public interface CompanyDataService {

    @WebMethod
    CompanyResponse getCompanyData(@WebParam(name = "request") CompanyRequest request);
}