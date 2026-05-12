package hu.pte.mik.prog4.zh2.service;

import hu.pte.mik.prog4.zh2.entity.CarEntity;
import hu.pte.mik.prog4.zh2.repository.CarRepository;

// FONTOS: A 3. feladatban generált SOAP osztályok importálása!
// (Ha az IntelliJ aláhúzná őket pirossal, használd az Alt+Enter-t a pontos név kiválasztásához)
import hu.pte.mik.prog4.generated.ws.CarDataRequest;
import hu.pte.mik.prog4.generated.ws.CarDataResponse;
import hu.pte.mik.prog4.generated.ws.CarDataService;

import java.util.List;

public class CarService {

    private final CarRepository carRepository;

    public CarService() {
        this.carRepository = new CarRepository();
    }

    public List<CarEntity> findAll() {
        return this.carRepository.listAll();
    }

    public CarEntity findById(Long id) {
        return this.carRepository.findById(id);
    }

    public CarEntity save(Long id, String manufacturer, String type, String licensePlate) {
        return this.carRepository.save(new CarEntity(id, manufacturer, type, licensePlate));
    }

    // 4. Feladat megoldása: SOAP kliens hívása a kilométer lekérdezésére
    public long getRemoteKm(String licensePlate) {
        try {
            // 1. Service példányosítása
            var service = new hu.pte.mik.prog4.generated.ws.CarService();

            // 2. Port lekérése
            var port = service.getSoapCarDataServicePort();

            // 3. A kérés összeállítása (Nincs többé szükség a GetRemoteKm wrapperre!)
            var request = new hu.pte.mik.prog4.generated.ws.CarDataRequest();
            request.setLicensePlate(licensePlate);

            // 4. Meghívjuk a porton a metódust (A Java a háttérben automatikusan becsomagolja)
            var response = port.getRemoteKm(request);

            // 5. Az eredmény lekérése
            return response.getKilometer() != null ? response.getKilometer() : 0L;

        } catch (Exception e) {
            System.err.println("Hiba a SOAP hívás során: " + e.getMessage());
            throw new RuntimeException("SOAP hiba", e);
        }
    }

}