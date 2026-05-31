package hu.pte.mik.prog4.potpotzh_2026.ws;

import hu.pte.mik.prog4.potpotzh_2026.entity.Hallgato;
import hu.pte.mik.prog4.potpotzh_2026.service.HallgatoService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService(serviceName = "HallgatoDataService")
public class HallgatoWebService {

    private final HallgatoService service = new HallgatoService();

    @WebMethod
    public List<Hallgato> getHallgatok() {
        return service.getAllHallgatok();
    }

    @WebMethod
    public Hallgato getHallgatoById(@WebParam(name = "id") int id) {
        return service.getHallgatoById(id);
    }

    @WebMethod
    public void createHallgato(@WebParam(name = "hallgato") Hallgato hallgato) {
        service.createHallgato(hallgato);
    }

    @WebMethod
    public void deleteHallgato(@WebParam(name = "id") int id) {
        service.deleteHallgato(id);
    }
}
