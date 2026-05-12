package hu.pte.mik.prog4.zh2.service;

import hu.pte.mik.prog4.zh2.entity.FoodEntity;
import hu.pte.mik.prog4.zh2.repository.FoodRepository;
import hu.pte.mik.prog4.zh2.ws.FoodDataRequest;
import hu.pte.mik.prog4.zh2.ws.FoodDataResponse;
import hu.pte.mik.prog4.zh2.ws.FoodDataService;
import hu.pte.mik.prog4.zh2.ws.FoodDataServiceService;
import org.apache.log4j.Logger; // Logger import

import java.util.List;

public class FoodService {

    private static final Logger LOGGER = Logger.getLogger(FoodService.class); // Logger inicializálás
    private final FoodRepository foodRepository;

    public FoodService() {
        this.foodRepository = new FoodRepository();
    }

    public List<FoodEntity> findAll() {
        return this.foodRepository.listAll();
    }

    public FoodEntity findById(Long id) {
        return this.foodRepository.findById(id);
    }

    public FoodEntity save(Long id, String restaurantName, String foodName, String price) {
        return this.foodRepository.save(new FoodEntity(id, restaurantName, foodName, price));
    }

    // 4. Feladat megoldása: SOAP kliens hívása
    public long getFoodPortion(String foodId) {
        try {
            FoodDataServiceService service = new FoodDataServiceService();
            FoodDataService port = service.getFoodDataServicePort();

            FoodDataRequest request = new FoodDataRequest();
            request.setFoodId(foodId);

            FoodDataResponse response = port.getFoodData(request);

            // A te null-biztos megoldásod!
            return response.getPortion() != null ? response.getPortion() : 0L;

        } catch (Exception e) {
            // System.err helyett profi logolás
            LOGGER.error("Hiba a SOAP webservice hívása közben a " + foodId + " ételhez: " + e.getMessage(), e);
            // És a te zseniális továbbdobásod a Controller felé!
            throw new RuntimeException("Webservice elérhetetlen", e);
        }
    }
}