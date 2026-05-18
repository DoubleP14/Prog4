package hu.pte.mik.prog4.potpotzh.service;

import hu.pte.mik.prog4.potpotzh.entity.CompanyEntity;
import hu.pte.mik.prog4.potpotzh.repository.CompanyRepository;
import hu.pte.mik.prog4.potpotzh.ws.CompanyRequest;
import hu.pte.mik.prog4.potpotzh.ws.CompanyResponse;
import hu.pte.mik.prog4.potpotzh.ws.CompanyDataServiceService; // A képeden lévő Service osztály
import hu.pte.mik.prog4.potpotzh.ws.CompanyDataService; // A képeden lévő (I) Interfész

import java.util.List;

public class CompanyService {

    private final CompanyRepository companyRepository = new CompanyRepository();

    // Példányosítjuk a képeden lévő Service osztályt
    private final CompanyDataServiceService wsService = new CompanyDataServiceService();

    public List<CompanyEntity> findAll() {
        return companyRepository.findAll();
    }

    public CompanyEntity findById(Long id) {
        return companyRepository.findById(id);
    }

    public CompanyEntity save(CompanyEntity company) {
        return companyRepository.save(company);
    }

    public Long getSoldProductsFromWS(Long companyId) {
        try {
            // Elkérjük a portot az interfészen keresztül
            // (Ha a getCompanyDataServicePort() piros lenne, használd az IntelliJ kódkiegészítőjét,
            // hogy megnézd, mi a pontos metódusnév a wsService-en belül, pl. get...Port())
            CompanyDataService port = wsService.getCompanyDataServicePort();

            CompanyRequest request = new CompanyRequest();
            request.setCompanyId(String.valueOf(companyId));

            CompanyResponse response = port.getCompanyData(request);

            return response.getSoldProducts();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }
}