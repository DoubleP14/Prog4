package hu.pte.mik.prog4.zh2.repository;

import hu.pte.mik.prog4.zh2.entity.CarEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarRepository extends Repository {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarRepository.class);

    private static final String FIND_ALL_QUERY = "SELECT id, manufacturer, type, license_plate FROM car";
    private static final String FIND_BY_ID_QUERY = "SELECT id, manufacturer, type, license_plate FROM car WHERE id = ?";
    private static final String SAVE_QUERY = "INSERT INTO car (manufacturer, type, license_plate) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE car SET manufacturer=?, type=?, license_plate=? WHERE id=?";

    public CarEntity save(CarEntity car) {
        try (var con = this.getConnection();
             var stmt = con.prepareStatement(SAVE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, car.getManufacturer());
            stmt.setString(2, car.getType());
            stmt.setString(3, car.getLicensePlate());

            // JAVÍTÁS: executeQuery() helyett executeUpdate() kell adatmódosításhoz!
            stmt.executeUpdate();

            var generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return this.findById(generatedKeys.getLong(1));
            }
            return car;
        } catch (SQLException e) {
            LOGGER.error("Data access error happened! " + e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (NamingException e) {
            LOGGER.error("Something happened! " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public CarEntity update(CarEntity car) {
        try (var con = this.getConnection();
             var stmt = con.prepareStatement(UPDATE_QUERY)) {
            stmt.setString(1, car.getManufacturer());
            stmt.setString(2, car.getType());
            stmt.setString(3, car.getLicensePlate());

            // JAVÍTÁS: Hiányzott a 4. paraméter (az ID) beállítása a WHERE feltételhez!
            stmt.setLong(4, car.getId());

            stmt.executeUpdate();

            return this.findById(car.getId());
        } catch (SQLException e) {
            LOGGER.error("Data access error happened! " + e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (NamingException e) {
            LOGGER.error("Something happened! " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public CarEntity findById(Long id) {
        try (var con = this.getConnection();
             var stmt = con.prepareStatement(FIND_BY_ID_QUERY)) {
            stmt.setLong(1, id);

            var resultSet = stmt.executeQuery();
            if (!resultSet.next()) {
                throw new RuntimeException("Car with id: " + id + " does not exist");
            }

            return mapToCar(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Data access error happened! " + e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (NamingException e) {
            LOGGER.error("Something happened! " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public List<CarEntity> listAll() {
        try (var con = this.getConnection(); Statement stmt = con.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(FIND_ALL_QUERY);

            List<CarEntity> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(mapToCar(resultSet));
            }

            return list;
        } catch (SQLException e) {
            LOGGER.error("Data access error happened! " + e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (NamingException e) {
            LOGGER.error("Something happened! " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private static CarEntity mapToCar(ResultSet resultSet) throws SQLException {
        var car = new CarEntity();
        car.setId(resultSet.getLong("id"));
        car.setManufacturer(resultSet.getString("manufacturer"));
        car.setType(resultSet.getString("type"));
        car.setLicensePlate(resultSet.getString("license_plate"));
        return car;
    }
}