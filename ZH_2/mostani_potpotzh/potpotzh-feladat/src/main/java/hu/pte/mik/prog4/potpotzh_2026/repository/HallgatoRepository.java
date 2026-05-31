package hu.pte.mik.prog4.potpotzh_2026.repository;

import hu.pte.mik.prog4.potpotzh_2026.entity.Hallgato;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HallgatoRepository {
    private DataSource getDataSource() throws Exception {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        return (DataSource) envContext.lookup("jdbc/PotPotZH_university_MariaDB");
    }

    public List<Hallgato> findAllOrderByName() {
        List<Hallgato> list = new ArrayList<>();
        String query = "SELECT * FROM potpotzh_university_database.hallgato ORDER BY hallgato_nev ASC";
        try (Connection conn = getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToHallgato(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Hallgato findById(int id) {
        String query = "SELECT * FROM potpotzh_university_database.hallgato WHERE ID = ?";
        try (Connection conn = getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToHallgato(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Hallgato h) {
        String query = "INSERT INTO potpotzh_university_database.hallgato (hallgato_nev, szak, felev, egyetemi_atlag) VALUES (?, ?, ?, ?)";
        try (Connection conn = getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, h.getHallgatoNev());
            stmt.setString(2, h.getSzak());
            stmt.setInt(3, h.getFelev());
            stmt.setDouble(4, h.getEgyetemiAtlag());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String query = "DELETE FROM potpotzh_university_database.hallgato WHERE ID = ?";
        try (Connection conn = getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Hallgato mapResultSetToHallgato(ResultSet rs) throws SQLException {
        Hallgato h = new Hallgato();
        h.setId(rs.getInt("ID"));
        h.setHallgatoNev(rs.getString("hallgato_nev"));
        h.setSzak(rs.getString("szak"));
        h.setFelev(rs.getInt("felev"));
        h.setEgyetemiAtlag(rs.getDouble("egyetemi_atlag"));
        return h;
    }
}