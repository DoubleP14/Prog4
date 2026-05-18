package hu.pte.mik.prog4.potpotzh.repository;

import hu.pte.mik.prog4.potpotzh.entity.UserEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository extends Repository {

    public UserEntity findByUsername(String username) {
        // Lekérdezés a magyar 'felhasznalo' táblából
        String sql = "SELECT * FROM felhasznalo WHERE felhasznalonev = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UserEntity user = new UserEntity();
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("felhasznalonev"));
                    user.setPassword(rs.getString("jelszo"));
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}