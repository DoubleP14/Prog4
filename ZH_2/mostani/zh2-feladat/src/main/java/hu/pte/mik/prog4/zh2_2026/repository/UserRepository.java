package hu.pte.mik.prog4.zh2_2026.repository;

import hu.pte.mik.prog4.zh2_2026.entity.UserEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository extends Repository {

    public UserEntity findByUsername(String username) {
        // Feltételezem, hogy a táblád neve 'user' (ha magyar, írd át 'felhasznalo'-ra!)
        String sql = "SELECT * FROM user WHERE username = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UserEntity user = new UserEntity();
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}