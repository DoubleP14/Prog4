package hu.pte.mik.prog4.potzh.repository;

import hu.pte.mik.prog4.potzh.entity.UserEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Feltételezem, hogy a Repository osztályod adja a Connection-t
public class UserRepository extends Repository {

    public UserEntity findByUsername(String username) {
        // Ha nálatok nem users a tábla neve, írd át!
        String sql = "SELECT * FROM user WHERE username = ?";

        // Cseréld le a getConnection() hívást arra, ahogy a Repository-dban van!
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
        return null; // Ha nem találja, null-t ad vissza
    }
}