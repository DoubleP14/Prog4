package hu.pte.mik.prog4.zh2_2026.ws.service;

import hu.pte.mik.prog4.zh2_2026.ws.entity.CarRequest;
import hu.pte.mik.prog4.zh2_2026.ws.entity.CarResponse;
import jakarta.jws.WebService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@WebService(endpointInterface = "hu.pte.mik.prog4.zh2_2026.ws.service.CarService", serviceName = "CarDataService")
public class SoapCarService implements CarService {

    private final Map<String, CarResponse> map = new HashMap<>();

    private final Random random = new Random();

    @Override
    public CarResponse getCarData(CarRequest request) {

        String key = createKey(request);

        if (!map.containsKey(key)) {
            map.put(key, generateResponse(request));
        }

        return map.get(key);
    }

    private String createKey(CarRequest request) {

        return request.getType()
                + "_"
                + request.getModel()
                + "_"
                + request.getProductionYear();
    }

    private CarResponse generateResponse(CarRequest request) {

        int productionYear = Integer.parseInt(request.getProductionYear());

        CarResponse response = new CarResponse();

        response.setSoldCount(generateSoldCount(productionYear));
        response.setEstimatedPrice(generateEstimatedPrice(productionYear));
        response.setFuelType(generateFuelType(productionYear));

        return response;
    }

    private long generateSoldCount(int productionYear) {

        int age = 2026 - productionYear;

        long base = 30000L / (age + 1);

        return base + random.nextInt(100000);
    }

    private long generateEstimatedPrice(int productionYear) {

        int age = 2026 - productionYear;

        long basePrice = 4_000_000L + random.nextInt(12_000_000);

        long depreciation = age * 400_000L;

        return Math.max(1_500_000L, basePrice - depreciation);
    }

    private String generateFuelType(int productionYear) {

        if (productionYear >= 2022) {

            int rnd = random.nextInt(100);

            if (rnd < 20) {
                return "electric";
            }

            if (rnd < 50) {
                return "hybrid";
            }
        }

        return random.nextBoolean()
                ? "petrol"
                : "diesel";
    }
}

