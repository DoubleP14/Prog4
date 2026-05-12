package hu.pte.mik.prog4.zh2.ws;

import hu.pte.mik.prog4.zh2.service.CarService;
import jakarta.jws.WebService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@WebService(endpointInterface = "hu.pte.mik.prog4.zh2.ws.CarDataService",
        serviceName = "carService",
        targetNamespace = "hu.pte.mik.prog4.zh2")
public class SoapCarDataService implements CarDataService {

    private final Map<String, Long> kmMap = new HashMap<>();

    @Override
    public CarDataResponse getData(CarDataRequest request) {
        return new CarDataResponse(request.getLicensePlate(), this.kmMap.computeIfAbsent(request.getLicensePlate(), licensePlate -> ThreadLocalRandom.current()
                                                                                                                                                     .nextLong(0, 1000000)));
    }

    @Override
    public CarDataResponse getRemoteKm(CarDataRequest request) {
        return new CarDataResponse(request.getLicensePlate(), this.kmMap.computeIfAbsent(request.getLicensePlate(), licensePlate -> ThreadLocalRandom.current()
                .nextLong(0, 1000000)));
    }

}
