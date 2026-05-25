package hu.pte.mik.prog4.potzh_2026.ws;

import hu.pte.mik.prog4.potzh_2026.entity.PetEntity;
import hu.pte.mik.prog4.potzh_2026.repository.PetRepository;

import jakarta.jws.WebMethod;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import java.util.List;

@WebService(serviceName = "PetDataService")
public class SoapPetDataService {

    private final PetRepository petRepository = new PetRepository();

    @WebMethod(operationName = "getAllPets")
    @WebResult(name = "PetListResponse")
    public List<PetEntity> getAllPets() {
        return petRepository.findAll();
    }
}