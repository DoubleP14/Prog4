package hu.pte.mik.prog4.potpotzh.ws;

import jakarta.jws.WebService;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

// A megvalósító osztály. Itt kötjük össze az interfésszel az endpointInterface paraméterrel.
@WebService(serviceName = "SoapCompanyDataService", endpointInterface = "hu.pte.mik.prog4.potpotzh.ws.CompanyDataService")
public class SoapCompanyDataService implements CompanyDataService {

    // Az állapotot a megvalósító osztályban tároljuk
    private final Map<String, Long> map = new HashMap<>();

    @Override
    public CompanyResponse getCompanyData(CompanyRequest request) {
        Long soldProducts = this.map.computeIfAbsent(request.getCompanyId(),
                companyId -> ThreadLocalRandom.current().nextLong(100000000L, 10000000000L));

        return new CompanyResponse(request.getCompanyId(), soldProducts);
    }
}