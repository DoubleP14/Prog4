package hu.pte.mik.prog4.zh2_2026.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository extends Repository {

    // Visszaadja a felhasználó titkosított jelszavát a bejelentkezéshez
    public String getPasswordByUsername(String username) {
        String sql = "SELECT password FROM user WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("password");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Hiba a felhasználó keresésekor", e);
        }
        return null;
    }
}