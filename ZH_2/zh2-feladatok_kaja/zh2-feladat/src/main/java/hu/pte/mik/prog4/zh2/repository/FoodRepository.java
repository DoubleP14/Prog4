package hu.pte.mik.prog4.zh2.repository;

import hu.pte.mik.prog4.zh2.entity.FoodEntity;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodRepository extends Repository {

    // 1. Logger hozzáadása a profi hibakezeléshez
    private final static Logger LOGGER = Logger.getLogger(FoodRepository.class);

    public FoodEntity save(FoodEntity food) {
        // A te logikád: visszakérjük a generált kulcsokat
        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO food (restaurant_name, food_name, price) VALUES (?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, food.getRestaurantName());
            stmt.setString(2, food.getFoodName());
            stmt.setString(3, food.getPrice());

            stmt.executeUpdate();

            // ID visszakérése és beállítása
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                food.setId(rs.getLong(1));
            }
            return food;
        } catch (SQLException | NamingException ex) {
            LOGGER.error("Hiba a Food mentésekor: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public FoodEntity update(FoodEntity food) {
        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE food SET food_name = ?, price = ?, restaurant_name = ? WHERE id = ?")) {

            stmt.setString(1, food.getFoodName());
            stmt.setString(2, food.getPrice());
            stmt.setString(3, food.getRestaurantName());
            stmt.setLong(4, food.getId());

            stmt.executeUpdate();
            return food;
        } catch (SQLException | NamingException ex) {
            LOGGER.error("Hiba a Food frissítésekor: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public FoodEntity findById(Long id) {
        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT id, restaurant_name, food_name, price FROM food WHERE id = ?")) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            // 2. A mapFood segédmetódus használata
            return rs.next() ? mapFood(rs) : null;
        } catch (SQLException | NamingException ex) {
            LOGGER.error("Hiba a Food lekérdezésekor (ID alapján): " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public List<FoodEntity> listAll() {
        // 3. Statement használata PreparedStatement helyett (nincs paraméter)
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT id, restaurant_name, food_name, price FROM food");
            List<FoodEntity> foods = new ArrayList<>();

            while (rs.next()) {
                // Itt is a mapFood-ot hívjuk
                foods.add(mapFood(rs));
            }
            return foods;
        } catch (SQLException | NamingException ex) {
            LOGGER.error("Hiba a Food lista lekérdezésekor: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    /**
     * 4. Segédmetódus a ResultSet sorának FoodEntity-vé alakításához.
     * Ez megszünteti a kódduplikációt és név szerint hivatkozik az oszlopokra.
     */
    private FoodEntity mapFood(ResultSet rs) throws SQLException {
        FoodEntity food = new FoodEntity();
        food.setId(rs.getLong("id"));
        food.setRestaurantName(rs.getString("restaurant_name"));
        food.setFoodName(rs.getString("food_name"));
        food.setPrice(rs.getString("price"));
        return food;
    }
}