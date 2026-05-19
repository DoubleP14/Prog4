package hu.pte.mik.prog4.zh2_2026.repository;

import hu.pte.mik.prog4.zh2_2026.entity.CarEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepository extends Repository {

    public CarEntity create(CarEntity car) {
        String sql = "INSERT INTO car (type, model, production_year, list_price) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, car.getType());
            ps.setString(2, car.getModel());
            ps.setString(3, car.getProductionYear());
            ps.setString(4, car.getListPrice());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    car.setId(rs.getLong(1));
                }
            }
            return car;
        } catch (SQLException e) {
            throw new RuntimeException("Hiba az autó létrehozásakor", e);
        }
    }

    public CarEntity update(CarEntity car) {
        String sql = "UPDATE car SET type=?, model=?, production_year=?, list_price=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, car.getType());
            ps.setString(2, car.getModel());
            ps.setString(3, car.getProductionYear());
            ps.setString(4, car.getListPrice());
            ps.setLong(5, car.getId());
            ps.executeUpdate();
            return car;
        } catch (SQLException e) {
            throw new RuntimeException("Hiba az autó frissítésekor", e);
        }
    }

    public CarEntity getById(Long id) {
        String sql = "SELECT * FROM car WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new CarEntity(
                            rs.getLong("id"),
                            rs.getString("type"),
                            rs.getString("model"),
                            rs.getString("production_year"),
                            rs.getString("list_price")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Hiba az autó lekérdezésekor", e);
        }
        return null;
    }

    public List<CarEntity> getAll() {
        List<CarEntity> cars = new ArrayList<>();
        String sql = "SELECT * FROM car";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                cars.add(new CarEntity(
                        rs.getLong("id"),
                        rs.getString("type"),
                        rs.getString("model"),
                        rs.getString("production_year"),
                        rs.getString("list_price")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Hiba az autók listázásakor", e);
        }
        return cars;
    }
}