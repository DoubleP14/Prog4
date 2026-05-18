package hu.pte.mik.prog4.zh2_2026.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository extends Repository {

    // Lekérdezi a felhasználóhoz tartozó jogosultságokat (kódokat)
    public List<String> getRolesByUsername(String username) {
        List<String> roles = new ArrayList<>();
        String sql = "SELECT r.code FROM role r " +
                "JOIN user_role ur ON r.id = ur.role_id " +
                "JOIN user u ON u.id = ur.user_id " +
                "WHERE u.username = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    roles.add(rs.getString("code"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Hiba a szerepkörök lekérdezésekor", e);
        }
        return roles;
    }
}