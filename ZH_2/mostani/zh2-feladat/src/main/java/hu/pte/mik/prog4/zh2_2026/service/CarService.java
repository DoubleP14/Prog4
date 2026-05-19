package hu.pte.mik.prog4.zh2_2026.service;

import hu.pte.mik.prog4.zh2_2026.entity.CarEntity;
import hu.pte.mik.prog4.zh2_2026.repository.CarRepository;

import java.util.List;

public class CarService {

    private final CarRepository carRepository;

    public CarService() {
        this.carRepository = new CarRepository();
    }

    public List<CarEntity> getAll() {
        return this.carRepository.getAll();
    }

    public CarEntity getById(Long id) {
        return this.carRepository.getById(id);
    }

    public CarEntity save(CarEntity car) {
        if (car.getId() == null) {
            return this.carRepository.create(car);
        }

        return this.carRepository.update(car);
    }

    // A 4. feladat véglegesített, visszatérési értékkel rendelkező verziója
    public hu.pte.mik.prog4.zh2_2026.ws.service.CarResponse getCarData(String carId) {
        try {
            java.net.URL wsdlLocation = new java.net.URL("http://localhost:8081/zh2_ws_feladat_war_exploded/ws/car?wsdl");
            javax.xml.namespace.QName serviceName = new javax.xml.namespace.QName("http://service.ws.zh2_2026.prog4.mik.pte.hu/", "CarDataService");

            hu.pte.mik.prog4.zh2_2026.ws.service.CarDataService service = new hu.pte.mik.prog4.zh2_2026.ws.service.CarDataService(wsdlLocation, serviceName);
            hu.pte.mik.prog4.zh2_2026.ws.service.CarService port = service.getSoapCarServicePort();

            hu.pte.mik.prog4.zh2_2026.ws.service.CarRequest request = new hu.pte.mik.prog4.zh2_2026.ws.service.CarRequest();

            hu.pte.mik.prog4.zh2_2026.entity.CarEntity car = getById(Long.valueOf(carId));
            if (car != null) {
                request.setType(car.getType());
                request.setModel(car.getModel());
                request.setProductionYear(car.getProductionYear());

                // Visszaadjuk a SOAP szerver válaszát!
                return port.getCarData(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
